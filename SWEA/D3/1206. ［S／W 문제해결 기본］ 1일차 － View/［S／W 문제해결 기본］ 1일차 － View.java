
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution{
	public static void main(String args[]) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++){
			int size = Integer.parseInt(bf.readLine());
			int[] arr = new int[size];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			for (int i = 0; i < size; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int sum = 0;
			for (int i = 2; i < size-2; i++) {
				int max = arr[i-2];
				max = max < arr[i-2] ? arr[i-2] : max;
				max = max < arr[i-1] ? arr[i-1] : max;
				max = max < arr[i+1] ? arr[i+1] : max;
				max = max < arr[i+2] ? arr[i+2] : max;
				if(arr[i] - max > 0) sum += arr[i] -max;
			}
			
			System.out.println("#" + test_case + " " + sum);
			
		}
	}
}