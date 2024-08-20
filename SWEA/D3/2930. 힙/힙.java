
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			sb.append("#").append(t);
			
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				
				if(x == 1) {
					int nextX = Integer.parseInt(st.nextToken());
					pq.offer(nextX);
				} else {
					if(pq.isEmpty()) { //비어있다면
						sb.append(" ").append(-1); //-1출력
					}
					else {
						sb.append(" ").append(pq.poll());
					}
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
