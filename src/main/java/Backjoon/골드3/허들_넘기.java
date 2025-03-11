package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 허들_넘기 {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        int T = Integer.parseInt(firstLine[2]);

        // dp
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][i] = 0;
        }

        // 간선 정보, u에서 v로 가는 간선의 허들 높이
        for (int i = 0; i < M; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            int h = Integer.parseInt(edge[2]);
            dp[u][v] = h;
        }

        // 플로이드-워셜 i -> j, i -> K -> j 경유 하는 것에서 비교
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i][k], dp[k][j]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String[] query = br.readLine().split(" ");
            int s = Integer.parseInt(query[0]);
            int e = Integer.parseInt(query[1]);
            if (dp[s][e] == INF) {
                sb.append("-1\n");
            } else {
                sb.append(dp[s][e]).append("\n");
            }
        }

        System.out.print(sb);
    }
}
