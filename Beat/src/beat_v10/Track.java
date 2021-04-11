package beat_v10;

//Track - 해당 곡의 정보 (앨범)
public class Track {
	private String titleImage; // 제목 부분 이미지
	private String startImage; // 게임 선택 창 표지 이미지
	private String gameImage; //게임 곡을 실행했을 때 이미지
	private String startMusic; //게임 선택 창 음악
	private String gameMusic; // 해당 곡을 실행했을 때 음악
	
	//생성자 - Track이라는 클래스를 사용하여 내부 변수들을 초기화 시켜주는 메소드(가장 먼저 실행)
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
