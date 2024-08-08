
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int guestCnt = Integer.parseInt(st.nextToken()); //N입력받기
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			//손님은 1명이상 100명이하
			int guestIdx = 0;
			int[] guestArr = new int[100];
			//손님 오시는 시간
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < guestCnt; i++) {
				int time = Integer.parseInt(st.nextToken());
				guestArr[guestIdx++] = time;
			}
			//손님 정렬행~
//			//버블정렬을 써볼까용용용
//			for (int i = 0; i < guestIdx; i++) {
//				for (int j = 0; j < guestIdx-i-1; j++) {
//					if(guestArr[j] > guestArr[j+1]) {
//						int temp = guestArr[j];
//						guestArr[j] = guestArr[j+1];
//						guestArr[j+1] = temp;
//					}
//					System.out.println(Arrays.toString(guestArr));
//				}
//			}
			Arrays.sort(guestArr, 0, guestCnt);
			
			//초당 붕어빵 갯수~
			int[] bunArr = new int[guestArr[guestIdx-1]+1];
			//초단위로 붕어빵의 갯수값넣기~
			for (int i = 0; i < bunArr.length; i++) {
				bunArr[i] = i/M*K;
			}
			
			//출력할 값
			String result = "Possible";
			//손님에게 준 붕어빵의 갯수
			int sellBunCnt = 0;
			//손님에게 줄 수 있는지 보자!
			for (int i = 0; i < guestIdx ; i++) {
				if(bunArr[guestArr[i]] - sellBunCnt > 0) { //손님이 왔을 때 붕어빵이 있다면
					sellBunCnt++; //하나 팔기
				} else { //없다면 끝낭
					result = "Impossible";
					break;
				}
			}
			
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
		
	}
}
