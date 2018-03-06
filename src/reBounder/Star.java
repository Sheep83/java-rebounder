package reBounder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Star extends GameObject{

//	private Handler handler;
	private Random r = new Random();

	public Star(int x, int y, ID id, int velY) {
		super(x, y, id);
		this.velY = velY;
//		this.handler = handler;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		y += velY;
		if (y >= Game.HEIGHT - 16) {
			y = 0;
			x = r.nextInt(1280);
			velY = r.nextInt(3 + 1) + 1;
		}
		x = Game.clamp(x, 0, Game.WIDTH - 16);
		y = Game.clamp(y, 0, Game.HEIGHT- 16);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 2, 2);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x, y, 16, 16);
	}

}

