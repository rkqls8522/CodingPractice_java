
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //행
			int M = Integer.parseInt(st.nextToken()); //열
			int K = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][M]; //0으로 초기화
			//처음 칠하는 것이라면 그냥 칠할 것
			boolean firstPaint = true;
			
			for (int i = 0; i < K; i++) { //K번 실행할 것
				st = new StringTokenizer(br.readLine());
				int startRow = Integer.parseInt(st.nextToken());
				int startCol = Integer.parseInt(st.nextToken());
				int endRow = Integer.parseInt(st.nextToken());
				int endCol = Integer.parseInt(st.nextToken());
				int color = Integer.parseInt(st.nextToken());

				if(firstPaint) {
					pullPaint(arr, startRow, startCol, endRow, endCol, color);
					firstPaint = false; //한번 칠했으니 이제 false로 바꿔주기
				}
				
				//이미 더 높은 색이 칠해져 있는지 확인
				boolean isNotPaint = isNotPainted(arr, startRow, startCol, endRow, endCol, color);
				
				//칠해도 된다면 칠해주기
				if(isNotPaint) {
					pullPaint(arr, startRow, startCol, endRow, endCol, color);
				}
			}

			//카운팅할 배열
			int[] counting = new int[11]; //물감번호는 0부터 10까지 있음
			
			//다 칠했으면 이제 영역별로 카운팅하기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					counting[arr[r][c]]++; //해당 색상값 카운팅
				}
			}
			
			int max = Integer.MIN_VALUE;
			//카운팅배열에서 max값 찾기
			for (int i = 0; i < counting.length; i++) {
				max = max > counting[i] ? max : counting[i];
			}
			
			//출력할 값 만들기
			sb.append("#").append(t).append(" ").append(max).append("\n");
			
			
		}
		System.out.print(sb);
		
	}

	public static void pullPaint(int[][] arr, int startRow, int startCol, int endRow, int endCol, int color) {
		for (int r = startRow; r <= endRow; r++) {
			for (int c = startCol; c <= endCol; c++) {
				arr[r][c] =color; //칠해주기
			}
		}
	}

	public static boolean isNotPainted(int[][] arr, int startRow, int startCol, int endRow, int endCol, int color) {
		for (int r = startRow; r <= endRow; r++) {
			for (int c = startCol; c <= endCol; c++) {
				if(arr[r][c] > color) {
					return false; //칠해져있다면 false를 반환
				}
			}
		}
		return true; //안 칠해져 있다면 true반환
	}

}
