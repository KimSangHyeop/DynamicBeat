package dynamic_beat;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicIamge = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; // 現在Noteの位置を教える、そして位置を教える
																							// 판정라인에 도달하게 한다. y좌표는 달라진다.
																							// 580은 판정 라인
	private String noteType;
	private boolean proceeded = true;

	public String getNoteType() {
		return noteType;
	}

	public boolean isProceeded() {
		return proceeded;
	}

	public void close() {
		proceeded = false;//Note移動不可
	}

	public Note(String noteType) {
		if (noteType.equals("S")) {// xを動的に変える
			x = 228;
		} else if (noteType.equals("D")) {
			x = 332;
		} else if (noteType.equals("F")) {
			x = 436;
		} else if (noteType.equals("Space")) {
			x = 540;
		} else if (noteType.equals("J")) {
			x = 744;
		} else if (noteType.equals("K")) {
			x = 848;
		} else if (noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType; // 初期化
	}// 現在Noteの位置を教える

	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) // SpaceKeyがない場合に一般的なNoteのImageを見せる
		{
			g.drawImage(noteBasicIamge, x, y, null);
		} else if (noteType.equals("continuity"))
		{
			g.drawImage(noteBasicIamge, x, y, null);
			g.drawImage(noteBasicIamge, x, y + 100, null);
		} else if (noteType.equals("double"))
		{
			g.drawImage(noteBasicIamge, x, y, null);
			g.drawImage(noteBasicIamge, x + 105, y, null);
		} else // SpaceKey設定
		{
			g.drawImage(noteBasicIamge, x, y, null);
			g.drawImage(noteBasicIamge, x + 100, y, null);
		}
	}

	public void drop() {
		y += Main.NOTE_SPEED;// Noteが落ちる速さ
		if (y > 620) {
			System.out.println("Miss");//判定関数
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);//SleepTime設定
				}else {
					interrupt();
					break;
				}
				Thread.sleep(Main.SLEEP_TIME);
			}
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
			
		}
	
	}
	public void judge() {
		if(y >=613) {
			System.out.println("Late");
			close();
		}else if(y >=600) {
			System.out.println("Good");
			close();
		}else if(y >=587) {
			System.out.println("Great");
			close();
		}else if(y >=573) {
			System.out.println("Perfect");
			close();
		}else if(y >=550) {
			System.out.println("Good");
			close();
		}else if(y >=535) {
			System.out.println("Early");
			close();
		}
	}
}
