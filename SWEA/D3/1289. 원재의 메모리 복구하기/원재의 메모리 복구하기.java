
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
			String str = br.readLine();
			
			int i = 0, cnt = 0;
			//만약 1부터 시작한다면 cnt++
			if(str.charAt(0) == '1') cnt++;
			
			//1에서 0으로, 또는 0에서 1로 바뀔 때마다 cnt++;
			while(i < str.length()-1) {
				if((str.charAt(i) =='0') && (str.charAt(i+1) =='1')) cnt++;
				else if((str.charAt(i) =='1') && (str.charAt(i+1) =='0')) cnt++;
				i++;
			}
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb);
	}
}
