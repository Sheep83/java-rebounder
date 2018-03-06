package reBounder;

import java.util.ArrayList;

public class Level {
	
	private ArrayList<Integer> arr = new ArrayList<Integer>();
	
	public Level() {
//		super(x, y, id);
//		this.handler = handler;
//		this.velX = (r.nextInt(4) + 1);
//		this.velY = -4;
	}
	
	public void addBrick(int amount, int type) {
		for(int i = 0; i < amount; i++) {
			this.arr.add(type);
			}
		
	}
	
	public void clearLevel() {
		for(int i = 0; i < arr.size(); i++) {
			arr.remove(i);
			}
	}
	
	public void build(Handler handler, int level) {
		
		int brickX = 64;
		int brickY = 48;
		this.arr = new ArrayList<Integer>();
		
		if (level == 1) {
			addBrick(16, 3);
		}else if (level == 2) {
			addBrick(16, 2);
			addBrick(16, 1);
			addBrick(16, 2);
			addBrick(16, 1);
		}else if (level == 3) {
			addBrick(16, 2);
			addBrick(16, 3);
			addBrick(16, 2);
			addBrick(16, 1);		
		}else if (level == 99) {
			addBrick(16, 2);
			addBrick(16, 3);
			addBrick(16, 2);
			addBrick(16, 1);		
		}
		
		for(int i = 0; i < arr.size(); i++) {
				if(arr.get(i) == 1) {
				handler.addObject(new Brick(brickX, brickY, ID.Brick, Bricktype.Normal));
				brickX += 64;
				if((i + 1) % 16 == 0) {
				brickX = 64;
				brickY += 32;
				}
			}else if(arr.get(i) == 2) {
				handler.addObject(new Brick(brickX, brickY, ID.Brick, Bricktype.Tough));
				brickX += 64;
				if((i + 1) % 16 == 0) {
				brickX = 64;
				brickY += 32;
				}
			}else if(arr.get(i) == 3) {
				handler.addObject(new Brick(brickX, brickY, ID.Brick, Bricktype.Metal));
				brickX += 64;
				if((i + 1) % 16 == 0) {
				brickX = 64;
				brickY += 32;
				}
			}else if(arr.get(i) == 0) {
//				handler.addObject(new Brick(brickX, brickY, ID.Brick, Bricktype.Metal, handler));
				brickX += 72;
				if((i + 1) % 16 == 0) {
				brickX = 64;
				brickY += 48;
				}
			}
		
	}
	
	}
	

}

