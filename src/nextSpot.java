import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;


class Threat{ //�� ��� �Ʒ��� threat�� ���� ����
	ArrayList<Spot[][]> block_win = new ArrayList<Spot[][]>(); //2�� 1�� ��� ����. ��밡 ������ ���ƾ� �ϴ� �¸��� �������� �ڸ�
	ArrayList<Spot[][]> block_th2 = new ArrayList<Spot[][]>(); //��밡 �ڽ��� th2�� �������� op2 and op3�� ����ڸ�
	ArrayList<Spot[][]> block_th1 = new ArrayList<Spot[][]>(); //��밡 �ڽ��� ���� th1�� ���� cl2, cl3�� ����ڸ�
	
	ArrayList<Spot[][]> win = new ArrayList<Spot[][]>(); // �ڽ��� �̱�� �¸��ڸ�
	ArrayList<Spot[][]> threat_2 = new ArrayList<Spot[][]>();	
	ArrayList<Spot[][]> threat_1 = new ArrayList<Spot[][]>();
	ArrayList<Spot[][]> open_3 = new ArrayList<Spot[][]>();
	ArrayList<Spot[][]> open_2 = new ArrayList<Spot[][]>();
	ArrayList<Spot[][]> close_3 = new ArrayList<Spot[][]>();
	ArrayList<Spot[][]> close_2 = new ArrayList<Spot[][]>();
}

class Spot{
	char ston = 'e';// black(�浹) = b, white(�鵹) = w, empty(����) = e, ban = n;
	Point[] stend = new Point[2]; //start, end out bound ���ؼ��� �ʿ�.
	Point point = null;
}

//���� ��ҹ��� ���� ���� 'i'�� ������ ���ĺ� a ~ t�� ǥ���Ѵ�
//Multiple notation(���� ��ǥ ǥ��): �ܼ� ǥ�⸦ ':'�� �����ڸ� ����Ͽ� ǥ���Ѵ�. Ex) A10:T19

public class nextSpot {

