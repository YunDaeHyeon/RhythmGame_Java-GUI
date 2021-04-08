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

//������ - ���� ���� ����Ǵ� �޼ҵ�
public class DynamicBeat extends JFrame {

	private Image screenImage; // ������۸��� Ȱ���ϱ� ���� ��üȭ�� �ν��Ͻ�
	private Graphics screenGraphic; // ����

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));

	// introBackground �ʱ�ȭ - ���ȭ�� ����
	private Image introBackground = new ImageIcon(Main.class.getResource("../images/IntroBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private int mouseX, mouseY;

	public DynamicBeat() {
		setUndecorated(true);
		setTitle("���̳��� ��Ʈ"); // â Ÿ��Ʋ ����
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // â ũ�� ����
		setResizable(false); // â ���� ���� �Ұ�
		setLocationRelativeTo(null); // â ���� �� �߾ӿ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� ���� ��, ������ ����
		setVisible(true); // ���� ���� â�� ���������� ȭ������ ��µǵ��� ����
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
		g.drawImage(introBackground, 0, 0, null); // introBackground 0,0 ��ġ�� �׷���
		paintComponents(g);
		this.repaint(); // paint �޼ҵ� �ҷ�����
	}
}
