package todayMeal;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main {
	
	//HD 화면 크기
	public static final int HD_WIDTH = 1280;
	public static final int HD_HEIGHT = 720;
	
	public static void main(String[] args) {
	
	System.setProperty("jxbrowser.license.key", "1BNDHFSC1FXNSK22F0XXS2G8ONBL211A1GI4QVNS2Z3LZELHNE8K1P73R0FQIKPKQQEQA8");		//체험판 키(30일)
		
	EventQueue.invokeLater(()->
		{
			JFrame StartFrame = new Start();
			StartFrame.setVisible(true);		//창 보이게함
			StartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//창 끄면 프로그램 꺼지도록
		});
	
	}

}