	public static void main(String[] args) { //return�� String���� �ؾ� �� �� ���� �ٲ㼭
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
		String input; //�Է��� ���ĺ�, ���ڷ� ���� ���̹Ƿ� ��ȯ �ʿ�
		
		String line_R = null;
		String line_C = null;
		String line_LC = null;
		String line_RC = null;
		
		Scanner scan = new Scanner(System.in);
		
		//�ʱ� �ݼ� �ڸ�
		System.out.println("���� �������� �Է����ּ���( ex) A,10 B,11...)");
		System.out.printf("���� ������ : ");
		do{ //�Է� ���� A,0 �̿� �ٽ� ������ �ؾ� �� ��
			input = scan.next(); //ex) A,15; -> convert
			map[row][column].ston = 'n';
		}while(input.equals(null));
		
		System.out.println("���� �����ּ���(black = 1, white = 2");
		System.out.printf("�� : ");
		color = scan.nextInt();
		
		//end flag 1�� ������ �ݺ�
		while(flag == 0){
		
		//��� �� �Է�. �� ���� ���������� �ݿ�
		for(int i=0; i<2; i++) {
		System.out.printf("��� ������ : "); //�Է� ���� A,0 �̿� �ٽ� ������ �ؾ� �� ��, ���� ��� ���� ���� �� ����
		input = scan.next(); //ex) A,15;
		
		//���ڿ� ���� �� ��ǥ�� ��ȯ (A->0)...	
		row = ;		
		column = ;
		temp_p.setLocation(row, column);
		set[i] = temp_p;
		}
		
		if(color == 1) //black turn. ������ black�� ù �ڸ� �����̴�.
		map[row][column].ston = 'w';
		if(color == 2) //white turn
		map[row][column].ston = 'b';
		
		
		
		turn++;
		
		//threat�з�. ��ǥ �����¿� �밢 �ݿ��Ͽ� threat �з�, ���� 
		//�� �з� �������� ���� threat�� �ݿ��ؾ� ��
		
		//3->4 or 3'->4'���� ����� �ڸ��� threat ���ο� ���� ����. 
		//������ 2->4 2->4' ���� ���� combo�� �ʿ���.
		//����� ��쿡�� ù ������ �ļ��� ������ �� ���� ������ �������� ����� �ʿ伺�� ����.
		
		//�׷� ������ ��� ����� ���ΰ�? -> ���� point �迭�� ���� ���� -> ũ��� ������ ��� �� ���ΰ�? -> �𸣰ڴ�
		
		// threat '����� �ڸ�'�� main �Լ��� ����, ���� threat�� threat ��� ���ο� ����
		// �׷� ���� ����� ���� ���� �̾����� �ڸ����� ������ ����������. ���� ���� �ڸ��� �ּ� open1�� �Ǵ��� Ȯ���ϸ� ��(op1->op2) ���ÿ� �� head�� tail�� ��ĭ���� Ȯ��! 
		// �ٸ� �����ؾ� �� ���� threat2�� ��� ����� ���� ������ �װ͵� main�� �迭�� �ִ� �� ���� or �߰� �������.		
		
		//������� 12���ھ� �޾Ƴ�.
		for(int i=-10; i<=10; i++) {
			line_R += map[row+i][column].ston;
			line_C += map[row][column+i].ston;
			line_LC += map[row-i][column+i].ston; //(/), ������ ���� ����
			line_RC += map[row+i][column+i].ston; //(\)
		
		
			if(color == 2) { //���� ���� ����� ���̹Ƿ� ���� white�� ���
				black_R = is_threat_black(1, set, black_R, line_R);
				black_C = is_threat_black(2, set, black_C, line_C);
				black_LC = is_threat_black(3, set, black_LC, line_LC); //(/)
				black_RC = is_threat_black(4, set, black_RC, line_RC); //(\)
			}
		
			else if(color == 1) { //���� ���� ����� ���̹Ƿ� ���� black�� ���
				white_R = is_threat_white(1, set, white_R, line_R);
				white_C = is_threat_white(2, set, white_C, line_C);
				white_LC = is_threat_white(3, set, white_LC, line_LC); //(/)
				white_RC = is_threat_white(4, set, white_RC, line_RC); //(\)
			}
		}
		//�켱����. ������ ���, point return
		//�켱 ���� ���� threat�� ū ���� ���ϸ� �ȴ�. �ٸ� ���� ������ �����ϱ� ���ؼ���...? tree�� ���� ���� ���� �� ����.
		//�� ������ ����ڸ��� ����� 4 or 4'�ڸ��� �ȴٸ� ���ؾߵȴ�(���� �¸��ϴ� ���� ������ ���� �����ϰ�)
		 
		priority(color, black_R, black_C, black_LC, black_RC, white_R, white_C, white_LC, white_RC);	//����� ������ �ƴϰ� �� ���� �����ε�...
		
		
		//priority���� �߰��� ��ǥ�� �ݿ�, ����� ������ ����
		
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
	

	
	//�������� threat ����Ͽ� ������ ������ ��ȯ.
	//�� ���̸� �� threat�� �߰�, ��� threat�� ����. ��� ���̸� ��� threat�� �߰� �� threat�� ���� �ݿ�.
	//�� ���� �߰� �� point�� ���ԵǴ� ��� threat ���� ���� or downgrade(�̰͵� case �м� �ؾ��� ��. �پ��°� �ٸ���) �ݴ� ���� ����
	//type1 = R, type2 = C, type3 = LC, type4 = RC
	
	static Threat is_threat_black(int type, Point[] set, Threat threat, String line) { // ������ �޾ƿ���, �����ؼ� �߰��ؼ� �����ֱ�
		
		Threat temp_t = threat; //threat�� �����ͼ� ���빰 �߰� �� �״�� ��ȯ �� ���̴�.
		ArrayList<Spot[][]> temp_2s = new ArrayList<Spot[][]>();
		Spot temp_s1 = new Spot(); //th2 case������ �� 2���� �ѹ��� �־���Ѵ�.
		Spot temp_s2 = new Spot();
		String temp = null;
		String head = null;
		String body = null;
		String tail = null;
		int row = 0;
		int column = 0;
		
		// ������ ���� �� �� ���� ����
		temp_s1.ston = 'b';
		temp_s2.ston = 'b';
		
		
		// line�� 17���� char�̹Ƿ�, for������ 8 and 9 and 12���� ������ case �Ǻ�����
		
		// ��� �� �Ÿ��� ���� �ٽ�!! �ϴ��� ũ�� �����.
		
		//type���� ��ǥ ������ ���ϴ� ���� �޶������Ѵ�.
		
		//case �з��� for 2�� �ݺ�
		
		for(int n=0; n<2; n++) {
			row = (int) set[n].getX();
			column = (int) set[n].getY();
		//1. Win case(th2)
			for(int i=0; i<=7; i++) {//�� ���� -1�� �ݺ�
				temp = line.substring(3+i,3+i+8); //8�� �и�(0~7) // ������ ���ԵǴ� ������ 8!
				head = temp.substring(i, i+1); //head 1��
				body = temp.substring(i+1,i+7); //body 6��
				tail = temp.substring(i+7,i+8); //tail 1��
			
				if((head.equals("e"))&&(tail.equals("e"))&&(body.equals("bbbbbe"))){ // �̰��� ��ǥ ��ȯ�� ���? => ���� ��ǥ(row,column)�� i�� ������ �ȴ�.
					temp_s1.stend = stend_point(type, set[n], 8, i); //start, end
					temp_s1.point = specific_point(type, temp_s1.stend, 6); //���ϴ� ��ǥ
					temp_2s.add(null) = point; 								//�ִ°� �ȵǳ�... ������, ������� �� �����ؾߵǴµ�...
									
				}
				if((head.equals("e"))&&(tail.equals("e"))&&(body.equals("ebbbb"))){
				
				}
			}
			
			for(int i=0; i<=7; i++) { //�� ���� -1�� �ݺ�	
				temp = line.substring(3+i,3+i+8); //8�� �и�
				head = temp.substring(i,i+2); //�� 2��
				body = temp.substring(i+2,i+6); //��ü 4��
				tail = temp.substring(i+6,i+8); //�� 2��
			
				if((head.equals("ee"))&&(tail.equals("ee"))&&(body.equals("bbbb"))){ // �̰��� ��ǥ ��ȯ�� ���? => i�� ������ �ȴ�.
				
				}
			}
			
			for(int i=0; i<=8; i++) { //9case			
				temp = line.substring(2+i,2+i+9); //9�� �и�
				head = temp.substring(i,i+3); //�� 3��
				body = temp.substring(i+3,i+6); //��ü 3��
				tail = temp.substring(i+7,i+9); //�� 3��
			
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
				temp = line.substring(i,i+11); //11�� �и�
				head = temp.substring(i,i+4); //�� 4��
				body = temp.substring(i+4,i+7); //��ü 3��
				tail = temp.substring(i+7,i+11); //�� 4��
			
				if(((3==countChar(head, 'b'))&&(1==countChar(head, 'e')))&&((3==countChar(tail, 'b'))&&(1==countChar(tail, 'e')))&&(body.equals("ebe"))){
					//e�ڸ� ã�� for������ ���� ���� �ֱ�
				}
				if(((3==countChar(head, 'b'))&&(1==countChar(head, 'e')))&&((2==countChar(tail, 'b'))&&(2==countChar(tail, 'e')))&&(body.equals("ebe"))){
					//e�ڸ� ã�� for������ ���� ���� �ֱ�
				}
			}			
		}
		
		for(int n=0; n<2; n++) {
			row = (int) set[n].getX();
			column = (int) set[n].getY();
			
		//2. Win case(th1)
			for(int i=0; i<=5; i++) {//�� ���� -1�� �ݺ�
				temp = line.substring(6+i,6+i+6); //6�� �и� 
				//head = temp.substring(i, i+1); //head 1��
				//body = temp.substring(i+1,i+7); //body 6��
				//tail = temp.substring(i+7,i+8); //tail 1��
			
				if(5==countChar(temp, 'b')&&(1==countChar(temp, 'e'))){ //th1 case�� ���� �������� th2�� ��ĥ ��� ���
					
									
				}
				if(4==countChar(temp, 'b')&&(2==countChar(temp, 'e'))){ //th1 case�� ���� �������� th2�� ��ĥ ��� ���
					
					
				}
			}
		}
		
		
		//3. th2 case
		
		for(int n=0; n<2; n++) {
			row = (int) set[n].getX();
			column = (int) set[n].getY();
			
			for(int i=0; i<=7; i++) {//�� ���� -1�� �ݺ�
				temp = line.substring(3+i,3+i+8); //8�� �и�(0~7) // ������ ���ԵǴ� ������ 8!
				head = temp.substring(i, i+1); //head 1��
				body = temp.substring(i+1,i+7); //body 6��
				tail = temp.substring(i+7,i+8); //tail 1��
			
				if((head.equals("e"))&&(tail.equals("e"))&&(3==countChar(body, 'b'))&&(1==countChar(body, 'e'))){ //th1 case�� ���� �������� th2�� ��ĥ ��� ���
					
									
				}
				if(4==countChar(temp, 'b')&&(2==countChar(temp, 'e'))){ //th1 case�� ���� �������� th2�� ��ĥ ��� ���
					
					
				}
			}
		}
		
		

		
		//close 4�� close 2Ȥ�� close 3���� close 4�� ����� �ڸ� �ΰ� Ȥ�� �ϳ� ����.
		
		//open 3�� open 1Ȥ�� open 2���� open 3�� ����� �ڸ� �ΰ� Ȥ�� �ϳ� ����.
		
		//close 3�� close 1Ȥ�� close 2���� close 3�� ����� �ڸ� �ΰ� Ȥ�� �ϳ� ����.
		
		//open 2��  0 Ȥ�� open 1���� open 2�� ����� �ڸ� �ΰ� Ȥ�� �ϳ� ����. �� �ڸ��� �߿��ϴ�(8ĭ �� �糡 4�� e, ���� e�� 
		
		//close 2��  b Ȥ�� close 1���� close 2�� ����� �ڸ� �ΰ� Ȥ�� �ϳ� ����.
		
		return null;
		
	}
	
	
	static Threat is_threat_white(int type, Point[] set, Threat threat, String line) { // ������ �޾ƿ���, �����ؼ� �߰��ؼ� �����ֱ�
		
		Threat temp_t = threat; //threat�� �����ͼ� ���빰 �߰� �� �״�� ��ȯ �� ���̴�. //���� ����? �̻��ϴٸ� ���� ã�ƺ���
		Spot temp_s_1 = new Spot();
		Spot temp_s_2 = new Spot();
		String temp = null;
		String head = null;
		String body = null;
		String tail = null;
		

		temp_s_1.ston = 'w';
		temp_s_2.ston = 'w';
		
		
		for(int i=0; i<=8; i++) {
			temp = line.substring(i,i+8); //8�� �и�(0~7)
			head = temp.substring(i,i+1); //head 1��
			body = temp.substring(i+1,i+7); //body 6��
			tail = temp.substring(i+7,i+8); //tail 1��
			
			if((head.equals("e"))&&(tail.equals("e"))&&(head.equals("bbbbbe"))){ // �̰��� ��ǥ ��ȯ�� ���? => ���� ��ǥ(row,column)�� i�� ������ �ȴ�.
				temp_s_1.point.setLocation(1, 1);						 // start, end, th, th_b
				
																				 
			}
			if((head.equals("e"))&&(tail.equals("e"))&&(head.equals("ebbbb"))){
				
			}
			
		}
		
		for(int i=0; i<=8; i++) {
		
		temp = line.substring(i,i+8); //8�� �и�
		head = temp.substring(i,i+2); //�� 2��
		body = temp.substring(i+2,i+6); //��ü 4��
		tail = temp.substring(i+6,i+8); //�� 2��
		
		if((head.equals("bee"))&&(tail.equals("ebe"))&&(head.equals("bbbb"))){ // �̰��� ��ǥ ��ȯ�� ���? => i�� ������ �ȴ�.
			
		}
		
		}
		

		
		//close 4�� close 2Ȥ�� close 3���� close 4�� ����� �ڸ� �ΰ� Ȥ�� �ϳ� ����.
		
		//open 3�� open 1Ȥ�� open 2���� open 3�� ����� �ڸ� �ΰ� Ȥ�� �ϳ� ����.
		
		//close 3�� close 1Ȥ�� close 2���� close 3�� ����� �ڸ� �ΰ� Ȥ�� �ϳ� ����.
		
		//open 2��  0 Ȥ�� open 1���� open 2�� ����� �ڸ� �ΰ� Ȥ�� �ϳ� ����. �� �ڸ��� �߿��ϴ�(8ĭ �� �糡 4�� e, ���� e�� 
		
		//close 2��  b Ȥ�� close 1���� close 2�� ����� �ڸ� �ΰ� Ȥ�� �ϳ� ����.
		
		return null;
		
	}	
	
	
	static Point[][] priority(int color, Threat black_R, Threat black_C, Threat black_LC, Threat black_RC, Threat white_R, Threat white_C, Threat white_LC, Threat white_RC){
		
		
		
		return null;
	}
	
	
	static Point[] stend_point(int type, Point core, int length, int index) { //start, end point, length -1 ���� ����
		
		Point point[] = new Point[2];
		length -= 1; //length -1 ����, �� core�� ���ԵǹǷ�...
		
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
	
	static Point specific_point(int type, Point[] stend, int index) { //���ϴ� ���� ��ǥ ��ȯ
		
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
	
	public static long countChar(String str, char ch) { //���� ���� ������ִ� �Լ�
		
		return str.chars().filter(c -> c == ch).count();
	}

}
