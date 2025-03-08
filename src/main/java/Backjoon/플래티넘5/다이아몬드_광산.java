package Backjoon.플래티넘5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 다이아몬드_광산 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] mine = new int[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                mine[i][j] = line.charAt(j) - '0';
            }
        }

        // 4 방향 DP 배열 (왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래)
        int[][][] dp = new int[R][C][4];

        // 왼쪽 위(0), 오른쪽 위(1) 값 채우기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (mine[i][j] == 1) {
                    if (i == 0) {
                        dp[i][j][0] = 1;
                        dp[i][j][1] = 1;
                    } else {
                        dp[i][j][0] = (j == 0) ? 1 : dp[i - 1][j - 1][0] + 1;
                        dp[i][j][1] = (j == C - 1) ? 1 : dp[i - 1][j + 1][1] + 1;
                    }
                }
            }
        }

        // 왼쪽 아래(2), 오른쪽 아래(3) 값 채우기
        for (int i = R - 1; i >= 0; i--) {
            for (int j = C - 1; j >= 0; j--) {
                if (mine[i][j] == 1) {
                    if (i == R - 1) {
                        dp[i][j][2] = 1;
                        dp[i][j][3] = 1;
                    } else {
                        dp[i][j][2] = (j == 0) ? 1 : dp[i + 1][j - 1][2] + 1;
                        dp[i][j][3] = (j == C - 1) ? 1 : dp[i + 1][j + 1][3] + 1;
                    }
                }
            }
        }

        int answer = 0;

        // 가능한 최대 다이아몬드 크기 탐색
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (mine[i][j] == 1) {
                    int n = Math.min(dp[i][j][2], dp[i][j][3]);
                    if (n == 0 || n <= answer) continue;

                    for (int _n = 1; _n <= n; _n++) {
                        int next = (_n - 1) * 2;
                        if (i + next >= R) continue;
                        if (dp[i + next][j][0] >= _n && dp[i + next][j][1] >= _n) {
                            answer = Math.max(answer, _n);
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
