package dynamic_beat;

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 5;//Noteの速さ
	public static final int SLEEP_TIME = 5;//얼마간의 시간을 주기로 떨어진다.
	public static final int REACH_TIME = 1;//노트가 생성되고 나서 판정바에 도달하기 까지의 시간 
	
	public static void main(String[] args) {
		
		 new DynamicBeat();

	}

}
