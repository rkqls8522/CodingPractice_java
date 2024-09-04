
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] arr;
	static int cnt;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			int max = Integer.MIN_VALUE;
			int roomNum = 0;
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					cnt = 0;
					search(r,c);
					
					if(max < cnt) {
						max = cnt;
						roomNum = arr[r][c];
					} else if(max == cnt && roomNum > arr[r][c]) {
						roomNum = arr[r][c];
					}
				}
			}
			sb.append("#").append(t).append(" ").append(roomNum).append(" ").append(max+1).append("\n");
		}
		System.out.print(sb);
	}

	private static void search(int row, int col) {
		
		//상하좌우 볼 것.
		
		//상 보기
		if(row > 0) {
			//다음방이 현재방보다 정확히 1크니?
			if(arr[row-1][col] -1 == arr[row][col]) {
//				System.out.println("상 - 현재 위치 : " + row + ", " + col + ", 그 값은 : " + arr[row][col]);
				cnt++;
				search(row-1, col); //다음방 기준으로 함 보자.
			}
		}

		//우 보기
		if(col+1 < N) {
			//다음방이 현재방보다 정확히 1크니?
			if(arr[row][col+1] -1 == arr[row][col]) {
//				System.out.println("우 - 현재 위치 : " + row + ", " + col + ", 그 값은 : " + arr[row][col]);
				cnt++;
				search(row, col+1); //다음방 기준으로 함 보자.
			}
		}
		
		//하 보기
		if(row+1 < N) {
			//다음방이 현재방보다 정확히 1크니?
			if(arr[row+1][col] -1 == arr[row][col]) {
//				System.out.println("하 - 현재 위치 : " + row + ", " + col + ", 그 값은 : " + arr[row][col]);
				cnt++;
				search(row+1, col); //다음방 기준으로 함 보자.
			}
		}
		
		//좌 보기
		if(col > 0) {
			//다음방이 현재방보다 정확히 1크니?
			if(arr[row][col-1] -1 == arr[row][col]) {
//				System.out.println("좌 - 현재 위치 : " + row + ", " + col + ", 그 값은 : " + arr[row][col]);
				cnt++;
				search(row, col-1); //다음방 기준으로 함 보자.
			}
		}
	}
}
