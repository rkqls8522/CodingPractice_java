
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	//큐 이용해서 하기
	static Queue<Integer> queue = new LinkedList<>();
	static int V, E, result;
	static int[] arrInEdge;
	static List<Integer>[] list;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = 10;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			result = 0;
			list = new ArrayList[V+1];
			
			//진입차수 세기
			arrInEdge = new int[V+1];

			//리스트 초기화
			for (int i = 1; i <= V; i++) {
				list[i] = new ArrayList<Integer>();
			}
			
			//리스트의 각 정점에 "연결된 정점"집어넣기
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < E; c++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				arrInEdge[to]++; //진입차수 추가
				list[from].add(to); //연결리스트에 정점번호 추가
			}
			
			sb.append("#").append(t);
			//위상정렬 실행
			startSort();
		}
		System.out.print(sb);
	}

	private static void startSort() {
		//진입 차수가 0인 모든 노드를 queue에 삽입
		for (int i = 1; i < arrInEdge.length; i++) {
			if(arrInEdge[i] == 0) {
				queue.add(i);
			}
		}
		
		//queue가 공백상태가 될 때까지 반복 수행
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			sb.append(" ").append(node);
			
			for (int i = 0; i < list[node].size(); i++) {
				int tempNode = list[node].get(i);
				
				//queue에서 원소를 꺼내 해당 노드에서 나가는 간선을 그래프에서 제거한다.
				//즉, 연결된 노드의 진입 차수를 감소시킨다.
				arrInEdge[tempNode]--;
				
				//새롭게 진입 차수가 0이 된 노드를 queue삽입한다.
				if(arrInEdge[tempNode] == 0) {
					queue.add(tempNode);
				}
			}
		}
		sb.append("\n");
	}
}