package dynamic_beat;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.jpg"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.jpg"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.jpg"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.jpg"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.jpg"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.jpg"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.jpg"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.jpg"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/rightButtonEntered.jpg"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.jpg"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.jpg"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.jpg"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.jpg"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.jpg"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.jpg"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.jpg"));

	
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.jpg")));

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);

	private int mouseX, mouseY;// Mouse座標

	private boolean isGameScreen = false;
	private boolean isMainScreen = false;//Main画面じゃないので falseを与える

	ArrayList<Track> trackList = new ArrayList<Track>();
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;// コードの変数化
	private Music introMusic = new Music("introMusic.mp3", true);
	private int nowSelected = 0;// 現在選択された曲 を意で、0は一番目の曲を意味

	public static Game game; //全体プログラムに使っている一つの変数
	
	public DynamicBeat() {
		trackList.add(new Track("miraiTitleImage.jpg", "miraiStartImage.png", "miraiGameImage.jpg", "miraiSelect.mp3",
				"mirai.mp3","mirai - jam project")); // 0番目の歌
		trackList.add(new Track("miraiTitleImage.jpg", "emeraldStartImage.jpg", "emeraldGameImage.jpg",
				"emeraldSwordSelect.mp3", "emeraldSword.mp3", "emeraldSword - Rhapsody of fire")); // 1番目の歌
		trackList.add(new Track("summerTitleImage.png", "summerStartImage.jpg", "summerGameImage.jpg",
				"summerSelect.mp3", "summer.mp3", "summer - hisaisi joe")); // 2番目の歌
		
		setUndecorated(true);
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));// 白色で変える
		setLayout(null);

		addKeyListener(new KeyListener());
		
		introMusic.start(); 

		// 曲のリストを管理する. 曲の順番も管理できる。
		exitButton.setBounds(1245, 0, 30, 30);// MenuBarの右に配置
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //  Mouseが上がる時、様子変更
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// Mouseの様子が手の様子に変更する
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // 押す時、ゲーム終了
			}
		});

		add(exitButton);

		startButton.setBounds(40, 200, 400, 100);// 位置配置
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //  Mouseが上がる時、様子変更
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// MousePointの様子が手の様子に変える
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
				enterMain();
	
			}
		});

		add(startButton);

		quitButton.setBounds(40, 330, 400, 100);// 位置配置
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // Mouseが上がる時、様子変更
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//Mouseの様子が手の様子に変更する
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);// 1秒後終了
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // 押す時ゲーム終了
			}
		});

		add(exitButton);

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);// 位置配置
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // Mouseが上がる時、様子変更
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//Mouseの様子が手の様子に変更する
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
				selectLeft();
				// 左ButtonEvent
			}
		});

		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);// 位置配置
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // Mouseが上がる時、様子変更
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// Mouseの様子が手の様子に変更する
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);//falseは一度だけ実行
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
				selectRight();
				// 右のButtonEvent
			}
		});

		add(rightButton);

		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);// 位置配置
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // Mouseが上がる時、様子変更
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// Mouseの様子が手の様子に変更する
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
				// 難易度EasyEvent
			}
		});

		add(easyButton);

		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);// 位置配置
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { //  Mouseが上がる時、様子変更
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// Mouseの様子が手の様子に変更する
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard");
				// 難易度HardEvent
			}
		});

		add(hardButton);

		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);// Buttonの位置を配置 x,y/大きさx,y
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // MouseがButtonの上にある時、様子が変更
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// falseは一度だけ実行
				buttonEnteredMusic.start();
				backMain();//Mainで戻る
			}
		});

		add(backButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {// Mouse座標追加
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) { //Dragする時現在ゲームの画面の位置を移動させる
				int x = e.getXOnScreen();// 現在MouseのXの位置を認識
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 0, null);

	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if (isMainScreen) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 50, null);
		} // 単純なイメージを見せて力動的なイメージを見える
		if(isGameScreen) {
			game.screenDraw(g);//Game.Javaを認識
		}
		paintComponents(g);// ScreenImageを描く以外にJFrameの中で描く。そして追加された要素を見せる
		try {
			Thread.sleep(5);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		this.repaint();// 継続反復

	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)// ! = not
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();// 現在選択されたことに曲を入れる意味
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);//音楽反復
		selectedMusic.start();// 音楽Start

	}

	public void selectLeft() {
		if (nowSelected == 0) // 0番目の曲の時、右の曲を選択するのができるため-1をする
			nowSelected = trackList.size() - 1;
		else
			nowSelected--; 
		selectTrack(nowSelected);

	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) // 先の曲に移動する
			nowSelected = 0;
		else
			nowSelected++; 
		selectTrack(nowSelected);

	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null)// 音楽が実行している時
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		// 今、選択されたゲームのイメージを呼び込む
		isGameScreen = true;
		game  = new Game(trackList.get(nowSelected).getTitleName(),difficulty, trackList.get(nowSelected).getGameMusic());//曲のTitleNameをGame.Javaで持っている
	    game.start();//run関数が実行
	    setFocusable(true);
	}
	public void backMain() {
		isMainScreen = true;//MainMethodに戻る
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
				.getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
		
	}
	public void enterMain() {
		selectTrack(0);
		startButton.setVisible(false);// 押す時StartButtonを消す
		quitButton.setVisible(false);// ExitButtonを消す
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true); 
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();// mainground																											// 등장
		isMainScreen = true;
		introMusic.close();
	}
}
