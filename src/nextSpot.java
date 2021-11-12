import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;


class Threat{ //둘 경우 아래의 threat를 형성 가능
	ArrayList<Spot[][]> block_win = new ArrayList<Spot[][]>(); //2개 1개 상관 없다. 상대가 무조건 막아야 하는 승리를 막기위한 자리
	ArrayList<Spot[][]> block_th2 = new ArrayList<Spot[][]>(); //상대가 자신의 th2를 막으려는 op2 and op3의 방어자리
	ArrayList<Spot[][]> block_th1 = new ArrayList<Spot[][]>(); //상대가 자신의 차기 th1을 막는 cl2, cl3의 방어자리
	
	ArrayList<Spot[][]> win = new ArrayList<Spot[][]>(); // 자신이 이기는 승리자리
	ArrayList<Spot[][]> threat_2 = new ArrayList<Spot[][]>();	
	ArrayList<Spot[][]> threat_1 = new ArrayList<Spot[][]>();
	ArrayList<Spot[][]> open_3 = new ArrayList<Spot[][]>();
	ArrayList<Spot[][]> open_2 = new ArrayList<Spot[][]>();
	ArrayList<Spot[][]> close_3 = new ArrayList<Spot[][]>();
	ArrayList<Spot[][]> close_2 = new ArrayList<Spot[][]>();
}

class Spot{
	char ston = 'e';// black(흑돌) = b, white(백돌) = w, empty(공석) = e, ban = n;
	Point[] stend = new Point[2]; //start, end out bound 위해서도 필요.
	Point point = null;
}

//행은 대소문자 구분 없이 'i'를 제외한 알파벳 a ~ t로 표기한다
//Multiple notation(복수 좌표 표기): 단수 표기를 ':'를 구분자를 사용하여 표기한다. Ex) A10:T19

public class nextSpot {

