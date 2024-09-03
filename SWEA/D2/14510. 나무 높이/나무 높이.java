
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			int dateSum = 0;

			//배열 채워넣기
			st = new StringTokenizer(br.readLine());
			for (int r = 0; r < N; r++) {
				arr[r] = Integer.parseInt(st.nextToken());
			}

			//최댓값 구하기
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < arr.length; i++) {
				max = max < arr[i] ? arr[i] : max;
			}
			
			//본격 계산 시작
			int even = 0;
			int odd = 0;
			for (int i = 0; i < arr.length; i++) {
				//최댓값과의 차이를 구하여 넣자.
				arr[i] = max - arr[i];
				
				if(arr[i]%2 == 1) odd++; //홀수일에 반드시 물을 줘야하는 날 세기
				even += arr[i]/2; //짝수일에 물 줄 날 세기
			}
//			System.out.println(Arrays.toString(arr));
//			System.out.println("even : " + even + ", odd : " + odd );

			//만약 짝수가 홀수보다 2이상 많다면 홀수에 분배해줄 수 있다.
			int num = 0;
			if(even - odd >= 2) {
				int diff = even -odd;
				num = (diff+1)/3;
				even -= num;
				odd += num*2;
			}
//			System.out.println("even : " + even + ", odd : " + odd );
			
			//짝수가 더 많은 경우 실행할 것
			if(even > odd) dateSum = odd*2 + 2;
			
			//홀수가 더 많은 경우 실행할 것
			else if(even < odd) dateSum = even*2 + (odd-even)*2-1;
			
			//같을 경우 실행할 것
			else dateSum = odd*2;
			
			
			sb.append("#").append(t).append(" ").append(dateSum).append("\n");
		}
		System.out.print(sb);
	}
}
