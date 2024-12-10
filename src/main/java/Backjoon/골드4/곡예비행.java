package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 곡예비행 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로 길이
        int M = Integer.parseInt(st.nextToken()); // 가로 길이

        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 상승 비행 DP 배열 초기화
        int[][] up = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                up[i][j] = Integer.MIN_VALUE;
            }
        }
        up[N-1][0] = grid[N-1][0]; // 시작점 초기화

        // 상승 비행 DP 계산
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (i < N - 1) {
                    up[i][j] = Math.max(up[i][j], up[i + 1][j] + grid[i][j]);
                }
                if (j > 0) {
                    up[i][j] = Math.max(up[i][j], up[i][j - 1] + grid[i][j]);
                }
            }
        }

        // 하강 비행 DP 배열 초기화
        int[][] down = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                down[i][j] = Integer.MIN_VALUE;
            }
        }
        down[N-1][M-1] = grid[N-1][M-1]; // 종료점 초기화

        // 하강 비행 DP 계산
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (i < N - 1) {
                    down[i][j] = Math.max(down[i][j], down[i + 1][j] + grid[i][j]);
                }
                if (j < M - 1) {
                    down[i][j] = Math.max(down[i][j], down[i][j + 1] + grid[i][j]);
                }
            }
        }

        // 최대 점수 계산
        int maxScore = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maxScore = Math.max(maxScore, up[i][j] + down[i][j]);
            }
        }

        // 결과 출력
        System.out.println(maxScore);
    }
}
