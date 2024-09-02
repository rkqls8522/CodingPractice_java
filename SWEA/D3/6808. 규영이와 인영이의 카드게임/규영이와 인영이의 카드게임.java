
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {


    public static int[] calculateScores(int[] kyCard, int[] inCard) {
        int kyScore = 0;
        int inScore = 0;
        
        for (int i = 0; i < 9; i++) {
            if (kyCard[i] > inCard[i]) {
                kyScore += kyCard[i] + inCard[i];
            } else if (kyCard[i] < inCard[i]) {
                inScore += kyCard[i] + inCard[i];
            }
        }
        
        return new int[]{kyScore, inScore};
    }
    
    //순열계산
    public static void permute(int[] arr, int l, int r, List<int[]> result) {
        if (l == r) {
            result.add(arr.clone());
        } else {
            for (int i = l; i <= r; i++) {
                swap(arr, l, i);
                permute(arr, l + 1, r, result);
                swap(arr, l, i); // 돌아가~~
            }
        }
    }
    
    // 수 교환
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	StringBuilder sb = new StringBuilder();
    	
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            int[] kyCard = new int[9];
            int[] inCard = new int[9];
            boolean[] used = new boolean[19]; 
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 9; i++) {
                kyCard[i] = Integer.parseInt(st.nextToken());
                used[kyCard[i]] = true;
            }
            
            int index = 0;
            for (int i = 1; i <= 18; i++) {
                if (!used[i]) {
                    inCard[index++] = i;
                }
            }
            
            List<int[]> permutations = new ArrayList<>();
            permute(inCard, 0, 8, permutations);
            
            int winCount = 0;
            int loseCount = 0;
            
            // 모든 순열에 대해 점수를 계산
            for (int[] perm : permutations) {
                int[] scores = calculateScores(kyCard, perm);
                if (scores[0] > scores[1]) {
                    winCount++;
                } else if (scores[0] < scores[1]) {
                    loseCount++;
                }
            }
            sb.append("#").append(t).append(" ").append(winCount).append(" ").append(loseCount).append("\n");
        }
        
        System.out.print(sb);
        
    }
}
