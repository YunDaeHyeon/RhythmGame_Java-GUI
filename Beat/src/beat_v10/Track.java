package beat_v10;

//Track - �ش� ���� ���� (�ٹ�)
public class Track {
	private String titleImage; // ���� �κ� �̹���
	private String startImage; // ���� ���� â ǥ�� �̹���
	private String gameImage; //���� ���� �������� �� �̹���
	private String startMusic; //���� ���� â ����
	private String gameMusic; // �ش� ���� �������� �� ����
	
	//������ - Track�̶�� Ŭ������ ����Ͽ� ���� �������� �ʱ�ȭ �����ִ� �޼ҵ�(���� ���� ����)
	public Track(String titleImage, String startImage, String gameImage, String startMusic, String gameMusic) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.gameImage = gameImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
	}
	
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getGameImage() {
		return gameImage;
	}
	public void setGameImage(String gameImage) {
		this.gameImage = gameImage;
	}
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
}
