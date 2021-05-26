package todayMeal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{
	
	private Player player;		//외부에서 추가로 불러온 JLayer1.01의 Library
	private boolean isLoop;		//곡이 한번만 재생되는지 아님 무한 반복인지
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	//생성자
	public Music(String name, boolean isLoop) {		//곡의 이름과, 무한반복인지를 파라미터로 받음
		try {
			this.isLoop=isLoop;
			file=new File(Main.class.getResource("../music/"+name).toURI());	//toURI로 해당 파일의 위치를 가져올수 있도록 함
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);		//해당 파일을 버퍼에 담아서 읽어올 수 있도록 한다.
			player = new Player(bis);		//player에 해당 버퍼를 담음
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void close() {		//언제든 음악을 끝내게 함
		isLoop = false;
		player.close();
		this.interrupt();		//해당 thread를 정지 시킴
	}
	
	@Override
	public void run() {			//thread를 상속받으면 무조건 사용해야하는 함수.
		try {
			do {
				player.play();	//곡을 play시킴
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);		//해당 파일을 버퍼에 담아서 읽어올 수 있도록 한다.
				player = new Player(bis);		//player에 해당 버퍼를 담음
			} while(isLoop);	//isLoop가 true면 무한반복시킴
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
