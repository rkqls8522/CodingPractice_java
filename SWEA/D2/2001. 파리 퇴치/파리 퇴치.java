
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      StringBuilder sb = new StringBuilder();
      int T = Integer.parseInt(br.readLine());
      for (int t = 1; t <= T; t++) {
         st = new StringTokenizer(br.readLine(), " ");
         int N = Integer.parseInt(st.nextToken());
         int M = Integer.parseInt(st.nextToken());
         int[][] arr = new int[N][N];

         //arr value 채우기
         for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int col = 0; col < N; col++) {
               arr[row][col] = Integer.parseInt(st.nextToken());
            }
         }

         //델타배열만들기
          int[][] delta = new int[M*M][2];
          int dr=-1, dc=0;
          for(int i=0; i < delta.length; i++, dc++){
        	  if(i%M == 0) {
        		  dr++;
        		  dc = 0;
        	  }
              delta[i][0] = dr; 
              delta[i][1] = dc;
          }

//          System.out.println(Arrays.deepToString(delta));
          
         //이제 값 더해서 최댓값 찾기
         int sum = 0, max = 0;
         for (int r = 0; r <= N-M; r++) {
            for (int c = 0; c <= N-M; c++) {
            	for (int k = 0, idx=0; k < delta.length; k++) {
            		int arrRow = r + delta[idx][0];
            		int arrCol = c + delta[idx++][1];
            		sum += arr[arrRow][arrCol];
				}
            	max = max > sum ? max : sum;
            	sum = 0;
            }
         }
         sb.append("#").append(t).append(" ").append(max).append("\n");
         

      }
      System.out.println(sb);
   }
}