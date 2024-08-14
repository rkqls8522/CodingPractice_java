
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
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
			
//			//get메소드 이용
//			for (int i = 0; i < 10; i++) { //10개만 출력
//				sb.append(" ").append(pwdList.get(i));
//			}
//			
//			//Iterator는 앞으로만 갈 수 있음
//			//Iterator의 next메소드 이용
			Iterator<Integer> iter = pwdList.iterator();
			//다음 원소가 없는데 next메소드를 쓰면 에러가 남
			int cnt = 10; //10개만 꺼낼 것임
			while(iter.hasNext() && cnt > 0) { //다음 원소가 있니?
				sb.append(" ").append(iter.next()); //그럼 출력해라
				cnt--;
			}
//			
//			//Iterator의 단점을 보완한 ListIterator. 앞과 뒤로 갈 수 있음
//			ListIterator<Integer> listIter = pwdList.listIterator();
//			while(listIter.hasNext()) { //다음 원소가 있니?
//				sb.append(" ").append(listIter.next()); //그럼 출력해라				
//			}
//			while(listIter.hasPrevious()) { //이전 원소가 있니?
//				sb.append(" ").append(listIter.previous()); //그럼 출력해라
//			}
			
			
			
			
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
