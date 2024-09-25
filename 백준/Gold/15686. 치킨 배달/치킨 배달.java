
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//하나하나 치킨집 삭제하지 말고
//조합으로 바꾸자!!
//그래야 최고의 경우의 수를 찾을 수 있다!!
//새로 해!!

public class Main {
	static int N, M, map[][], cityCnt, cityMinCnt;
	
	//집위치관리
	static List<House> houseList;
	static class House{
		int r;
		int c;
		
		public House(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return "House [r=" + r + ", c=" + c + "]";
		}
	}
	
	//치킨집 위치관리
	static List<Chicken> chickenList;
	static int chickenListSize;
	static class Chicken{
		int r;
		int c;
		
		public Chicken(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return "Chicken [r=" + r + ", c=" + c + "]";
		}
	}

	//조합 때 사용할 것
	static Chicken[] chickenArr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		houseList = new ArrayList<>();
		chickenList = new ArrayList<>();
		chickenArr = new Chicken[M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int type = Integer.parseInt(st.nextToken());
				map[r][c] = type;
				
				if(type == 1) { //집이라면
					houseList.add(new House(r,c)); //집추가
				} else if(type == 2) { //치킨집이라면
					chickenList.add(new Chicken(r, c)); //치킨집추가
				}
			}
		}
		
		chickenListSize = chickenList.size();
		
		cityCnt = 0; //얘는 없어도 됨. 읽기 편하라고 있음
		cityMinCnt = Integer.MAX_VALUE;
		
//		distanceCal(); //도시의 치킨거리 계산하기
//		System.out.println("초기 cityMinCnt : " + cityMinCnt);
		
		//조합 실행하자
		combination(0,0);
		
		System.out.print(cityMinCnt);
	}
	
	//idx : 치킨집의 인덱스
	//sidx : 남기기로 선택한 치킨집의 인덱스
	static void combination(int idx, int sidx) {
		//기저조건
		if(sidx == M) {
//			System.out.println(Arrays.toString(chickenArr));
			//M개만큼 선택이 끝났으니 계산을 수행하자!
			distanceCal();
			
//			System.out.println(Arrays.toString(chickenArr));
			return;
		}
		if(idx == chickenListSize) { 
			return;
		}
		
		//재귀부분
		//해당 idx 번째 치킨집을 남기기로 했는지 안했는지 
		chickenArr[sidx] = chickenList.get(idx);
		combination(idx+1, sidx+1);
		combination(idx+1, sidx);
	}

	//도시의 치킨거리 계산기
	private static void distanceCal() {
		int distance = 0; //각 집의 치킨거리를 계산하자
		int minDistance = Integer.MAX_VALUE; //각 집의 치킨거리의 최솟값을 구하자
		cityCnt = 0;
//		cityMinCnt = Integer.MAX_VALUE;
		
		for (House house : houseList) { //각 집마다
			minDistance = Integer.MAX_VALUE;
			
			for (Chicken chicken : chickenArr) { //가장 가까운 치킨집과의 거리계산
				int rowDistance = Math.abs(house.r - chicken.r); //세로거리계산
				int colDistance = Math.abs(house.c - chicken.c); //가로거리계산
				distance = rowDistance + colDistance; //총거리계산
				
				//최소거리 갱신
				if(minDistance > distance) {
					minDistance = distance;
				}
			}
			
			//한집을 다 봤으면 이제 그 집의 치킨거리를 도시의 치킨거리에 더하자
			cityCnt += minDistance;
		}
		
		//도시의 치킨집 최소거리 갱신
		if(cityMinCnt > cityCnt) {
			cityMinCnt = cityCnt;
		}
	}
	
//디버깅용. 각 집의 치킨거리 계산
	static void printCityDistance() {
		
	}
}

