package beat_v3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

//생성자 - 가장 먼저 실행되는 메소드
public class DynamicBeat extends JFrame{
	
	private Image  screenImage; //더블버퍼링을 활용하기 위한 전체화면 인스턴스
	private Graphics screenGraphic; //동일
	
	private Image introBackground;
   
	public DynamicBeat() {
		setTitle("다이나믹 비트"); // 창 타이틀 설정
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); //창 크기 설정
		setResizable(false); // 창 임의 변경 불가
		setLocationRelativeTo(null); //창 실행 시 중앙에
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //게임 종료 시, 완전히 종료
		setVisible(true); //만든 게임 창이 정상적으로 화면으로 출력되도록 설정
		
		//introBackground 초기화 - 배경화면 지정
		introBackground = new ImageIcon(Main.class.getResource("../images/IntroBackground.jpg")).getImage();
		
		//시작화면에서 음악 무한재생
		Music introMusic = new Music("IntroMusic.mp3", true);
		introMusic.start(); //시작
	}
	
	public void paint(Graphics g) { //그림을 그려주는 함수
		screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); // 이미지를 만들어준 뒤, screenImage에 저장
		screenGraphic = screenImage.getGraphics(); // 그래픽 객체 가져오기
		screenDraw(screenGraphic);  
		g.drawImage(screenImage, 0, 0, null); //게임 창에 스크린 이미지 출력 (0,0 위치에 그려라)
	}
	
	//화면에 그려주기
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground,0,0,null); //introBackground 0,0 위치에 그려라
		this.repaint(); //paint 메소드 불러오기
	}
}
