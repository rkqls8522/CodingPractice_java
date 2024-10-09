
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, result;
	static boolean[][] map;
	static boolean[][][] visited; //3번째 차원 배열에는 벽파괴유무기록할 것
	
	static Queue<Visit> q = new LinkedList<>();
	static int[] dr = {-1, 1, 0, 0}; //상하좌우
	static int[] dc = {0, 0, -1, 1}; //상하좌우
	
	//방문할 때마다 그 위치와 거리와 벽파괴유무를 객체에 저장해서 할 것
	static class Visit{
		int r, c, dist, destroy;

		public Visit(int r, int c, int dist, int destroy) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
			
			//벽 부쉈는지의 여부는 boolean타입으로 저장해도 되지만 통일성있게 int로 하고 싶었음.
			this.destroy = destroy; //벽 부쉈으면 1, 안 부쉈으면 0
		}

		@Override
		//얘는 없어도 되지만 디버깅용
		public String toString() {
			return "Visit [r=" + r + ", c=" + c + ", dist=" + dist + ", destroy=" + destroy + "]";
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		
		//visited[][][0]자리 : 벽을 안 부순 경로의 방문처리
		//visited[][][1]자리 : 벽을 한 번 부순 경로의 방문처리
		visited = new boolean[N][M][2];
		int result = -1; //도착못하면 -1을 출력해야 하기 때문에 -1로 초기화해두기.

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				//한 줄이 띄어쓰기 없이 주어져서 이 방법으로는 안 되더라.. 스트링 이용하자!
//				int num = Integer.parseInt(st.nextToken());
				
				int num = str.charAt(c);
				
				if(num == '1') {
					map[r][c] = true;
				}
			}
		}
		
			
		//이제 큐를 이용한 bfs수행하기
		q.add(new Visit(0,0,1,0)); //0,0에서 시작, 시작위치에도 dist는 1증가하므로 1넣어주기.
		
		//큐가 빌 때까지 새로 방문한 위치(Visit객체)를 추가하며 bfs돌 것!
		while(!q.isEmpty()) {
			Visit nowVisiting = q.poll(); //원소를 큐에서 하나 꺼내고
			int nowR = nowVisiting.r;
			int nowC = nowVisiting.c;
			
			//기저조건 : n, m위치에 도달했을 때
			if(nowR == N-1 && nowC == M-1) {
				result = nowVisiting.dist; // 현재까지의 거리를 저장하고
				break; //멈춰
			}
			
			for (int d = 0; d < 4; d++) { // 상하좌우를 볼 것이니 4번 수행.
				
				//새롭게 갈 위치
				int newRow = nowR + dr[d]; 
				int newCol = nowC + dc[d];
				
				//범위를 벗어났는지 체크
				if(newRow >= 0 && newCol >= 0 && newRow < N && newCol < M) {
					// 범위를 벗어나지 않았을 때 수행
					int newDist = nowVisiting.dist+1; // 새로 갈 위치에서는 거리+1됨
					
					//벽이 아닐 때 수행할 내용
					//현재 map에는 벽이면 true고 벽이 아니면 false가 저장되어 있음
					if(!map[newRow][newCol]) {
						
						//벽을 부신 적이 없었니?
						//현재 visited의 3번째 차원 배열의 0번째에는 벽을 부수지 않고 이동한 경로방문체크,
						//1번째에는 벽을 부순 후에 이동한 경로방문체크가 기록되어 있음.
						if(nowVisiting.destroy == 0 && !visited[newRow][newCol][0]) {
							q.add(new Visit(newRow, newCol, newDist, 0)); //벽을 부수지 않은 그 상태를 유지하며 새 객체를 큐에 추가
							visited[newRow][newCol][0] = true;
						}
						
						//벽을 부신 적이 있구나?
						//!visited[newRow][newCol][1] 의 의미 : 벽을 부순 적이 있는 경로상에서의 방문체크를 보고, false면 가기!
						else if(nowVisiting.destroy == 1 && !visited[newRow][newCol][1]) {
							q.add(new Visit(newRow, newCol, newDist, 1)); //벽을 부순 그 상태를 유지하며 새 객체를 큐에 추가
							visited[newRow][newCol][1] = true;
						}
					}
					
					//벽일 때 수행할 내용
					else {
						//벽을 부순 적이 없는 친구니?
						if(nowVisiting.destroy == 0) {
							//그렇다면 한 번 부술 기회가 남아 있구나!
							q.add(new Visit(newRow, newCol, newDist, 1)); //벽을 부순 상태로 집어넣기
							visited[newRow][newCol][1] = true; //벽을 부순 상태에서 지나간 것이기 때문에 visited[][][1]을 방문처리.
						}
					}
				}
			}
//			System.out.println("0번째체크");
//			checkVisited0();
//			System.out.println("1번째체크");
//			checkVisited1();
			
		}
		System.out.print(result); //정답거리 출력
	}
	
	//디버깅용. 벽을 부수지 않고 이동한 경로의 방문유무확인
	static void checkVisited0() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(String.format("%-7b", visited[r][c][0]));
			}
			System.out.println();
		}
		System.out.println("----------------\n");
	}
	
	//디버깅용. 벽을 부수고 이동한 경로의 방문유무확인
	static void checkVisited1() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(String.format("%-7b", visited[r][c][1]));
			}
			System.out.println();
		}
		System.out.println("----------------\n");
	}
}