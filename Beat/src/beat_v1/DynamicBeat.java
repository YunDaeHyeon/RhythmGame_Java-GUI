package beat_v1;

import javax.swing.JFrame;

public class DynamicBeat extends JFrame{
	
	//������ - ���� ���� ����Ǵ� �޼ҵ�
	public DynamicBeat() {
		setTitle("���̳��� ��Ʈ"); // â Ÿ��Ʋ ����
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT); //â ũ�� ����
		setResizable(false); // â ���� ���� �Ұ�
		setLocationRelativeTo(null); //â ���� �� �߾ӿ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���� ���� ��, ������ ����
		setVisible(true); //���� ���� â�� ���������� ȭ������ ��µǵ��� ����
	}
}
