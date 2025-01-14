package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이미 검증된 경로로 가는 것으로 처리하면 빠름
 */

public class 문자판 {

    static int N, M, K;
    static char[][] board;
    static String word;
    static int[][][] dp;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        word = br.readLine();

        //dp -1 초기화
        dp = new int[N][M][word.length()];
        for (int[][] dp2 : dp) {
            for (int[] dp3 : dp2) {
                Arrays.fill(dp3, -1);
            }
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == word.charAt(0)) {
                    result += dfs(i, j, 0);
                }
            }
        }

        System.out.println(result);

    }

    private static int dfs(int x, int y, int idx) {
        if (idx == word.length() - 1) {
            return 1;
        }

        if (dp[x][y][idx] != -1) {
            return dp[x][y][idx];
        }

        dp[x][y][idx] = 0; // 초기값
        for (int d = 0; d < 4; d++) {
            for (int step = 1; step <= K; step++) {
                int nx = x + dx[d] * step;
                int ny = y + dy[d] * step;

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (board[nx][ny] == word.charAt(idx + 1)) {
                    dp[x][y][idx] += dfs(nx, ny, idx + 1);
                }
            }
        }
        return dp[x][y][idx];
    }
}
