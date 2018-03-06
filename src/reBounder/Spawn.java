package reBounder;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
//	private HUD hud;
	private Random r = new Random();
	private int monsterCount;
	private int playerCount;
	private Textures textures;
	
	public Spawn(Handler handler, HUD hud, Textures textures) {
		this.handler = handler;
		this.textures = textures;
//		this.hud = hud;
	}
	
	public void tick() {
		
		//timer code and spawn logic here
//		try {
//		Thread.sleep(1000);
//		}catch(Exception e) {
//			System.out.println(e);
//		}

		//single monster spawn logic
		playerCount = 0;
		monsterCount = 0;
		for(int i=0; i < handler.objects.size(); i++) {
			GameObject tempObject = handler.objects.get(i);
			if (tempObject.getId() == ID.Ball) {
				monsterCount ++;
				}
			if (tempObject.getId() == ID.Player) {
				playerCount ++;
				}
			}
		
		if(monsterCount == 0){
//			hud.setMonsterCount(0);
			handler.addObject(new Ball (r.nextInt(Game.WIDTH) - 16, r.nextInt(Game.HEIGHT) - 16, ID.Ball, handler, textures));
//			hud.setMonsterCount(hud.getMonsterCount() + 1);
			monsterCount ++;
		}
		if(playerCount == 0){
//			hud.setMonsterCount(0);
			handler.addObject(new Player(Game.WIDTH/2 - 32, Game.HEIGHT/2 - 32, ID.Player, handler, textures));
//			hud.setMonsterCount(hud.getMonsterCount() + 1);
			playerCount ++;
		}
		//end single monster spawn logic
		
		}
	//end of tick
		
		
		//chicken and egg spawn logic
//			if(tempObject.getId() == ID.Chicken) {
//				Chicken tempChicken = (Chicken)tempObject;
//				chickenCount ++;
//				hud.setChickenCount(chickenCount);
//				if(tempChicken.getLaid() == false) {
//					if(tempChicken.getLife() <= 0) {
//						System.out.println("Chicken Died!");
//						handler.removeObject(tempChicken);
//					}else if(tempChicken.getLife() > 0 && tempChicken.getLife() < 200) {
//						int roll = r.nextInt(100);
//						if (roll >= 50) {
//							tempChicken.setLaid(true);
//							handler.addObject(new Egg(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Egg, handler, 400));
//							handler.addObject(new Egg(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Egg, handler, 400));
//							System.out.println("Chicken Laid Egg!");
//						}
//						
//					}
//				}else if (tempChicken.getLaid()) {
//					if(tempChicken.getLife() <= 0 && tempChicken.getLife() <= 100) {
//						int roll = r.nextInt(100);
//						if (roll < tempChicken.getLife()) {
//							System.out.println("Chicken Died!");
//							handler.removeObject(tempChicken);
//						}else if(roll >= tempChicken.getLife()) {
//							System.out.println("Chicken Survived!");
//						}
//						
//					}
//				}
//				
//			}
//		}			
//		if (eggCount == 0) {
//			handler.addObject(new Egg(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Egg, handler, 400));
//			hud.setEggCount(eggCount);
//			System.out.println("life finds a way...");
//		}//end of chicken and egg spawn logic
	
}

