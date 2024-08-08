
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			//카운팅하기
			int[] arr = new int[N+M+1];
			
			//1번카드부터 시작
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					arr[i+j]++;
				}
			}
			
			//최댓값구하기
			int maxCnt = Integer.MIN_VALUE; //이 문제에선 8로 초기화해줘도 된다. maxCnt=8;
			// 제일 많이 나온 값(i+j)은 뭘까?
			for (int i = 0; i < arr.length; i++) {
				maxCnt = maxCnt > arr[i] ? maxCnt : arr[i];
			}
			
			//최댓값(최대 카운트값)을 구했으니 그게 몇 종류인지 보자~~
			StringBuilder resultSbValue = new StringBuilder();
			for (int i = 0; i < arr.length; i++) {
				if(arr[i] == maxCnt) resultSbValue.append(i).append(' ');
			}
			
			//마지막 공백 제거
			resultSbValue.deleteCharAt(resultSbValue.length()-1);
			
			sb.append('#').append(t).append(' ').append(resultSbValue).append('\n');
		}
		System.out.println(sb);
	}
}
