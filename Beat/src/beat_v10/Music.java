package beat_v10;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player;
	private boolean isLoop;  //������ �� ���� �������, ���� �ݺ�����
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI()); //���� ��ġ �о����
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis); //�ش� ���� ���
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//���� ������ � �������� ����ǰ� �ִ°�? (ms����)
	//���� ��Ʈ�� ����߸� �� �� �޼ҵ带 �̿��Ͽ� �ð��� �м�
	public int getTime() {
		if(player == null)
			return 0;
		return player.getPosition();
	}
	
	//�������� ������ �� �ֵ��� �����ִ� �޼ҵ� 
	//���������� �շ�
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt(); //�ش� �����带 ������ �������� ����
	}
	
	
	//�����带 ��ӹ����� �ݵ�� ����ؾ��ϴ� �޼ҵ�
	@Override
	public void run() {
		try {
			do {
					player.play(); // �� ����
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					player = new Player(bis); //�ش� ���� ���
					}while(isLoop);  //isLoop�� true�̶�� �ش� �� ���ѹݺ�
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
