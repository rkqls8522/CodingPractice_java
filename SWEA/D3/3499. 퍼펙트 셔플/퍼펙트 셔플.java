
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
			String[] arr = new String[N];
			int half = N/2; //중간을 나눠서 셔플할 것.

			String temp="";
			//배열채우기 : 배열이 짝수일 경우
			if(N%2 == 0) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int r = 0; r < N; r++) {
					arr[r] = st.nextToken();
				}
			} else { //배열채우기 : 배열이 홀수일 경우
				int idx=0;
				st = new StringTokenizer(br.readLine(), " ");
				for (int r = 0; r < N; r++) {
					if(r != half) arr[idx++] = st.nextToken();
					else temp = st.nextToken(); //중간값은 temp에 넣어주기
				}
			}
			
			sb.append("#").append(t);
			
			for (int i = 0; i < half; i++) {
				sb.append(" ").append(arr[i]);
				sb.append(" ").append(arr[i+half]);
			}
			
			sb.append(" ").append(temp);
			
			sb.append("\n");
			
		}
		System.out.print(sb);
	}
}
