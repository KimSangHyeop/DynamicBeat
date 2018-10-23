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

	public static Game game; //全体プログラムに使って사용되는 하나의 변수 , 변수 선언만 한다
	
	public DynamicBeat() {
		trackList.add(new Track("miraiTitleImage.jpg", "miraiStartImage.png", "miraiGameImage.jpg", "miraiSelect.mp3",
				"mirai.mp3","mirai - jam project")); // 0번 노래
		trackList.add(new Track("miraiTitleImage.jpg", "emeraldStartImage.jpg", "emeraldGameImage.jpg",
				"emeraldSwordSelect.mp3", "emeraldSword.mp3", "emeraldSword - Rhapsody of fire")); // 1번 노래
		trackList.add(new Track("summerTitleImage.png", "summerStartImage.jpg", "summerGameImage.jpg",
				"summerSelect.mp3", "summer.mp3", "summer - hisaisi joe")); // 2번 노래 / 곡을 한개 더 추가 해서 곡이 안정적으로 들어 갈 수 있게 초기 화 한다. 
		//로딩 최소화
		setUndecorated(true);// 실행시 기본적으로 존제하는 매뉴바가 보이지 않는다
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));// 하얀색으로 바뀐다
		setLayout(null);// 그 위치 그대로 나온다

		addKeyListener(new KeyListener());
		
		introMusic.start(); 

		// 곡들의 리스트를 관리한다. 곡 순서도 관리 가능 하다
		exitButton.setBounds(1245, 0, 30, 30);// 메뉴바의 가장 오른쪽에 위치하도록 배치
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스가 올라갔을 시 변경
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 모양 이 손 모양으로 바뀐다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // Press 시 게임 종료
			}
		});

		add(exitButton);

		startButton.setBounds(40, 200, 400, 100);// 위치 배치 배치
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스가 올라갔을 시 변경
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 모양 이 손 모양으로 바뀐다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
				enterMain();
	
			}
		});

		add(startButton);

		quitButton.setBounds(40, 330, 400, 100);// 위치 배치
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스가 올라갔을 시 변경
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 모양 이 손 모양으로 바뀐다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);// 1초 후 종료
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0); // Press 시 게임 종료
			}
		});

		add(exitButton);

		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);// 위치 배치
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스가 올라갔을 시 변경
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 모양 이 손 모양으로 바뀐다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
				selectLeft();
				// 왼쪽 버튼 이벤트
			}
		});

		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);// 위치 배치
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스가 올라갔을 시 변경
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 모양 이 손 모양으로 바뀐다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
				selectRight();
				// 오른쪽 버튼 이벤트
			}
		});

		add(rightButton);

		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);// 위치 배치
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // 마우스가 올라갔을 시 변경
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 모양 이 손 모양으로 바뀐다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
				// 난이도 쉬움 이벤트
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
			public void mouseEntered(MouseEvent e) { // 마우스가 올라갔을 시 변경
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 모양 이 손 모양으로 바뀐다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// falseは一度だけ試行
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard");
				// 난이도 어려움 이벤트
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
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));// 마우스 모양 이 손 모양으로 바뀐다
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
			}

			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			};

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);// false는 한번만 실행 되도록 한다
				buttonEnteredMusic.start();
				backMain();//main으로 돌아간다
			}
		});

		add(backButton);

		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {// Mouse좌표 추가
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) { //Dragする時現在ゲームの画面の位置を移動させる
				int x = e.getXOnScreen();// 現在Mouseのxの位置を認識
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
		paintComponents(g);// 스크린 이미지를 그려주는 것 이외에 JFrame 안에 그려주는 것 항상 고정시키는 이미지를 그린다. 및 추가된 요소들을 보여준다
		try {
			Thread.sleep(5);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		this.repaint();// 계속 반복

	}

	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)// ! = not
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage()))
				.getImage();// 현제 선택된 값의 곡을 넣어주겠다는 의미이다.
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage()))
				.getImage();// 현제 선택된 값의 곡을 넣어주겠다는 의미이다.
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);// 음악 반복
		selectedMusic.start();// 음악 시작

	}

	public void selectLeft() {
		if (nowSelected == 0) // 0번째 곡일 때는 오른쪽 곡 선택가능하게 -1를 한다.
			nowSelected = trackList.size() - 1;
		else
			nowSelected--; // 아닐시 다시 세팅
		selectTrack(nowSelected);

	}

	public void selectRight() {
		if (nowSelected == trackList.size() - 1) // 처음으로 이동하도록 만든다.
			nowSelected = 0;
		else
			nowSelected++; // 아닐시 다시 세팅
		selectTrack(nowSelected);

	}

	public void gameStart(int nowSelected, String difficulty) {
		if (selectedMusic != null)// 음악이 실행 중일 때
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();// mainground 등장
		backButton.setVisible(true);
		// 현제 선택된 게임 이미지 불러오기
		isGameScreen = true;
		game  = new Game(trackList.get(nowSelected).getTitleName(),difficulty, trackList.get(nowSelected).getGameMusic());//곡 제목을 가지고 올 수 있게 한다.
	    game.start();//run 함수가 실행이 된다
	    setFocusable(true);
	}
	public void backMain() {
		isMainScreen = true;//다시 메인함수로 돌아온다
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
		startButton.setVisible(false);// 눌렀을 시 스타트 버튼 사라짐
		quitButton.setVisible(false);// 나가기 버튼 사라짐
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true); // 메인 화면으로 넘어왔을시 보여진다.
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg")).getImage();// mainground																											// 등장
		isMainScreen = true;
		introMusic.close();
	}
}
