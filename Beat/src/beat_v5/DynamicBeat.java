package beat_v5;

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

	//이미지 아이콘 객체 생성
	//프로그램 종료 버튼
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png")); //마우스 올라갔을 때 버튼
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")); //기본 종료버튼
	//화면 전환 메뉴 버튼
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png")); //시작하기 버튼 - 기본
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png")); //시작하기 버튼 - 이벤트
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png")); //종료하기 버튼 - 기본
	private ImageIcon quitButtoinEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png")); //종료하기 버튼 - 이벤트
	
	// introBackground 초기화 - 배경화면 지정
	private Image Background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	//메뉴바 종료버튼
	private JButton exitButton = new JButton(exitButtonBasicImage);
	//시작하기 버튼
	private JButton startButton = new JButton(startButtonBasicImage);
	//종료하기 버튼
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private int mouseX, mouseY;  //프로그램 안에서 마우스의 위치 값

	public DynamicBeat() {
		setUndecorated(true); //실행을 했을 때, 기본적으로 존재하는 메뉴바가 안보임. 
		setTitle("다이나믹 비트"); // 창 타이틀 설정
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 창 크기 설정
		setResizable(false); // 창 임의 변경 불가
		setLocationRelativeTo(null); // 창 실행 시 중앙에
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임 종료 시, 완전히 종료
		setVisible(true); // 만든 게임 창이 정상적으로 화면으로 출력되도록 설정
		setBackground(new Color(0, 0, 0, 0)); //paintCompoents를 진행했을 시, 전부 하얀색으로 배경 설정
		setLayout(null); //레이아웃 설정 시 그자리 그대로 설정

		//프로그램 종료버튼(exit) 그리기
		exitButton.setBounds(1245, 0, 30, 30); //버튼 위치 설정
		exitButton.setBorderPainted(false); //JButton은 따로 지정을 통하여 바꾸어줘야함.
		exitButton.setContentAreaFilled(false); 
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() { //마우스 리스너 & 어뎁터 연결
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 경우 이벤트
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //마우스가 해당 버튼 위에 올라가있지 않을 경우 이벤트
				exitButton.setIcon(exitButtonBasicImage); //아이콘 변경 (아이콘 세팅)
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스 올라갔을 때 마우스 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) { //마우스 버튼 클릭 이벤트
				Music buttonEntedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEntedMusic.start();
				//버튼을 클릭했을 때 바로 종료되기 때문에 try-catch 사용
				try {
					Thread.sleep(1000); //버튼 클릭 시 0.1초간 대기했다가 exit(0) 실행
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //프로그램 종료
			}
		});
		add(exitButton);
		
		//프로그램 시작버튼 그리기
		startButton.setBounds(40, 200, 400, 100); //버튼 위치 설정
		startButton.setBorderPainted(false); //JButton은 따로 지정을 통하여 바꾸어줘야함.
		startButton.setContentAreaFilled(false); 
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() { //마우스 리스너 & 어뎁터 연결
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 경우 이벤트
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //마우스가 해당 버튼 위에 올라가있지 않을 경우 이벤트
				startButton.setIcon(startButtonBasicImage); //아이콘 변경 (아이콘 세팅)
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스 올라갔을 때 마우스 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) { //마우스 버튼 클릭 이벤트
//				Music buttonEntedMusic = new Music("buttonPressedMusic.mp3",false);
//				buttonEntedMusic.start();
				// 게임 시작 이벤트
				startButton.setVisible(false); //시작 버튼 투명하게
				quitButton.setVisible(false); //종료 버튼 투명하게
				Background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); //이미지를 mainBackground로 변경 후, 그려주기
			}
		});
		add(startButton);
		
		//프로그램 종료버튼(quit) 그리기
		quitButton.setBounds(40, 330, 400, 100); //버튼 위치 설정
		quitButton.setBorderPainted(false); //JButton은 따로 지정을 통하여 바꾸어줘야함.
		quitButton.setContentAreaFilled(false); 
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() { //마우스 리스너 & 어뎁터 연결
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 경우 이벤트
				quitButton.setIcon(quitButtoinEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //마우스가 해당 버튼 위에 올라가있지 않을 경우 이벤트
				quitButton.setIcon(quitButtonBasicImage); //아이콘 변경 (아이콘 세팅)
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스 올라갔을 때 마우스 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) { //마우스 버튼 클릭 이벤트
				Music buttonEntedMusic = new Music("buttonPressedMusic.mp3",false);
				buttonEntedMusic.start();
				try {
					Thread.sleep(1000);
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		add(quitButton);

		//메뉴바 그리기
		menuBar.setBounds(0, 0, 1280, 30); //메뉴바 위치 지정
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {  //마우스로 버튼을 눌렀을 때 이벤트
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseAdapter() {
			@Override //드래그 했을 때, 매 순간 창의 위치를 바꿈 - 핸들러 설정
			public void mouseDragged(MouseEvent e) { //마우스로 드래그 이벤트
				int x = e.getXOnScreen(); 
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar); //메뉴바 지정
		
		

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
		g.drawImage(Background, 0, 0, null); // introBackground 0,0 위치에 그려라
		paintComponents(g); //paintComponets는 항상 고정되어있는 이미지를 말함.
		this.repaint(); // paint 메소드 불러오기
	}
}
