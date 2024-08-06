
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean change = true;
		//중간값 출력위해 정렬하기
		Arrays.sort(arr);
//		while(change) {
//			change = false;
//			for (int i = 0; i < arr.length-1; i++) {
//				if (arr[i] > arr[i+1]) {
//					change = true;
//					int temp = arr[i];
//					arr[i] = arr[i+1];
//					arr[i+1] = temp;
//				}
//			}	
//		}
		
		System.out.println(arr[arr.length/2]);
		
		
	}
}
