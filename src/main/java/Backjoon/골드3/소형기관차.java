package Backjoon.골드3;

import java.util.Scanner;

public class 소형기관차 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 객차 수
        int[] passenger = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            passenger[i] = sc.nextInt();
        }

        int m = sc.nextInt(); // 소형 기관차가 끌 수 있는 객차 수

        // 누적합 계산
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + passenger[i];
        }

        // 객차 인덱스, 기관차 수
        int[][] dp = new int[n + 1][4]; // 기관차 수는 최대 3

        for (int i = m; i <= n; i++) {
            for (int j = 1; j <= 3; j++) {
                dp[i][j] = Math.max(
                        dp[i - 1][j], // i번째 객차 안 쓰고 이전값 유지
                        dp[i - m][j - 1] + (sum[i] - sum[i - m])); // j번째 기관차가 이 구간을 가져간 경우
                // 이전 기관차까지 고려한 최댓값, m칸 구간에 포함된 손님 수
            }
        }

        System.out.println(dp[n][3]);
    }
}
