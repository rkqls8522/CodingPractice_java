import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); 
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());

			//달팽이 숫자를 채울 배열을 만들기
			int[][] arr = new int[n][n];
			
			
			int num = 1;
			int wallCnt = 1;
			int r=0, c=0, size=n*n;
			//움직일 거리
			int move = n-wallCnt;
			while(size > 1) {
				
				
				//우로 이동
				for (int i = 0; i < move; i++) {
					arr[r][c++] = num++;
				}
				
				//하로 이동
				for (int i = 0; i < move; i++) {
					arr[r++][c] = num++;
				}
				
				//좌로 이동
				for (int i = 0; i < move; i++) {
					arr[r][c--] = num++;
				}
				
				//상으로 이동
				for (int i = 0; i < move; i++) {
					arr[r--][c] = num++;
				}
				
				size -= move*4;
				move -= 2;
				r++;
				c++;

			}
			
			//가운데 한 칸 남으면 채워주기
			if(size == 1) {
				arr[r][c] = num;
			}
			
			//출력할 문자열 만들기
			sb.append("#").append(t).append("\n");
			for (int row = 0; row < n; row++) {
				for (int col = 0; col < n; col++) {
					sb.append(arr[row][col]).append(" ");
				}
				sb.append("\n");
			}
			
			
		}
		System.out.print(sb);
	}
}
