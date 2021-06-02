package taxiStop;

import java.sql.*;
import java.util.Scanner;

public class TaxiFare {
	
	Statement st=null;
	ResultSet rs=null;
	
	Scanner scan = new Scanner(System.in);
	
	//constructor
	public TaxiFare(Connection conn) throws SQLException {
		st = conn.createStatement();
	}
	
	public void start() {
		int isRepeat=0;
		String city;
		String taxiType;
		
		while(isRepeat!=1) 
		{
			System.out.println("<택시요금 검색입니다.> ex)서울, 중형택시");
			System.out.println("시 이름을 입력해 주세요");
			city=scan.nextLine();
			System.out.println("택시 종류를 입력해 주세요");
			taxiType=scan.nextLine();
			taxiFare(city, taxiType);
			
			System.out.println("\n<종료할까요?>\n1.종료, 2.재검색");
			isRepeat=scan.nextInt();
			scan.nextLine();
		}
	}
	
	public void taxiFare(String city, String taxiType){
		String Query = "select * from TaxiFare where city ='" + city + "' and taxiType = '" + taxiType + "';";

		String extraCharge;
		int perTime, perDistance;
		
		try {
			rs = st.executeQuery(Query);
			
			System.out.println("\n---------------------------------------------------------------------------------------------");
			System.out.printf("%7s %17s %16s %16s %28s\n", "city", "taxiType", "m당 100원", "초당 100원", "심야할증");
			while(rs.next()) {
				city = rs.getString(1);
				taxiType = rs.getString(2);
				perDistance = rs.getInt(3);
				perTime = rs.getInt(4);
				extraCharge = rs.getString(5);
				System.out.printf("%6s %15s %15dm %15d초 %30s\n", city, taxiType, perTime, perDistance, extraCharge);
			}
			System.out.println("---------------------------------------------------------------------------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
