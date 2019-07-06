package reBounder;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int health = 100;
	private int greenValue = 0;
	private int score = 0;
	private int level;
	private int lives;
	private int bricks;
	private Player player;
	private Handler handler;
	
	public HUD(Handler handler) {
		this.handler = handler;
		this.player = (Player)handler.getPlayer();
	}
	

	public void tick() {

		health = Game.clamp(health, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = health *2;
		score = player.getScore();
//		health = Game.clamp(health, 0, 100);
		health = player.getHealth();
		lives = player.getLives();
		level = Game.getLevel();
		bricks = handler.getBricks();
	}
	
	public void setScore(int score) {
		this.score = player.getScore();
	}
	
	public int getScore() {
		return score;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setBrickCount(int bricks) {
		this.bricks = bricks;
	}
	
	public int getBrickCount() {
		return bricks;
	}
	
	public void render(Graphics g) {
		
//		g.setColor(new Color(75, greenValue, 0));
//		g.fillRect(15, 15, health * 2, 32);
		g.setColor(Color.blue);
//		g.drawRect(15, 15, 200, 32);
		g.drawString("Score: " + score, 10, 80);
		g.drawString("Level: " + level, 10, 100);
		g.drawString("Bricks: " + bricks, 10, 120);
		g.drawString("Lives: " + lives, 10, 140);
		g.drawString("Left: " + player.getLeft(),10, 160);
		g.drawString("Right: " + player.getRight(),10, 180);
		g.drawString("Stationary: " + player.getNotMoving(),10, 200);
	}


}

