package reBounder;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage sheet) {
		this.image = sheet;
	
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * 32) - 32, (row*32) - 32, width, height);
		return img;
	}
	
	public BufferedImage grabImage64(int col, int row, int width, int height) {
		BufferedImage img = image.getSubimage((col * 64) - 64, (row*64) - 64, width, height);
		return img;
	}

}

