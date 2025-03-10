package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 우주_탐사선 {

    static int N, K;
    static int[][] cost;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행성의 개수
        K = Integer.parseInt(st.nextToken()); // 행성의 위치

        cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드 워셜 사용 -> 방문한 행성 다시 중복해서 가야할수도 있어서
        for (int k = 0; k < N; k++) { // k 경유
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }

        visited = new boolean[N];
        visited[K] = true;
        backtrack(K, 1, 0);
        System.out.println(answer);

    }

    private static void backtrack(int current, int visitCount, int sum) {

        if (visitCount == N) {
            answer = Math.min(answer, sum);
            return;
        }

        if (sum >= answer) return;

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(i, visitCount + 1, sum + cost[current][i]);
                visited[i] = false;
            }
        }
    }

}