	public static void main(String[] args) { //return은 String으로 해야 할 듯 숫자 바꿔서
		Spot map[][] = new Spot[19][19];
		Point set[] = new Point[2];
		Point temp_p = null;
		
		Threat black_R = new Threat();//black_Row
		Threat black_C = new Threat();//black_Column
		Threat black_LC = new Threat();//black_Left Cross(/)
		Threat black_RC = new Threat();//black_right Cross(\)
		
		Threat white_R = new Threat();//white_Row
		Threat white_C = new Threat();//white_Column
		Threat white_LC = new Threat();//white_Left Cross(/)
		Threat white_RC = new Threat();//white_right Cross(\)
		
		int flag = 0;
		int row = 0;
		int column = 0;
		int color = 0; // black = 1, white = 2
		int turn = 1;
		String input; //입력은 알파벳, 숫자로 들어올 것이므로 변환 필요
		
		String line_R = null;
		String line_C = null;
		String line_LC = null;
		String line_RC = null;
		
		Scanner scan = new Scanner(System.in);
		
		//초기 금수 자리
		System.out.println("착수 금지점을 입력해주세요( ex) A,10 B,11...)");
		System.out.printf("착수 금지점 : ");
		do{ //입력 기준 A,0 이외 다시 적도록 해야 할 것
			input = scan.next(); //ex) A,15; -> convert
			map[row][column].ston = 'n';
		}while(input.equals(null));
		
		System.out.println("색을 정해주세요(black = 1, white = 2");
		System.out.printf("색 : ");
		color = scan.nextInt();
		
		//end flag 1될 때까지 반복
		while(flag == 0){
		
		//상대 돌 입력. 내 돌은 내부적으로 반영
		for(int i=0; i<2; i++) {
		System.out.printf("상대 착수점 : "); //입력 기준 A,0 이외 다시 적도록 해야 할 것, 대충 적어서 버퍼 오류 날 수도
		input = scan.next(); //ex) A,15;
		
		//문자열 구분 후 좌표로 변환 (A->0)...	
		row = ;		
		column = ;
		temp_p.setLocation(row, column);
		set[i] = temp_p;
		}
		
		if(color == 1) //black turn. 어차피 black은 첫 자리 고정이다.
		map[row][column].ston = 'w';
		if(color == 2) //white turn
		map[row][column].ston = 'b';
		
		
		
		turn++;
		
		//threat분류. 좌표 상하좌우 대각 반영하여 threat 분류, 변경 
		//이 분류 과정에서 변경 threat도 반영해야 함
		
		//3->4 or 3'->4'으로 만드는 자리는 threat 내부에 저장 가능. 
		//하지만 2->4 2->4' 같은 경우는 combo가 필요함.
		//방어의 경우에도 첫 수에는 후수를 감안할 수 없기 때문에 조합으로 계산할 필요성이 있음.
		
		//그럼 조합은 어떻게 계산할 것인가? -> 따로 point 배열을 만들어서 저장 -> 크기와 수정은 어떻게 할 것인가? -> 모르겠다
		
		// threat '만드는 자리'는 main 함수에 저장, 현재 threat는 threat 요소 내부에 저장
		// 그럼 다음 경우의 수가 공격 이어지는 자리인지 결정은 수월해진다. 착수 예정 자리에 최소 open1이 되는지 확인하면 됨(op1->op2) 동시에 그 head와 tail이 빈칸인지 확인! 
		// 다만 유의해야 할 점은 threat2의 경우 경우의 수가 많은데 그것들 main의 배열에 있는 것 수정 or 추가 해줘야함.		
		
		//사방으로 12글자씩 받아냄.
		for(int i=-10; i<=10; i++) {
			line_R += map[row+i][column].ston;
			line_C += map[row][column+i].ston;
			line_LC += map[row-i][column+i].ston; //(/), 위에서 보는 기준
			line_RC += map[row+i][column+i].ston; //(\)
		
		
			if(color == 2) { //받은 것이 상대의 돌이므로 내가 white일 경우
				black_R = is_threat_black(1, set, black_R, line_R);
				black_C = is_threat_black(2, set, black_C, line_C);
				black_LC = is_threat_black(3, set, black_LC, line_LC); //(/)
				black_RC = is_threat_black(4, set, black_RC, line_RC); //(\)
			}
		
			else if(color == 1) { //받은 것이 상대의 돌이므로 내가 black일 경우
				white_R = is_threat_white(1, set, white_R, line_R);
				white_C = is_threat_white(2, set, white_C, line_C);
				white_LC = is_threat_white(3, set, white_LC, line_LC); //(/)
				white_RC = is_threat_white(4, set, white_RC, line_RC); //(\)
			}
		}
		//우선순위. 착수점 계산, point return
		//우선 순위 따라 threat가 큰 것을 정하면 된다. 다만 다음 수까지 감안하기 위해서는...? tree를 쓰는 것이 좋을 것 같다.
		//내 공격의 방어자리가 상대의 4 or 4'자리가 된다면 피해야된다(내가 승리하는 수가 나오는 것을 제외하고)
		 
		priority(color, black_R, black_C, black_LC, black_RC, white_R, white_C, white_LC, white_RC);	//흑백이 문제가 아니고 내 돌이 문제인데...
		
		
		//priority에서 추가한 좌표도 반영, 변경된 착수점 저장
		
		for(int i=-10; i<=10; i++) { 
			line_R += map[row+i][column].ston;
			line_C += map[row][column+i].ston;
			line_LC += map[row+i][column-i].ston; //(/)
			line_RC += map[row+i][column+i].ston; //(\)
		
		
			if(color == 1) {
				black_R = is_threat_white(1, set, black_R, line_R);
				black_C = is_threat_white(2, set, black_C, line_C);
				black_LC = is_threat_white(3, set, black_LC, line_LC); //(/)
				black_RC = is_threat_white(4, set, black_RC, line_RC); //(\)
			}
		
			else if(color == 2) {
				white_R = is_threat_black(1, set, white_R, line_R);
				white_C = is_threat_black(2, set, white_C, line_C);
				white_LC = is_threat_black(3, set, white_LC, line_LC); //(/)
				white_RC = is_threat_black(4, set, white_RC, line_RC); //(\)
			}
		}
		
		
		}

	}
	

	
	//착수점의 threat 계산하여 수정된 착수점 반환.
	//내 돌이면 내 threat의 추가, 상대 threat의 제거. 상대 돌이면 상대 threat의 추가 내 threat의 제거 반영.
	//내 돌이 추가 된 point가 포함되는 상대 threat 전부 제거 or downgrade(이것도 case 분석 해야할 듯. 줄어드는게 다르다) 반대 역시 포함
	//type1 = R, type2 = C, type3 = LC, type4 = RC
	
