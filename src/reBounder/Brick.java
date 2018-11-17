package reBounder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.util.Random;

//import com.baangine.alpha.Game.STATE;

public class Brick extends GameObject{
	
//	private Handler handler;
	private Bricktype type;
	private BufferedImage brickSprite = null;
	private Textures textures;
	private boolean isHit = false;
	private Animation brickNotHit, brickHit;
	private long timeOfHit;

	
//	private Random r = new Random();

	public Brick(int x, int y, ID id, Bricktype type) {
		super(x, y, id);
//		this.handler = handler;
		this.type = type;
		this.textures = Game.getTextures();
		this.brickSprite = textures.brick[0];
		this.brickHit = new Animation(2, textures.brick[0], textures.brick[1], textures.brick[2], textures.brick[3], textures.brick[4], textures.brick[5], textures.brick[6], textures.brick[7], textures.brick[8], textures.brick[9]);
		this.brickNotHit = new Animation(5, textures.brick[0], textures.brick[0]);
	}

	@Override
	public void tick() {
		
		
		x = Game.clamp(x, 0, Game.WIDTH - 16);
		y = Game.clamp(y, 0, Game.HEIGHT- 16);
		if(isHit) {
			brickHit.runAnimation();
		}else {
			brickNotHit.runAnimation();
		}
//		collision();
		
	}

	@Override
	public void render(Graphics g) {
		
		if(isHit) {
			brickHit.drawAnimation(g, x, y, 0);
			Handler handler = Game.getHandler();
			if(System.currentTimeMillis() - timeOfHit > 200 ) {
//				handler.removeObject(this);
//				alive = true;
//				ball = (Ball)handler.getBall();
//				ball.reset();
//				resetXY();
			}
//			handler.removeObject(this);
		}else {
		if(this.type == Bricktype.Normal) {
			g.drawImage(brickSprite, x, y, null);
//			g.setColor(Color.red);
//			g.fillRect(x, y, 72, 48);
//			g.setColor(Color.blue);
//			g.fillRect((x+1), (y+1), 70, 46);
		}else if(this.type == Bricktype.Tough) {
			g.drawImage(brickSprite, x, y, null);
//			g.setColor(Color.red);
//			g.fillRect(x, y, 72, 48);
//			g.setColor(Color.green);
//			g.fillRect((x+1), (y+1), 70, 46);
		}else if(this.type == Bricktype.Metal) {
			g.drawImage(brickSprite, x, y, null);
//			g.setColor(Color.red);
//			g.fillRect(x, y, 72, 48);
//			g.setColor(Color.gray);
//			g.fillRect((x+1), (y+1), 70, 46);
//			g.setColor(Color.white);
//			g.fillRect((x+2), (y+2), 14, 14);
//			g.setColor(Color.gray);
//			g.fillRect((x+4), (y+4), 12, 12);
		}
		}
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y + 16, 64, 32);
	}
	
	public void setHit(boolean hit) {
		this.isHit = hit;
		timeOfHit = System.currentTimeMillis();
	}
	
	public boolean getHit() {
		return this.isHit;
	}
	public Bricktype getType() {
		return this.type;
	}
	
	
	

}

