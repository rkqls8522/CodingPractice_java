
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//테스트 케이스가 10번이니 10번 수행할 것
		int T = 10;
		for (int t = 1; t <= T; t++) {
			//배열이 아닌 stack을 이용할 것이므로 이 size는 현재 사용x
			int size = Integer.parseInt(br.readLine());
			//중위계산식을 입력받자
			String midMarkStr = br.readLine();
			
			//계산기를 이용하자
			int answerValue = calculator(midMarkStr);
			//출력 문자열을 만들자
			sb.append("#").append(t).append(" ").append(answerValue).append("\n");
		}
		//출력하자
		System.out.print(sb);
	}

	public static int calculator(String midMarkStr) {
		//중위 표기식을 받아서 후위 표기식으로 만들자
		String endMarkStr = midMarkToEndMark(midMarkStr);
		//후위 표기식을 받아서 계산하자
		int answerValue = endMarkStrToValue(endMarkStr);
		return answerValue;
	}

	//중위 표기식을 받아서 후위표기식으로 만드는 함수
	public static String midMarkToEndMark(String midMarkStr) {
		//리턴할 후위표기식
		String endMarkStr = "";
		//스택을 이용하자!
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < midMarkStr.length(); i++) {
			//문자열 내의 문자 하나하나 꺼내서 볼 것임
			char c = midMarkStr.charAt(i);
			
			//피연산자(숫자)라면 리턴할 문자열에 추가할 것.
			if(c >= '0' && c <= '9') endMarkStr += c;
			//여는 괄호라면 스택에 집어넣을 것
			else if(c == '(') stack.push(c);
			//연산자라면, '+' 하나뿐일테니, 연산자 우선순위 생각하지 말고 다 꺼내!
			else if( c == '+') {
				//스택이 비어있지 않고 마지막 값이 여는 괄호가 아니라면 다 꺼낼 것
				while(!stack.isEmpty() && stack.peek() != '(') {
					endMarkStr += stack.pop();
				}
				//이제 나 들어갈래~
				stack.push(c);
			}
			//닫는 괄호라면 여는 괄호를 만날 때까지 다 꺼내서 리턴할 문자열에 추가할 것
			else {
				char popValue = stack.pop();
				while (popValue != '(') {
					endMarkStr += popValue;
					popValue = stack.pop();
				}
			}
		}
		//스택 비워주자
		while (!stack.isEmpty()) endMarkStr += stack.pop();
		
		//후위표기식 반환
		return endMarkStr;
	}
	
	//후위 표기식을 받아서 계산해주는 함수
	public static int endMarkStrToValue(String endMarkStr) {
		//리턴할 값 = 모든 연산을 수행한 값.
		int answerValue = 0;
		//스택을 이용해볼까~~~~
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < endMarkStr.length(); i++) {
			//문자열의 문자값 하나하나 다 꺼내서 보기
			char c = endMarkStr.charAt(i);
			
			if(c == '+') { //연산자라면 할 행동
				int num2 = stack.pop();
				int num1 = stack.pop();
				//연산한 것 집어넣엉~!~!~!!~!~!~!오에~~~~~~
				stack.push(num1 + num2);
			} else { //피연산자(=숫자)라면 할 행동이얌!
				//이번엔 숫자를 스택에 집어넣을꼬얌!! 내맘이얌~~
				stack.push(c-'0'); 
			}
		}
		answerValue = stack.pop();
		//최종적으로 스택에는 값이 하나만 남아있을거예여. 아니라구여? 그럼 문제가 잘못된 거임.암튼 그럼
		
		//연산 완료된 값 하나를 리턴
		return answerValue;
	}
}
