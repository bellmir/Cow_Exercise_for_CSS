package todayMeal;

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.swing.BrowserView;

public class Start extends JFrame{
	
	private Image WhiteBackground_HD= new ImageIcon(Main.class.getResource("../image/Main(1280x720).jpg")).getImage();
	private Image DarkBackground_HD= new ImageIcon(Main.class.getResource("../image/Main_1280_720.jpg")).getImage();
	private JPanel MapControl = new JPanel();
    private JPanel StartButtonPanel = new JPanel();
    private ButtonGroup MapButtons = new ButtonGroup();
    
	Start(){
		setTitle("Today's Meal");
		setSize(Main.HD_WIDTH,Main.HD_HEIGHT);		//HD 스크린 사이즈로
		setResizable(false);	//사이즈 조절 불가
		setLocationRelativeTo(null);	//창 뜰때 화면 정 중앙에 오도록
		setLayout(new BorderLayout());
		
		//아이콘 설정
		Image StartIcon = new ImageIcon(Main.class.getResource("../image/StartIcon.png")).getImage();
		setIconImage(StartIcon);
		
		//배경 설정
		JComponent WhitePanel_HD = new StartBackground(WhiteBackground_HD);
		JComponent DarkPanel_HD = new StartBackground(DarkBackground_HD);
		add(WhitePanel_HD);
		add(DarkPanel_HD);
		DarkPanel_HD.setVisible(false);
		//add(StartPanel_FHD);
		//StartPanel_FHD.setVisible(false);
		
		//시작화면에 틀어놓을 음악의 이름과, 반복재생 유무를 파라미터로 전달한다.
		Music introMusic = new Music("introMusic_Bread.mp3", true);
		introMusic.start();
		
		//메뉴바 설정
		JMenuBar StartMenuBar = new JMenuBar();		//메뉴바
		setJMenuBar(StartMenuBar);
		JMenu SettingMenu = new JMenu("설정");		//설정 메뉴
		StartMenuBar.add(SettingMenu);
		
		//설명서 메뉴
		JMenu HelpMenu = new JMenu("도움말");
		StartMenuBar.add(HelpMenu);
		JMenuItem Manual = new JMenuItem("사용방법");
		HelpMenu.add(Manual);
		Manual.addActionListener(e -> {
				JOptionPane.showMessageDialog(null, " 배경음악은 설정>음악설정에서 끌 수 있습니다.\n"
						+ "\n시작하기를 누르시면 지도화면으로 이동하고, 학교식당을 선택하시면 학교 식당 메뉴를 확인하실 수 있습니다."
						+ "\n인터넷 검색을 선택하시면 검색기능 또한 사용 가능합니다", "사용방법", JOptionPane.INFORMATION_MESSAGE);
			});
		
		//설정 메뉴의 submenu 음악 설정
		JMenu SoundOption = new JMenu("음악 설정");	
		SettingMenu.add(SoundOption);
		JRadioButtonMenuItem MusicOnOff = new JRadioButtonMenuItem("배경음악 Off");
		ButtonGroup BackMusicGroup = new ButtonGroup();
		BackMusicGroup.add(MusicOnOff);
		SoundOption.add(MusicOnOff);
		MusicOnOff.addActionListener( e -> {
			if(MusicOnOff.isSelected())
				introMusic.close();
			});
		
		//설정 메뉴의 submenu 화면 설정 메뉴
		JMenu ScreenOption = new JMenu("화면 배경");
		SettingMenu.add(ScreenOption);
		JRadioButtonMenuItem White = new JRadioButtonMenuItem("White Background");
		JRadioButtonMenuItem Dark = new JRadioButtonMenuItem("Dark Background");
		ScreenOption.add(White); ScreenOption.add(Dark);
		White.setSelected(true);
		ButtonGroup ScreenBackgroundbtn = new ButtonGroup();	//버튼그룹
		ScreenBackgroundbtn.add(White); ScreenBackgroundbtn.add(Dark);
		White.addActionListener(e-> {
			WhitePanel_HD.setVisible(true); DarkPanel_HD.setVisible(false);
		});
		Dark.addActionListener(e-> {
			WhitePanel_HD.setVisible(false); DarkPanel_HD.setVisible(true);
		});

		
		
		//JAVA Swing에서 Web화면을 띄워주는 기능
		//Creating and running Chromium engine
        EngineOptions options =
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build();
        Engine engine = Engine.newInstance(options);
        Browser browser = engine.newBrowser();
        // Creating Swing component for rendering web content
        // loaded in the given Browser instance.
        BrowserView view = BrowserView.newInstance(browser);
        // Close Engine and onClose app window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                engine.close();
            }
        });
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        browser.navigation().loadUrl("https://www.google.com/maps/search/%EC%95%84%EC%A3%BC%EB%8C%80+%EB%A7%9B%EC%A7%91/@37.2784803,127.0421117,17z");
        JButton viewClose = new JButton("닫기");
        JButton toNaver = new JButton("네이버 지도");		//지도를 네이버로 변경
        JButton toGoogle = new JButton("구글 지도");		//구글로 지도 변경
        MapButtons.add(viewClose); MapButtons.add(toNaver); MapButtons.add(toGoogle);		//지도 조작 버튼들
        viewClose.addActionListener(e->{
				view.setVisible(false);
				MapControl.setVisible(false);
				StartButtonPanel.setVisible(true);
			});
        toNaver.addActionListener(e -> {
        	browser.navigation().loadUrl("https://map.naver.com/v5/search/%EC%95%84%EC%A3%BC%EB%8C%80%20%EB%A7%9B%EC%A7%91?c=14142156.8282098,4478530.2845567,15,0,0,0,dh");
        	toNaver.setVisible(false); toGoogle.setVisible(true);
        });
        toGoogle.addActionListener(e -> {
        	browser.navigation().loadUrl("https://www.google.com/maps/search/%EC%95%84%EC%A3%BC%EB%8C%80+%EB%A7%9B%EC%A7%91/@37.2784803,127.0421117,17z");
        	toGoogle.setVisible(false); toNaver.setVisible(true);
        });
        MapControl.setLayout(new FlowLayout(FlowLayout.LEFT));
        viewClose.setPreferredSize(new Dimension(100, 30));
        toNaver.setPreferredSize(new Dimension(200, 30));
        toGoogle.setPreferredSize(new Dimension(200, 30));
        MapControl.add(viewClose); MapControl.add(toNaver); MapControl.add(toGoogle);
        add(view, BorderLayout.CENTER);
        add(MapControl, BorderLayout.NORTH);
        view.setVisible(false);
		MapControl.setVisible(false);
		
		//시작화면 버튼
		JButton ShowMapButton = new JButton("지도 보기");
		JButton ShowSchoolMenuButton = new JButton("학교 식당");	//학교 식당 메뉴 보이게하는 버튼
		JButton SearchButton = new JButton("인터넷 검색");
		ShowSchoolMenuButton.addActionListener(e-> {
			browser.navigation().loadUrl("https://bellmir.github.io/PersonalProject/LunchMenu.html");		//깃허브와 html iframe기능 이용하여 식당 정보 제공
			MapControl.setVisible(true); toNaver.setVisible(false); toGoogle.setVisible(false);
			view.setVisible(true);
			StartButtonPanel.setVisible(false);
		});
		ShowMapButton.addActionListener(e-> {
				browser.navigation().loadUrl("https://www.google.com/maps/search/%EC%95%84%EC%A3%BC%EB%8C%80+%EB%A7%9B%EC%A7%91/@37.2784803,127.0421117,17z");
				MapControl.setVisible(true);
				toNaver.setVisible(true); toGoogle.setVisible(false);
				view.setVisible(true);
				StartButtonPanel.setVisible(false);
			});
		SearchButton.addActionListener(e-> {
			browser.navigation().loadUrl("https://google.com");
			MapControl.setVisible(true); toNaver.setVisible(false); toGoogle.setVisible(false);
			view.setVisible(true);
			StartButtonPanel.setVisible(false);
		});
		StartButtonPanel.add(ShowMapButton);
		ShowMapButton.setPreferredSize(new Dimension(150, 65));
		StartButtonPanel.add(ShowSchoolMenuButton);
		ShowSchoolMenuButton.setPreferredSize(new Dimension(150, 65));
		StartButtonPanel.add(SearchButton);
		SearchButton.setPreferredSize(new Dimension(150, 65));
		add(StartButtonPanel, BorderLayout.WEST);
	}
}

class StartBackground extends JComponent{
	private Image img;
	StartBackground(Image img){
		this.img=img;
		setSize(Main.HD_WIDTH,Main.HD_HEIGHT);
		setLayout(null);
	}
	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
