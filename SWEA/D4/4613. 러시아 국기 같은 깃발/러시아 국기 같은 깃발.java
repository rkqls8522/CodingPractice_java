
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
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][] arr = new char[N][M];

			String str;
			for (int r = 0; r < N; r++) {
				str = br.readLine();
				for (int c = 0, i=0; c < M; c++, i++) {
					arr[r][c] = str.charAt(i);
				}
			}
			
			int startCount = 0;
			//첫 번째 줄은 화이트로 채우기
			startCount += paintWhite(arr, 0, 1); //첫 번째 줄은 무조건 흰색
			startCount += paintRed(arr, N-1, N); //마지막 줄은 무조건 빨간색
			
			int min = Integer.MAX_VALUE;
			for (int rowCnt = 0; rowCnt < N-2; rowCnt++) {
				for (int secondRowCnt = 0; secondRowCnt < N-rowCnt-2; secondRowCnt++) {
					int count = startCount;
					//인자 : 배열, 시작행, 끝행
					//흰 부분 채우기
					count += paintWhite(arr, 1, rowCnt+1); //한 줄씩 늘리면서 채워짐
					//파란 부분 채우기
					count += paintBlue(arr, rowCnt+1, rowCnt+2+secondRowCnt ); //흰줄의 다음줄부터 한 줄씩 줄이면서 채워지되 최소 1줄있음
					//빨간 부분 채우기
					count += paintRed(arr, rowCnt+2+secondRowCnt, N-1);
					min = min > count ? count : min;
				}
			}
			
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.print(sb);
	}

	public static int paintWhite(char[][] arr, int startRow, int endRow) {
		int count = 0;
		for (int row = startRow; row < endRow; row++) {
			for (int c = 0; c < arr[row].length; c++) {
				if(arr[row][c] != 'W') count++;
			}
		}
		return count;
	}

	public static int paintBlue(char[][] arr, int startRow, int endRow) {
		int count = 0;
		for (int row = startRow; row < endRow; row++) {
			for (int c = 0; c < arr[row].length; c++) {
				if(arr[row][c] != 'B') count++;
			}
		}
		return count;
	}

	public static int paintRed(char[][] arr, int startRow, int endRow) {
		int count = 0;
		for (int row = startRow; row < endRow; row++) {
			for (int c = 0; c < arr[row].length; c++) {
				if(arr[row][c] != 'R') count++;
			}
		}
		return count;
	}

}
