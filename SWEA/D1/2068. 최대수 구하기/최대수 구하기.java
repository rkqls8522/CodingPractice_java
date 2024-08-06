
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int[] arr = new int[10];
			for (int i = 0; i < 10; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = arr[0];
			for (int i = 0; i < arr.length; i++) {
				max = max > arr[i] ? max : arr[i];
			}
			sb.append("#").append(t+1).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
}
