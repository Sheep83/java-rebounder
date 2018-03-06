package reBounder;

import java.awt.image.BufferedImage;

public class Textures {
	
	public BufferedImage[] ball = new BufferedImage[4];
	public BufferedImage[] player = new BufferedImage[10];
	public BufferedImage[] brick = new BufferedImage[10];
	
	private SpriteSheet ballSprites, playerSprites, deathSprites, brickSprites;
	
	public Textures(Game game) {
		ballSprites = new SpriteSheet(Game.getBallSpriteSheet());
		playerSprites = new SpriteSheet(Game.getPlayerSpriteSheet());
		deathSprites = new SpriteSheet(Game.getDeathSpriteSheet());
		brickSprites = new SpriteSheet(Game.getBrickSpriteSheet());
		getTextures();
	}
	
	public void getTextures() {
		ball[0] = ballSprites.grabImage(1, 1, 32, 32);
		ball[1] = ballSprites.grabImage(2, 1, 32, 32);
		ball[2] = ballSprites.grabImage(1, 2, 32, 32);
		ball[3] = ballSprites.grabImage(2, 2, 32, 32);
		//////////
		player[0] = playerSprites.grabImage(1, 1, 32, 32);
		player[1] = playerSprites.grabImage(2, 1, 32, 32);
		player[2] = playerSprites.grabImage(1, 2, 32, 32);
		player[3] = playerSprites.grabImage(2, 2, 32, 32);
		player[4] = playerSprites.grabImage(1, 3, 32, 32);
		player[5] = playerSprites.grabImage(2, 3, 32, 32);
		//////////
		player[6] = deathSprites.grabImage(1, 1, 32, 32);
		player[7] = deathSprites.grabImage(2, 1, 32, 32);
		player[8] = deathSprites.grabImage(1, 2, 32, 32);
		player[9] = deathSprites.grabImage(2, 2, 32, 32);
		//////////
		brick[0] = brickSprites.grabImage64(1, 1, 64, 64);
		brick[1] = brickSprites.grabImage64(2, 1, 64, 64);
		brick[2] = brickSprites.grabImage64(3, 1, 64, 64);
		brick[3] = brickSprites.grabImage64(1, 2, 64, 64);
		brick[4] = brickSprites.grabImage64(2, 2, 64, 64);
		brick[5] = brickSprites.grabImage64(3, 2, 64, 64);
		brick[6] = brickSprites.grabImage64(1, 3, 64, 64);
		brick[7] = brickSprites.grabImage64(2, 3, 64, 64);
		brick[8] = brickSprites.grabImage64(3, 3, 64, 64);
		brick[9] = brickSprites.grabImage64(1, 4, 64, 64);
		
		
	}

}
