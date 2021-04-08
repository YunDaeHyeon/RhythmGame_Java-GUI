package beat_v3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

//������ - ���� ���� ����Ǵ� �޼ҵ�
public class DynamicBeat extends JFrame{
	
	private Image  screenImage; //������۸��� Ȱ���ϱ� ���� ��üȭ�� �ν��Ͻ�
	private Graphics screenGraphic; //����
	
	private Image introBackground;
   
	public DynamicBeat() {
		setTitle("���̳��� ��Ʈ"); // â Ÿ��Ʋ ����
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); //â ũ�� ����
		setResizable(false); // â ���� ���� �Ұ�
		setLocationRelativeTo(null); //â ���� �� �߾ӿ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���� ���� ��, ������ ����
		setVisible(true); //���� ���� â�� ���������� ȭ������ ��µǵ��� ����
		
		//introBackground �ʱ�ȭ - ���ȭ�� ����
		introBackground = new ImageIcon(Main.class.getResource("../images/IntroBackground.jpg")).getImage();
		
		//����ȭ�鿡�� ���� �������
		Music introMusic = new Music("IntroMusic.mp3", true);
		introMusic.start(); //����
	}
	
	public void paint(Graphics g) { //�׸��� �׷��ִ� �Լ�
		screenImage = createImage(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); // �̹����� ������� ��, screenImage�� ����
		screenGraphic = screenImage.getGraphics(); // �׷��� ��ü ��������
		screenDraw(screenGraphic);  
		g.drawImage(screenImage, 0, 0, null); //���� â�� ��ũ�� �̹��� ��� (0,0 ��ġ�� �׷���)
	}
	
	//ȭ�鿡 �׷��ֱ�
	public void screenDraw(Graphics g) {
		g.drawImage(introBackground,0,0,null); //introBackground 0,0 ��ġ�� �׷���
		this.repaint(); //paint �޼ҵ� �ҷ�����
	}
}
