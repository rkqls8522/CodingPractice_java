
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		int N = 100;
		for (int t = 1; t <= T; t++) {
			char[][] charArr = new char[N][];
			t = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				char [] forPullArr = br.readLine().toCharArray();
				charArr[i] = forPullArr;
			}
			
			//비교할 배열 만들기. 문자들 뒤집은 값 넣을 것.
			char[] tempArr, charArrCol;
			int max = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					//가로에서 보기
					//뒤에서부터 길이 1씩 자르며 비교
					int i = 0;
					while(i < N-col) {
						char[] cutCharArr = Arrays.copyOfRange(charArr[row], col, N-i);
						int size = cutCharArr.length;
						tempArr = new char[size];
						for (int j = 0; j < size; j++) {
							tempArr[j] = cutCharArr[size-j-1];
						}
						if (Arrays.equals(cutCharArr, tempArr)) {
							max = max >  size ? max : size;
						}
						i++;
					}
					
					//세로에서 보기
					//charArrCol에 값 채우기
					charArrCol = new char[N-col];
					for (int idx = col; idx < N; idx++) {
						charArrCol[idx-col] = charArr[idx][row];
					}
					//뒤에서부터 길이 1씩 자르며 비교
					i = 0;
					while(i < N-col) {
						char[] cutCharArr = Arrays.copyOfRange(charArrCol, 0, charArrCol.length-i);
						int size = cutCharArr.length;
						tempArr = new char[size];
						for (int j = 0; j < size; j++) {
							tempArr[j] = cutCharArr[size-j-1];
						}
						if (Arrays.equals(cutCharArr, tempArr)) {
							max = max >  size ? max : size;
						}
						i++;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
}
