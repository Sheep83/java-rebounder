package reBounder;

import java.awt.Graphics;
import java.awt.Rectangle;
//import java.util.Random;
import java.awt.image.BufferedImage;

//import com.baangine.alpha.Game.STATE;

public class Ball extends GameObject{
	
	private Handler handler;
//	private BufferedImage ballSprite = null;
	private Animation animation_clockwise, animation_counter;
	private Textures textures;
	private SoundPlayer soundPlayer;
//	private Random r = new Random();

	public Ball(int x, int y, ID id, Handler handler, Textures textures) {
		super(x, y, id);
		this.handler = handler;
//		this.velX = (r.nextInt(4) + 1);
		this.velX = 0;
		this.velY = 6;
		this.textures = textures;
//		SpriteSheet spriteSheet = new SpriteSheet(Game.getSpriteSheet());
//		ballSprite = spriteSheet.grabImage(1, 1, 32, 32);
		animation_clockwise = new Animation(3, textures.ball[0], textures.ball[1], textures.ball[2], textures.ball[3]);
		animation_counter = new Animation(3, textures.ball[3], textures.ball[2], textures.ball[1], textures.ball[0]);
		
	}

	@Override
	public void tick() {
		collision();
		x += velX;
		y += velY;
		if (x <= 0 || x >= Game.WIDTH - 16) {
			rebound(0, 0, true, false);
		}
		if (y <= 0 || y >= Game.HEIGHT - 16) {
			rebound(0, 0, false, true);
		}
		x = Game.clamp(x, 0, Game.WIDTH - 16);
		y = Game.clamp(y, 0, Game.HEIGHT- 16);
//		oopsCheck();
//		collision();
		if(velX < 1) {
		animation_counter.runAnimation();
		}else {
			animation_clockwise.runAnimation();
		}
	}

	@Override
	public void render(Graphics g) {
		if(velX < 1) {
		animation_counter.drawAnimation(g, x, y, 0);
		}else {
			animation_clockwise.drawAnimation(g, x, y, 0);
		}
//		g.drawImage(ballSprite, x, y, null);
//		g.setColor(Color.red);
//		g.fillRect(x, y, 8, 8);
//		g.drawRect(x + 12, y + 12, 8, 8);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x + 12, y + 12, 8, 8);
	}
	
	private void rebound(int deltaVX, int deltaVY, boolean xBounce, boolean yBounce) {
		
		if(velX < 0) {
			velX -= deltaVX;
		}else {
			velX += deltaVX;
		}
		if(velY < 0) {
			velY -= deltaVY;
		}else {
			velY += deltaVY;
		}
		
		if(yBounce == true) {
			velY *= -1;
		}
		
		if(xBounce == true) {
			velX *= -1;
		}
		
		
	}
	
	public void reset() {
		x = 640;
		y = 256;
		velX = 0;
		velY = 6;
	}
	
	public BufferedImage getTexture(int index) {
		return textures.ball[index];
	}
	
