package reBounder;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Handler {
	
	LinkedList<GameObject> objects = new LinkedList<GameObject>();
	private Random r = new Random();
	private SoundPlayer soundPlayer = new SoundPlayer();
	
	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.tick();
		}
		
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			tempObject.render(g);
		}
	}
		
	public void addObject(GameObject object) {
		this.objects.add(object);
		
	}
	
	public void removeObject(GameObject object) {
		this.objects.remove(object);
		
	}
	
	public GameObject getPlayer() {
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getId() == ID.Player) {
				return objects.get(i);
			}
		}
		return null;
	}
	
	public GameObject getBall() {
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getId() == ID.Ball) {
				return objects.get(i);
			}
		}
		return null;
	}
	
	public void setupStars() {
		for(int i = 0; i < 75; i++) {
			Star star = new Star(r.nextInt(1280), r.nextInt(1024), ID.Star, r.nextInt(3 + 1) + 1);
			this.addObject(star);
		}
	}
	
	public void clearBricks() {
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getId() == ID.Brick) {
				objects.remove(i);
			}	
		}
	}
	
	public int getBricks() {
		int bricks = 0;
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getId() == ID.Brick) {
				bricks++;
			}
		}
		return bricks;
	}
	
	public void playSound(String path) {
		this.soundPlayer = new SoundPlayer();
		soundPlayer.playSound(path);
	}
	
	public void reset() {
		for(int i = 0; i < objects.size(); i++) {
			objects.remove(i);
		}
	}
	
	
	
}
