
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = 10; // 테스트 케이스
		for (int t = 1; t <= T; t++) {
			//초기 셋팅
			int pwdCnt = Integer.parseInt(br.readLine()); // 암호문 갯수
//			int[] pwdArr = new int[pwdCnt];
			List<Integer> pwdList = new LinkedList<>();
			
			//암호문 배열 채워넣기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < pwdCnt; i++) {
//				pwdArr[i] = Integer.parseInt(st.nextToken());
				pwdList.add(Integer.parseInt(st.nextToken()));
			}
			
			//실행할 명령어 갯수
			int commandCnt = Integer.parseInt(br.readLine());
			//명령어 줄 읽어오기
			st = new StringTokenizer(br.readLine());
			for (int commandI = 0; commandI < commandCnt; commandI++) {
				
				String command = st.nextToken();
				
				//삽입이라면
				if(command.equals("I")) {
					int x = Integer.parseInt(st.nextToken()); //x번째부터 삽입
					int y = Integer.parseInt(st.nextToken()); //y개 암호문 삽입
					for (int i = 0; i < y; i++) {
						pwdList.add(x+i, Integer.parseInt(st.nextToken()));
					}
				}
				
				//삭제라면
				else if(command.equals("D")) {
					int x = Integer.parseInt(st.nextToken()); //x번째부터
					int y = Integer.parseInt(st.nextToken()); //y개 암호문 삭제
					for (int i = 0; i < y; i++) {
						pwdList.remove(x); //삭제되니 값 땡겨와질 것. 따라서 x+i로 하지 않음.
					}
				}
				
				//추가라면
				else {
					int y = Integer.parseInt(st.nextToken()); //y개 암호문 삽입
					for (int i = 0; i < y; i++) {
						pwdList.add(pwdList.size(), Integer.parseInt(st.nextToken()));
					}
				}
				
			}
			
			//출력할 문자열 만들기
			sb.append("#").append(t);
			for (int i = 0; i < 10; i++) { //10개만 출력
				sb.append(" ").append(pwdList.get(i));
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
