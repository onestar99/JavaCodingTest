package Backjoon;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] heights = new int[N];

        for (int i = 0; i < N; i++) {
            heights[i] = sc.nextInt();
        }
        sc.close();

        int maxAnswer = 0;
        for (int i = 0; i < N; i++) {

            int visibleCount = 0;

            // Left direction
            double leftSlope = Double.NEGATIVE_INFINITY;  // 기울기 초기화 (음의 무한대로 설정)
            for (int j = i - 1; j >= 0; j--) {
                double slope = (double) (heights[j] - heights[i]) / (j - i);  // 기울기 계산
                if (slope > leftSlope) {  // 기울기가 더 크면 (높은 빌딩이 낮은 빌딩을 가리지 않음)
                    visibleCount++;
                    leftSlope = slope;
                }
            }

            // Right direction
            double rightSlope = Double.NEGATIVE_INFINITY;  // 기울기 초기화 (음의 무한대로 설정)
            for (int j = i + 1; j < N; j++) {
                double slope = (double) (heights[j] - heights[i]) / (j - i);  // 기울기 계산
                if (slope > rightSlope) {  // 기울기가 더 크면 (높은 빌딩이 낮은 빌딩을 가리지 않음)
                    visibleCount++;
                    rightSlope = slope;
                }
            }

            // 높은 빌딩을 낮은 빌딩이 가리지 않는 경우 (기울기 기준 고려)
            leftSlope = Double.POSITIVE_INFINITY;  // 기울기 초기화 (양의 무한대로 설정)
            for (int j = i - 1; j >= 0; j--) {
                double slope = (double) (heights[i] - heights[j]) / (i - j);  // 기울기 반대 방향으로 계산
                if (slope < leftSlope) {  // 기울기가 더 작으면 (낮은 빌딩에서 높은 빌딩을 볼 수 있음)
                    visibleCount++;
                    leftSlope = slope;
                }
            }

            rightSlope = Double.POSITIVE_INFINITY;  // 기울기 초기화 (양의 무한대로 설정)
            for (int j = i + 1; j < N; j++) {
                double slope = (double) (heights[i] - heights[j]) / (i - j);  // 기울기 반대 방향으로 계산
                if (slope < rightSlope) {  // 기울기가 더 작으면 (낮은 빌딩에서 높은 빌딩을 볼 수 있음)
                    visibleCount++;
                    rightSlope = slope;
                }
            }

            maxAnswer = Math.max(maxAnswer, visibleCount);
        }

        System.out.println(maxAnswer);
    }
}