package reBounder;

//import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	static int score;
	static int health;
	static int Playerlives;
	Textures textures;
	private BufferedImage leftSprite, rightSprite;
	private Animation moveLeft, moveRight, playerDead;
	private Ball ball;
	private boolean alive;
	private long timeOfDeath;
	private SoundPlayer soundPlayer;

	public Player(int x, int y, ID id, Handler handler, Textures textures) {
		super(x, y, id);
		this.handler = handler;
		this.textures = textures;
		leftSprite = textures.player[0];
		rightSprite = textures.player[1];
		playerDead = new Animation(10, textures.player[6], textures.player[7],textures.player[8], textures.player[9]);
		moveLeft = new Animation(5, textures.player[2], textures.player[3]);
		moveRight = new Animation(5, textures.player[4], textures.player[5]);
		health = 100;
		score = 0;
		Playerlives = 3;
		alive = true;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x -24, y + 8, 48, 16);
	}
	
	@SuppressWarnings("static-access")
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getVelX() {
		return velX;
	}
	
	public void reset() {
		Playerlives = 3;
		score = 0;
	}
	
	public void resetXY() {
		x = (Game.WIDTH / 2);
		y = Game.HEIGHT - 32;
	}
	
private void oopsCheck() {
		if(this.getY() < handler.getBall().getY()) {
			ball = (Ball)handler.getBall();
		setLives(getLives()-1);
		alive = false;
		soundPlayer = new SoundPlayer();
		soundPlayer.playSound("dead.wav");
		timeOfDeath = System.currentTimeMillis();
		ball.reset();
		
		ball.setVelX(0);
		ball.setVelY(0);
		}
	}

	public void tick() {
		oopsCheck();
		if(alive) {
			x += velX;
			y += velY;
			x = Game.clamp(x, 0 + 24, Game.WIDTH - 30);
			y = Game.clamp(y, Game.HEIGHT - 72, Game.HEIGHT - 72);
		if(velX < 1) {
			moveLeft.runAnimation();
			}else if(velX > 1){
				moveRight.runAnimation();
			}
		}else {
//			long deadTime = System.currentTimeMillis();
			playerDead.runAnimation();
			if(System.currentTimeMillis() - timeOfDeath > 1000 ) {
				alive = true;
				ball = (Ball)handler.getBall();
				ball.reset();
				resetXY();
			}
		}

//		collision();
	}

	
	public void render(Graphics g) {

//		Graphics2D g2d = (Graphics2D) g;
		if(alive) {
			if(velX < 0) {
				moveLeft.drawAnimation(g, x, y, 0);
				g.drawImage(leftSprite, x-32, y, null);
//				g2d.setColor(Color.red);
//				g2d.fillRect(x+72, y+4, 8, 8);
				}else if(velX > 0) {
					moveRight.drawAnimation(g, x-32, y, 0);
					g.drawImage(rightSprite, x, y, null);
//				g2d.setColor(Color.red);
//				g2d.fillRect(x-16, y+4, 8, 8);
				}else {
					g.drawImage(leftSprite, x-32, y, null);
					g.drawImage(rightSprite, x, y, null);
				}
		}else {
			playerDead.drawAnimation(g, x, y, 0);
		}
//		g2d.setColor(Color.white);
//		g2d.fillRect(x, y, 64, 16);
//		g2d.setColor(Color.gray);
//		g2d.fillRect(x+1, y+1, 62, 14);
	}

	public int getHealth() {
		return health;
	}
	
	public int getLives() {
		return Playerlives;
	}
	
	public void setLives(int lives) {
		Playerlives = lives;
	}
	
	public void setX(int newX) {
		x = newX;
	}
		
}
