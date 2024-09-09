
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, maxCoreCnt, minWire, coreArrCnt;
	static boolean[][] processor;
	static int[][] coreArr;
	static final int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};//상하좌우
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			processor = new boolean[N][N];
			coreArr = new int[12][2]; //최대 12개. 위치저장할 것.
			coreArrCnt = 0;
			minWire = Integer.MAX_VALUE; //최소 전선 길이의 합을 최댓값으로 초기화
			maxCoreCnt = Integer.MIN_VALUE; //최대 연결 코어갯수를 최솟값으로 초기화
			
			//프로세서 배열채우기. 1인 곳만 true로.
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					int num = Integer.parseInt(st.nextToken());
					if(num ==1) {
						processor[r][c] = true;
						//가장자리에 있는 거 아니면 추가하자~
						if(r >0 && c>0 && r <N-1 && c <N-1) {
							coreArr[coreArrCnt][0] = r;
							coreArr[coreArrCnt][1] = c;
							coreArrCnt++;
						}
					}
				}
			}

			startConnect(0,0,0);

			sb.append("#").append(t).append(" ").append(minWire).append("\n");
		}
		System.out.print(sb);
	} //end main

	private static void startConnect(int idx, int coreCnt, int wireCnt) {
		
		//기저조건
		if(idx == coreArrCnt) { //코어들 다 봤니?
			if(maxCoreCnt < coreCnt) { //최대한 많은 코어를 연결해야 해
				maxCoreCnt = coreCnt; //최댓값 갱신해~
				minWire = wireCnt; //사용한 와이어갯수!
			} else if(maxCoreCnt == coreCnt) { //많약 연결한 코어 수가 같다면
				minWire = Math.min(minWire, wireCnt); //더 적게 사용한 전선갯수값 넣기.
			}
			return;
		}
		
		int r = coreArr[idx][0];
		int c = coreArr[idx][1];
		
		//상하좌우 탐색해
		for (int d = 0; d < delta.length; d++) {
			int tempCnt = 0;
			int dr = r;
			int dc = c;
		
			//일단 계속 이동
			while(true) {
				//전진하라
				dr += delta[d][0];
				dc += delta[d][1];

				//가장자리까지 전선을 닿게 하였는가?
				if(dr < 0 || dc < 0 || dr >= N || dc >= N) {
					break;
				}
				
				//앞으로 나아갈 수 있는가 없는가!
				if(processor[dr][dc]) { //이미 다른 코어나 전선이 있는 칸이라면
					tempCnt = 0; //0부터 다시 세
					break;
				}
				
				//전진하면서 cnt세기
				tempCnt++;
			}
			
			//코어 연결 및 전진을 완료했으니 그 자리를 true로 채워주기
			int toTrueR = r;
			int toTrueC = c;
			
			for (int i = 0; i < tempCnt; i++) {
				toTrueR += delta[d][0];
				toTrueC += delta[d][1];
				processor[toTrueR][toTrueC] = true;
			}
			
//			System.out.println("\n프로세서 체크 idx : " + idx + ", coreCnt : " + coreCnt + ", wireCnt : " + wireCnt + ", tempCnt : " + tempCnt + ", r : " + r + ", c : " + c + ", d : " + d);
//			coreCheck();
			
			//만약 tempCnt가 0이라면 == 전선을 연결할 수 없었다면
			if(tempCnt == 0) {
				//다음 코어를 보자.
				startConnect(idx+1, coreCnt, wireCnt);
			} else { //전선을 연결했다면
				//다음 코어를 보되, 연결한 코어갯수+1, 사용한 전선의 갯수+tempCnt
				startConnect(idx+1, coreCnt+1, wireCnt+tempCnt);
				
				//원상복구
				toTrueR = r;
				toTrueC = c;
				for (int i = 0; i < tempCnt; i++) {
					toTrueR += delta[d][0];
					toTrueC += delta[d][1];
					processor[toTrueR][toTrueC] = false;
				}
			}
		}// 상하좌우 보는 중. 끝.
	} // end startConnect

	//디버깅용. 현재 프로세서 확인하기.
	private static void coreCheck() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(String.format("%-6b", processor[r][c]));
			}
			System.out.println();
		}
	}
}