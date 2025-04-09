package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 첫 번째 색을 선택x -> 나머지 N -1 개의 색에서 K개 선택
 * 첫 번째 색을 선택 -> 두 번째 색과 마지막 색을 고를 수 없다. N - 3개에서 K-1개 선택
 *
 */

public class 색상환 {

    static final long MOD = 1000000003L; // 10억 3

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // 선택불가
        if (K > N / 2) {
            System.out.println(0);
            return;
        }

        // dp[i][j] : i개의 색 중에서 인접한 두 색이 선택되지 않도록 하여 j개를 고르는 경우의 수
        long[][] dp = new long[N + 1][K + 1];

        // 아무것도 선택하지 않는 경우는 방법이 1가지
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        // i개 중에 1개 선택 1가지
        for (int i = 1; i < N; i++) {
            dp[i][1] = i;
        }

        // DP 점화식을 이용하여 dp 테이블 채우기
        // i = 2부터 N-1까지 (i개의 색을 고려할 때)
        for (int i = 2; i < N; i++) {
            // j는 선택할 색의 개수 (2 이상부터 K까지)
            for (int j = 2; j <= K; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % MOD;
            }
        }

        // 첫 번째 색을 선택하지 않은 경우: dp[N-1][K]
        // 첫 번째 색을 선택한 경우: dp[N-3][K-1]
        long result = (dp[N - 1][K] + dp[N - 3][K - 1]) % MOD;
        System.out.println(result);
    }
}
