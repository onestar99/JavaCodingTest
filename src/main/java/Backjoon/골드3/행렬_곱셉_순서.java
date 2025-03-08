package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬_곱셉_순서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] p = new int[N+1]; // p 배열: p[i-1]와 p[i]를 이용하여 행렬 i의 크기 표현
        int[][] dp = new int[N+1][N+1];

        // 행렬의 크기를 입력 받기
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            p[i-1] = (i == 1) ? r : p[i-1];
            p[i] = c;
        }

        // dp[i][i]는 0으로 초기화 (하나의 행렬은 곱셈이 필요없음)
        for (int len = 2; len <= N; len++) { // 행렬 체인 길이
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + p[i-1] * p[k] * p[j]);
                }
            }
        }

        System.out.println(dp[1][N]);
    }
}
