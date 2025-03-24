class Solution {
    public String solution(String rsp) {
        
        char[] cList = rsp.toCharArray();
        for(int i = 0; i < cList.length; i++){
            switch(cList[i]){
                case '2' : 
                    cList[i] = '0';
                    break;
                case '0' : 
                    cList[i] = '5';
                    break;
                case '5' : 
                    cList[i] = '2';
                    break;
            }
        }
        
        
        String answer = new String(cList);
        return answer;
    }
}