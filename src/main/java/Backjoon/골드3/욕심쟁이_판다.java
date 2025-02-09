package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 욕심쟁이_판다 {

    private static int[][] dp; // 좌표 (X, Y)의 최대 방문 칸 개수 저장
    private final static int[] dx = {1, -1, 0, 0};
    private final static int[] dy = {0, 0, 1, -1};
    private static int[][] bambooMap; // 대나무 맵
    private static int n;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n][n];
        bambooMap = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                bambooMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DFS + DP
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dfs(i, j));
            }
        }

        System.out.println(result);


    }

    private static int dfs(int x, int y) {

        // 이미 방문하여 계산 된 적이 있다면 그 값을 가져오기
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        // 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n && bambooMap[nx][ny] > bambooMap[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
            }
        }

        return dp[x][y];

    }


}
