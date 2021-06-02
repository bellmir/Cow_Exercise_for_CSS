package taxiStop;

import java.sql.*;
import java.util.Scanner;

public class SearchAddr {
	
	String user_city;
	String user_district;
	
	Statement st=null;
	ResultSet rs=null;
	
	Scanner scan = new Scanner(System.in);
	
	//constructor
	public SearchAddr(Connection conn) throws SQLException {
		st = conn.createStatement();
	}
	
	public void start() {
		int isRepeat=0;
		
		while(isRepeat!=1) {
			System.out.println("<도로명/지번 주소 기반 검색입니다.> ex)경기도 수원시 권선구 매송고색로 724");
			System.out.println("시 이름을 입력해 주세요");
			user_city=scan.nextLine();
			System.out.println("구 이름을 입력해 주세요");
			user_district=scan.nextLine();
			showAddr(user_city, user_district);
			
			System.out.println("\n<종료할까요?>\n1.종료, 2.재검색");
			isRepeat=scan.nextInt();
			scan.nextLine();
		}
	}
	
	public void showAddr(String user_city, String user_district){
		String Query = "select pAddr from Address where city='"+ user_city+ "' and pAddr like '%"+ user_district +"%';";
		int tupleCount=0;
		String pAddr;
		
		try {
			rs = st.executeQuery(Query);
			System.out.println("\n--------------------------------------------------");
			while(rs.next()) {
				tupleCount++;
				pAddr=rs.getString(1);
				System.out.printf("[%2d] : %s\n", tupleCount, pAddr);
			}
			if(tupleCount==0) {
				System.out.println("입력하신 시/구에 해당하는 택시 승강장이 없습니다.");
			}
			System.out.println("--------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
