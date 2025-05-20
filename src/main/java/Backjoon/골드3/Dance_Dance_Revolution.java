package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Dance_Dance_Revolution {

    static int[][][] dp;
    static int[] moves;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> input = new ArrayList<>();
        while(true) {
            int x = Integer.parseInt(st.nextToken());
            if (x == 0) break;
            input.add(x);
        }
        int n = input.size();
        moves = new int[n];
        for (int i = 0; i < n; i++) {
            moves[i] = input.get(i);
        }

        dp = new int[n + 1][5][5];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        dp[0][0][0] = 0;
        for (int step = 0; step < n; step++) {
            int next = moves[step];
            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    if (dp[step][l][r] == INF) continue;
                    if (next != r) {
                        dp[step + 1][next][r] = Math.min(dp[step + 1][next][r], dp[step][l][r] + cost(l, next));
                    }
                    if (next != l) {
                        dp[step + 1][l][next] = Math.min(dp[step + 1][l][next], dp[step][l][r] + cost(r, next));
                    }
                }
            }
        }
        int ans = INF;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                ans = Math.min(ans, dp[n][i][j]);

        System.out.println(ans);

    }

    static int cost(int from, int to) {
        if (from == 0) return 2;
        if (from == to) return 1;
        if ((from == 1 && to == 3) || (from == 3 && to == 1) ||
                (from == 2 && to == 4) || (from == 4 && to == 2)) return 4;
        return 3;
    }
}
