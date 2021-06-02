package taxiStop;

import java.sql.*;
import java.util.Scanner;

public class Main {
	
	static String url = "jdbc:postgresql://localhost:5432/DBClass";
	static String user = "postgres";
	static String password = "";		//동작전 추가할것
	
	public static void main(String[] args) throws SQLException {
		
		Scanner scan = new Scanner(System.in);
		
		Connection conn=null;
		Statement st=null;
		
		String createTables = "create table Address(pID int, city varchar(10), pAddr varchar(50), primary key(pID));"
				+ "create table Location(pID int, latitude numeric(10,8), longitude numeric(10,7), primary key(pID), foreign key(pID) references Address(pID));"
				+ "create table TaxiFare(city varchar(20), taxiType varchar(20), perTime int, perDistance int, extraCharge varchar(20), primary key(city, taxiType));";
		String DeleteTables = "drop Table Address cascade;" + "drop Table Location cascade;" + "drop Table TaxiFare cascade;";
		
		String insertTuples_Address = "insert into Address values (001, '수원시', '경기도 수원시 권선구 고색동 45-19, 신병원');"
				+ "insert into Address values (002, '수원시', '경기도 수원시 권선구 곡반정동 516-2, 곡반중학교');" + "insert into Address values (003, '수원시', '경기도 수원시 권선구 곡반정동 558-6');"
				+ "insert into Address values (004, '수원시', '경기도 수원시 권선구 곡선동 상록아파트 512동 후문');" + "insert into Address values (005, '수원시', '경기도 수원시 권선구 곡선동하고렴사거리');"
				+ "insert into Address values (006, '수원시', '경기도 수원시 권선구 곡선동한솔아파트 108동');" + "insert into Address values (007, '수원시', '경기도 수원시 권선구 구운동 463, 강남아파트');"
				+ "insert into Address values (008, '수원시', '경기도 수원시 권선구 곡선동 925, 이마트 서수원점(서수원터미널)');" + "insert into Address values (009, '수원시', '경기도 수원시 권선구 구운동507-1, 웃거리 지하도');"
				+ "insert into Address values (010, '수원시', '경기도 수원시 권선구 권선동 1330');" + "insert into Address values (011, '수원시', '경기도 수원시 권선구 권선동 1051, 권선종합상가');"
				+ "insert into Address values (012, '수원시', '경기도 수원시 권선구 권선동1090,세계로중앙병원');" + "insert into Address values (013, '수원시', '경기도 수원시 권선구 권선동 1189, 이마트 정문');"
				+ "insert into Address values (014, '수원시', '경기도 수원시 권선구 권선동 1201, 삼성아파트 입구');" + "insert into Address values (015, '수원시', '경기도 수원시 권선구 권선동1296-5,롯데마트');"
				+ "insert into Address values (016, '수원시', '경기도 수원시 권선구 금곡동 1085-1, 비즈웍스상가');" + "insert into Address values (017, '수원시', '경기도 수원시 권선구 금곡동 79, 삼익3차 아파트 입구');"
				+ "insert into Address values (018, '수원시', '경기도 수원시 권선구 금곡동160,서수원홈플러스');" + "insert into Address values (019, '수원시', '경기도 수원시 권선구 금곡동 1081, 호매실휴먼시아 521동(상촌성당 맞은편)');"
				+ "insert into Address values (020, '수원시', '경기도 수원시 권선구 금곡동 530, 엘지빌리지 202동 후문');" + "insert into Address values (021, '수원시', '경기도 수원시 권선구 당수동 159-5, 가락공판장');"
				+ "insert into Address values (022, '수원시', '경기도 수원시 권선구 서둔동 296-97(롯데몰 맞은편)');" + "insert into Address values (023, '수원시', '경기도 수원시 권선구 세류동 1147, 농협 남수원지점(미영아파트)');"
				+ "insert into Address values (024, '수원시', '경기도 수원시 권선구 오목천동 946 주공아파트 102동');" + "insert into Address values (025, '수원시', '경기도 수원시 권선구 평동 23-28, 공구유통타운 버스승강장');"
				+ "insert into Address values (026, '수원시', '경기도 수원시 권선구 호매실동 377, GS아파트 상가');" + "insert into Address values (027, '수원시', '경기도 수원시 영통구 망포동 541-11,중앙프라자');"
				+ "insert into Address values (028, '수원시', '경기도 수원시 영통구 망포동 망포역 4번출구');" + "insert into Address values (029, '수원시', '경기도 수원시 영통구 망포동 558-2, 하나은행 신영통지점');"
				+ "insert into Address values (030, '수원시', '경기도 수원시 영통구 매탄2동 111,KT플라자');" + "insert into Address values (031, '수원시', '경기도 수원시 영통구 매탄2동 1199,원천성일201동');"
				+ "insert into Address values (032, '수원시', '경기도 수원시 영통구 매탄3동 990, 위브하늘채102동');" + "insert into Address values (033, '수원시', '경기도 수원시 영통구 매탄3동 1235-1, 코닥사진관');"
				+ "insert into Address values (034, '수원시', '경기도 수원시 영통구 매탄3동 1267-5, 밀래니엄프라자(영통구청)');" + "insert into Address values (035, '수원시', '경기도 수원시 영통구 매탄3동 1329, 영통구청 맞은편');"
				+ "insert into Address values (036, '수원시', '경기도 수원시 영통구 매탄동 310, 한울림프라자 (매원고등학교)');" + "insert into Address values (037, '수원시', '경기도 수원시 영통구 매탄동 474-6, 현진빌딩(삼성전자)');"
				+ "insert into Address values (038, '수원시', '경기도 수원시 영통구 매탄동 560, 삼성전자 남문');" + "insert into Address values (039, '수원시', '경기도 수원시 영통구 영통동 998-8, 키넥스GS25');"
				+ "insert into Address values (040, '수원시', '경기도 수원시 영통구 영통동 1010-13, 키넥스CU마트');" + "insert into Address values (041, '수원시', '경기도 수원시 영통구 영통동 1024-12, 경희대입구 맞은편(파리바게트)');"
				+ "insert into Address values (042, '수원시', '경기도 수원시 영통구 영통동 945, 황골육교(한빛정형외과)');" + "insert into Address values (043, '수원시', '경기도 수원시 영통구 영통동 1053-1, 벽산아파트 223동');"
				+ "insert into Address values (044, '수원시', '경기도 수원시 영통구 영통동 1053-1, 벽산아파트 223동');" + "insert into Address values (045, '수원시', '경기도 수원시 영통구 영통동 1060, 롯데마트영통점');"
				+ "insert into Address values (046, '수원시', '경기도 수원시 영통구 영통동 1081, 수원출입국사무소');" + "insert into Address values (047, '수원시', '경기도 수원시 영통구 영통동 955-4, 하나은행 영통지점');"
				+ "insert into Address values (048, '수원시', '경기도 수원시 영통구 영통동 958-1, 마사회(느티나무사거리)');" + "insert into Address values (049, '수원시', '경기도 수원시 영통구 원천동 177-1, 홈플러스');"
				+ "insert into Address values (050, '수원시', '경기도 수원시 영통구 매탄동 1231-2 공원 앞');" + "insert into Address values (051, '수원시', '경기도 수원시 영통구 하동 956, 휴먼시아32단지');"
				+ "insert into Address values (052, '수원시', '경기도 수원시 영통구 이의동 광교중앙역 1번출구');" + "insert into Address values (053, '수원시', '경기도 수원시 장안구 연무동 260-20, 다인병원');"
				+ "insert into Address values (054, '수원시', '경기도 수원시 장안구 영화동 285-9,신한은행');" + "insert into Address values (055, '수원시', '경기도 수원시 장안구 영화동 285-8, 신한은행');"
				+ "insert into Address values (056, '수원시', '경기도 수원시 장안구 영화동 311-14, 신한은행 맞은편(스페인안경점)');" + "insert into Address values (057, '수원시', '경기도 수원시 장안구 영화동 308-1, 동성아울렛');"
				+ "insert into Address values (058, '수원시', '경기도 수원시 장안구 천천동 558-4');" + "insert into Address values (059, '수원시', '경기도 수원시 장안구 율전동 285-6, 율전고가차도 밑');"
				+ "insert into Address values (060, '수원시', '경기도 수원시 장안구 정자동 874-6, 국민은행 북수원지점');" + "insert into Address values (061, '수원시', '경기도 수원시 장안구 정자동 435-1, 크로커다일 파장동점');"
				+ "insert into Address values (062, '수원시', '경기도 수원시 장안구 정자동879-1,정자3동주민센터');" + "insert into Address values (063, '수원시', '경기도 수원시 장안구 정자동 890, 북수원지식정보 도서관');"
				+ "insert into Address values (064, '수원시', '경기도 수원시 장안구 정자동 877-8, 화이트쇼핑몰(정자3동주민센터 맞은편)');" + "insert into Address values (065, '수원시', '경기도 수원시 장안구 정자동 877-8, 화이트쇼핑몰(정자3동주민센터 맞은편)');"
				+ "insert into Address values (066, '수원시', '경기도 수원시 장안구 정자동 945, SK스카이뷰아파트');" + "insert into Address values (067, '수원시', '경기도 수원시 장안구 정자동 945, SK스카이뷰아파트 후문');"
				+ "insert into Address values (068, '수원시', '경기도 수원시 장안구 정자동 395-3, 동신아파트 203동');" + "insert into Address values (069, '수원시', '경기도 수원시 장안구 원동25,장안구청');"
				+ "insert into Address values (070, '수원시', '경기도 수원시 장안구 조원동 855, 조원주공아파트 108동');" + "insert into Address values (071, '수원시', '경기도 수원시 장안구 천천동 334-2, 푸르지오 종합상가');"
				+ "insert into Address values (072, '수원시', '경기도 수원시 팔달구 고등동 208-1, 택시쉼터(해병전우회)');" + "insert into Address values (073, '수원시', '경기도 수원시 팔달구 수동11-814,택시쉼터');"
				+ "insert into Address values (074, '수원시', '경기도 수원시 팔달구 매산로1가 18, 수원역 육교');" + "insert into Address values (075, '수원시', '경기도 수원시 팔달구 북수동187-2,국제열쇠');"
				+ "insert into Address values (076, '수원시', '경기도 수원시 팔달구 우만동 72-1, 아주공원 솔밭화장실');" + "insert into Address values (077, '수원시', '경기도 수원시 팔달구 우만동145-3,호텔캐슬 옆');"
				+ "insert into Address values (078, '수원시', '경기도 수원시 팔달구 우만동604,수원구치소');" + "insert into Address values (079, '수원시', '경기도 수원시 팔달구 우만동 600, 월드메르디앙 후문(농협 맞은편)');"
				+ "insert into Address values (080, '수원시', '경기도 수원시 팔달구 인계동489-7,KBS드라마센터');" + "insert into Address values (081, '수원시', '경기도 수원시 팔달구 인계동 755, 강한의원 부근');"
				+ "insert into Address values (082, '수원시', '경기도 수원시 팔달구 인계동 755, 강한의원 부근');" + "insert into Address values (083, '수원시', '경기도 수원시 팔달구 인계동 1125-1, 갤러리아 백화점');"
				+ "insert into Address values (084, '수원시', '경기도 수원시 팔달구 인계동 1113, 씨네파크(나혜석거리 맞은편)');" + "insert into Address values (085, '수원시', '경기도 수원시 팔달구 경수대로 560');"
				+ "insert into Address values (086, '수원시', '경기도 수원시 팔달구 인계동 1120-11, 나혜석거리 입구');" + "insert into Address values (087, '수원시', '경기도 수원시 팔달구 인계동 1122-9, 경기문화예술의전당 서문(국민연금)');"
				+ "insert into Address values (088, '수원시', '경기도 수원시 팔달구 인계동 1132-12, IBIS 호텔');" + "insert into Address values (089, '수원시', '경기도 수원시 팔달구 인계동 1125-1, 갤러리아 백화점');"
				+ "insert into Address values (090, '수원시', '경기도 수원시 팔달구 인계동 994-5, 윌스병원 앞');" + "insert into Address values (091, '수원시', '경기도 수원시팔달구 팔달로2가18-3,조한약방');"
				+ "insert into Address values (092, '수원시', '경기도 수원시 팔달구 화서2동646,화서주공아파트308동');" + "insert into Address values (093, '수원시', '경기도 수원시 팔달구 화서2동 727, 주안교회 (율전중학교)');"
				+ "insert into Address values (094, '수원시', '경기도 수원시 팔달구 팔달로2가 112-2, 남문 택시승강장');" + "insert into Address values (095, '수원시', '경기도 수원시 권선구 권선동 1240, 현대아파트 상가');"
				+ "insert into Address values (096, '수원시', '경기도 수원시 권선구 권선동 1218, 수원버스터미널');" + "insert into Address values (097, '수원시', '경기도 수원시 권선구 서둔동 296-3, 롯데몰');"
				+ "insert into Address values (098, '수원시', '경기도 수원시 권선구 장지동, 세류역');" + "insert into Address values (099, '수원시', '경기도 수원시 권선구 수원역환승센터 1층');"
				+ "insert into Address values (100, '수원시', '경기도 수원시 영통구 매탄동 457-15, 삼성전자 연구단지');";
		
		String insertTuples_Location = "insert into Location values (001, 37.25134434, 126.9837192);"
				+ "insert into Location values (002, 37.24434997, 127.0330571);" + "insert into Location values (003, 37.23916605, 127.0294265);"
				+ "insert into Location values (004, 37.25065271, 127.0379928);" + "insert into Location values (005, 37.23776919, 127.0283061);"
				+ "insert into Location values (006, 37.23714971, 127.0285845);" + "insert into Location values (007, 37.28202415, 126.9761353);"
				+ "insert into Location values (008, 37.23776919, 127.0283061);" + "insert into Location values (009, 37.27668307, 126.9784966);"
				+ "insert into Location values (010, 37.25489567, 127.0257079);" + "insert into Location values (011, 37.25610284, 127.0245763);"
				+ "insert into Location values (012, 37.26018956, 127.0229679);" + "insert into Location values (013, 37.25010971, 127.0200995);"
				+ "insert into Location values (014, 37.25148659, 127.0220657);" + "insert into Location values (015, 37.25025646, 127.0346951);"
				+ "insert into Location values (016, 37.27349614, 126.941849);" + "insert into Location values (017, 37.27735111, 126.9548641);"
				+ "insert into Location values (018, 37.27415066, 126.9546828);" + "insert into Location values (019, 37.27376574, 126.9387445);"
				+ "insert into Location values (020, 37.27144045, 126.9376834);" + "insert into Location values (021, 37.29164381, 126.9408283);"
				+ "insert into Location values (022, 37.26524618, 126.9961825);" + "insert into Location values (023, 37.24746842, 127.0145317);"
				+ "insert into Location values (024, 37.24047598, 126.9648289);" + "insert into Location values (025, 37.25848625, 126.9970387);"
				+ "insert into Location values (026, 37.26839885, 126.9584491);" + "insert into Location values (027, 37.23863276, 127.0571473);"
				+ "insert into Location values (028, 37.24136644, 127.0530445);" + "insert into Location values (029, 37.23834314, 127.0558548);"
				+ "insert into Location values (030, 37.27303318, 127.0510813);" + "insert into Location values (031, 37.27002664, 127.0571173);"
				+ "insert into Location values (032, 37.26231799, 127.0397387);" + "insert into Location values (033, 37.26383613, 127.0458526);"
				+ "insert into Location values (034, 37.25911409, 127.0446945);" + "insert into Location values (035, 37.25959722, 127.0465034);"
				+ "insert into Location values (036, 37.26569938, 127.0546006);" + "insert into Location values (037, 37.25619332, 127.0557902);"
				+ "insert into Location values (038, 37.2546747, 127.0574549);" + "insert into Location values (039, 37.25294992, 127.0752983);"
				+ "insert into Location values (040, 37.25220305, 127.0754314);" + "insert into Location values (041, 37.24794308, 127.0780308);"
				+ "insert into Location values (042, 37.26745074, 127.0813788);" + "insert into Location values (043, 37.2654447, 127.0829815);"
				+ "insert into Location values (044, 37.2654447, 127.0829815);" + "insert into Location values (045, 37.25274128, 127.0716598);"
				+ "insert into Location values (046, 37.25175984, 127.0763725);" + "insert into Location values (047, 37.26322965, 127.0785438);"
				+ "insert into Location values (048, 37.25593714, 127.074732);" + "insert into Location values (049, 37.27006909, 127.0636112);"
				+ "insert into Location values (050, 37.26629837, 127.0574005);" + "insert into Location values (051, 37.2946029, 127.0655296);"
				+ "insert into Location values (052, 37.29820293, 127.0480022);" + "insert into Location values (053, 37.29191828, 127.0275208);"
				+ "insert into Location values (054, 37.2908886, 127.0130529);" + "insert into Location values (055, 37.2906031, 127.0133949);"
				+ "insert into Location values (056, 37.29012144, 127.012905);" + "insert into Location values (057, 37.2914991, 127.0117028);"
				+ "insert into Location values (058, 37.30208546, 126.9733822);" + "insert into Location values (059, 37.29897007, 126.9715747);"
				+ "insert into Location values (060, 37.2961444, 126.9951184);" + "insert into Location values (061, 37.30602281, 126.9922348);"
				+ "insert into Location values (062, 37.29513723, 126.9911166);" + "insert into Location values (063, 37.29809887, 126.9907725);"
				+ "insert into Location values (064, 37.29562435, 126.9932982);" + "insert into Location values (065, 37.29562435, 126.9932982);"
				+ "insert into Location values (066, 37.30940025, 126.983605);" + "insert into Location values (067, 37.30940025, 126.983605);"
				+ "insert into Location values (068, 37.30437536, 126.993475);" + "insert into Location values (069, 37.3040757, 127.0103202);"
				+ "insert into Location values (070, 37.30426212, 127.0164767);" + "insert into Location values (071, 37.29171106, 126.9824441);"
				+ "insert into Location values (072, 37.27444573, 126.9961169);" + "insert into Location values (073, 37.28330913, 127.021587);"
				+ "insert into Location values (074, 37.26567577, 127.0001003);" + "insert into Location values (075, 37.28464125, 127.0159649);"
				+ "insert into Location values (076, 37.27767559, 127.0434567);" + "insert into Location values (077, 37.27638801, 127.0391204);"
				+ "insert into Location values (078, 37.28059828, 127.0327408);" + "insert into Location values (079, 37.28033043, 127.036364);"
				+ "insert into Location values (080, 37.26793826, 127.0305686);" + "insert into Location values (081, 37.27518663, 127.0211394);"
				+ "insert into Location values (082, 37.27518663, 127.0211394);" + "insert into Location values (083, 37.26098672, 127.0322698);"
				+ "insert into Location values (084, 37.26374368, 127.0322233);" + "insert into Location values (085, 37.27563386, 127.0306054);"
				+ "insert into Location values (086, 37.26395671, 127.0329308);" + "insert into Location values (087, 37.261842, 127.0354944);"
				+ "insert into Location values (088, 37.25922177, 127.0313115);" + "insert into Location values (089, 37.26098672, 127.0322698);"
				+ "insert into Location values (090, 37.26531991, 127.02554);" + "insert into Location values (091, 37.27879249, 127.0169656);"
				+ "insert into Location values (092, 37.28620132, 126.99145);" + "insert into Location values (093, 37.28582719, 126.9814181);"
				+ "insert into Location values (094, 37.27803746, 127.0161072);" + "insert into Location values (095, 37.25343506, 127.0377433);"
				+ "insert into Location values (096, 37.25010971, 127.0200995);" + "insert into Location values (097, 37.26287534, 126.9960674);"
				+ "insert into Location values (098, 37.23579465, 127.00427);" + "insert into Location values (099, 37.27598814, 127.009154);"
				+ "insert into Location values (100, 37.25764092, 127.0531342);";
		
		String insertTuples_TaxiFare = "insert into TaxiFare values ('서울', '중형택시', 31, 132, null);" + "insert into TaxiFare values ('서울', '대형택시', 36, 151, null);"
				+ "insert into TaxiFare values ('부산', '중형택시', 34, 133, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('부산', '대형택시', 34, 141, null);"
				+ "insert into TaxiFare values ('대구', '중형택시', 32, 134, '택시 요금의 40% 할증');" + "insert into TaxiFare values ('대구', '대형택시', 27, 114, '택시 요금의 20% 할증');"
				+ "insert into TaxiFare values ('인천', '중형택시', 33, 135, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('인천', '대형택시', 36, 151, null);"
				+ "insert into TaxiFare values ('광주', '중형택시', 32, 134, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('광주', '대형택시', 36, 156, null);"
				+ "insert into TaxiFare values ('대전', '중형택시', 34, 133, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('대전', '대형택시', 27, 111, null);"
				+ "insert into TaxiFare values ('울산', '중형택시', 30, 125, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('경기', '대형택시', 36, 148, null);"
				+ "insert into TaxiFare values ('강원', '중형택시', 33, 133, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('강원', '대형택시', 28, 115, null);"
				+ "insert into TaxiFare values ('충북', '중형택시', 34, 137, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('충북', '대형택시', 36, 138, null);"
				+ "insert into TaxiFare values ('충남', '중형택시', 37, 131, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('충남', '대형택시', 27, 109, null);"
				+ "insert into TaxiFare values ('전북', '중형택시', 37, 133, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('전북', '대형택시', 40, 168, null);"
				+ "insert into TaxiFare values ('전남', '중형택시', 32, 134, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('전남', '대형택시', 25, 106, null);"
				+ "insert into TaxiFare values ('경북', '중형택시', 33, 134, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('경북', '대형택시', 33, 138, null);"
				+ "insert into TaxiFare values ('경남', '중형택시', 34, 133, null);" + "insert into TaxiFare values ('경남', '대형택시', 38, 160, null);"
				+ "insert into TaxiFare values ('제주', '중형택시', 30, 126, '택시 요금의 20% 할증');" + "insert into TaxiFare values ('제주', '대형택시', 33, 133, '택시 요금의 20% 할증');";
		
		
		
		int select=0;
		
		try {
			System.out.println("------------------------TaxiStop을 실행합니다.------------------------");
			// JDBC를 이용해 PostgreSQL 서버 및 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);
			if (conn!=null) {
				System.out.println("Connection OK");
				st=conn.createStatement();
			}
			
			// 3개 테이블 생성
			st.executeUpdate(createTables);
			// 3개 테이블에 튜플 생성
			st.executeUpdate(insertTuples_Address);
			st.executeUpdate(insertTuples_Location);
			st.executeUpdate(insertTuples_TaxiFare);
			
			SearchAddr sa= new SearchAddr(conn);
			SearchLoc sl = new SearchLoc(conn);
			TaxiFare tf = new TaxiFare(conn);
			
			while(select!=4) {
				System.out.println("<기능을 선택해 주세요>");
				System.out.println("1.주소 기반 검색 / 2.위치 기반 검색 / 3.택시 요금 검색 / 4.종료");
				select= scan.nextInt();
				
				switch(select) {
					case 1:
						sa.start();
						break;
						
					case 2:
						sl.start();
						break;
						
					case 3:
						tf.start();
						break;
					
					case 4:
						System.out.println("------------------------TaxiStop을 종료합니다.------------------------");
						st.executeUpdate(DeleteTables);
						break;
						
					default:
						System.out.println("1~4까지 입력해 주세요");
						break;
				}
				
			}
			
		} catch(SQLException ex) {
			System.out.println("<오류가 발생하였습니다.>");
			System.out.println("------------------------TaxiStop을 종료합니다.------------------------");
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