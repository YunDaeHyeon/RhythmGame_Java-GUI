package beat_v4;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//생성자 - 가장 먼저 실행되는 메소드
public class DynamicBeat extends JFrame {

	private Image screenImage; // 더블버퍼링을 활용하기 위한 전체화면 인스턴스
	private Graphics screenGraphic; // 동일

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));

	// introBackground 초기화 - 배경화면 지정
	private Image introBackground = new ImageIcon(Main.class.getResource("../images/IntroBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private int mouseX, mouseY;

	public DynamicBeat() {
		setUndecorated(true);
		setTitle("다이나믹 비트"); // 창 타이틀 설정
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 창 크기 설정
		setResizable(false); // 창 임의 변경 불가
		setLocationRelativeTo(null); // 창 실행 시 중앙에
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 종료 시, 완전히 종료
		setVisible(true); // 만든 게임 창이 정상적으로 화면으로 출력되도록 설정
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
//				buttonEntedMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
//				Music buttonEntedMusic = new Music("buttonPressedMusic.mp3",false);
//				buttonEntedMusic.start();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException ex) {
//					ex.printStackTrace();
//				}
				System.exit(0);
			}
		});
		add(exitButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

		// 시작화면에서 음악 무한재생
		Music introMusic = new Music("IntroMusic.mp3", true);
		introMusic.start(); // 시작
	}

	public void paint(Graphics g) { // 그림을 그려주는 함수
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 이미지를 만들어준 뒤, screenImage에 저장
		screenGraphic = screenImage.getGraphics(); // 그래픽 객체 가져오기
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // 게임 창에 스크린 이미지 출력 (0,0 위치에 그려라)
	}

	// 화면에 그려주기
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground, 0, 0, null); // introBackground 0,0 위치에 그려라
		paintComponents(g);
		this.repaint(); // paint 메소드 불러오기
	}
}
