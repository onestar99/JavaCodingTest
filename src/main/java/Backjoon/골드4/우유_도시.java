package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 우유_도시 {

    /**
     * DFS 와 백트레킹으로 문제 풀어볼려고 했다.
     * 시간 제한이 초과날 확률이 높다. n 이 1000까지도 되기 떄문..
     *
     * DP 테이블을 만들어서 각 위치까지의 최대 먹을 수 있는 우유의 개수를 갱신한다.
     * 방향 ↘ 이렇게 흘러가니까 위쪽과 왼쪽의 값 중 큰 걸 가져와서 우유를 먹을 수 있는지 체크.
     * @param args
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] city = new int[N][N];
        int[][] dp = new int[N][N];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작지점이 딸기우유면 1로 시작.
        if (city[0][0] == 0) {
            dp[0][0] = 1;
        }

        // 첫번째 행 초기화
        for (int j = 1; j < N; j++) {
            dp[0][j] = dp[0][j - 1];
            int toMilk = dp[0][j] % 3;
            if (city[0][j] == toMilk) {
                dp[0][j]++;
            }
        }
        // 첫번째 열 초기화
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0];
            int toMilk = dp[i][0] % 3;
            if (city[i][0] == toMilk) {
                dp[i][0]++;
            }
        }

        // 남은 DP 채우기
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                int toMilk = dp[i][j] % 3;
                if (city[i][j] == toMilk) {
                    dp[i][j]++;
                }
            }
        }
        System.out.println(dp[N - 1][N - 1]);

    }
}
