
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map, favoriteFriends, favoriteCntMap, emptyCntMap;
	static int[] deltaR = {-1, 1, 0, 0};
	static int[] deltaC = {0, 0, -1, 1};
	static int[] sequence;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		favoriteFriends = new int[N*N][5];
		favoriteCntMap = new int[N][N]; // 좋아하는 학생이 인접한 칸에 몇 명이나 있는가?
		emptyCntMap = new int[N][N]; // 인접한 빈 칸 세서 맵에 넣기
		sequence = new int[N*N]; //해당 학생이 배열에서 몇 번째 순서인지 기록
		int result = 0;
		
		//map초기화
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = -1;
			}
		}
//		mapCheck();
		for (int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			favoriteFriends[i][0] = student;
			// 좋아하는 학생 4명을 배열에 저장하기
			for (int friend = 1; friend < 5; friend++) {
				int friendNumber = Integer.parseInt(st.nextToken());
				favoriteFriends[i][friend] = friendNumber;
			}
		}
		
		for (int student = 0; student < N*N; student++) {
			
			// 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
			// 2. 그 자리들 중에서 인접 칸 중 빈 칸이 많은 자리로 정하자.
			// 3. 위를 다 통과한 자리들 중에서 행과 열이 가장 작은 칸으로 자리를 정하자.
			emptyAndFavoriteCheck(student);
			
		}
//		sequenceCheck();
		int cnt = 0;
		// 4. 마지막으로, 학생의 만족도를 구하자.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				cnt = 0;
				//해당 자리에 앉은 학생은 몇 번째 순서인가
				int studentNumberSequence = sequence[map[r][c]-1];
//				System.out.println(map[r][c] + " 자리에 앉은 학생은 "+ studentNumberSequence + "번째로 봐야 하는 학생이다.");
				//상하좌우를 보며
				for(int d = 0; d < 4; d++) {
					int dr = r + deltaR[d];
					int dc = c + deltaC[d];
					
					// 범위를 체크하고
					if(checker(dr, dc)) {
						
						//좋아하는 학생이 있는지 확인
						for(int friend = 1; friend < 5; friend++) {
							if(map[dr][dc] == favoriteFriends[studentNumberSequence][friend]) {
								cnt++;
							}
						}
					}
				}
				
				//cnt값에 따라 만족도가 달라진다.
				switch(cnt) {
				case 1:
					result += 1;
					break;
				case 2:
					result += 10;
					break;
				case 3:
					result += 100;
					break;
				case 4:
					result += 1000;
					break;
				}
			}
		}
		System.out.print(result);
	}

	// 비어있는 칸들 살피고 and 좋아하는 학생췍~!
	private static void emptyAndFavoriteCheck(int student) {
		int studentNumber = favoriteFriends[student][0];
//		System.out.println("studentNumber : " + studentNumber);
		int cnt = 0;
		int maxFavoriteCnt = 0; // 해당 자리에 인접한 칸 중 좋아하는 학생이 가장 많을 때!
		
		//favoriteCntMap초기화
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				favoriteCntMap[r][c] = -1;
			}
		}
		
		//자리 다 돌기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
				//좋아하는 학생을 세기 위해 cnt초기화
				cnt = 0;
				
				// 빈 칸인가?
				if(map[r][c] == -1) {
					// 상하좌우에 좋아하는 학생이 인접해 있는가?
					for (int d = 0; d < 4; d++) {
						int dr = r + deltaR[d];
						int dc = c + deltaC[d];
						
						//범위를 벗어나는지 췍~!
						if(checker(dr, dc)) {
							//범위를 벗어나지 않았으면, 해당 자리에 좋아하는 학생이 있는지 보자
							for (int friend = 1; friend < 5; friend++) {
								int friendNumber = favoriteFriends[student][friend];
								if(map[dr][dc] == friendNumber) { //좋아하는 학생이 있다면
									cnt++;
								}
							}
						}
					}
					
					//cnt가 max갱신을 하였는가?
					if(maxFavoriteCnt < cnt) {
						maxFavoriteCnt = cnt;
					}
					
					//favoriteCntMap에 cnt를 기록
					favoriteCntMap[r][c] = cnt;
				}
				
			}
		}
//		favoriteCntMapCheck();
//		System.out.println("maxFavoriteCnt : " + maxFavoriteCnt);
		
		//자리를 다 돌았으니 맥스cnt값을 가진 자리들을 찾아서 주변 빈 칸이 가장 많은 칸으로 자리를 정하기
		int maxEmptyCnt  = 0; // 빈 칸 최댓값 갱신하기
		int emptyCnt = 0; //비어있는 칸 세기
		
		//emptyCntMap을 -1로 초기화
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				emptyCntMap[r][c] = -1;
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				//cnt 초기화하기
				emptyCnt = 0;
				
				//인접한 칸에 좋아하는 학생 수가 가장 많은 칸인 것인가?
				if(favoriteCntMap[r][c] == maxFavoriteCnt) { 
					//그렇다면 비어있는 칸 갯수를 세서 emptyCntMap에 넣자
					// 그리고 빈 칸 갯수 최댓값을 갱신하자
					
					//상하좌우를 보자
					for (int d = 0; d < 4; d++) {
						int dr = r + deltaR[d];
						int dc = c + deltaC[d];
						if(checker(dr, dc)) {
							//범위를 벗어나지 않았다면
							//그리고 해당 칸이 빈 칸이라면
							if(map[dr][dc] == -1) {
								emptyCnt++;
							}
						}
					}
					
					//상하좌우를 보며 빈 칸을 세었으니 기록하자
					emptyCntMap[r][c] = emptyCnt;
					
					//최댓값 갱신하자
					if(maxEmptyCnt < emptyCnt) {
						maxEmptyCnt = emptyCnt;
					}
					
				}
			}
		}
//		emptyCntMapCheck();
//		System.out.println("maxEmptyCnt : " + maxEmptyCnt);
		
		// 위를 다 통과한 자리들 중에서 행의 번호와 열의 번호가 가장 작은 칸으로 자리를 정하자
		out : for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(emptyCntMap[r][c] == maxEmptyCnt) {
					map[r][c] = studentNumber;
//					mapCheck();
					//해당 자리의 학생의 넘버를 기록해놓기
					sequence[studentNumber-1] = student;
//					sequenceCheck();
					break out;
				}
			}
		}
	}

	private static boolean checker(int dr, int dc) {
		return (dr >= 0 && dc >= 0 && dr < N && dc < N);
	}
	
	//디버깅용. 맵 체크
	static void mapCheck() {
		System.out.println("map체크중");
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("----------");
	}
	
	//디버깅용. 좋아하는 친구 수 맵 체크
	static void favoriteCntMapCheck() {
		System.out.println("favoriteCntMap체크중");
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(favoriteCntMap[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("----------");
	}
	
	//디버깅용. 빈칸체크한 맵 체크
	static void emptyCntMapCheck() {
		System.out.println("emptyCntMap체크중");
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				System.out.print(emptyCntMap[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("----------");
	}
	
	//디버깅용. 순서 체크
	static void sequenceCheck() {
		System.out.println("sequence체크중");
		for (int i = 0; i < N*N; i++) {
			System.out.print(sequence[i]+"  ");
		}
		System.out.println("----sequence체크끝-----");
	}
}