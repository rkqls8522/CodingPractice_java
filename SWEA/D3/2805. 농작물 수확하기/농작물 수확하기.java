
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
			int N = Integer.parseInt(br.readLine());
			int result = 0;

			for (int r = 0; r < N; r++) {
				String str = br.readLine();
				for (int c = 0; c < N; c++) {
					//수확범위 밖이라면 0입력
					if((r < N/2 && c+r < N/2) || (r < N/2 && c-r > N/2) || (r > N/2 && r-c > N/2) || (r > N/2 && r+c >= N+N/2)) continue;
					else result += str.charAt(c)-'0'; //농작물 가치 더하기
				}
			}

			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
