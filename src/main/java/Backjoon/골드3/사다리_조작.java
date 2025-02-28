package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 1. 사다리 그래프 만들기
 * 2. 배치시키기.
 * --(3 ~ 4 결과 나올 때 까지 반복)--
 * 3. 사다리 추가 0 ~ 3까지 놓아보기 (백트레킹 시도)
 * 4. 시뮬레이션을 통해 결과 확인
 */

public class 사다리_조작 {

    static int N;
    static int M;
    static int H;

    static int[][] graph;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        graph = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1; // a번 점선에서 b, b+1 연결
        }

        // 3번까지 가로선 추가하여 백트레킹 dfs 시작
        for (int i = 0; i < 4; i++) {
            dfs(0, i);
            if (result != Integer.MAX_VALUE) break;
        }

        System.out.println(result != Integer.MAX_VALUE ? result : -1);
    }

    static void dfs(int depth, int maxDepth) {

        if (depth == maxDepth) {
            if (simulate()) {
                result = Math.min(result, depth); // 최소값 갱신
            }
            return;
        }

        // 가로선 추가
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (graph[i][j] == 0 && graph[i][j - 1] == 0 && graph[i][j + 1] == 0) {
                    graph[i][j] = 1;
                    dfs(depth + 1, maxDepth);
                    graph[i][j] = 0; // 백트래킹
                }
            }
        }
    }

    // 사다리 결과 확인
    static boolean simulate() {
        for (int start = 1; start <= N; start++) {
            int current = start; // 현재 세로선
            // 시뮬레이션
            for (int i = 1; i <= H; i++) {
                if (current > 1 && graph[i][current - 1] == 1) { // 왼쪽으로 이동
                    current--;
                } else if (current < N && graph[i][current] == 1) { // 오른쪽으로 이동
                    current++;
                }
            }
            // 시작점과 세로선 도착지점이 다르면 실패
            if (current != start) return false;
        }
        return true;
    }

}
