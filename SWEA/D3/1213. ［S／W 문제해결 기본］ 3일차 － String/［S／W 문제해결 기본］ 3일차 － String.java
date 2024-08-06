
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = 10;
		for (int t = 1; t <= T; t++) {
			t = Integer.parseInt(br.readLine());
			String findString = br.readLine();
			String str = br.readLine();
			int cnt = 0;
			char findChar = findString.charAt(0);
			
			for (int i = 0; i <= str.length()-findString.length(); i++) {
				if (str.charAt(i) == findChar) {
					
					if(str.substring(i, i+findString.length()).equals(findString)) cnt++;
				}
			}
			
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
		
	}
}
