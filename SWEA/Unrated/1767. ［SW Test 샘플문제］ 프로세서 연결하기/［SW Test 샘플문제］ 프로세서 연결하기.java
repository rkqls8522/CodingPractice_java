
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	static class Point{
		int r;
		int c;
		
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	//전역변수로 쓸 것들 static으로 빼기
	private static ArrayList<Point> coreList;
	private static int N;
	private static int[][] m;
	private static int outLineCoreCnt;
	private static int minWireSum;
	private static int totalCoreCnt;
	private static int maxCoreCnt;
	private static final int[] dr = {-1,1,0,0};
	private static final int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			m = new int[N][N];
			coreList = new ArrayList<>();
			outLineCoreCnt = 0;
			
			for (int r = 0; r < N; r++) {
				String s = br.readLine();
				for (int c = 0, index=0; c < N; c++, index += 2) {
					m[r][c] = s.charAt(index); // '0' : 빈 셀, '1' : core
					if(m[r][c] == '1') { //core면
						if(r==0 || c==0 || r == N-1 || c == N-1) {
							outLineCoreCnt++;
						} else {
							coreList.add(new Point(r,c));
						}
					}
				}
			}
			totalCoreCnt = coreList.size() + outLineCoreCnt; //총 코어의 수
			maxCoreCnt = 0; // 작업해서 전원연결된 코어의 수
			minWireSum = Integer.MAX_VALUE; //코어가 최대일 때 최소 와이어 수
			dfs(0,outLineCoreCnt, 0, 0);

			sb.append("#").append(t).append(" ").append(minWireSum).append("\n");
		}
		System.out.print(sb);
	} // end main

	/** index : Core순번, coreCnt : 현재까지 전원이 연결된 core개수,
	 * wireSum : 전선길이의 합, noCore : 연결안하겠다고 선언한 코어의 개수 
	 */
	private static void dfs(int index, int coreCnt, int wireSum, int noCore) {
		//가지치기
		if(maxCoreCnt > totalCoreCnt - noCore) { //최댓값이 업데이트되지 않을 경우
			return;
		}
		
		//기저조건
		if(index == coreList.size()) { //끝 코어까지 다 생각했으면 끝.
			if(maxCoreCnt < coreCnt) { //최대 개수이면
				maxCoreCnt = coreCnt;
				minWireSum = wireSum;
			} else if(maxCoreCnt == coreCnt && minWireSum > wireSum) { //저 적은 전선을 쓸 수 있다면
				minWireSum = wireSum; //갱신하자
			}
			return;
		}
		
		// 각 코어를 상하좌우로 연결가능하면(위쪽 끝까지 빈칸이어야함),
		// 연결해보기 (맵에 표시남기기, 나중에는 표시 제거하기)
		// 연결 안 하는 것도 고려하자
		Point core = coreList.get(index);
		dfs(index+1, coreCnt, wireSum, noCore+1); // 연결을 안 하는 경우
		for (int dir = 0; dir < dr.length; dir++) {
			if(check(core.r, core.c, dir)) { //연결이 가능하니?
				int cntWire = fill(core.r, core.c, dir, '2');//해당 방향으로 전선깔기
				dfs(index+1, coreCnt+1, wireSum + cntWire, noCore); //위쪽연결후
				fill(core.r, core.c, dir, '0');//해당방향으로 전선깔은 것을 원복하기
			}
		}
	}

	/**core위치에서 외곽으로 직선연결/해제, 해당맵에 작성, 사용한 전선의 길이 리턴
	 */
	private static int fill(int r, int c, int dir, char val) {
		int cntWire = 0; //사용된 전선의 길이
		for (int i = 1; true ; i++) {
			int nr = r + dr[dir] * i;
			int nc = c + dc[dir] * i;
			if(0 > nr || 0> nc || nr >= N || nc >= N) { //외곽에 도착했니?
				return cntWire;
			}
			m[nr][nc] = val;
			cntWire++;
		}
	} //end fill

	/** core위치에서 외곽으로 직선연결이 가능한지 체크
	 * (r,c) : 코어좌표
	 * dir : 방향, 상하좌우 순서, 0,1,2,3순서
	 */
	private static boolean check(int r, int c, int dir) {
		for (int i = 1; true; i++) {
			int nr = r + dr[dir] * i;
			int nc = c + dc[dir] * i;
			if(0> nr || 0> nc || nr >= N || nc >= N) { //외곽에 도착했나?
				return true;
			}
			if(m[nr][nc] != '0') { // 외곽에 가기 전에, 빈칸이 아닌 칸을 만나면?
				return false;
			}
		}
	} //end check
	
	
}//end class