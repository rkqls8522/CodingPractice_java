
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	//상하좌우 순서
	static final int[][] delta = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static int N, M, safeZoneCnt;
	static int[][] map, tempMap;
	static Queue<Virus> virusQ;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		tempMap = new int[N][M];
		safeZoneCnt = 0; //안전영역 크기를 일단 0으로
		//너비탐색은 queue활용
		virusQ = new LinkedList<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0); // 벽세우자

		System.out.print(safeZoneCnt);
	}

	//dfs로 벽세우기
	private static void dfs(int wallCnt) {
		
		//기저조건 : wall이 3개일 때
		if(wallCnt == 3) {
			bfs(); //바이러스 퍼진 후, 안전영역 세기
			return;
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 0) { //연구소에서 이 칸이 빈 칸이라면
					map[r][c] = 1; //벽을 세우자!
					
					dfs(wallCnt+1); //다음 벽을 세우자!
					map[r][c] = 0; //모든 경우의 수를 봐야 하므로, 벽 없애자!
				}
			}
		}
	}

	//bfs로 바이러스 퍼진 뒤의 안전영역 계산하기
	private static void bfs() {
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 2) { //바이러스가 있다면
					virusQ.add(new Virus(r,c)); //해당 바이러스를 큐에 추가!
				}
			}
		}
		
		//tempMap에 copy하기. 이 때, 깊은 복사를 위해 줄 하나하나 바꾸기.
		for (int i = 0; i < map.length; i++) {
			tempMap[i] = map[i].clone(); //이래야 깊은 복사
		}
		
		//바이러스 퍼트리자
		while(!virusQ.isEmpty()) { //비어있지 않으면 계속 수행해
			Virus thisVirus = virusQ.poll(); //바이러스 꺼내!
			
			//상하좌우를 볼까~
			for (int i = 0; i < delta.length; i++) {
				int dr = thisVirus.r + delta[i][0]; //행
				int dc = thisVirus.c + delta[i][1]; //열
				
				//범위 Check~!
				if(dr >= 0 && dc >= 0 && dr < N && dc < M) {
					//빈칸이면 바이러스 퍼트려~
					if(tempMap[dr][dc] == 0) {
						virusQ.add(new Virus(dr,dc)); //새 바이러스!
						tempMap[dr][dc] = 2; //바이러스 퍼졌어!
					}
				}
			}
		}//바이러스 다 퍼트렸어
		//이제 안전영역의 크기를 확인하자
		safeZoneCal();
	}

	//디버깅용. tempMap 중간체크.
	private static void checkMap() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(String.format("%2d", map[r][c]));
			}
			System.out.println();
		}
	}
	
	//디버깅용. tempMap 중간체크.
	private static void tempMapCheck() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				System.out.print(String.format("%2d", tempMap[r][c]));
			}
			System.out.println();
		}
	}

	private static void safeZoneCal() {
		int tempSafeZoneCnt = 0; //임시로 0개부터 시작
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(tempMap[r][c] == 0) { //안전영역이니??
					tempSafeZoneCnt++; //갯수 세기
				}
			}
		}
		//최댓값 갱신
		safeZoneCnt = safeZoneCnt > tempSafeZoneCnt ? safeZoneCnt : tempSafeZoneCnt;
	}

	//바이러스 생성
	static class Virus{
		int r; //행
		int c; //열
		
		public Virus() {
			super();
		}
		
		public Virus(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}