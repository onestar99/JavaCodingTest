package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 등산 {

    final static int[] dy = {0, 0, 1, -1};
    final static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);
        int T = Integer.parseInt(firstLine[2]);
        int D = Integer.parseInt(firstLine[3]);

        int[][] mount = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                char c = line[j];
                if (c >= 'A' && c <= 'Z') {
                    mount[i][j] = c - 'A';
                } else if (c >= 'a' && c <= 'z') {
                    mount[i][j] = c - 'a' + 26;
                }
            }
        }

        // 해당 정점 이동까지 걸리는 최소 시간
        int totalSpace = N * M;
        int[][] dp = new int[totalSpace][totalSpace];
        for (int i = 0; i < totalSpace; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        // 비용 계산 후 dp에 넣기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int curVertex = i * M + j;
                for (int d = 0; d < 4; d++) {
                    int ni = i + dy[d];
                    int nj = j + dx[d];
                    // 범위 벗어남
                    if (ni < 0 || ni >= N || nj < 0 || nj >= M)
                        continue;
                    // 인접한 칸으로의 높이 차이가 T 이하일 때만 이동 가능
                    if (Math.abs(mount[ni][nj] - mount[i][j]) <= T) {
                        int nextVertex = ni * M + nj;
                        int cost;
                        if (mount[ni][nj] > mount[i][j]) {
                            // 높은 곳으로 가면 높이 차의 제곱만큼 소요
                            int diff = mount[ni][nj] - mount[i][j];
                            cost = diff * diff;
                        } else {
                            // 낮거나 같으면 1초 소요
                            cost = 1;
                        }
                        dp[curVertex][nextVertex] = cost;
                    }
                }
            }
        }

        // 플로이드–워셜 사용, 모든 정점 간 최소 이동 시간 구하기
        for (int k = 0; k < totalSpace; k++) {
            for (int i = 0; i < totalSpace; i++) {
                for (int j = 0; j < totalSpace; j++) {
                    if (dp[i][j] > dp[i][k] + dp[k][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }

        // (0,0) 호텔의 위치
        // 모든 정점에 대해 호텔 -> 가능한 최대 높이 정점 -> 호텔,  경로의 시간 합이 D 이하이면 최대 높이 갱신
        int answer = mount[0][0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int vertex = i * M + j;
                if (dp[0][vertex] < Integer.MAX_VALUE && dp[vertex][0] < Integer.MAX_VALUE) {
                    int totalTime = dp[0][vertex] + dp[vertex][0];
                    if (totalTime <= D) {
                        answer = Math.max(answer, mount[i][j]);
                    }
                }
            }
        }
        System.out.println(answer);


    }
}
