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

//������ - ���� ���� ����Ǵ� �޼ҵ�
public class DynamicBeat extends JFrame {

	private Image screenImage; // ������۸��� Ȱ���ϱ� ���� ��üȭ�� �ν��Ͻ�
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
	
	// introBackground �ʱ�ȭ - ���ȭ�� ����
	private Image Background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	//�޴��� �����ư
	private JButton exitButton = new JButton(exitButtonBasicImage);
	//�����ϱ� ��ư
	private JButton startButton = new JButton(startButtonBasicImage);
	//�����ϱ� ��ư
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private int mouseX, mouseY;  //���α׷� �ȿ��� ���콺�� ��ġ ��

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
				Music buttonEntedMusic = new Music("buttonPressedMusic.mp3",false);
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
//				Music buttonEntedMusic = new Music("buttonPressedMusic.mp3",false);
//				buttonEntedMusic.start();
				// ���� ���� �̺�Ʈ
				startButton.setVisible(false); //���� ��ư �����ϰ�
				quitButton.setVisible(false); //���� ��ư �����ϰ�
				Background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage(); //�̹����� mainBackground�� ���� ��, �׷��ֱ�
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
		
		

		// ����ȭ�鿡�� ���� �������
		Music introMusic = new Music("IntroMusic.mp3", true);
		introMusic.start(); // ����
	}

	public void paint(Graphics g) { // �׸��� �׷��ִ� �Լ�
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // �̹����� ������� ��, screenImage�� ����
		screenGraphic = screenImage.getGraphics(); // �׷��� ��ü ��������
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null); // ���� â�� ��ũ�� �̹��� ��� (0,0 ��ġ�� �׷���)
	}

	// ȭ�鿡 �׷��ֱ�
	public void screenDraw(Graphics g) {
		g.drawImage(Background, 0, 0, null); // introBackground 0,0 ��ġ�� �׷���
		paintComponents(g); //paintComponets�� �׻� �����Ǿ��ִ� �̹����� ����.
		this.repaint(); // paint �޼ҵ� �ҷ�����
	}
}
