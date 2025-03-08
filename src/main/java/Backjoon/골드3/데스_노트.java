package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 순서대로 배치하는데,
 * 전체 비용을 최소로 하는 배치 찾기 -> DP
 * 최적 부분 구조? 이거 왠지 네모칸에 최대 몇개의 네모가 들어갈 수 있는지 이런것도 가능할 것 같기도 하고..
 * 최대한 한 줄에 배치시키면 그게 best, 그게 안되면 Cost를 최소로 낮추는 방향으로 진행
 */

public class 데스_노트 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] lengths = new int[N];
        for (int i = 0; i < N; i++) {
            lengths[i] = Integer.parseInt(br.readLine());
        }

        // 누적 합 배열(prefixSum) 생성 (0-indexed: prefixSum[i]는 0~i-1까지의 합)
        // 여러 이름의 길이를 더하는 작업이 반복되기 때문에, 누적합 배열 사용
        int[] prefixSum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + lengths[i - 1];
        }

        // dp 배열: dp[i]는 i번째 이름부터 끝까지 쓸 때 최소 비용
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;  // 마지막 이후의 비용은 0

        // 역순으로 dp 계산 (이미 dp[j+1] 값들이 계산이 되었기 때문 ,dp[N] = 0)
        for (int i = N - 1; i >= 0; i--) {
            for (int j = i; j < N; j++) {
                // 이름들을 i부터 j까지 한 줄에 쓸 때 필요한 길이 계산
                // 공백은 (j - i)개 필요
                // @param lengthSum : i번째 이름부터 j번째 이름까지 한 줄에 배치할 때 필요한 전체 길이
                int lengthSum = prefixSum[j + 1] - prefixSum[i] + (j - i);
                if (lengthSum > M) break; // j를 늘리면 M을 초과

                int cost = 0;
                if (j < N - 1) { // 마지막 줄이 아니면 남은 칸의 제곱을 비용에 추가
                    cost = (M - lengthSum) * (M - lengthSum);
                }
                dp[i] = Math.min(dp[i], cost + dp[j + 1]);
            }
        }

        System.out.println(dp[0]);
    }
}
