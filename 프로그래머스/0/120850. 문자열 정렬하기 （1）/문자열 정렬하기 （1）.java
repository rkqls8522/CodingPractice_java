import java.util.*;
class Solution {
    public int[] solution(String my_string) {
        int[] nList = new int[my_string.length()];
        int cnt = 0;
        
        char[] cList = my_string.toCharArray();
        
        for(int i = 0; i < cList.length; i++){
            if(cList[i] >= 48 && cList[i] <= 57){
                nList[cnt++] = cList[i] -48;
            }
        }
        
        int[] answer = Arrays.copyOf(nList, cnt);
        Arrays.sort(answer);
        
        
    
        return answer;
    }
}