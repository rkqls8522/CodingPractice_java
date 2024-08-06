
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		for (int t = 1; t <= T; t++) {
			//배열을 만들어서 쓰는 것이 아니라 스택을 이용하는 것이라서 사실 지금은 size 안 쓰일 것.
			int size = Integer.parseInt(br.readLine());
			//중위표현식 입력받기
			String midMarkStr = br.readLine();
			
			//내가 만든 계산기를 이용하자!
			int answerValue = calculator(midMarkStr);
			
			//출력할 문자열 만들기
			sb.append("#").append(t).append(" ").append(answerValue).append("\n");
		}
		//최종 출력
		System.out.print(sb);
	}

	//중위표기식을 받아서 결과값을 반환하는 계산기를 만들자
	static int calculator(String midMarkStr) {
		//중위표기식을 후위 표기식으로 바꾸는 함수 호출
		String endMarkStr = toEndMarkCal(midMarkStr);
		//계산하는 함수 호출
		int answerValue = endMarkStrToAnswerValue(endMarkStr);
		//최종 값 출력
		return answerValue;
	}
	
	
	
	//우선순위를 보기 위해 키와 우선순위값이 매칭되는 map을 생성해서 사용한다.
	static Map<Character, Integer> priorityMap = new HashMap<>();
	//값을 초기화해준다.
	static {
		priorityMap.put('+', 1); //더하기는 우선순위 1
		priorityMap.put('*', 2); //곱하기는 우선순위 2
	}
	
	//중위표기식을 받아서 후위표기식으로 바꾸는 함수
	public static String toEndMarkCal(String midMarkStr) {
		//출력할 문자열 만들기
		String endMarkStr = "";
		//stack이용하기. 문자를 담을 것임
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < midMarkStr.length(); i++) {
			//후위표기식으로 만들기 위한 문자를 하나하나 받음
			char c = midMarkStr.charAt(i);
//			System.out.println("현재 c : " + c);
			if (c >= '0' && c <= '9') { //숫자라면 출력
				endMarkStr += c;
			} else if(c == '(') {
				stack.push(c); //여는 괄호라면 스택에 쌓기
			} else if(c == ')') {
				//닫는 괄호를 만나면 여는 괄호를 만날 때까지 다 출력
				char popItem = stack.pop();
				while(popItem != '(') {
					endMarkStr += popItem;
					popItem = stack.pop(); //이때 pop됐으니 여는 괄호를 만나면 걔는 지워질 것!
				}
			} else { //연산자일 때 하는 액션
				//자신의 우선순위보다 같거나 높은 애들 다 꺼낸다.
				//여는 괄호는 꺼내지 않는다.
				//다 꺼내고 나면 자기가 들어간다.
				//스택이 비면 꺼내지 않고 바로 자기가 들어간다.
				while (!stack.isEmpty() && stack.peek() != '(' && priorityMap.get(stack.peek()) >= priorityMap.get(c)) {
					char popItem = stack.pop();
					endMarkStr += popItem;
				}
				//과정을 마쳤으면 이제 자기가 들어간다.
				stack.push(c);
			}
		}
		
		//마무리 : 스택을 비워줘야 한다.
		while (!stack.isEmpty()) {
			endMarkStr += stack.pop();
		}
		return endMarkStr;
	}
	
	
	
	//후위표기식을 받아서 계산된 결과값을 출력하는 함수
	public static int endMarkStrToAnswerValue(String endMarkStr) {
		//스택을 이용하자
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < endMarkStr.length(); i++) {
			char c = endMarkStr.charAt(i);
			
			//피연산자라면 스택에 넣어준다.
			if(c >= '0' && c <= '9' ) {
				stack.push(c-'0');
			} else { //연산자라면 스택의 숫자를 꺼내서 연산을 수행한다.
				int num2 = stack.pop();
				int num1 = stack.pop();
				int result;
				
				if(c == '+') result = num1 + num2; 
				else result = num1 * num2;
				
				stack.push(result);
			}
		}
		//결론적으로 연산수행이 다 끝난 뒤에는 스택에 값이 하나만 남게 된다.
		return stack.pop();
	}
}
