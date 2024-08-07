
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = 10;
		for (int t = 1; t <= T; t++) {
			
			t = sc.nextInt();
			int num = sc.nextInt(); //밑
			int power = sc.nextInt(); //지수
			
			//거듭제곱 값 구하는 함수 이용
			int answerValue = raise(num, power);
			
			System.out.printf("#%d %d\n", t, answerValue);
		}
	}

	//거듭제곱 해주는 함수임~ 결괏값을 리턴합니돵~
	public static int raise(int num, int power) {
		if(power < 1) return 1; //1을 시작으로 쭉 num을 곱할 것
		num*= raise(num, --power); //num에다가
		return num; //num을 계속(power수만큼) 곱해~
	}
	
}