class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        
        //일단 통분을 해준다.
        int temp = denom1;
        numer1 *= denom2;
        denom1 *= denom2;
        numer2 *= temp;
        denom2 *= temp;
        
        //분자를 더해준다. 분자와 분모를 선언해준다.
        int numer = numer1 + numer2;
        int denom = denom1;
        
        
        //최대공약수로 나눈다.
        while(denom != 0){
            temp = denom;
            denom = numer % denom;
            numer = temp;
        }
        
        int[] answer = {(numer1+numer2)/numer, denom1/numer};
        
        
        
        return answer;
    }
}