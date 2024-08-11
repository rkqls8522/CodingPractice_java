
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

			st = new StringTokenizer(br.readLine());
			for (int r = 0; r < N; r++) {
				arr[r] = Integer.parseInt(st.nextToken());
			}
			
			sb.append("#").append(t);
			
			//정렬해서 값 넣기
			Arrays.sort(arr);
			for (int r = 0; r < N; r++) {
				sb.append(" ").append(arr[r]);
			}
			
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