	static Threat is_threat_black(int type, Point[] set, Threat threat, String line) { // 스레드 받아오기, 복사해서 추가해서 돌려주기
		
		Threat temp_t = threat; //threat를 가져와서 내용물 추가 후 그대로 반환 할 것이다.
		ArrayList<Spot[][]> temp_2s = new ArrayList<Spot[][]>();
		Spot temp_s1 = new Spot(); //th2 case에서는 점 2개를 한번에 넣어야한다.
		Spot temp_s2 = new Spot();
		String temp = null;
		String head = null;
		String body = null;
		String tail = null;
		int row = 0;
		int column = 0;
		
		// 착수점 결정 전 돌 색깔 설정
		temp_s1.ston = 'b';
		temp_s2.ston = 'b';
		
		
		// line이 17개의 char이므로, for문으로 8 and 9 and 12개씩 나눠서 case 판별하자
		
		// 장외 잘 거르는 것이 핵심!! 일단은 크게 만든다.
		
		//type따라 좌표 복구시 더하는 숫자 달라져야한다.
		
		//case 분류는 for 2번 반복
		
		for(int n=0; n<2; n++) {
			row = (int) set[n].getX();
			column = (int) set[n].getY();
		//1. Win case(th2)
			for(int i=0; i<=7; i++) {//총 길이 -1번 반복
				temp = line.substring(3+i,3+i+8); //8개 분리(0~7) // 원점이 포함되는 내에서 8!
				head = temp.substring(i, i+1); //head 1개
				body = temp.substring(i+1,i+7); //body 6개
				tail = temp.substring(i+7,i+8); //tail 1개
			
				if((head.equals("e"))&&(tail.equals("e"))&&(body.equals("bbbbbe"))){ // 이것의 좌표 반환은 어떻게? => 원점 좌표(row,column)와 i로 따지면 된다.
					temp_s1.stend = stend_point(type, set[n], 8, i); //start, end
					temp_s1.point = specific_point(type, temp_s1.stend, 6); //원하는 좌표
					temp_2s.add(null) = point; 								//넣는게 안되네... 공격점, 방어점도 다 구별해야되는데...
									
				}
				if((head.equals("e"))&&(tail.equals("e"))&&(body.equals("ebbbb"))){
				
				}
			}
			
			for(int i=0; i<=7; i++) { //총 길이 -1번 반복	
				temp = line.substring(3+i,3+i+8); //8개 분리
				head = temp.substring(i,i+2); //앞 2개
				body = temp.substring(i+2,i+6); //본체 4개
				tail = temp.substring(i+6,i+8); //뒤 2개
			
				if((head.equals("ee"))&&(tail.equals("ee"))&&(body.equals("bbbb"))){ // 이것의 좌표 반환은 어떻게? => i로 따지면 된다.
				
				}
			}
			
			for(int i=0; i<=8; i++) { //9case			
				temp = line.substring(2+i,2+i+9); //9개 분리
				head = temp.substring(i,i+3); //앞 3개
				body = temp.substring(i+3,i+6); //본체 3개
				tail = temp.substring(i+7,i+9); //뒤 3개
			
				if((head.equals("bee"))&&(tail.equals("ebe"))&&(body.equals("ebe"))){
				
				}
				if((head.equals("bee"))&&(tail.equals("ebb"))&&(body.equals("ebe"))){
					
				}	
				if((head.equals("ebe"))&&(tail.equals("ebe"))&&(body.equals("ebe"))){
					
				}
				if((head.equals("ebe"))&&(tail.equals("eeb"))&&(body.equals("ebe"))){
					
				}
			}
			
			for(int i=0; i<=10; i++) { //11case			
				temp = line.substring(i,i+11); //11개 분리
				head = temp.substring(i,i+4); //앞 4개
				body = temp.substring(i+4,i+7); //본체 3개
				tail = temp.substring(i+7,i+11); //뒤 4개
			
				if(((3==countChar(head, 'b'))&&(1==countChar(head, 'e')))&&((3==countChar(tail, 'b'))&&(1==countChar(tail, 'e')))&&(body.equals("ebe"))){
					//e자리 찾고 for문으로 조합 만들어서 넣기
				}
				if(((3==countChar(head, 'b'))&&(1==countChar(head, 'e')))&&((2==countChar(tail, 'b'))&&(2==countChar(tail, 'e')))&&(body.equals("ebe"))){
					//e자리 찾고 for문으로 조합 만들어서 넣기
				}
			}			
		}
		
		for(int n=0; n<2; n++) {
			row = (int) set[n].getX();
			column = (int) set[n].getY();
			
		//2. Win case(th1)
			for(int i=0; i<=5; i++) {//총 길이 -1번 반복
				temp = line.substring(6+i,6+i+6); //6개 분리 
				//head = temp.substring(i, i+1); //head 1개
				//body = temp.substring(i+1,i+7); //body 6개
				//tail = temp.substring(i+7,i+8); //tail 1개
			
				if(5==countChar(temp, 'b')&&(1==countChar(temp, 'e'))){ //th1 case는 나온 착수점이 th2와 겹칠 경우 폐기
					
									
				}
				if(4==countChar(temp, 'b')&&(2==countChar(temp, 'e'))){ //th1 case는 나온 착수점이 th2와 겹칠 경우 폐기
					
					
				}
			}
		}
		
		
		//3. th2 case
		
		for(int n=0; n<2; n++) {
			row = (int) set[n].getX();
			column = (int) set[n].getY();
			
			for(int i=0; i<=7; i++) {//총 길이 -1번 반복
				temp = line.substring(3+i,3+i+8); //8개 분리(0~7) // 원점이 포함되는 내에서 8!
				head = temp.substring(i, i+1); //head 1개
				body = temp.substring(i+1,i+7); //body 6개
				tail = temp.substring(i+7,i+8); //tail 1개
			
				if((head.equals("e"))&&(tail.equals("e"))&&(3==countChar(body, 'b'))&&(1==countChar(body, 'e'))){ //th1 case는 나온 착수점이 th2와 겹칠 경우 폐기
					
									
				}
				if(4==countChar(temp, 'b')&&(2==countChar(temp, 'e'))){ //th1 case는 나온 착수점이 th2와 겹칠 경우 폐기
					
					
				}
			}
		}
		
		

		
		//close 4는 close 2혹은 close 3에서 close 4를 만드는 자리 두개 혹은 하나 감지.
		
		//open 3는 open 1혹은 open 2에서 open 3를 만드는 자리 두개 혹은 하나 감지.
		
		//close 3는 close 1혹은 close 2에서 close 3를 만드는 자리 두개 혹은 하나 감지.
		
		//open 2는  0 혹은 open 1에서 open 2를 만드는 자리 두개 혹은 하나 감지. 이 자리가 중요하다(8칸 중 양끝 4개 e, 사이 e는 
		
		//close 2는  b 혹은 close 1에서 close 2를 만드는 자리 두개 혹은 하나 감지.
		
		return null;
		
	}
	
	
	static Threat is_threat_white(int type, Point[] set, Threat threat, String line) { // 스레드 받아오기, 복사해서 추가해서 돌려주기
		
		Threat temp_t = threat; //threat를 가져와서 내용물 추가 후 그대로 반환 할 것이다. //얕은 복사? 이상하다면 여기 찾아보자
		Spot temp_s_1 = new Spot();
		Spot temp_s_2 = new Spot();
		String temp = null;
		String head = null;
		String body = null;
		String tail = null;
		

		temp_s_1.ston = 'w';
		temp_s_2.ston = 'w';
		
		
		for(int i=0; i<=8; i++) {
			temp = line.substring(i,i+8); //8개 분리(0~7)
			head = temp.substring(i,i+1); //head 1개
			body = temp.substring(i+1,i+7); //body 6개
			tail = temp.substring(i+7,i+8); //tail 1개
			
			if((head.equals("e"))&&(tail.equals("e"))&&(head.equals("bbbbbe"))){ // 이것의 좌표 반환은 어떻게? => 원점 좌표(row,column)와 i로 따지면 된다.
				temp_s_1.point.setLocation(1, 1);						 // start, end, th, th_b
				
																				 
			}
			if((head.equals("e"))&&(tail.equals("e"))&&(head.equals("ebbbb"))){
				
			}
			
		}
		
		for(int i=0; i<=8; i++) {
		
		temp = line.substring(i,i+8); //8개 분리
		head = temp.substring(i,i+2); //앞 2개
		body = temp.substring(i+2,i+6); //본체 4개
		tail = temp.substring(i+6,i+8); //뒤 2개
		
		if((head.equals("bee"))&&(tail.equals("ebe"))&&(head.equals("bbbb"))){ // 이것의 좌표 반환은 어떻게? => i로 따지면 된다.
			
		}
		
		}
		

		
		//close 4는 close 2혹은 close 3에서 close 4를 만드는 자리 두개 혹은 하나 감지.
		
		//open 3는 open 1혹은 open 2에서 open 3를 만드는 자리 두개 혹은 하나 감지.
		
		//close 3는 close 1혹은 close 2에서 close 3를 만드는 자리 두개 혹은 하나 감지.
		
		//open 2는  0 혹은 open 1에서 open 2를 만드는 자리 두개 혹은 하나 감지. 이 자리가 중요하다(8칸 중 양끝 4개 e, 사이 e는 
		
		//close 2는  b 혹은 close 1에서 close 2를 만드는 자리 두개 혹은 하나 감지.
		
		return null;
		
	}	
	
	
	static Point[][] priority(int color, Threat black_R, Threat black_C, Threat black_LC, Threat black_RC, Threat white_R, Threat white_C, Threat white_LC, Threat white_RC){
		
		
		
		return null;
	}
	
	
	static Point[] stend_point(int type, Point core, int length, int index) { //start, end point, length -1 보정 해줌
		
		Point point[] = new Point[2];
		length -= 1; //length -1 보정, 늘 core가 포함되므로...
		
		if(type == 1) { //row
			point[0].setLocation(core.getX()-length+index, core.getY()); //start
			point[1].setLocation(core.getX()+index, core.getY()); //end
		}
		
		if(type == 2) { //column
			point[0].setLocation(core.getX(), core.getY()-length+index); //start
			point[1].setLocation(core.getX(), core.getY()+index); //end
		}
		
		if(type == 3) { //LC(/)
			point[0].setLocation(core.getX()+length-index, core.getY()-length+index); //start
			point[1].setLocation(core.getX()-index, core.getY()+index); //end
		}
		
		if(type == 4) { //RC(\)
			point[0].setLocation(core.getX()-length+index, core.getY()-length+index); //start
			point[1].setLocation(core.getX()+index, core.getY()+index); //end
		}
		
		return point;
	}
	
	static Point specific_point(int type, Point[] stend, int index) { //원하는 점의 좌표 반환
		
		Point point = null;
		
		if(type == 1) { //row
			point.setLocation(stend[0].getX()+index, stend[0].getY());
		}
		
		if(type == 2) { //column
			point.setLocation(stend[0].getX(), stend[0].getY()+index);
		}
		
		if(type == 3) { //LC(/)
			point.setLocation(stend[0].getX()-index, stend[0].getY()+index);
		}
		
		if(type == 4) { //RC(\)
			point.setLocation(stend[0].getX()+index, stend[0].getY()+index);
		}
		
		return point;
	}
	
	public static long countChar(String str, char ch) { //문자 개수 계산해주는 함수
		
		return str.chars().filter(c -> c == ch).count();
	}

}
