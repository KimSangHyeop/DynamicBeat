package dynamic_beat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {// ゲーム内のNote管理

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
	private Image judgeImage = new ImageIcon(Main.class.getResource("../images/judgeEarly.png")).getImage();
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();


	private String titleName = "mirai - jam project";
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);

	}// TitleName（ゲームの名前）、Difficulty（難易度）、MusicTitle(音楽の名前)を生成者で生成

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (!note.isProceeded()) {
				noteList.remove(i);
				i++; // 必要ないNote消す
			} else {
				note.screenDraw(g);
			}
			note.screenDraw(g);
		} // Note.JavaにあるNoteを読み込む
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);// 文字化けを防止
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);// TitleNameをみせる.
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 270, 609);// Key表示
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("SPACE BAR", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString("000000", 565, 702);// 点数を表示
		g.drawImage(blueFlareImage, 465, 380, null);
		g.drawImage(judgeImage, 465, 450, null);
		g.drawImage(keyPadSImage, 228, 580, null);
		g.drawImage(keyPadDImage, 332, 580, null);
		g.drawImage(keyPadFImage, 436, 580, null);
		g.drawImage(keyPadSpace1Image, 540, 580, null);
		g.drawImage(keyPadSpace2Image, 640, 580, null);
		g.drawImage(keyPadJImage, 774, 580, null);
		g.drawImage(keyPadKImage, 848, 580, null);
		g.drawImage(keyPadLImage, 952, 580, null);

		
	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("DrumSmall1.mp3", false).start(); // 音を一度だけ出るためfalseを入れる
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("DrumSmall1.mp3", false).start(); // 音を一度だけ出るためfalseを入れる
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		new Music("DrumSmall1.mp3", false).start(); // 音を一度だけ出るためfalseを入れる
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("DrumSmall2.mp3", false).start(); // 音を一度だけ出るためfalseを入れる
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("DrumSmall1.mp3", false).start(); // 音を一度だけ出るためfalseを入れる
	}

	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("DrumSmall1.mp3", false).start(); // 音を一度だけ出るためfalseを入れる
	}

	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("DrumSmall1.mp3", false).start(); // 音を一度だけ出るためfalseを入れる
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(titleName);

	}

	public void close() {
		gameMusic.close();
		this.interrupt();// Thread終了
	}

	public void dropNotes(String titleName) {
		Beat[] beats = null; // 配列生成
		if (titleName.equals("mirai - jam project") && difficulty.equals("Easy")) {
			int startTime = 4460 - Main.REACH_TIME * 1000;
			int gap = 125;// 拍子計算
			beats = new Beat[] { new Beat(startTime, "Space"), 
					new Beat(startTime + gap * 2, "D" ),
					new Beat(startTime + gap * 2, "F"), 
					new Beat(startTime + gap * 4, "J"),
					new Beat(startTime + gap * 4, "K"), 
					new Beat(startTime + gap * 8, "S"),
					new Beat(startTime + gap * 12, "L"), 
					new Beat(startTime + gap * 14, "D"),
					new Beat(startTime + gap * 16, "F"), 
					new Beat(startTime + gap * 18, "S"),
					new Beat(startTime + gap * 18, "K"), 
					new Beat(startTime + gap * 20, "J"),
					new Beat(startTime + gap * 22, "D"),
					new Beat(startTime + gap * 24, "S"),
					new Beat(startTime + gap * 26, "Space"),
					new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "L"),
					new Beat(startTime + gap * 32, "J"),
					new Beat(startTime + gap * 34, "K"), 
					new Beat(startTime + gap * 38, "S"),
					new Beat(startTime + gap * 40, "L"), 
					new Beat(startTime + gap * 42, "D"),
					new Beat(startTime + gap * 44, "F"), 
					new Beat(startTime + gap * 46, "S"),
					new Beat(startTime + gap * 48, "K"), 
					new Beat(startTime + gap * 50, "J"),
					new Beat(startTime + gap * 52, "D"),
					new Beat(startTime + gap * 54, "S"),
					new Beat(startTime + gap * 56, "Space"),
					new Beat(startTime + gap * 58, "K"),
					new Beat(startTime + gap * 60, "L"),
					new Beat(startTime + gap * 62, "J"),
					new Beat(startTime + gap * 64, "K"), 
					new Beat(startTime + gap * 68, "S"),
					new Beat(startTime + gap * 70, "L"), 
					new Beat(startTime + gap * 72, "D"),
					new Beat(startTime + gap * 74, "F"), 
					new Beat(startTime + gap * 76, "S"),
					new Beat(startTime + gap * 78, "K"), 
					new Beat(startTime + gap * 80, "J"),
					new Beat(startTime + gap * 82, "D"),
					new Beat(startTime + gap * 84, "S"),
					new Beat(startTime + gap * 86, "Space"),
					new Beat(startTime + gap * 88, "K"),
					new Beat(startTime + gap * 90, "L"),
					new Beat(startTime + gap * 91, "D"),
					new Beat(startTime + gap * 92, "F"),
					new Beat(startTime + gap * 94, "Space"),
					new Beat(startTime + gap * 96, "K"),
					new Beat(startTime + gap * 98, "J"),
					new Beat(startTime + gap * 100, "K"),
					new Beat(startTime + gap * 100, "D"), 
					new Beat(startTime + gap * 102, "S"),
					new Beat(startTime + gap * 104, "Space"), 
					new Beat(startTime + gap * 106, "D"),
					new Beat(startTime + gap * 108, "L"), 
					new Beat(startTime + gap * 110, "F"),
					new Beat(startTime + gap * 112, "K"), 
					new Beat(startTime + gap * 114, "J"),
					new Beat(startTime + gap * 116, "D"),
					new Beat(startTime + gap * 117, "F"),
					new Beat(startTime + gap * 108, "Space"),
					new Beat(startTime + gap * 120, "Space"),
					new Beat(startTime + gap * 122, "Space"),
					new Beat(startTime + gap * 124, "J"),
					new Beat(startTime + gap * 126, "L"), 
					new Beat(startTime + gap * 128, "F"),
					new Beat(startTime + gap * 130, "J"), 
					new Beat(startTime + gap * 132, "D"),
					new Beat(startTime + gap * 134, "J"), 
					new Beat(startTime + gap * 136, "S"),
					new Beat(startTime + gap * 137, "S"), 
					new Beat(startTime + gap * 138, "S"),
					new Beat(startTime + gap * 140, "K"),
					new Beat(startTime + gap * 140, "S"),
					new Beat(startTime + gap * 141, "Space"),
					new Beat(startTime + gap * 143, "K"),
					new Beat(startTime + gap * 145, "L"),
			
					
			};
		} else if (titleName.equals("emeraldSword - Rhapsody of fire") && difficulty.equals("Easy")) {
			int startTime = 4460 - Main.REACH_TIME * 1000;// 別の曲に合うNoteをせいせい
			
		} else if (titleName.equals("summer - hisaisi joe") && difficulty.equals("Easy")) {
			int startTime = 4460 - Main.REACH_TIME * 1000;
			
		}
		int i = 0;

		gameMusic.start();// 音楽実行の時、Noteが落ちる
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;// iを1ずっつ 増加させる
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);// Noteを0.005秒休ませる
				} catch (InterruptedException e) {

				} // 時間をおいでNoteを落ちる
			}
		}
	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
               note.judge();
               break;
			}

		}
	}
}
