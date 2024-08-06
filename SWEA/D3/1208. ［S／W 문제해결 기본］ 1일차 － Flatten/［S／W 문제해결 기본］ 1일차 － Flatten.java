
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		int T = 10;
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] arr = new int[100];
		for (int t = 0; t < T; t++) {
			//덤프값받기
			int dump = Integer.parseInt(buffer.readLine());
			//배열 담기
			st = new StringTokenizer(buffer.readLine()," ");
			for (int i = 0; i < 100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			//최댓값, 최솟값의 위치를 저장할 변수 저장
			int maxIdx = 0, max = 0;
			int minIdx = 0, min = 0;
			for (int i = 0; i <= dump; i++) {
				for (int j = 0; j < 100; j++) {
					maxIdx = arr[j] > arr[maxIdx] ? j : maxIdx;
					minIdx = arr[j] < arr[minIdx] ? j : minIdx;
				}
				max = arr[maxIdx];
				min = arr[minIdx];
                if(max == min) break;

				arr[maxIdx] = arr[maxIdx] - 1;
				arr[minIdx] = arr[minIdx] + 1;
			}
			
			int result = max-min;
			System.out.printf("#%d %d\n", t+1, result);
		}
	}
}
