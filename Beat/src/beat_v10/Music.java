package beat_v10;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player;
	private boolean isLoop;  //음악이 한 번만 재생인지, 무한 반복인지
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI()); //파일 위치 읽어오기
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis); //해당 파일 담기
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//현재 음악이 어떤 시점에서 실행되고 있는가? (ms단위)
	//추후 노트를 떨어뜨릴 때 이 메소드를 이용하여 시간을 분석
	public int getTime() {
		if(player == null)
			return 0;
		return player.getPosition();
	}
	
	//언제든지 종료할 수 있도록 도와주는 메소드 
	//안정적으로 죵료
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); //해당 스레드를 강제로 중지상태 변경
	}
	
	
	//스레드를 상속받으면 반드시 사용해야하는 메소드
	@Override
	public void run() {
		try {
			do {
					player.play(); // 곡 실행
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					player = new Player(bis); //해당 파일 담기
					}while(isLoop);  //isLoop가 true이라면 해당 곡 무한반복
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
