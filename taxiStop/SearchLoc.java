package taxiStop;

import java.sql.*;
import java.util.Scanner;

public class SearchLoc {
	
	double userLat,userLong;
	double howClose = 0;
	
	Statement st=null;
	ResultSet rs=null;
	
	Scanner scan = new Scanner(System.in);
	
	//constructor
	public SearchLoc(Connection conn) throws SQLException {
		st = conn.createStatement();
	}
	
	public void start() {
		int isRepeat = 0;
		while(isRepeat!=1) {
			System.out.println("<위치 좌표 기반 검색입니다.> ex)37.25134434, 126.9837192");
			System.out.println("위도를 입력해주세요");
			userLat=scan.nextFloat();
			System.out.println("경도를 입력해주세요");
			userLong=scan.nextFloat();
			System.out.println("택시 승강장과의 최대 거리를 입력해주세요 (m단위)");
			howClose=scan.nextFloat();
			showLoc(userLat,userLong,howClose);
			
			System.out.println("\n<종료할까요?>\n1.종료, 2.재검색");
			isRepeat=scan.nextInt();
			scan.nextLine();
		}
	}
	
	private static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
         
        //마일 단위에서 미터 단위로 변환
        dist = dist * 1609.344;
 
        return (dist);
    }
		// 각도를 라디안으로 바꿈
		private static double deg2rad(double deg) {
	        return (deg * Math.PI / 180.0);
	    }
	    
	    // 라디안을 각도로 바꿈
	    private static double rad2deg(double rad) {
	        return (rad * 180 / Math.PI);
	    }
	
	
	public void showLoc(double userLat, double userLong, double howClose){
		String Query = "select pAddr, latitude, longitude from Address, Location where Address.pID=Location.pID;";
		int tupleCount=0;
		double DBLat,DBLong;
		String pAddr;
		
		try {
			rs = st.executeQuery(Query);
			
			System.out.println("\n------------------------------------------------------------------------------------------------------");
			System.out.printf("%16s %44s %19s %13s\n", "거리(m)", "주소", "위도", "경도");
			while(rs.next()) {
				pAddr = rs.getString(1);
				DBLat = rs.getDouble(2);
				DBLong = rs.getDouble(3);
				double distance = distance(userLat, userLong, DBLat, DBLong);
				if (distance <= howClose) {
					tupleCount++;
					System.out.printf("[%3d] %10.2f %50s %13f %13f\n",tupleCount, distance,  pAddr, DBLat, DBLong);
				}
			}
			if(tupleCount==0) {
				System.out.println("입력하신 위치에 해당하는 택시 승강장이 없습니다.");
			}
			System.out.println("------------------------------------------------------------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	
