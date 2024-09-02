
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String input = br.readLine();
			st = new StringTokenizer(input);

			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			int[] taste = new int[n];
			int[] calorie = new int[n];

			for (int i = 0; i < n; i++) {
				
				st = new StringTokenizer(br.readLine());

				taste[i] = Integer.parseInt(st.nextToken());
				calorie[i] = Integer.parseInt(st.nextToken());
			}

			combination(0, 0, 0, 0, n, l, taste, calorie);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
			result = 0;
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void combination(int tSum, int cSum, int depth, int start, int n, int l, int[] taste, int[] calorie) {
		if (cSum > l) return;
		result = Math.max(result, tSum);
		if (depth == n) return;
		for (int i = start; i < n; i++) {
			combination(tSum + taste[i], cSum + calorie[i], depth + 1, i + 1,  n, l, taste, calorie);
		}
	}
}
