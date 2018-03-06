package reBounder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;

import reBounder.Game.STATE;
// public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 50, 800, 100, 50);
//public class MouseInput implements MouseListener{


public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	
	
	public MouseInput(Handler handler) {
		this.handler = handler;

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int mouseX = e.getX();
		int mouseY = e.getY();
		if(Game.gameState == STATE.Menu) {
		if (mouseX >= Game.WIDTH / 2 - 50 && mouseX <= Game.WIDTH/2 + 50) {
			if(mouseY >= 800 && mouseY <= 850) {
				Game.setState(STATE.Game);
			}
		}
		}else if(Game.gameState == STATE.Game) {
			
			
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		// TODO Auto-generated method stub
		
	}
	
	

}

