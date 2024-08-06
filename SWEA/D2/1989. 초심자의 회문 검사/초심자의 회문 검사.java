
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int result = 0;
		for (int t = 1; t <= T; t++) {
			result = 0;
			String str = br.readLine();
			StringBuilder reverseToSb = new StringBuilder(str).reverse();
			
			if(str.equals(reverseToSb.toString())) result = 1;
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		
	}
}
