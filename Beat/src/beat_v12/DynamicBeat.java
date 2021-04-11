package beat_v12;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//리펙토링
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

	//곡 선택(오른쪽, 왼쪽) 버튼
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png")); 
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png")); 
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png")); 
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png")); 
	
	//이지&하드모드 버튼
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png")); 
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png")); 
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png")); 
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png")); 
	
	//뒤로가기 버튼
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png")); 
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png")); 
	
	// introBackground 초기화 - 배경화면 지정
	private Image Background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	//메뉴바 종료버튼
	private JButton exitButton = new JButton(exitButtonBasicImage);
	//시작하기 버튼
	private JButton startButton = new JButton(startButtonBasicImage);
	//종료하기 버튼
	private JButton quitButton = new JButton(quitButtonBasicImage);
	//곡 선택(왼쪽) 버튼
	private JButton leftButton = new JButton(leftButtonBasicImage);
	//곡 선택(오른쪽) 버튼
	private JButton rightButton = new JButton(rightButtonBasicImage);
	//이지 모드 버튼
	private JButton easyButton = new JButton(easyButtonBasicImage);
	//하드 모드 버튼
	private JButton hardButton = new JButton(hardButtonBasicImage);
	//뒤로가기 버튼
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private int mouseX, mouseY;  //프로그램 안에서 마우스의 위치 값
	
	private boolean isMainScreen = false; //메인화면 불린 - 기본값은 false , 메인화면으로 이동했으면 true
	private boolean isGameScreen = false; //게임화면 불린
	
	ArrayList<Track> trackList = new ArrayList<Track>(); //제레릭 사용 - 트랙을 사용해서 각 곡의 정보를 담음.
	
	//곡 타이틀 이미지
	private Image titleImage;
	//선택된 곡 선택
	private Image selectedImage;
	private Music selectedMusic; //코드의 메소드화
	// 시작화면에서 음악 무한재생
	private Music introMusic = new Music("IntroMusic.mp3", true);
	private int nowSelected = 0;
	
	//Game 클래스 선언
	public static Game game = new Game();

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
		
		addKeyListener(new beat_v11.KeyListener());
		
		introMusic.start();
		
		//곡 정보(경로) 삽입 - (타이틀 이미지 / 곡 시작 이미지 / 곡 인게임 이미지 / 곡 하이라이트 음악 / 곡 실행 음악)
		//1번 음악 - 토토로
		trackList.add(new Track("Totoro Title Image.png", "Totoro Start Image.png", "Totoro Game Image.png", "totoro selected.mp3", "totoro.mp3"));
		//2번 음악 - 레전드 네버 다이
		trackList.add(new Track("Legends Title Image.png", "Legends Start Image.png", "Legends Game Image.png", "LND selected.mp3", "LND.mp3"));	
		//3번 음악 - 인생의 회전목마
		trackList.add(new Track("Howls Title Image.png", "Howls Start Image.png", "Howls Game Image.png", "mgrol selected.mp3", "mgrol.mp3"));
		//4번 음악 - 우주
		trackList.add(new Track("Space Title Image.png", "Space Start Image.png", "Space Game Image.png", "space selected.mp3", "space.mp3"));
		
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
				Music buttonEntedMusic = new Music("exitbuttonPressedMusic.mp3",false);
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
				// 게임 시작 이벤트
				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEntedMusic.start();
				enterMain(); //메소드 실행
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
				Music buttonEntedMusic = new Music("exitbuttonPressedMusic.mp3",false);
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
		
		//곡 선택(왼쪽) 버튼
		leftButton.setVisible(false); //보이지 않게(false)
		leftButton.setBounds(140, 310, 60, 60); //버튼 위치 설정
		leftButton.setBorderPainted(false); //JButton은 따로 지정을 통하여 바꾸어줘야함.
		leftButton.setContentAreaFilled(false); 
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() { //마우스 리스너 & 어뎁터 연결
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 경우 이벤트
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //마우스가 해당 버튼 위에 올라가있지 않을 경우 이벤트
				leftButton.setIcon(leftButtonBasicImage); //아이콘 변경 (아이콘 세팅)
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스 올라갔을 때 마우스 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) { //마우스 버튼 클릭 이벤트
				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEntedMusic.start();
				//왼쪽 버튼 이벤트
				selectedLeft(); //메소드 호출
			}
		});
		add(leftButton);
		
		//곡 선택(오른쪽) 버튼
		rightButton.setVisible(false); //보이지 않게(false)
		rightButton.setBounds(1080, 310, 60, 60); //버튼 위치 설정
		rightButton.setBorderPainted(false); //JButton은 따로 지정을 통하여 바꾸어줘야함.
		rightButton.setContentAreaFilled(false); 
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() { //마우스 리스너 & 어뎁터 연결
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 경우 이벤트
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //마우스가 해당 버튼 위에 올라가있지 않을 경우 이벤트
				rightButton.setIcon(rightButtonBasicImage); //아이콘 변경 (아이콘 세팅)
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스 올라갔을 때 마우스 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) { //마우스 버튼 클릭 이벤트
				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEntedMusic.start();
				//오른쪽 버튼 이벤트
				selectedRight(); //메소드 호출
			}
		});
		add(rightButton);
		
		//이지 모드 버튼
		easyButton.setVisible(false); //보이지 않게(false)
		easyButton.setBounds(375,500,250,67); //버튼 위치 설정
		easyButton.setBorderPainted(false); //JButton은 따로 지정을 통하여 바꾸어줘야함.
		easyButton.setContentAreaFilled(false); 
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() { //마우스 리스너 & 어뎁터 연결
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 경우 이벤트
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //마우스가 해당 버튼 위에 올라가있지 않을 경우 이벤트
				easyButton.setIcon(easyButtonBasicImage); //아이콘 변경 (아이콘 세팅)
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스 올라갔을 때 마우스 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) { //마우스 버튼 클릭 이벤트
				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEntedMusic.start();
				//난이도 이지모드 이벤트
				gameStart(nowSelected,"easy");
			}
		});
		add(easyButton);
		
		//하드 모드 버튼
		hardButton.setVisible(false); //보이지 않게(false)
		hardButton.setBounds(655, 500, 250, 67); //버튼 위치 설정
		hardButton.setBorderPainted(false); //JButton은 따로 지정을 통하여 바꾸어줘야함.
		hardButton.setContentAreaFilled(false); 
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() { //마우스 리스너 & 어뎁터 연결
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 경우 이벤트
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //마우스가 해당 버튼 위에 올라가있지 않을 경우 이벤트
				hardButton.setIcon(hardButtonBasicImage); //아이콘 변경 (아이콘 세팅)
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스 올라갔을 때 마우스 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) { //마우스 버튼 클릭 이벤트
				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEntedMusic.start();
				//하드모드 시작 이벤트
				gameStart(nowSelected,"hard");
			}
		});
		add(hardButton);

		//뒤로 가기
		backButton.setVisible(false); //보이지 않게(false)
		backButton.setBounds(20, 50, 60, 60); //버튼 위치 설정
		backButton.setBorderPainted(false); //JButton은 따로 지정을 통하여 바꾸어줘야함.
		backButton.setContentAreaFilled(false); 
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() { //마우스 리스너 & 어뎁터 연결
			@Override
			public void mouseEntered(MouseEvent e) {//마우스가 해당 버튼 위에 올라왔을 경우 이벤트
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //마우스가 해당 버튼 위에 올라가있지 않을 경우 이벤트
				backButton.setIcon(backButtonBasicImage); //아이콘 변경 (아이콘 세팅)
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //마우스 올라갔을 때 마우스 커서 변경
			}

			@Override
			public void mousePressed(MouseEvent e) { //마우스 버튼 클릭 이벤트
				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEntedMusic.start();
				//메인화면으로 돌아가는 이벤트
				backMain();
			}
		});
		add(backButton);

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
	}

	public void paint(Graphics g) { // 그림을 그려주는 함수
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 이미지를 만들어준 뒤, screenImage에 저장
		screenGraphic = screenImage.getGraphics(); // 그래픽 객체 가져오기
		screenDraw((Graphics2D)screenGraphic); //Graphics2D로 형변환 시키기
		g.drawImage(screenImage, 0, 0, null); // 게임 창에 스크린 이미지 출력 (0,0 위치에 그려라)
	}

	// 화면에 그려주기
	public void screenDraw(Graphics2D g) {
		//drawImage의 경우 add가 아닌, 이미지를 그냥 화면에 출력시킬 경우 이용
		//주로 역동적인 이미지나 애니메이션의 경우 drawImage에 사용
		g.drawImage(Background, 0, 0, null); // introBackground 0,0 위치에 그려라
		if(isMainScreen) { //isMainScreen이 true일 경우, 즉, 인트로 화면이 아닌 메인화면으로 넘어갔을 경우
			g.drawImage(selectedImage,340,100,null); //selectedImage를 그려줘라
		    g.drawImage(titleImage,340,70,null); //타이틀 이미지
		}
		if(isGameScreen) {
			game.ScreenDraw(g);
		}
		paintComponents(g); //paintComponets는 항상 고정되어있는 이미지를 말함. (add로 불러오는 이미지들)
		this.repaint(); // paint 메소드 불러오기
	}
	
	//어떤 곡이 선택되었는가? (현재 선택된 곡의 포지션)
	public void selectedTrack(int nowSelected) {
		if(selectedMusic != null) selectedMusic.close(); //어떠한 곡이 실행되고 있다면 바로 종료
			//현재 선택된 곡이 가지고 있는 타이틀 이미지 값을 가져와서 titleImage에 삽입.
		titleImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getTitleImage())).getImage(); //곡에 대한 타이틀 변경
		selectedImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getStartImage())).getImage(); //곡에 대한 이미지를 변경
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true); //선택된 음악 파일 가져오기
		selectedMusic.start(); //selectedMusic 스타트
	}
	
	//왼쪽 버튼 클릭 이벤트
	public void selectedLeft() {
		if(nowSelected == 0) { //0번째 곡일 경우
			nowSelected = trackList.size() - 1; //0번째 곡일 때 왼쪽을 누를 경우 제일 마지막 곡으로 이동
		}else {
			nowSelected--; //아닐 경우 왼쪽으로 한 칸 이동
		}
		selectedTrack(nowSelected);
	}
	
	//오른쪽 버튼 클릭 이벤트
	public void selectedRight() {
		if(nowSelected == trackList.size()-1) { //트랙에서 제일 마지막 곡일 경우
			nowSelected = 0; //제일 첫 번째 곡으로 이동
		}else {
			nowSelected++; //아닐 경우 오른쪽 한 칸 이동
		}
		selectedTrack(nowSelected);
	}
	
	//게임 시작
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null) 
				selectedMusic.close();
			isMainScreen = false; //메인 스크린이 아니게 설정
			leftButton.setVisible(false);
			rightButton.setVisible(false);
			easyButton.setVisible(false);
			hardButton.setVisible(false);
			Background = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getGameImage())).getImage();
			backButton.setVisible(true);
			isGameScreen = true;
			setFocusable(true); //메인 화면에 메인 포커싱 설정
 	}
	
	//뒤로가기 버튼 이벤트
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true); //왼쪽 버튼 보이게
		rightButton.setVisible(true); //오른쪽 버튼 보이게
		easyButton.setVisible(true); 
		hardButton.setVisible(true); 
		Background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();
		backButton.setVisible(false); //뒤로가기 투명하게
		selectedTrack(nowSelected); //다시 트랙 재생
		isGameScreen = false;
	}
	
	//시작하기 버튼 이벤트
	public void enterMain() {
		startButton.setVisible(false); //시작 버튼 투명하지 않게
		quitButton.setVisible(false); //종료 버튼 투명하지 않게
		Background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); //이미지를 mainBackground로 변경 후, 그려주기
		isMainScreen = true; //메인화면 감지
		leftButton.setVisible(true); //왼쪽 버튼 투명하게
		rightButton.setVisible(true); //오른쪽 버튼 투명하게
		easyButton.setVisible(true); 
		hardButton.setVisible(true); 
		introMusic.close();
		selectedTrack(0); //제일 첫 번째 곡 실행
	}
}
