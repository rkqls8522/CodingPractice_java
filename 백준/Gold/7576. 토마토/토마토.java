
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, map[][], badTomato;
	static int[] dr = {-1,1,0,0}; //상하좌우
	static int[] dc = {0,0, -1, 1}; //상하좌우
	
	//숙성된 토마토 위치를 보자. 숙성된 토마토를 저장하자
	static Queue<Tomato> tomatoQueue = new LinkedList<>();
	static class Tomato{
		int r;
		int c;
		
		public Tomato(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return "Tomato [r=" + r + ", c=" + c + "]";
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); //가로칸수
		N = Integer.parseInt(st.nextToken()); //세로칸수
		map = new int[N][M];
		int timeResult = -1;
		
		badTomato = 0; //익혀야 하는 토마토의 갯수는 0개부터 cnt시작
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) {
					tomatoQueue.add(new Tomato(r,c)); //익혀진 토마토면 토마토객체를 리스트에 추가
				}
				else if(map[r][c] == 0) badTomato++; //익혀야 하는 토마토 갯수카운트
			}
		}

		if(badTomato == 0) { //익힐 토마토가 없으면
			System.out.print(0); //0을 출력하고
			return; //끝내
		}
		
		//토마토 숙성 시뮬레이션
		boolean ripen = true; //이번 시뮬레이션때 익혔니? 일단 true로 시작
		while(ripen) { //익혔다면 새 시뮬레이션 시작. 더 익힐 것이 없다면 끝내
			int tempBadTomatoCnt = badTomato; //기억해뒀다가 while문의 조건식에 쓰이기
//			System.out.println("tempBadTomatoCnt : " + tempBadTomatoCnt);
			
			timeResult++;
			ripen = false; //각 시뮬레이션마다 숙성여부 초기화
			
			//토마토를 리스트에서 하나씩 위치를 꺼내서 보자~
			int tomatoListSize = tomatoQueue.size();
			for (int i = 0; i < tomatoListSize; i++) {
				Tomato tomato = tomatoQueue.poll();
				int r = tomato.r;
				int c = tomato.c;
				
				//상하좌우를 보고 만약 익힐 토마토가 옆에 있다면 익히자.
				for (int d = 0; d < 4; d++) {
					int row = r + dr[d];
					int col = c + dc[d];
					
					//범위 벗어났는지 체크
					if(row < N && col < M && row >= 0 && col >= 0) {
						if(map[row][col] == 0) { //익혀야 하는 토마토니?
//							System.out.print("row : " + row + ", col : " + col + ", badTomato : " + badTomato);
							map[row][col] = 1; //익히자
							badTomato--; //익혀야 할 토마토갯수가 줄었엉!!
							tomatoQueue.add(new Tomato(row,col)); //익힌 토마토객체 추가
						}					
					}
				}
			}
			
//			System.out.println("badTomato : " + badTomato);
			if(tempBadTomatoCnt != badTomato) { //숙성되지 않은 토마토의 갯수가 변경됐니?
				ripen = true; //이번 시뮬레이션때 숙성을 했엉~
			}
		} //시뮬레이션 끝났어
		
		//토마토를 더 익힐 것이 없어서 시뮬레이션이 끝났는데,
		//과연 다 익혔을까?
		if(badTomato == 0) { //더이상 익힐 토마토가 없니?
			System.out.print(timeResult); //그럼 시간 출력행
		} else System.out.print(-1); //익힐 토마토가 남았다면 -1출력
	}
}