	private void collision() {
		for (int i = 0; i < handler.objects.size(); i++) {
			if (handler.objects.get(i).getId() == ID.Brick) {
				Brick brick = (Brick)handler.objects.get(i);
				if (getBounds().intersects(brick.getBounds())){
					//collision with brick
					brick.setHit(true);
					int ballX = this.getX();
					int ballX1 = (this.getX() - 8);
					int ballX2 = (this.getX() + 8);
					int ballcenterX = (this.getX() + 4);
					int ballcenterY = (this.getY() + 4);
					int ballY = this.getY();
					int ballY1 = (this.getY());
					int ballY2 = (this.getY() + 8);
					int brickX = brick.getX();
					int brickX1 = (brick.getX() + 64);
					int brickY = brick.getY();
					int brickY1 = (brick.getY() + 16);
					int brickY2 = (brick.getY() + 48);
//					int brickcenterX = (brick.getX() + 36);
//					int brickcenterY = (brick.getY() + 24);
//					boolean left, right, top, bottom = false;
					System.out.println("Ball coordinates (x1, x2, y1, y2) : (" + ballX + ", " + ballX2 + ", " + ballY + ", " + ballY2 + ")");
					System.out.println("Brick coordinates (x1, x2, y1, y2) : (" + brickX + ", " + brickX1 + ", " + brickY + ", " + brickY1 + ")");
					//collision zones
					Rectangle brickLeft = new Rectangle(brickX, brickY + 18, 2, 28 );
					Rectangle brickRight = new Rectangle((brickX1 - 2), brickY + 2, 2, 28);
					Rectangle brickTop = new Rectangle(brickX, brickY, 64, 2);
					Rectangle brickBottom = new Rectangle(brickX, brickY2 - 2, 64, 2);
					// corner sectors
					Rectangle topLeft = new Rectangle(brickX, brickY, 2, 2);
					Rectangle topRight = new Rectangle((brickY1 + 70), brickY, 2, 2);
					Rectangle bottomLeft = new Rectangle(brickX, (brickY1 - 2), 2, 2);
					Rectangle bottomRight = new Rectangle((brickX + 70), (brickY2 - 2), 2, 2);
					
					if (getBounds().intersects(brickLeft.getBounds())){
						//collision left
						System.out.print("Left");
						handler.removeObject(brick);
						brickLeft = null;
						brickRight = null;
						brickTop = null;
						brickBottom = null;
						topLeft = null;
						topRight = null;
						bottomLeft = null;
						bottomRight = null;
						rebound(0, 0, true, false);
					}else
					if (getBounds().intersects(brickRight.getBounds())){
						//collision right
						System.out.print("Right");
						handler.removeObject(brick);
						brickLeft = null;
						brickRight = null;
						brickTop = null;
						brickBottom = null;
						topLeft = null;
						topRight = null;
						bottomLeft = null;
						bottomRight = null;
						rebound(0, 0, true, false);
					}else
					if (getBounds().intersects(brickTop.getBounds())){
						//collision top
						System.out.print("Top");
						handler.removeObject(brick);
						brickLeft = null;
						brickRight = null;
						brickTop = null;
						brickBottom = null;
						topLeft = null;
						topRight = null;
						bottomLeft = null;
						bottomRight = null;
						rebound(0, 0, false, true);
					}else
					if (getBounds().intersects(brickBottom.getBounds())){
						//collision bottom
						System.out.print("Bottom");
						handler.removeObject(brick);
						brickLeft = null;
						brickRight = null;
						brickTop = null;
						brickBottom = null;
						topLeft = null;
						topRight = null;
						bottomLeft = null;
						bottomRight = null;
						rebound(0, 0, false, true);
					}else
						if (getBounds().intersects(topLeft.getBounds())){
							//collision left
							System.out.print("Left");
							handler.removeObject(brick);
							brickLeft = null;
							brickRight = null;
							brickTop = null;
							brickBottom = null;
							topLeft = null;
							topRight = null;
							bottomLeft = null;
							bottomRight = null;
							rebound(0, 0, true, true);
//							rebound(0, 0, false, true);
						}else
						if (getBounds().intersects(topRight.getBounds())){
							//collision right
							System.out.print("Right");
							handler.removeObject(brick);
							brickLeft = null;
							brickRight = null;
							brickTop = null;
							brickBottom = null;
							topLeft = null;
							topRight = null;
							bottomLeft = null;
							bottomRight = null;
							rebound(0, 0, true, true);
//							rebound(0, 0, false, true);
						}else
						if (getBounds().intersects(bottomLeft.getBounds())){
							//collision top
							System.out.print("Top");
							handler.removeObject(brick);
							brickLeft = null;
							brickRight = null;
							brickTop = null;
							brickBottom = null;
							topLeft = null;
							topRight = null;
							bottomLeft = null;
							bottomRight = null;
							rebound(0, 0, true, true);
//							rebound(0, 0, false, true);
						}else
						if (getBounds().intersects(bottomRight.getBounds())){
							//collision bottom
							System.out.print("Bottom");
							handler.removeObject(brick);
							brickLeft = null;
							brickRight = null;
							brickTop = null;
							brickBottom = null;
							topLeft = null;
							topRight = null;
							bottomLeft = null;
							bottomRight = null;
							rebound(0, 0, true, true);
//							rebound(0, 0, false, true);
						}
					
					
					///////////////////////////////
//					if ((ballX2 <=  brickX || ballX1 >= brickX1) && (ballY2 >=  brickY || ballY1 <= brickY1)) {
//					if ((ballY1 >= brickY) && (ballY <= brickY1)) {
//					if (ballY2 >  brickY && ballY1 < brickY1) {
//					if (ballcenterX < brickX || ballcenterX > brickX1) {
//						rebound(0, 0, true, false);
//					}else if(ballcenterY > brickY1 || ballcenterY < brickY ){
//						rebound(0, 0, false, true);
//					}else {
//						rebound(0,0,false, true);
//					}
					///////////////////////
//					if((ballcenterY > brickcenterY && ballcenterY < brickcenterY) && (ballcenterX < brickcenterX || ballcenterX > brickcenterX)) {
//						rebound(0, 0, true, false);
//					}else if(ballcenterX > brickX && ballcenterX < brickX1){
//						rebound(0,0,false, true);
//					}else {
//						rebound(0,0,false, true);
//					}
					///////////////////////////
//					float w = (float) (0.5 * (8 + 72));
//					float h = (float) (0.5 * (8 + 48));
//					float dx = ballcenterX - brickcenterX;
//					float dy = ballcenterY - brickcenterY;

//					if ( dx <= w && dy <= h)
//					{
//						System.out.println("collision");
//					    /* collision! */
//					    float wy = w * dy;
//					    float hx = h * dx;
//
//					    if (wy > hx)
//					        if (wy > -hx)
//					            /* collision at the top */
//					        	rebound(0, 0, false, true);
//					        else
//					            /* on the left */
//					        	rebound(0, 0, true, false);
//					    else
//					        if (wy > -hx)
//					            /* on the right */
//					        	rebound(0, 0, true, false);
//					        else
//					            /* at the bottom */
//					        	rebound(0, 0, false, true);
//					}
					/////////////////////////////////////
					
//					if(ballcenterY > (brickcenterY + 24))
//						  //Hit was from below the brick
////						this.setY(brickY + 48);
//						rebound(0, 0, false, true);
//						if(ballcenterY < (brickcenterY -24))
////							this.setY(brickY - 8);
//						  //Hit was from above the brick
//							rebound(0, 0, false, true);
//						if(ballcenterX < brickX)
////							this.setX(brickX - 8);
//						  //Hit was on left
//							rebound(0, 0, true, false);
//						if(ballcenterX > brickX)
////							this.setX(brickX1);
//						  //Hit was on right
//							rebound(0, 0, true, false);
					/////////////////////////////////////////////
					
					for (int j = 0; j < handler.objects.size(); j++) {
						if (handler.objects.get(j).getId() == ID.Player) {
							Player player = (Player) handler.objects.get(j);
//							Brick brick = (Brick) tempObject;
							if(brick.getType() == Bricktype.Normal) {
								player.setScore(player.getScore() + 25);
								soundPlayer = new SoundPlayer();
								soundPlayer.playSound("brick_hit.wav");
							}else if(brick.getType() == Bricktype.Tough) {
								player.setScore(player.getScore() + 50);
								soundPlayer = new SoundPlayer();
								soundPlayer.playSound("brick_hit.wav");
							}else if(brick.getType() == Bricktype.Metal) {
								player.setScore(player.getScore() + 100);
								soundPlayer = new SoundPlayer();
								soundPlayer.playSound("metalBrick_hit.wav");
							}
							}
						}
					
//					new Sound("brick_hit.wav");
//					handler.playSound("brick-hit.wav");
//					handler.objects.remove(brick);
					
					}
				
				}else if (handler.objects.get(i).getId() == ID.Player) {
				GameObject tempObject = handler.objects.get(i);
				if (getBounds().intersects(tempObject.getBounds())){
					//collision with player
					int playerVelX = tempObject.getVelX();
					int ballVelX = getVelX();
					int deltaV = 0;
					
					if((ballVelX * playerVelX) > 0) {
						deltaV += 1;
					
					}
					else if((ballVelX * playerVelX) < 0) {
						deltaV -= 1;
					}
					else if(ballVelX == 0) {
						if((this.getX() < (tempObject.getX() + 32))){
							deltaV -= 1;
						}else if((this.getX() >= (tempObject.getX() + 32))) {
							deltaV += 1;
						}
					}
					soundPlayer = new SoundPlayer();
					soundPlayer.playSound("boing.wav");
//					new Sound("boing.wav");
//					handler.playSound("boing.wav");
					rebound(deltaV, 0, false, true);
					
					
					//////////////////////////////////////////////
//					HUD.HEALTH -= 2;
//					handler.objects.remove(tempObject);
//					handler.objects.remove(this);
//					setScore(getScore() +100);
//					System.out.println("Score: " + score);
//					handler.addObject(new Monster (r.nextInt(Game.WIDTH - 16), r.nextInt(Game.HEIGHT - 16), ID.Monster, handler));
				}
			}
		}
	}

}

