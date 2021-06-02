package assignment2;

import java.util.*;
import java.sql.*;

public class SqlTest2 {
	
	public static void main(String[] args) throws SQLException {
		
		Scanner scan = new Scanner(System.in);
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		int isContinue = 0;
		
		String cname, state, sname, major, decision;
		int enroll, sid, sizehs;
		float gpa, mingpa, maxgpa;
		String major1, major2;
		
		String url = "jdbc:postgresql://localhost:5432/assignment2";
        String user = "postgres";
        String password = "";		//동작 전 추가할 것
        
        String createTables = "create table College(cName varchar(20), state char(2), enrollment int);"
        		+ "create table Student(sID int, sName varchar(20), GPA numeric(2,1), sizeHS int);"
        		+ "create table Apply(sID int, cName varchar(20), major varchar(20), decision char);";
        String insertTuples = "insert into College values ('Stanford', 'CA', 15000);" + "insert into College values ('Berkeley', 'CA', 36000);"
        		+ "insert into College values ('MIT', 'MA', 10000);" + "insert into College values ('Cornell', 'NY', 21000);"
        		+ "insert into Student values (123, 'Amy', 3.9, 1000);" + "insert into Student values (234, 'Bob', 3.6, 1500);"
        		+ "insert into Student values (345, 'Craig', 3.5, 500);" + "insert into Student values (456, 'Doris', 3.9, 1000);"
        		+ "insert into Student values (567, 'Edward', 2.9, 2000);" + "insert into Student values (678, 'Fay', 3.8, 200);"
        		+ "insert into Student values (789, 'Gary', 3.4, 800);" + "insert into Student values (987, 'Helen', 3.7, 800);"
        		+ "insert into Student values (876, 'Irene', 3.9, 400);" + "insert into Student values (765, 'Jay', 2.9, 1500);"
        		+ "insert into Student values (654, 'Amy', 3.9, 1000);" + "insert into Student values (543, 'Craig', 3.4, 2000);"
        		+ "insert into Apply values (123, 'Stanford', 'CS', 'Y');" + "insert into Apply values (123, 'Stanford', 'EE', 'N');"
        		+ "insert into Apply values (123, 'Berkeley', 'CS', 'Y');" + "insert into Apply values (123, 'Cornell', 'EE', 'Y');"
        		+ "insert into Apply values (234, 'Berkeley', 'biology', 'N');" + "insert into Apply values (345, 'MIT', 'bioengineering', 'Y');"
        		+ "insert into Apply values (345, 'Cornell', 'bioengineering', 'N');" + "insert into Apply values (345, 'Cornell', 'CS', 'Y');"
        		+ "insert into Apply values (345, 'Cornell', 'EE', 'N');" + "insert into Apply values (678, 'Stanford', 'history', 'Y');"
        		+ "insert into Apply values (987, 'Stanford', 'CS', 'Y');" + "insert into Apply values (987, 'Berkeley', 'CS', 'Y');"
        		+ "insert into Apply values (876, 'Stanford', 'CS', 'N');" + "insert into Apply values (876, 'MIT', 'biology', 'Y');"
        		+ "insert into Apply values (876, 'MIT', 'marine biology', 'N');" + "insert into Apply values (765, 'Stanford', 'history', 'Y');"
        		+ "insert into Apply values (765, 'Cornell', 'history', 'N');" + "insert into Apply values (765, 'Cornell', 'psychology', 'Y');"
        		+ "insert into Apply values (543, 'MIT', 'CS', 'N');";
		String Query1 = "select * from College;";
		String Query2 = "select * from Student;";
		String Query3 = "select * from Apply;";
		String Query4 = "select cName, major, min(GPA), max(GPA)"
				+ "from Student, Apply "
				+ "where Student.sID = Apply.sID "
				+ "group by cName, major "
				+ "having min(GPA) > 3.0 "
				+ "order by cName, major;";
		String Query5 ="select distinct cName "
				+ "from Apply A1 "
				+ "where 6 > (select count(*) from Apply A2 where A2.cName = A1.cName);";
		//(CS, EE는 사용자에게서 입력을 받아서 Query 처리).
		String Query6_1 = "select sID, sName "
				+ "from Student "
				+ "where sID = any (select sID from Apply where major = '";
		String Query6_2 ="') and not sID = any (select sID from Apply where major = '";
		String Query6_3 = "');";
		//(CS, Stanford는 사용자에게서 입력을 받아서 Query 처리).
		String Query7_1 = "select sName, GPA "
				+ "from Student natural join Apply "
				+ "where major = '";
		String Query7_2 = "' and cName = '";
		String Query7_3 = "';";
        		
		try {
			
			System.out.println("SQL Programming Test");
			System.out.println("Connecting PostgreSQL database");
			// JDBC를 이용해 PostgreSQL 서버 및 데이터베이스 연결
			
			conn = DriverManager.getConnection(url, user, password);
			if (conn!=null) {
				System.out.println("Connection OK");
				st = conn.createStatement();
			}
			
			System.out.println("Creating College, Student, Apply relations");
			// 3개 테이블 생성: Create table문 이용
			st.executeUpdate(createTables);
			
			System.out.println("Inserting tuples to College, Student, Apply relations");
			// 3개 테이블에 튜플 생성: Insert문 이용
			st.executeUpdate(insertTuples);
			
			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;
			
			System.out.println("---------------------------------------------------------------");
			System.out.println("Query 1");
			// Query 1을 실행: Select문 이용
			// Query 처리 결과는 적절한 Print문을 이용해 Display
			rs = st.executeQuery(Query1);
			while(rs.next()) {
				cname = rs.getString(1);
				state = rs.getString(2);
				enroll = rs.getInt(3);
				System.out.printf("%15s %15s %15d\n",cname, state, enroll);
			}
			System.out.println("---------------------------------------------------------------");
			
			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;
			
			// Query 2 ~ Query 5에 대해 Query 1과 같은 방식으로 실행: Select문 이용
			// Query 처리 결과는 적절한 Print문을 이용해 Display
			System.out.println("---------------------------------------------------------------");
			System.out.println("Query 2");
			rs = st.executeQuery(Query2);
			while(rs.next()) {
				sid = rs.getInt(1);
				sname = rs.getString(2);
				gpa = rs.getFloat(3);
				sizehs = rs.getInt(4);
				System.out.printf("%15d %15s %15.4f %15d\n",sid, sname, gpa, sizehs);
			}
			System.out.println("---------------------------------------------------------------");
			System.out.println("Query 3");
			rs = st.executeQuery(Query3);
			while(rs.next()) {
				sid = rs.getInt(1);
				cname = rs.getString(2);
				major = rs.getString(3);
				decision = rs.getString(4);
				System.out.printf("%15d %15s %15s %15s\n",sid, cname, major, decision);
			}
			System.out.println("---------------------------------------------------------------");
			System.out.println("Query 4");
			rs = st.executeQuery(Query4);
			while(rs.next()) {
				cname = rs.getString(1);
				major = rs.getString(2);
				mingpa = rs.getFloat(3);
				maxgpa = rs.getFloat(4);
				System.out.printf("%15s %15s %15.4f %15.4f\n", cname, major, mingpa, maxgpa);
			}
			System.out.println("---------------------------------------------------------------");
			System.out.println("Query 5");
			rs = st.executeQuery(Query5);
			while(rs.next()) {
				cname = rs.getString(1);
				System.out.printf("%15s\n",cname);
			}
			System.out.println("---------------------------------------------------------------");
			
			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;
			scan.nextLine();
			
			// Query 6을 실행: Select문 이용
			// 사용자에게 major1, major2 값으로 CS, EE 입력 받음
			// 입력 받은 값을 넣어 Query를 처리하고
			// 결과는 적절한 Print문을 이용해 Display
			System.out.println();
			System.out.println("major1를 입력하세요");
			major1 = scan.nextLine();
			System.out.println("major2를 입력하세요");
			major2 = scan.nextLine();
			System.out.println("---------------------------------------------------------------");
			System.out.println("Query 6 ("+major1+","+major2+")");
			rs = st.executeQuery(Query6_1+major1+Query6_2+major2+Query6_3);
			while(rs.next()) {
				sid = rs.getInt(1);
				sname = rs.getString(2);
				System.out.printf("%15d %15s\n", sid, sname);
			}
			System.out.println("---------------------------------------------------------------");
			
			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;
			scan.nextLine();
			
			
			// Query 7을 실행: Select문 이용
			// 사용자에게 major, cName 값으로 CS, Stanford 입력 받음
			// 입력 받은 값을 넣어 Query를 처리하고
			// 결과는 적절한 Print문을 이용해 Display
			System.out.println();
			System.out.println("major를 입력하세요");
			major = scan.nextLine();
			System.out.println("cName을 입력하세요");
			cname = scan.nextLine();
			System.out.println("---------------------------------------------------------------");
			System.out.println("Query 7 ("+major+","+cname+")");
			rs = st.executeQuery(Query7_1+major+Query7_2+cname+Query7_3);
			while(rs.next()) {
				sname = rs.getString(1);
				gpa = rs.getFloat(2);
				System.out.printf("%15s %15.4f\n", sname, gpa);
			}
			System.out.println("---------------------------------------------------------------");
			
		} catch(SQLException e) {
			if(conn==null)
				System.out.println("Connection Failed");
			throw e;
		} finally{
			if(conn!=null)
				conn.close();
			if(st!=null)
				st.close();
			if(rs!=null)
				rs.close();
			scan.close();
		}

	}
}
