
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String str = br.readLine();
			int sum = 0, cnt = 0;
			for (int i = 1; i < str.length(); i++) {
				if(str.charAt(i) == '(') {
					if(str.charAt(i-1) == '(') cnt++;
				}
				if(str.charAt(i) == ')') {
					if(str.charAt(i-1) == '(' && cnt > 0) sum += cnt;
					if(str.charAt(i-1) == ')') {sum ++; cnt--;}
				}
			}
			sb.append("#").append(t).append(" ").append(sum).append("\n");
			
		}
		System.out.print(sb);
	}
}
