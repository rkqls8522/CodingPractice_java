
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] arr;
	static boolean[][] cloud, tempCloud;
	static int N, M, d, s, dr, dc;
	static int[][] delta = {{0, -1},{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
	static int cnt;
	
	//main함수 실행
	public static void main(String[] args) throws NumberFormatException, IOException {

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		cloud = new boolean[N][N];
		tempCloud = new boolean[N][N];
		
		//배열채우기
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		//처음엔 왼쪽아래 4칸에 비구름이 생김. 이건 고정임.
		cloud[N-1][0] = true;
		cloud[N-1][1] = true;
		cloud[N-2][0] = true;
		cloud[N-2][1] = true;
		
		//M번 움직일 거임.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			moveAndHide(); //이동해서 물1증가하고 구름사라졍!
			waterCopy(); //물복사!
			createCloud(); //물의 양이 2이상인 모든 칸에 구름이 생기고, 2 줄어듦.
		}
		
		//남은 물의 총합이 얼만큼인 것인가?!?
		int result = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				result += arr[r][c];
			}
		}
		System.out.print(result);
	} //main

	//구름이 움직이고 물의양 1증가시키고, 구름 사라질거양~
	private static void moveAndHide() {
		//2차원배열 복사
		tempCloud = new boolean[N][N]; //임시 배열을 만들어서 하자
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(cloud[r][c]) {//구름이 있니?
					dr = r+ delta[d-1][0] * s;
					dc = c + delta[d-1][1] * s; // s번 이동할거야.
					
					//만약 범위를 벗어나게 된다면
					if(dr >= N) dr = dr % N; //반대로 돌아왕~
					else if(dr < 0) dr = (N - ((-dr)%N))%N;
					if(dc >= N) dc = dc % N;
					else if(dc < 0) dc = (N - ((-dc)%N))%N;
					
					//그리고 해당 위치에 비가 내릴거야.
					//바구니에 저장된 물의 양이 1증가할거야.
					
					arr[dr][dc]++;
					
					//이동완료하여 구름이 사라진 곳에 true하기. 이것을 참고하여서 다음에 구름이 안 생길 것.
					tempCloud[dr][dc] = true;
				}
			}
		}
		arrayCopy(); //임시 배열의 내용을 기존 cloud에 반영하자
	} // end moveAndHide

	//2중배열은 따로 이렇게 메소드를 만들어주고 하나하나 복사해야 깊은 복사더라..
	private static void arrayCopy() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				cloud[r][c] = tempCloud[r][c];
			}
		}
	} // end arrayCopy

	// 물이 증가한 칸에 물복사버그 실행 함수.
	private static void waterCopy() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(cloud[r][c]) {
					//물의 양이 증가한 칸에 물복사버그 마법을 시전할거야.
					//대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수가 몇 개야?
					cnt = 0;
					//대각선 4방향 다 보자
					for (int d = 1; d < delta.length; d+=2) {
						dr = r + delta[d][0];
						dc = c + delta[d][1];
						//범위를 벗어나지 않았다면
						if(dr < N && dr >= 0 && dc < N && dc >= 0) {
							if(arr[dr][dc] > 0) { //대각선 방향에 물이 있다면
								cnt++; //갯수 세자~
							}
						}
					}
					//갯수 다 셌으니 이제 바구니의 물 증가타임!
					arr[r][c] += cnt;
				}
			}
		}
	} // end waterCopy
	
	//마지막으로, 구름을 다시 만들거야.
	private static void createCloud() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(!cloud[r][c]) { // 이전 move에서 구름이 사라졌던 공간이 아니고
					if(arr[r][c] >= 2) { //물의 양이 2 이상이라면
						cloud[r][c] = true; //이제 구름이 생경
						arr[r][c] -= 2; // 그리고 물의 양이 2 줄어들어
					} 
				} else cloud[r][c] = false; //다음엔 구름이 생성될 수 있는 칸.
			}
		}
	} // end createCloud
	
	//디버깅용. 현재 구름위치 체크
	private static void cloudCheck() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(String.format("%7b", cloud[r][c]));
			}
			System.out.println();
		}
	} //end cloudCheck
	
	//디버깅용. 현재 구름 옮겨지고 있는 칸 실시간체크
	private static void tempCloudCheck() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(String.format("%7b", tempCloud[r][c]));
			}
			System.out.println();
		}
	} //end tempCloudCheck
	
	//디버깅용. 물의 양 체크
	private static void waterCheck() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
	} //end waterCheck
} //end class
