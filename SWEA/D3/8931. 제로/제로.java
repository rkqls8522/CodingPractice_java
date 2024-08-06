import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int T = Integer.parseInt(br.readLine());
         
        for (int t = 1; t <= T; t++) {
             
            int K = Integer.parseInt(br.readLine());
            Stack<Integer> stack = new Stack<>();
            int num;
            for (int k = 0; k < K; k++) {
                num = Integer.parseInt(br.readLine());
                if(num !=0) {
                    stack.push(num);
                }else {
                    stack.pop();
                }
            }
             
            //합 구하기
            int sum = 0;
            for (Integer integer : stack) {
                sum += integer;
            }
             
            sb.append("#").append(t).append(" ").append(sum).append("\n");
             
        }
         
        System.out.print(sb);
         
    }
}