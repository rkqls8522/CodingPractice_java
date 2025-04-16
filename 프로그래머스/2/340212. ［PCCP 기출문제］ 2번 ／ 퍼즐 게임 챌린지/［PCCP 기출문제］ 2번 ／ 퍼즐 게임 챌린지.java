class Solution {
    //level을 이분탐색으로 찾을 것
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int row = 1;
        int high = 100000; //레벨을 조정할 것
        int mid = (row + high) /2; 
        long sum = 0;
        
        while(row <= high){
            sum = 0;
            
            for(int i = 0; i < diffs.length; i++){
                // System.out.println("diff : " + diffs[i] + ", mid : " + mid + ", i : " + i);
                
                if(diffs[i] <= mid){
                    sum += times[i];
                } else{
                    sum += (diffs[i]-mid) * (times[i] + times[i-1]) + times[i];    
                }                
            }
            
            if(sum <= limit){
                answer = mid;
                high = mid-1;
                mid = (row + high)/2;
            } else {
                row = mid + 1;
                mid = (row + high)/2;
            }
            
            
        }
        
        
        
        
        return answer;
    }
}