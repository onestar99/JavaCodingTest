package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * 최소 비용으로 앱을 비활성화하는 방법 찾기
 * dp[j] -> j의 비용으로 확보할 수 있는 최대 메모리
 * dp[j] = max(dp[j],dp[j−cost[i]]+memory[i])
 */

public class 앱 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] memory = new int[N];
        int[] cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int totalCost = Arrays.stream(cost).sum();
        int[] dp = new int[totalCost + 1];

        Arrays.fill(dp, 0);

        for (int i = 0; i < N; i++) {
            for (int j = totalCost; j >= cost[i]; j--) {
                // 이전 비용 j - cost[i]로 확보한 메모리에 현재 앱을 비활성화하여 추가로 확보한 메모리를 더한 값.
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);
            }
        }


        int result = Integer.MAX_VALUE;
        for (int i = 0; i <= totalCost; i++) {
            if (dp[i] >= M) {
                result = Math.min(result, i);
            }
        }

        System.out.println(result);
    }
}
