package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class 사전 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        // 가능한 전체 조합 수 확인
        if (combination(N, M, K) < K) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        int n = N, m = M;
        int k = K;

        while (n > 0 && m > 0) {
            // a를 선택했을 때 남은 조합 수 계산
            int comb = combination(n - 1, m, k);
            if (comb >= k) {
                sb.append('a');
                n--;
            } else {
                sb.append('z');
                m--;
                k -= comb;
            }
        }

        // 남은 a나 z를 모두 추가
        while (n-- > 0) sb.append('a');
        while (m-- > 0) sb.append('z');

        System.out.println(sb.toString());
    }

    private static int combination(int a, int z, int k) {
        int total = a + z;
        int min = Math.min(a, z);
        long result = 1;

        for (int i = 1; i <= min; i++) {
            result = result * (total - i + 1) / i;
            if (result > k) {
                return k + 1;
            }
        }

        return (int) result;

    }
}
