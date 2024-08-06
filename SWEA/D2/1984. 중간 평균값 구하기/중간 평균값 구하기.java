
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
			//배열 집어넣기
			int[] arr = new int[10];
			for (int i = 0, num = 0; i < 10; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = arr[0], min = arr[0];
			double sum = 0;
			for (int i = 0; i < 10; i++) {
				max = max > arr[i] ? max : arr[i];
				min = min > arr[i] ? arr[i] : min;
				sum+= arr[i];
			}
			sum= sum-max-min;
			double result = Math.round(sum/8);
			sb.append("#").append(t+1).append(" ").append((int)result).append("\n");
		}
		System.out.println(sb);
	}
}
