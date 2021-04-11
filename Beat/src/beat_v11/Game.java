package beat_v11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

//�ϳ��� ���ӿ� ���� ��Ʈ�� Ŭ����
public class Game extends Thread{ 
	
	//��Ʈ �̹���
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	//��Ʈ ��� ��
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	//��Ʈ ���� ��
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	//��Ʈ ��
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	//��Ʈ ���
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();


	
	public void ScreenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage,228,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteDImage,332,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteFImage,436,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteSpace1Image,540,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteSpace2Image,640,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteJImage,744,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteKImage,848,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteLImage,952,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteLineImage,224,30,null);  //��Ʈ ���� ��� �̹��� �׸���
		g.drawImage(noteRouteLineImage,328,30,null);  //��Ʈ ���� ��� �̹��� �׸���
		g.drawImage(noteRouteLineImage,432,30,null);  //��Ʈ ���� ��� �̹��� �׸���
		g.drawImage(noteRouteLineImage,536,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteLineImage,740,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteLineImage,844,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteLineImage,948,30,null);  //��Ʈ ��� �̹��� �׸���
		g.drawImage(noteRouteLineImage,1052,30,null);  //��Ʈ ��� �̹��� �׸���
		
		g.drawImage(gameInfoImage,0,740,null);  //���� ������ �׸���
		g.drawImage(judgementLineImage,0,660,null);  //��Ʈ ������ �׸���
		
		g.setColor(Color.white); //�� ���� �ۼ� ( ��Ʈ )
		
		//��Ƽ ������� Ȱ��ȭ
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString("Ghibrl - Totoro",20,782);
		g.drawString("Easy",1190,782);
		g.setFont(new Font("Arial",Font.PLAIN,26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 689);
		g.drawString("D", 374, 689);
		g.drawString("F", 478, 689);
		g.drawString("Space Bar", 580, 689);
		g.drawString("J", 784, 689);
		g.drawString("K", 889, 689);
		g.drawString("L", 993, 689);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant",Font.BOLD,30));
		g.drawString("000000", 565, 782);
	}
	
	//������ ��
	public void pressS() { 
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	//���� ��
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressD() { 
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressF() { 
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressSpace() { 
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressJ() { 
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressK() { 
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	
	public void pressL() { 
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	//Thread�� �ϳ��� ���α׷� �ȿ��� ����Ǵ� ���� ���α׷�
	@Override
	public void run() { //Game�̶�� Ŭ������ ���� �ν��Ͻ��� run �ȿ� �ִ� �޼ҵ� ������ �����ϰ�  ��.
		
	}
}
