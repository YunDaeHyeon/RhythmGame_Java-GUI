package beat_v7;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//������ - ���� ���� ����Ǵ� �޼ҵ�
public class DynamicBeat extends JFrame {

	private Image screenImage; // �������۸��� Ȱ���ϱ� ���� ��üȭ�� �ν��Ͻ�
	private Graphics screenGraphic; // ����

	//�̹��� ������ ��ü ����
	//���α׷� ���� ��ư
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png")); //���콺 �ö��� �� ��ư
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png")); //�⺻ �����ư
	
	//ȭ�� ��ȯ �޴� ��ư
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png")); //�����ϱ� ��ư - �⺻
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png")); //�����ϱ� ��ư - �̺�Ʈ
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png")); //�����ϱ� ��ư - �⺻
	private ImageIcon quitButtoinEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png")); //�����ϱ� ��ư - �̺�Ʈ

	//�� ����(������, ����) ��ư
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png")); 
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png")); 
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png")); 
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png")); 
	
	// introBackground �ʱ�ȭ - ���ȭ�� ����
	private Image Background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	//�޴��� �����ư
	private JButton exitButton = new JButton(exitButtonBasicImage);
	//�����ϱ� ��ư
	private JButton startButton = new JButton(startButtonBasicImage);
	//�����ϱ� ��ư
	private JButton quitButton = new JButton(quitButtonBasicImage);
	//�� ����(����) ��ư
	private JButton leftButton = new JButton(leftButtonBasicImage);
	//�� ����(������) ��ư
	private JButton rightButton = new JButton(rightButtonBasicImage);
	
	private int mouseX, mouseY;  //���α׷� �ȿ��� ���콺�� ��ġ ��
	
	private boolean isMainScreen = false; //����ȭ�� �Ҹ� - �⺻���� false , ����ȭ������ �̵������� true
	
	ArrayList<Track> trackList = new ArrayList<Track>(); //������ ��� - Ʈ���� ����ؼ� �� ���� ������ ����.
	
	//�� Ÿ��Ʋ �̹���
	private Image titleImage;
	//���õ� �� ����
	private Image selectedImage;
	private Music selectedMusic; //�ڵ��� �޼ҵ�ȭ
	private int nowSelected = 0;

