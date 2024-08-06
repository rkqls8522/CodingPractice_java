
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		for (int t = 1; t <= T; t++) {
			int size = Integer.parseInt(br.readLine());
			String str = br.readLine();
			char[] arr = new char[size];
			int arrSize = 0;
			int result = 0;
			end : for (int i = 0; i < size; i++) {
				switch(str.charAt(i)){
				case '<' :
				case '(' :
				case '{' :
				case '[' : arr[arrSize++] = str.charAt(i); break;
				case '>' : if(arr[arrSize-1] == '<') { arr[--arrSize] = ' '; break;} else break end; 
				case ')' : if(arr[arrSize-1] == '(') { arr[--arrSize] = ' '; break;} else break end; 
				case '}' : if(arr[arrSize-1] == '{') { arr[--arrSize] = ' '; break;} else break end; 
				case ']' : if(arr[arrSize-1] == '[') { arr[--arrSize] = ' '; break;} else break end; 
				}
//				System.out.println(arrSize);
			}
			
			if(arrSize == 0) result = 1;
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		
		System.out.print(sb);
	}
}
