
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][] belt; //컨베이어 벨트. 내구도 기록하고, 로봇이 있는지 기록
	static int[] tempBelt; //회전시 사용할 임시변수
	static List<robot> robotList;
	
	static class robot{
		int d; // 해당 로봇의 위치

		public robot(int d) {
			this.d = d;
		}

		@Override
		public String toString() {
			return "robot [d=" + d + "]";
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); //내구도가 0인 칸의 갯수가 K개이면 종료
		belt = new int[N*2][2]; //내구도와 로봇유무를 함께 기록할 것
//		tempBelt = new int[2];
		robotList = new ArrayList<>();
		
		int result = 0;
		int kCnt = 0; //내구도가 0인 칸 갯수세기

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N*2; i++) {
			int durability = Integer.parseInt(st.nextToken()); //내구도 기록 
			belt[i][0] = durability;
			if(durability == 0) kCnt++; //내구도가 0인 칸이면 cnt증가
		}

		while(kCnt < K) { //kCnt센 것이 K가 될 때까지 반복
//			System.out.println("회전하기 전이야. 초기 배열을 볼 거야.");
//			beltCheck();
//			robotCheck();
//			System.out.println("다 봤어. 이제 진행하자");
			
			// 1. 벨트회전하기
			//마지막 배열값을 임시벨트배열변수에 넣어주기
			tempBelt = belt[N*2-1];
			
			// 한 칸씩 전부 옮기기
			for (int i = N*2-1; i > 0; i--) {
				belt[i] = belt[i-1]; //회전
			}
			
			//회전이 끝났으면 임시벨트배열변수에 저장한 것을 집어넣어주기
			belt[0] = tempBelt;
			
			//로봇의 위치도 새로 갱신해주기
			for (int i = 0; i < robotList.size(); i++) {
				robotList.get(i).d++;
			}
			
			//n번 칸에 있는 로봇은 즉시 내린다.
			for (int i = 0; i < robotList.size(); i++) {
				if(robotList.get(i).d == N-1) {
					belt[N-1][1]--; //로봇 줄어들고
					robotList.remove(i--); //해당 로봇 삭제
				}
			}
			
			/////////////////////////////////////
			// 2. 가장 먼저 벨트에 올라간 로봇부터 순서대로 이동한다.
			// 이동 조건은, 이동할 칸에 로봇이 없어야 하고 그 칸의 내구도 1이상이어야 한다.
//			System.out.println(robotList);
//			System.out.println("현재 벨트와 로봇을 체크해볼까");
//			beltCheck();
//			robotCheck();
			for (int i = 0; i < robotList.size(); i++) {
				int robotLocation = robotList.get(i).d; //로봇의 위치
				if(belt[robotLocation+1][1] == 0) { //이동할 칸에 로봇이 없니?
					if(belt[robotLocation+1][0] >= 1) { //이동할 칸의 내구도가 1이상이니?
						//그럼 움직여
//						System.out.println("움직일거야. 로봇의 위치 : " + robotLocation);
//						beltCheck();
						belt[robotLocation][1]--; //원래칸에서 로봇이 줄고
						if(robotLocation+1 == N-1) { //만약 로봇이 내리는 칸으로 간 거라면?
							//그럼 그 칸에서 내려
							robotList.remove(i--);
						}
						else {
							belt[robotLocation+1][1]++; // 아니라면 다음칸에 로봇이 생겨
							//그리고 로봇의 위치 갱신해
							robotList.get(i).d++;
						}
						belt[robotLocation+1][0]--; //그리고 그 칸의 내구도가 깎여
						if(belt[robotLocation+1][0] == 0) { //만약 깎인 후 내구도가 0이 되었다면?
//							System.out.println("2번째 단계에서 내구도0인 칸이 생겼엉");
							kCnt++; //갯수 세.
						}
//						System.out.println("움직였엉");
//						beltCheck();
					}
				}
			}
//			System.out.println("모든 로봇이 움직였어");
//			robotCheck();
			/////////////////////////////////////
			// 3. belt의 0번째 칸의 내구도가 1이상이면 로봇을 올령~
			if(belt[0][0] >= 1) {
				//로봇을 올려
				belt[0][1]++;
				robotList.add(new robot(0));
				
				belt[0][0]--; //그리고 내구도를 깎을 건데,
				if(belt[0][0] == 0) { //만약 깎인 후 내구도가 0이 되었다면?
//					System.out.println("3번째 단계에서 내구도0인 칸이 생겼엉");
					kCnt++; //갯수 세.
				}
			}
			result++; // 한 단계를 진행함
//			System.out.println("result : " + result + ", kCnt : " + kCnt);
		}

		System.out.print(result);
	}
	
	//디버깅용. 벨트 정보 보기
	static void beltCheck() {
		for (int i = 0; i < belt.length; i++) {
			System.out.print(belt[i][0] + "  ");
		}
		System.out.println("----------");
	}
	
	//디버깅용. 로봇 정보 보기
	static void robotCheck() {
		for (int i = 0; i < belt.length; i++) {
			System.out.print(belt[i][1] + "  ");
		}
		System.out.println("----------");
	}
}
