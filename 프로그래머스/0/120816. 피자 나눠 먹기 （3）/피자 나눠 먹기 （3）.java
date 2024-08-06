class Solution {
    public int solution(int slice, int n) {
        int answer = 1;
        for(int i = slice; i < n; i+=slice) {
			answer++;
		}
        return answer;
    }
}