package beat_v11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

//하나의 게임에 대한 컨트롤 클래스
public class Game extends Thread{ 
	
	//노트 이미지
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	//노트 경로 선
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	//노트 판정 바
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	//노트 바
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	//노트 경로
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();


	
	public void ScreenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage,228,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteDImage,332,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteFImage,436,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteSpace1Image,540,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteSpace2Image,640,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteJImage,744,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteKImage,848,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteLImage,952,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteLineImage,224,30,null);  //노트 라인 경로 이미지 그리기
		g.drawImage(noteRouteLineImage,328,30,null);  //노트 라인 경로 이미지 그리기
		g.drawImage(noteRouteLineImage,432,30,null);  //노트 라인 경로 이미지 그리기
		g.drawImage(noteRouteLineImage,536,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteLineImage,740,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteLineImage,844,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteLineImage,948,30,null);  //노트 경로 이미지 그리기
		g.drawImage(noteRouteLineImage,1052,30,null);  //노트 경로 이미지 그리기
		
		g.drawImage(gameInfoImage,0,740,null);  //게임 정보선 그리기
		g.drawImage(judgementLineImage,0,660,null);  //노트 판정선 그리기
		
		g.setColor(Color.white); //곡 정보 작성 ( 폰트 )
		
		//안티 엘리어싱 활성화
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
	
	//눌렀을 때
	public void pressS() { 
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	}
	
	//땠을 때
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
	//Thread는 하나의 프로그램 안에서 실행되는 작은 프로그램
	@Override
	public void run() { //Game이라는 클래스로 만든 인스턴스는 run 안에 있는 메소드 내용을 실행하게  됨.
		
	}
}
