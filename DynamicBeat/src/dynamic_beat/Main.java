package dynamic_beat;

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 5;//Noteの速さ
	public static final int SLEEP_TIME = 5;//Noteが落ちる時間
	public static final int REACH_TIME = 1;//Noteが生成されて判定Barに到達するまでの時間 
	
	public static void main(String[] args) {
		
		 new DynamicBeat();

	}

}
