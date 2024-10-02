
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 입력
	입력의 첫 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 10)가 주어진다. 
	각 테스트 케이스의 첫 번째 줄에는 동전의 가지 수 N(1 ≤ N ≤ 20)이 주어지고 
	두 번째 줄에는 N가지 동전의 각 금액이 오름차순으로 정렬되어 주어진다. 
	각 금액은 정수로서 1원부터 10000원까지 있을 수 있으며 공백으로 구분된다. 
	세 번째 줄에는 주어진 N가지 동전으로 만들어야 할 금액 M(1 ≤ M ≤ 10000)이 주어진다.
	
	편의를 위해 방법의 수는 231 - 1 보다 작고, 같은 동전이 여러 번 주어지는 경우는 없다.
	
   출력
	각 테스트 케이스에 대해 입력으로 주어지는 N가지 동전으로 금액 M을 만드는 모든 방법의 수를 
	한 줄에 하나씩 출력한다.
 */

//2293동전1 : 얘도 어려움..ㅜ
public class Main {
	static int N, M, T, D, maxH, result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //동전의 가짓수
			int[] arr = new int[N];
			int result = 0;

			// 동전의 가짓수에 관한 배열 완성해~
			st = new StringTokenizer(br.readLine());
			// ☆☆☆☆☆☆질문☆☆☆☆☆☆ : 매번 arr.length라 뜨는 것을 N으로 바꿔쓰는데 사실 걸리는 시간이 아예 똑같은 건가?
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken()); //동전 가짓수들 모두 받기
				arr[i] = num;
			}
			
			int M = Integer.parseInt(br.readLine()); //만들 금액
			//dp이용
			int[] dp = new int[M+1]; //M원을 만드려면 M+1이 있어야 함
			dp[0] = 1; //0원을 만들 수 있는 가짓수는 1가지임
		
			for (int i = 0; i < N; i++) {
				// 이번 머니단위는 arr[i].
				int money = arr[i];
				
				for (int j = money; j <= M; j++) {
					dp[j] += dp[j-money]; //해당 머니단위를 이용할 수 있는 금액마다 플러스해주는 셈
//					System.out.println(Arrays.toString(dp)); //진행과정을 확인
				}
//				System.out.println(); //보기좋게 줄바꿈
			}
			
			//결과를 출력하자. M원을 만들 수 있는 가짓수는 dp[M]가지이다.
			result = dp[M];

			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
}