	public DynamicBeat() {
		setUndecorated(true); //������ ���� ��, �⺻������ �����ϴ� �޴��ٰ� �Ⱥ���. 
		setTitle("���̳��� ��Ʈ"); // â Ÿ��Ʋ ����
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // â ũ�� ����
		setResizable(false); // â ���� ���� �Ұ�
		setLocationRelativeTo(null); // â ���� �� �߾ӿ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� ���� ��, ������ ����
		setVisible(true); // ���� ���� â�� ���������� ȭ������ ��µǵ��� ����
		setBackground(new Color(0, 0, 0, 0)); //paintCompoents�� �������� ��, ���� �Ͼ������ ��� ����
		setLayout(null); //���̾ƿ� ���� �� ���ڸ� �״�� ����
		
		// ����ȭ�鿡�� ���� �������
		Music introMusic = new Music("IntroMusic.mp3", true);
		introMusic.start(); // ����
		
		//�� ����(���) ���� - (Ÿ��Ʋ �̹��� / �� ���� �̹��� / �� �ΰ��� �̹��� / �� ���̶���Ʈ ���� / �� ���� ����)
		//1�� ���� - �����
		trackList.add(new Track("Totoro Title Image.png", "Totoro Start Image.png", "Totoro Game Image.png", "totoro selected.mp3", "totoro.mp3"));
		//2�� ���� - ������ �׹� ����
		trackList.add(new Track("Legends Title Image.png", "Legends Start Image.png", "Legends Game Image.png", "LND selected.mp3", "LND.mp3"));	
		//3�� ���� - �λ��� ȸ����
		trackList.add(new Track("Howls Title Image.png", "Howls Start Image.png", "Howls Game Image.png", "mgrol selected.mp3", "mgrol.mp3"));
		//4�� ���� - ����
		trackList.add(new Track("Space Title Image.png", "Space Start Image.png", "Space Game Image.png", "space selected.mp3", "space.mp3"));
		
		//���α׷� �����ư(exit) �׸���
		exitButton.setBounds(1245, 0, 30, 30); //��ư ��ġ ����
		exitButton.setBorderPainted(false); //JButton�� ���� ������ ���Ͽ� �ٲپ������.
		exitButton.setContentAreaFilled(false); 
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() { //���콺 ������ & ��� ����
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� ��� �̺�Ʈ
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //���콺�� �ش� ��ư ���� �ö����� ���� ��� �̺�Ʈ
				exitButton.setIcon(exitButtonBasicImage); //������ ���� (������ ����)
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺 �ö��� �� ���콺 Ŀ�� ����
			}

			@Override
			public void mousePressed(MouseEvent e) { //���콺 ��ư Ŭ�� �̺�Ʈ
				Music buttonEntedMusic = new Music("exitbuttonPressedMusic.mp3",false);
				buttonEntedMusic.start();
				//��ư�� Ŭ������ �� �ٷ� ����Ǳ� ������ try-catch ���
				try {
					Thread.sleep(1000); //��ư Ŭ�� �� 0.1�ʰ� ����ߴٰ� exit(0) ����
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); //���α׷� ����
			}
		});
		add(exitButton);
		
		//���α׷� ���۹�ư �׸���
		startButton.setBounds(40, 200, 400, 100); //��ư ��ġ ����
		startButton.setBorderPainted(false); //JButton�� ���� ������ ���Ͽ� �ٲپ������.
		startButton.setContentAreaFilled(false); 
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() { //���콺 ������ & ��� ����
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� ��� �̺�Ʈ
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //���콺�� �ش� ��ư ���� �ö����� ���� ��� �̺�Ʈ
				startButton.setIcon(startButtonBasicImage); //������ ���� (������ ����)
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺 �ö��� �� ���콺 Ŀ�� ����
			}

			@Override
			public void mousePressed(MouseEvent e) { //���콺 ��ư Ŭ�� �̺�Ʈ
				// ���� ���� �̺�Ʈ
				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEntedMusic.start();
				introMusic.close(); //��Ʈ�� ���� ����
				selectedTrack(0); //���� ù ��° �� ����
				startButton.setVisible(false); //���� ��ư �������� �ʰ�
				quitButton.setVisible(false); //���� ��ư �������� �ʰ�
				leftButton.setVisible(true); //���� ��ư �����ϰ�
				rightButton.setVisible(true); //������ ��ư �����ϰ�
				Background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); //�̹����� mainBackground�� ���� ��, �׷��ֱ�
				isMainScreen = true; //����ȭ�� ����
			}
		});
		add(startButton);
		
		//���α׷� �����ư(quit) �׸���
		quitButton.setBounds(40, 330, 400, 100); //��ư ��ġ ����
		quitButton.setBorderPainted(false); //JButton�� ���� ������ ���Ͽ� �ٲپ������.
		quitButton.setContentAreaFilled(false); 
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() { //���콺 ������ & ��� ����
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� ��� �̺�Ʈ
				quitButton.setIcon(quitButtoinEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //���콺�� �ش� ��ư ���� �ö����� ���� ��� �̺�Ʈ
				quitButton.setIcon(quitButtonBasicImage); //������ ���� (������ ����)
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺 �ö��� �� ���콺 Ŀ�� ����
			}

			@Override
			public void mousePressed(MouseEvent e) { //���콺 ��ư Ŭ�� �̺�Ʈ
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
		
		//�� ����(����) ��ư
		leftButton.setVisible(false); //������ �ʰ�(false)
		leftButton.setBounds(140, 310, 60, 60); //��ư ��ġ ����
		leftButton.setBorderPainted(false); //JButton�� ���� ������ ���Ͽ� �ٲپ������.
		leftButton.setContentAreaFilled(false); 
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() { //���콺 ������ & ��� ����
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� ��� �̺�Ʈ
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //���콺�� �ش� ��ư ���� �ö����� ���� ��� �̺�Ʈ
				leftButton.setIcon(leftButtonBasicImage); //������ ���� (������ ����)
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺 �ö��� �� ���콺 Ŀ�� ����
			}

			@Override
			public void mousePressed(MouseEvent e) { //���콺 ��ư Ŭ�� �̺�Ʈ
				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEntedMusic.start();
				//���� ��ư �̺�Ʈ
				selectedLeft(); //�޼ҵ� ȣ��
			}
		});
		add(leftButton);
		
		//�� ����(������) ��ư
		rightButton.setVisible(false); //������ �ʰ�(false)
		rightButton.setBounds(1080, 310, 60, 60); //��ư ��ġ ����
		rightButton.setBorderPainted(false); //JButton�� ���� ������ ���Ͽ� �ٲپ������.
		rightButton.setContentAreaFilled(false); 
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() { //���콺 ������ & ��� ����
			@Override
			public void mouseEntered(MouseEvent e) {//���콺�� �ش� ��ư ���� �ö���� ��� �̺�Ʈ
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {  //���콺�� �ش� ��ư ���� �ö����� ���� ��� �̺�Ʈ
				rightButton.setIcon(rightButtonBasicImage); //������ ���� (������ ����)
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //���콺 �ö��� �� ���콺 Ŀ�� ����
			}

			@Override
			public void mousePressed(MouseEvent e) { //���콺 ��ư Ŭ�� �̺�Ʈ
				Music buttonEntedMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEntedMusic.start();
				//������ ��ư �̺�Ʈ
				selectedRight(); //�޼ҵ� ȣ��
			}
		});
		add(rightButton);

		//�޴��� �׸���
		menuBar.setBounds(0, 0, 1280, 30); //�޴��� ��ġ ����
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {  //���콺�� ��ư�� ������ �� �̺�Ʈ
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseAdapter() {
			@Override //�巡�� ���� ��, �� ���� â�� ��ġ�� �ٲ� - �ڵ鷯 ����
			public void mouseDragged(MouseEvent e) { //���콺�� �巡�� �̺�Ʈ
				int x = e.getXOnScreen(); 
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar); //�޴��� ����
	}

	public void paint(Graphics g) { // �׸��� �׷��ִ� �Լ�
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // �̹����� ������� ��, screenImage�� ����
		screenGraphic = screenImage.getGraphics(); // �׷��� ��ü ��������
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // ���� â�� ��ũ�� �̹��� ��� (0,0 ��ġ�� �׷���)
	}

	// ȭ�鿡 �׷��ֱ�
	public void screenDraw(Graphics g) {
		//drawImage�� ��� add�� �ƴ�, �̹����� �׳� ȭ�鿡 ��½�ų ��� �̿�
		//�ַ� �������� �̹����� �ִϸ��̼��� ��� drawImage�� ���
		g.drawImage(Background, 0, 0, null); // introBackground 0,0 ��ġ�� �׷���
		if(isMainScreen) { //isMainScreen�� true�� ���, ��, ��Ʈ�� ȭ���� �ƴ� ����ȭ������ �Ѿ�� ���
			g.drawImage(selectedImage,340,100,null); //selectedImage�� �׷����
		    g.drawImage(titleImage,340,70,null); //Ÿ��Ʋ �̹���
		}
		paintComponents(g); //paintComponets�� �׻� �����Ǿ��ִ� �̹����� ����. (add�� �ҷ����� �̹�����)
		this.repaint(); // paint �޼ҵ� �ҷ�����
	}
	
	//� ���� ���õǾ��°�? (���� ���õ� ���� ������)
	public void selectedTrack(int nowSelected) {
		if(selectedMusic != null) selectedMusic.close(); //��� ���� ����ǰ� �ִٸ� �ٷ� ����
			//���� ���õ� ���� ������ �ִ� Ÿ��Ʋ �̹��� ���� �����ͼ� titleImage�� ����.
		titleImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getTitleImage())).getImage(); //� ���� Ÿ��Ʋ ����
		selectedImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getStartImage())).getImage(); //� ���� �̹����� ����
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true); //���õ� ���� ���� ��������
		selectedMusic.start(); //selectedMusic ��ŸƮ
	}
	
	//���� ��ư Ŭ�� �̺�Ʈ
	public void selectedLeft() {
		if(nowSelected == 0) { //0��° ���� ���
			nowSelected = trackList.size() - 1; //0��° ���� �� ������ ���� ��� ���� ������ ������ �̵�
		}else {
			nowSelected--; //�ƴ� ��� �������� �� ĭ �̵�
		}
		selectedTrack(nowSelected);
	}
	
	//������ ��ư Ŭ�� �̺�Ʈ
	public void selectedRight() {
		if(nowSelected == trackList.size()-1) { //Ʈ������ ���� ������ ���� ���
			nowSelected = 0; //���� ù ��° ������ �̵�
		}else {
			nowSelected++; //�ƴ� ��� ������ �� ĭ �̵�
		}
		selectedTrack(nowSelected);
	}
}