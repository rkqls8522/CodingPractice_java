
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
			int[] arr = new int[N];
			long result = 0;

			st = new StringTokenizer(br.readLine());
			for (int r = 0; r < N; r++) {
				arr[r] = Integer.parseInt(st.nextToken());
			}
			//배열을 돌 시작 위치 저장
			int startIdx = 0;
			while(startIdx < N-1) { //끝까지 최대한 이익낼겨!
				//최댓값과 그 위치 저장
				int max = Integer.MIN_VALUE;
				int maxIdx = 0;
				for (int i = startIdx; i < N; i++) {
					if(max < arr[i]) {
						max = arr[i];
						maxIdx = i;
					}
				}
				
				//최댓값위치까지 돌며 이익값계산
				for (int i = startIdx; i < maxIdx; i++) {
					// 각 날마다 구입액과 판매액 차이값을 result에 추가
					result += max - arr[i]; 
				}
				startIdx = maxIdx+1; //그 다음 날부터 또 이익내기 시작
			}

			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
