package dynamic_beat;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {  //System.out.println("");//키 입력 인식
		if(DynamicBeat.game == null) {//現在ゲームが進行しない時ならNoteの認識ができない
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) { // sKeyを押す時、ｓが認識させるコード
			DynamicBeat.game.pressS();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) { // ｄKeyを押す時、ｄが認識させるコード
			DynamicBeat.game.pressD();
		}
		if (e.getKeyCode() == KeyEvent.VK_F) { 
			DynamicBeat.game.pressF();
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { 
			DynamicBeat.game.pressSpace();
		}
		if (e.getKeyCode() == KeyEvent.VK_J) { 
			DynamicBeat.game.pressJ();
		}
		if (e.getKeyCode() == KeyEvent.VK_K) { 
			DynamicBeat.game.pressK();
		}
		if (e.getKeyCode() == KeyEvent.VK_L) { 
			DynamicBeat.game.pressL();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) { 
		if (e.getKeyCode() == KeyEvent.VK_S) { // sKeyを押す時、ｓが認識させるコード
			DynamicBeat.game.releaseS();
		}
		if (e.getKeyCode() == KeyEvent.VK_D) { 
			DynamicBeat.game.releaseD();
		}
		if (e.getKeyCode() == KeyEvent.VK_F) { 
			DynamicBeat.game.releaseF();
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) { 
			DynamicBeat.game.releaseSpace();
		}
		if (e.getKeyCode() == KeyEvent.VK_J) { 
			DynamicBeat.game.releaseJ();
		}
		if (e.getKeyCode() == KeyEvent.VK_K) { 
			DynamicBeat.game.releaseK();
		}
		if (e.getKeyCode() == KeyEvent.VK_L) { 
			DynamicBeat.game.releaseL();
		}
	}

}