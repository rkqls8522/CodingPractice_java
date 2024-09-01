import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =  new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			String str;
			
			
			int X = (1 << N) - 1;// 2^N - 1
			if((M & X) == X) {
				str = "ON";
			} else {
				str = "OFF";
			}
			
			
			
			sb.append("#").append(t).append(" ").append(str).append("\n");
		}
		System.out.println(sb);
	}
}