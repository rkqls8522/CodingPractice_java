
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; ) {
			t = Integer.parseInt(br.readLine());
			int[] arr = new int[101];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 1000; i++) {
				arr[Integer.parseInt(st.nextToken())] += 1;
			}
			
			//최대빈수 찾기 ( 값이 아닌 위치를 저장. 중요!)
			int max = 0;
			for (int i = 0; i < arr.length; i++) {
				max = arr[max] > arr[i] ? max : i;
			}
			
			System.out.printf("#%d %d\n",t, max);
		}
	}
}
