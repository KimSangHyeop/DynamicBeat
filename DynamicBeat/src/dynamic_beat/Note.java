package dynamic_beat;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {

	private Image noteBasicIamge = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; // 현제 노트의 위치 알려준다 및 1초 뒤에
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
		proceeded = false;// 노트 이동 불가
	}

	public Note(String noteType) {
		if (noteType.equals("S")) {// x 값을 동적으로 바꾼다.
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
		this.noteType = noteType; // 초기화
	}// 현제 노트의 위치를 알려 준다.

	public void screenDraw(Graphics2D g) {
		if (!noteType.equals("Space")) // 스페이스가 아닌 경우에는 일반적인 이미지가 뜨게 한다
		{
			g.drawImage(noteBasicIamge, x, y, null);
		} else if (noteType.equals("continuity"))// 연속으로 2개
		{
			g.drawImage(noteBasicIamge, x, y, null);
			g.drawImage(noteBasicIamge, x, y + 100, null);
		} else if (noteType.equals("double"))// 연속으로 2개
		{
			g.drawImage(noteBasicIamge, x, y, null);
			g.drawImage(noteBasicIamge, x + 105, y, null);
		} else // space 키 설정
		{// 스페이스 바에 쓰인다
			g.drawImage(noteBasicIamge, x, y, null);
			g.drawImage(noteBasicIamge, x + 100, y, null);
		}
	}

	public void drop() {
		y += Main.NOTE_SPEED;// 아래로 7만큼 떨어진다
		if (y > 620) {
			System.out.println("Miss");// 판정 함수
			close();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);//쉬는 시간 설정
				}else {
					interrupt();
					break;
				}
				Thread.sleep(Main.SLEEP_TIME); // 1초에 100번 실행하게 한다
			}
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
			
		}
	
	}// Thread 실행 함수
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
