package beat_v11;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//사용자 키 감지 리스너
public class KeyListener extends KeyAdapter {
	
	//입력한 키에 대한 이벤트 처리
	@Override
	public void keyPressed(KeyEvent e) {
		if(DynamicBeat.game == null) { //현재 게임이 진행되고 있지 않는 상태, 즉 게임이라는 변수가 null이라면 키보드 무력화
			return; 
		}
		if(e.getKeyCode() == KeyEvent.VK_S) { // 해당 키보드를 눌렀다면
			DynamicBeat.game.pressS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.pressD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.pressF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.pressSpace();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.pressJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.pressK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.pressL();
		}
	}
	
	@Override // 키보드를 눌렀다가 땠을 때
	public void  keyReleased(KeyEvent e) {
		if(DynamicBeat.game == null) { //현재 게임이 진행되고 있지 않는 상태, 즉 게임이라는 변수가 null이라면 키보드 무력화
			return; 
		}
		if(e.getKeyCode() == KeyEvent.VK_S) { // 해당 키보드를 눌렀다면
			DynamicBeat.game.releaseS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.releaseD();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F) {
			DynamicBeat.game.releaseF();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.releaseSpace();
		}
		else if(e.getKeyCode() == KeyEvent.VK_J) {
			DynamicBeat.game.releaseJ();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.releaseK();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.releaseL();
		}
	}
}
