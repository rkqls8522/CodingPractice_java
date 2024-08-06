
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = 10;
		int N = 100;
		int[][] arr = new int[N][N];
		for (int t = 1; t <= T; t++) {
			t = Integer.parseInt(br.readLine());
			
			//max값이 몇이 될지 모르며 Integer범위를 벗어나지 않는다고 하니 Integer의 최솟값을 max로 선언해두기
			int max = Integer.MIN_VALUE;
			int sum = 0;
			//배열 채우기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					//가로줄에서 max값 찾기
					sum += arr[i][j];
				}
				max = max > sum ? max : sum;
				sum = 0;
			}

			//세로줄에서 max값 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sum += arr[j][i];
				}
				max = max > sum ? max : sum;
				sum = 0;
			}
		
			//왼쪽 대각선에서 max값 찾기
			for (int i = 0; i < N; i++) {
				sum += arr[i][i];
				max = max > sum ? max : sum;
			}
			sum = 0;
		
			//오른쪽 대각선에서 max값 찾기
			for (int i = 0; i < N; i++) {
				sum += arr[i][N-i-1];
				max = max > sum ? max : sum;
			}
			sum = 0;
			
			
			sb.append("#").append(t).append(" ").append(max).append("\n");
		
		}
//		sb.setLength(sb.length()-1);
		System.out.print(sb);
		
	}
}
