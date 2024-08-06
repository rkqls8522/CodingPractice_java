import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		int N = 100;
		int[][] arr = new int[N][N];
		for (int t = 1; t <= T; t++) {
			t = Integer.parseInt(br.readLine());
			
			//배열채우기
			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken()); 
				}
			}
			
			
			//맨 마지막 줄에서 2가 나올 때 즉시 위로 가는 코드 실행
			st = new StringTokenizer(br.readLine());
			int r=N-1,c=0;
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				arr[N-1][j] = num;
				if(num == 2) {
					c = j; //2나온 위치 저장
					break;
				}
			}
			
//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(arr[i]));
//			}
			
			//좌, 우로 사다리 건너갈 것이 있는지 확인하며 이동
			boolean move;
			while (r > 0) { //행이 0이 되면 위쪽 도착한 것. 멈추기
				move = false;
				//열이 오른쪽 범위 밖으로 벗어나지 않았으며 오른쪽에 사다리가 있으면 이동
				while (c+1 < N && arr[r][c+1] == 1) {
					move = true;
					//사다리가 끊길 때까지 이동
					c++;
				}
				
				// 오른쪽으로 이동하자 마자 왼쪽으로 이동하는 것을 방지하기 위해 !move (= 이동하지 않았다면)이라는 조건문을 함께 넣음
				while (!move && c-1 >= 0 && arr[r][c-1] == 1) {
					c--;
				}
				
				r--; //위로 계속 이동
				
				
			}
			
			sb.append("#").append(t).append(" ").append(c).append("\n");
			
			
			
			
			
		}
		System.out.println(sb);
		
	}
}
