package beat_v1;

import javax.swing.JFrame;

public class DynamicBeat extends JFrame{
	
	//생성자 - 가장 먼저 실행되는 메소드
	public DynamicBeat() {
		setTitle("다이나믹 비트"); // 창 타이틀 설정
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); //창 크기 설정
		setResizable(false); // 창 임의 변경 불가
		setLocationRelativeTo(null); //창 실행 시 중앙에
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //게임 종료 시, 완전히 종료
		setVisible(true); //만든 게임 창이 정상적으로 화면으로 출력되도록 설정
	}
}
