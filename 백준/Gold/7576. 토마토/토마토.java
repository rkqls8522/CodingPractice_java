
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    //전역변수 관리
    static int N, M;
    static int[][] box;
    static int tomatoCnt, ripeTomatoCnt, days;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {

        // 초기 설정
        init();

        //모든 토마토가 다 익기까지 걸리는 최소 날짜 계산
        bfs();

        //출력
        int result = tomatoCnt > ripeTomatoCnt? -1 : days;
        System.out.println(result);
    }

    private static void bfs() {

        //변수 설정
        int[] dr_arr = { -1, 0, 1, 0 }; //상, 우, 하, 좌
        int[] dc_arr = { 0, 1, 0, -1 }; //상, 우, 하, 좌
        int row, col, dr, dc, size;
        int[] num;

        //queue에서 하나씩 전부 꺼내며 주변의 0위치(익지 않은 토마토 위치)를 q에 추가.
        //주변에 익지 않은 토마토가 하나도 없을 때까지 반복(큐에 값이 없어질 때까지)
        while(!q.isEmpty()) {
            size = q.size();

            //하루 동안 익히기
            for (int i = 0; i < size; i++) {

                //변수 셋팅
                num = q.poll();
                row = num[0];
                col = num[1];
//                System.out.println("row = " + row + ", col = " + col);

                //해당 위치에서 주변 상, 우, 하, 좌 위치 토마토 확인
                for (int d = 0; d < 4; d++) { // 네 개의 방향
                    dr = row + dr_arr[d];
                    dc = col + dc_arr[d];

                    // 범위를 벗어나지 않았고 익지 않은 토마토가 있는 위치라면
                    if(check(dr, dc) && box[dr][dc] == 0){
                        box[dr][dc] = 1; //토마토를 익히고
                        ripeTomatoCnt++; //익은 토마토 갯수 추가
                        q.add(new int[]{dr, dc}); //q에 추가
                    }
                }
            }

            // 하루 지났으니 days 증가
            days++;
        }

        days--; //마지막 하루는 날짜 계산 x
    }

    //box범위 벗어나는지 체크
    private static boolean check(int dr, int dc) {
        if(dr < 0 || dc < 0 || dr >= N || dc >= M){
            return false;
        }
        return true;
    }

    private static void init() throws IOException {

        //버퍼를 이용하여 인풋값 빠르게 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //스트링 토크나이저 이용하여 인풋값 잘라 배열에 넣기
        StringTokenizer st = new StringTokenizer(br.readLine());

        //초기 변수 설정
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        tomatoCnt = 0; //토마토 총 갯수 세기
        ripeTomatoCnt = 0; //익은 토마토 총 갯수 세기
        days = 0; //총 걸리는 날짜 수 계산

        // 큐 사용. (r,c)위치를 넣을 것이므로 배열을 담을 queue임.
        q = new LinkedList<>();

        // 초기 맵(박스)셋팅
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] != -1) tomatoCnt++; //빈 칸이 아니라면 토마토갯수 증가.
                if(box[i][j] == 1) {
                    ripeTomatoCnt++; //익은 토마토라면 익은 토마토갯수 증가.
                    q.add(new int[]{i, j}); // queue에도 추가. 배열 말고 숫자로만 넣자
                }
            }
        }
    }
}
