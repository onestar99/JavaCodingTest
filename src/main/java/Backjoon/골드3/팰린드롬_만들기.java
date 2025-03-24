package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 팰린드롬_만들기 {

    static int N;
    static int[] pd;
    static int[][] dp;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        pd = new int[N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            pd[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }

        // 제일 큰 값을 찾은 다음에 맞춰 들어가기
        // 제일 큰 값이 홀수개면 양쪽으로 비교하면서 맞춰 들어가고
        // 제일 큰 값이 짝수개면 같은 부분의 양쪽으로 맞춰 들어가기, 스택으로 넣기?
        // 양 수 를 비교

        // 에반데

       int L = 0;
       int R = N - 1;
       System.out.println(recur(L, R));
    }

    private static int recur(int L, int R) {

        if (L > R) {
            return 0;
        }

        if (dp[L][R] != -1) {
            return dp[L][R];
        }

        if (pd[L] == pd[R]) { // 양쪽이 같으면
            dp[L][R] = recur(L + 1, R - 1);
        } else { // 양쪽이 다르면
            dp[L][R] = Math.min(recur(L + 1, R) + 1, recur(L, R - 1) + 1);
        }

        return dp[L][R];
    }
}
