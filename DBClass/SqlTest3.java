package assignment3;

import java.sql.*;
import java.util.Scanner;

public class SqlTest3 {
	
	public static void main(String[] args) throws SQLException {
		
		Scanner scan = new Scanner(System.in);
		
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		int isContinue = 0;
		int tupleCount=0;
		
		float gpa;
		int sid, sizehs, enroll;
		String sname, cname, major, decision, state;
		
		String url = "jdbc:postgresql://localhost:5432/assignment3";
        String user = "postgres";
        String password = "";	//동작 전 추가할 것
        
        String createTables = "create table College(cName varchar(20), state varchar(20), enrollment int);"
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
		
        //5. Triggers_Demo1.pdf중 Page 14의 Trigger R2 생성: 아래의 “4. 참고 사항” 참조
        String TriggerR2 = "create or replace function TriggerR2_function() returns trigger as $$ begin "
				+ "delete from Apply where sId = Old.sID; return New; "
				+ "end; $$ language 'plpgsql'; "
				+ "create trigger R2 after delete on Student for each row execute procedure TriggerR2_function();";
        String Delete = "delete from Student where GPA < 3.5;";
        String Query1 = "select * from Student order by sID;";
		String Query2 = "select * from Apply order by sID, cName, major;";
		//5. Triggers_Demo1.pdf중 Page 20의 Trigger R4 생성
		String TriggerR4 = "create or replace function TriggerR4_function() returns trigger as $$ begin "
				+ "IF exists(select * from College where cName = New.cName) THEN return null; ELSE return New; END IF; "
				+ "end; $$ language 'plpgsql'; "
				+ "create trigger R4 before insert on College for each row execute procedure TriggerR4_function();";
		//Insert문 실행. insert into College values ('UCLA', 'CA', 20000); insert into College values ('MIT', 'hello', 10000);
		String Insert1 = "insert into College values ('UCLA', 'CA', 20000);" + "insert into College values ('MIT', 'hello', 10000);";
		String Query3 = "select * from College order by cName;";
		//2)의 table tuple 상태로 리셋
		String resetTables = "drop table Student;" + "drop table Apply;" + "drop table College";
		//3. ModifyingViewsTriggers.pdf중 Page 14의 View CSEE 생성
		String ViewCSEE = "create view CSEE as select sID, cName, major from Apply where (major = 'CS' or major = 'EE');";
		String Query4 = "select * from CSEE order by sID, cName, major;";
		//3. ModifyingViewsTriggers.pdf중 Page 21의 Trigger CSEEinsert 생성
		String TriggerCSEEinsert = "create or replace function TriggerCSEEinsert_function() returns trigger as $$ begin "
				+ "IF (New.major = 'CS' or New.major='EE') THEN insert into Apply values (New.sID, New.cName, New.major, null); END IF; return New; "
				+ "end; $$ language 'plpgsql'; "
				+ "create trigger CSEEinsert instead of insert on CSEE for each row "
				+ "execute procedure TriggerCSEEinsert_function();";
		//insert문 실행. insert into CSEE values (333, 'UCLA', 'biology'); ;
		String Insert2 = "insert into CSEE values (333, 'UCLA', 'biology');";
		String Query5 = Query4;
		String Query6 = Query2;
		//insert문 실행. insert into CSEE values (333, 'UCLA', 'CS');
		String Insert3 = "insert into CSEE values (333, 'UCLA', 'CS');";
		String Query7 = Query4;
		String Query8 = Query2;
		
		try {
			
			System.out.println("SQL Programming Test");
			
			// JDBC를 이용해 PostgreSQL 서버 및 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);
			if (conn!=null) {
				System.out.println("Connection OK");
				st = conn.createStatement();
			}
			
			// 3개 테이블 생성: Create table문 이용
			st.executeUpdate(createTables);
			// 3개 테이블에 튜플 생성: Insert문 이용
			st.executeUpdate(insertTuples);

			System.out.println("Trigger test 1");
			// Trigger R2 생성
			st.executeUpdate(TriggerR2);
			// Delete문 실행
			st.executeUpdate(Delete);
			
			// Query 1 실행하고 결과는 적절한 Print문을 이용해 Display
			rs=st.executeQuery(Query1);
			tupleCount=1;
			System.out.println("\n<Query1>");
			System.out.println("       sID    sName     GPA    sizeHS");
			System.out.println("---------------------------------------");
			while(rs.next()) {
				sid = rs.getInt(1);
				sname = rs.getString(2);
				gpa = rs.getFloat(3);
				sizehs =rs.getInt(4);
				System.out.printf("%2d %7d %8s %7.1f %9d\n", tupleCount, sid, sname, gpa, sizehs);
				tupleCount++;
			}
			
			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;
			
			// Query 2 실행하고 결과는 적절한 Print문을 이용해 Display
			rs=st.executeQuery(Query2);
			tupleCount=1;
			System.out.println("\n<Query2>");
			System.out.println("       sID      cName           major    decision");
			System.out.println("---------------------------------------------------");
			while(rs.next()) {
				sid = rs.getInt(1);
				cname = rs.getString(2);
				major = rs.getString(3);
				decision =rs.getString(4);
				System.out.printf("%2d %7d %10s %15s %10s\n", tupleCount, sid, cname, major, decision);
				tupleCount++;
			}
			
			
			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;
			
			System.out.println("Trigger test 2");
			// Trigger R4 생성
			st.executeUpdate(TriggerR4);
			// Insert문 실행
			st.executeUpdate(Insert1);
			
			// Query 3 실행하고 결과는 적절한 Print문을 이용해 Display
			rs=st.executeQuery(Query3);
			tupleCount=1;
			System.out.println("\n<Query3>");
			System.out.println("        cName    state  enrollment");
			System.out.println("-------------------------------------");
			while(rs.next()) {
				cname = rs.getString(1);
				state = rs.getString(2);
				enroll = rs.getInt(3);
				System.out.printf("%2d %10s %8s %11d\n", tupleCount, cname, state, enroll);
				tupleCount++;
			}
			
			
			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;
			
			//table 처음 상태로 리셋
			st.executeUpdate(resetTables);
			st.executeUpdate(createTables);
			st.executeUpdate(insertTuples);
			
			System.out.println("View test 1");
			// View CSEE 생성
			st.executeUpdate(ViewCSEE);
			
			// Query 4 실행하고 결과는 적절한 Print문을 이용해 Display
			rs=st.executeQuery(Query4);
			tupleCount=1;
			System.out.println("\n<Query4>");
			System.out.println("        sID       cName            major");
			System.out.println("------------------------------------------");
			while(rs.next()) {
				sid = rs.getInt(1);
				cname = rs.getString(2);
				major = rs.getString(3);
				System.out.printf("%2d %8d %11s %15s\n", tupleCount, sid, cname, major);
				tupleCount++;
			}
			
			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;
			
			System.out.println("View test 2");
			// Trigger CSEEinsert 생성
			st.executeUpdate(TriggerCSEEinsert);
			// Insert문 실행
			st.executeUpdate(Insert2);
			
			// Query 5 실행하고 결과는 적절한 Print문을 이용해 Display
			rs=st.executeQuery(Query5);
			tupleCount=1;
			System.out.println("\n<Query5>");
			System.out.println("        sID       cName            major");
			System.out.println("------------------------------------------");
			while(rs.next()) {
				sid = rs.getInt(1);
				cname = rs.getString(2);
				major = rs.getString(3);
				System.out.printf("%2d %8d %11s %15s\n", tupleCount, sid, cname, major);
				tupleCount++;
			}
			
			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;
			
			// Query 6 실행하고 결과는 적절한 Print문을 이용해 Display
			rs=st.executeQuery(Query6);
			tupleCount=1;
			System.out.println("\n<Query6>");
			System.out.println("       sID      cName           major    decision");
			System.out.println("---------------------------------------------------");
			while(rs.next()) {
				sid = rs.getInt(1);
				cname = rs.getString(2);
				major = rs.getString(3);
				decision =rs.getString(4);
				System.out.printf("%2d %7d %10s %15s %10s\n", tupleCount, sid, cname, major, decision);
				tupleCount++;
			}
			
			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;
			
			System.out.println("View test 3");
			// Insert문 실행
			st.executeUpdate(Insert3);
			
			// Query 7 실행하고 결과는 적절한 Print문을 이용해 Display
			rs=st.executeQuery(Query7);
			tupleCount=1;
			System.out.println("\n<Query7>");
			System.out.println("        sID       cName            major");
			System.out.println("------------------------------------------");
			while(rs.next()) {
				sid = rs.getInt(1);
				cname = rs.getString(2);
				major = rs.getString(3);
				System.out.printf("%2d %8d %11s %15s\n", tupleCount, sid, cname, major);
				tupleCount++;
			}

			System.out.println("Continue? (Enter 1 for continue)");
			isContinue=scan.nextInt();
			if(isContinue!=1)
				return;


			// Query 8 실행하고 결과는 적절한 Print문을 이용해 Display
			rs=st.executeQuery(Query8);
			tupleCount=1;
			System.out.println("\n<Query8>");
			System.out.println("       sID      cName           major    decision");
			System.out.println("---------------------------------------------------");
			while(rs.next()) {
				sid = rs.getInt(1);
				cname = rs.getString(2);
				major = rs.getString(3);
				decision =rs.getString(4);
				System.out.printf("%2d %7d %10s %15s %10s\n", tupleCount, sid, cname, major, decision);
				tupleCount++;
			}
			
		} catch(SQLException ex) {
			throw ex;
		} finally {
			if(conn!=null)
				conn.close();
			if(st!=null)
				st.close();
			scan.close();
		}
	}
}