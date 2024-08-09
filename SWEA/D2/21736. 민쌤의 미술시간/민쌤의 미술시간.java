import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스
        
        // 색칠작업
        // 직사각형으로만 색칠
        // 물감색상 번호 0~10번
        // 도화지는 처음에 전체가 0번 물감으로 채워짐
        // 색칠하려는데 높은 색상이 색칠될 경우 -> 이번 색칠작업 x -> 다음 색칠로 넘어감
        // 색칠 완료 후 가장 넓은 영역에 칠해진 색상번호의 영역크기 출력하기
        
        // 앞두개 행렬좌표
        // 뒤두개 행렬좌표
        // 색상번호
    
        
        for (int testCase=1; testCase<=T; testCase++) { 
            int[] answer = new int[11]; 
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); //  행의 갯수
            int M = Integer.parseInt(st.nextToken()); // 열의 갯수
            int K = Integer.parseInt(st.nextToken()); // 사각형 모양으로 색칠 작업할 횟수
            answer[0] = N*M; 
            
            int[][] arr = new int[N][M]; // 0으로 채워진 초기판
            
            for (int i=0; i<K; i++) {
                
                st = new StringTokenizer(br.readLine());

                // (firstR, firstC)
                int firstR = Integer.parseInt(st.nextToken());
                int firstC = Integer.parseInt(st.nextToken());
                // (secondR, secondC)
                int secondR = Integer.parseInt(st.nextToken());
                int secondC = Integer.parseInt(st.nextToken());
                // 색상
                int color = Integer.parseInt(st.nextToken());
                
                boolean isPainted = false;
                
                //기존보다 높은 색이 칠해져 있냐? 확인
                out : for (int r = firstR; r <= secondR; r++) {
                    for (int c = firstC; c <= secondC; c++) {
                        if(arr[r][c] > color) {
                            isPainted = true;
                            break out;
                        }
                    }
                }
                
                if(!isPainted) {
                    // 기존보다 컬러가 값이 작은 부분만 새로운 색으로 색칠
                    for (int r=firstR; r<=secondR; r++) {
                        for (int c=firstC; c<=secondC; c++) {
                            answer[arr[r][c]] -= 1;
                            arr[r][c] = color;
                            answer[color] += 1;
//                            if(arr[r][c] < color) { // 새로 칠해지는 경우

                            } 
                            
                        }
                    }
                
                }
                Arrays.sort(answer);
                sb.append("#").append(testCase).append(" ").append(answer[answer.length-1]).append("\n");
            }
            
        System.out.print(sb);

            
        }
        
        
    }