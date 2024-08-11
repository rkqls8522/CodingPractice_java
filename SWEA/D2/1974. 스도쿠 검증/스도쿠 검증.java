
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int size = 9; //스도쿠 크기
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int cnt = 0;
		
		int T = Integer.parseInt(br.readLine());
		out : for (int t = 1; t <= T; t++) {
			cnt = 0;
			int[][] arr = new int[size][size];
			//배열채우기
			for (int r = 0; r < size; r++) {
				st = new StringTokenizer(br.readLine());
				cnt++; // 한 줄 받을 때마다 카운트 세기
				for (int c = 0; c < size; c++) {
					int num = Integer.parseInt(st.nextToken());
					
					//가로줄 겹치는 수 찾기
					arr[r][c] = num;
				}
				//겹치는 수 있는지 확인
				if(check(arr[r])) {
					sb.append("#").append(t).append(" ").append(0).append("\n");
					skipString(cnt); //브레이크를 한다면 그 전에 남은 배열들은 스킵할 것
					continue out; //다른 수가 껴있다면 이번꺼는 브레이크
				}
			}

			//세로줄 겹치는 수 찾기
			for (int c = 0; c < size; c++) {
				int[] rSum = new int[size];
				int idx = 0;
				for (int r = 0; r < size; r++) {
					rSum[idx++] = arr[r][c];
				}
				//겹치는 수 있는지 확인
				if(check(rSum)) {
					sb.append("#").append(t).append(" ").append(0).append("\n");
					continue out; //다른 수가 껴있다면 이번꺼는 브레이크
				}
			}

			//3*3크기 안에서 겹치는 수 없는지 확인
			int oneSize = size/3;
			for (int i = 0; i < oneSize; i++) {
				for (int j = 0; j < oneSize; j++) {
					int[] oneSizeSum = new int[size];
					int idx = 0;
					for (int r = i*oneSize; r < i*oneSize + oneSize; r++) {
						for (int c = j*oneSize; c < j*oneSize + oneSize; c++) {
							oneSizeSum[idx++] = arr[r][c];
						}
					}
					//겹치는 수 있는지 확인
					if(check(oneSizeSum)) {
						sb.append("#").append(t).append(" ").append(0).append("\n");
						continue out; //다른 수가 껴있다면 이번꺼는 브레이크
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(1).append("\n");
		}
		System.out.print(sb);
	}

	public static void skipString(int cnt) throws IOException {
		for (int i = 0; i < size-cnt; i++) br.readLine();
	}

	public static boolean check(int[] arr) {
		boolean result = false;
		int[] counting = new int[size+1];
		for (int i = 0; i < size; i++) {
			counting[arr[i]]++;
		}
		for (int i = 0; i < size+1; i++) {
			if(counting[i] > 1) {
				result = true;
			}
		}
		return result;
	}
}
