
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = 10;
		int size = 8;
		for (int t = 1; t <= T; t++) {
			t = Integer.parseInt(br.readLine());
			Queue<Integer> queue = new LinkedList<>();

			//큐채우기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < size; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			//사이클돌기
			out : while (true) {
				int cnt = 1;
				int temp;
				for (int i = 0; i < 5; i++) { //5번씩 반복할 것
					temp = queue.poll() - cnt++;
					if(temp <= 0) { //실행 후, 0보다 작아지는 수가 생기면
						queue.add(0); //마지막에 0을 넣고 종료
						break out;
					}
					queue.add(temp);
				}
			}
			sb.append("#").append(t);
			for (int i = 0; i < size; i++) {
				sb.append(" ").append(queue.poll());
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}	
