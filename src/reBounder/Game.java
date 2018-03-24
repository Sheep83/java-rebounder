package reBounder;

import java.awt.Canvas;
import java.awt.Color;
//import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
//import java.io.File;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;

//import com.baangine.alpha.Game.STATE;


//import net.java.games.input.Controller;
//import java.util.Random;
//import net.java.games.input.ControllerEnvironment;




public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 7436840021209078721L;
//	public static final int WIDTH = 1280, HEIGHT = (WIDTH / 12) * 9;
	public static final int WIDTH = 1280, HEIGHT = 1024;
	private Thread thread;
//	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private static BufferedImage ballSpriteSheet, playerSpriteSheet, deathSpriteSheet, brickSpriteSheet = null;
	private BufferedImage background = null;
	private boolean running = false;
//	private Random r = new Random();
	private static Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Level level;
	private static Textures textures;
	private SoundPlayer soundPlayer;
	private static int levelCount;
//	private Combat combat;
	public static enum STATE {
		Menu,
		Game
	};
	public static STATE gameState;
	private Menu menu;
	
	public Game() {
		gameState = STATE.Menu;
		new Window(WIDTH, HEIGHT, "reBounder", this);
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
		soundPlayer = new SoundPlayer();
		menu = new Menu(this, handler, soundPlayer);
		loadGfx();
		spawner = new Spawn(handler, hud, textures);
//		initGame();
		handler.addObject(new Player(WIDTH/2 - 32, HEIGHT - 32, ID.Player, handler, textures));
		handler.addObject(new Ball(WIDTH/2 - 128, 640, ID.Ball, handler, textures));
		handler.setupStars();
		hud = new HUD(handler);
		level = new Level();
		level.build(handler, 1);
		levelCount = 1;
//		menu.tick();
	}
	
	private void loadGfx() {
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			ballSpriteSheet = loader.loadImage("/ball_sprites.png");
			playerSpriteSheet = loader.loadImage("/player_sprites.png");
			deathSpriteSheet = loader.loadImage("/explosion.png");
			background = loader.loadImage("/my_background.jpg");
			brickSpriteSheet = loader.loadImage("/brick_sprites.png");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textures = new Textures(this);
		
	}
	
	public static BufferedImage getBallSpriteSheet() {
		return ballSpriteSheet;
	}
	
	public static BufferedImage getPlayerSpriteSheet() {
		return playerSpriteSheet;
	}
	
	public static BufferedImage getDeathSpriteSheet() {
		return deathSpriteSheet;
	}
	
	public static BufferedImage getBrickSpriteSheet() {
		return brickSpriteSheet;
	}
	
	private void initGame() {
		gameState = STATE.Menu;
		setLevel(1);
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
//		handler.reset();
		handler.addObject(new Player(WIDTH/2 - 32, HEIGHT - 32, ID.Player, handler, textures));
		handler.addObject(new Ball(WIDTH/2 - 128, HEIGHT/2 - 32, ID.Ball, handler, textures));
		hud = new HUD(handler);
		level = new Level();
		level.build(handler, levelCount);
		handler.setupStars();
//		new Sound("title.mid");
		menu.tick();
	}
	
	private void gameOverCheck() {
		Player player = (Player) handler.getPlayer();
		if(player.getLives() <= 0) {
			gameState = STATE.Menu;
			initGame();
		}
	}
	
	private void brickCheck() {
		int brickCount = 0;
		for (int i = 0; i < handler.objects.size(); i++) {
			if (handler.objects.get(i).getId() == ID.Brick) {
				brickCount ++;
				}
			}
		if(brickCount == 0) {
			
			level = new Level();
			levelCount ++;
			level.build(handler, levelCount);
		}
	}
	
	public static int getLevel() {
		return levelCount;
	}
	
	public static void setLevel(int level) {
		levelCount = level;
	}
	
	public static void setState(STATE state) {
		gameState = state;
	}
	
	public static Textures getTextures() {
		return textures;
	}
	
	public static Handler getHandler() {
		return handler;
	}
	
	public synchronized void start() {
	
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
	
		try {
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run()
    {
		this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while(running){
        	long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1){
            	tick();
            	updates ++;
                delta--;
                }
            
//            if(running)
            	render();
                frames++;
                            
                if(System.currentTimeMillis() - timer > 1000){
                	timer += 1000;
                	System.out.println("FPS: "+ frames + "   Ticks: " + updates);
                	updates = 0;
                	frames = 0;
                 }
        }
        stop();	
	}
	
	private void tick() {
		
		if(gameState == STATE.Menu) {
//			menu = new Menu(this, handler);
			menu.tick();
		}else if(gameState == STATE.Game) {
			soundPlayer.stopSound();
			soundPlayer.currentClip = null;
			gameOverCheck();
			brickCheck();
			spawner.tick();
			hud.tick();	
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		if(gameState == STATE.Game) {
			g.drawImage(background, 0, 0, this);
			handler.render(g);
			hud.render(g);	
		}else if (gameState == STATE.Menu) {
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if (var >= max) {
			return var = max;
		}else if (var <= min) {
			return var = min;
		}
		else {
			return var;
		}
	}
	

	public static void main(String[] args) {
		new Game();
	}
	
//	private void pause(int ms) {
//	try        
//	{
//	    Thread.sleep(ms);
//	} 
//	catch(InterruptedException ex) 
//	{
//	    Thread.currentThread().interrupt();
//	}
//}
	

}

