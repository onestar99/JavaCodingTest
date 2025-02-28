package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 인접 행렬(그래프) 구성
 * 플로이드–워셜(Floyd–Warshall) 알고리즘 이용해 모든 사건 쌍의 전후 관계를 전부 구함
 *
 */

public class 역사 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 사건의 개수
        int k = Integer.parseInt(st.nextToken()); // 알고 있는 사건 전후 관계의 개수

        // 인접 행렬: graph[a][b] = true면 a가 b보다 먼저 일어났음을 의미
        boolean[][] graph = new boolean[n + 1][n + 1];

        // 전후 관계 입력 받기
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = true;
        }

        // 플로이드–워셜 알고리즘으로 모든 쌍에 대한 전후 관계 추론
        for (int mid = 1; mid <= n; mid++) {
            for (int start = 1; start <= n; start++) {
                // start에서 mid까지 가는 경로가 없다면 continue
                if (!graph[start][mid]) continue;

                for (int end = 1; end <= n; end++) {
                    // mid에서 end까지 가는 경로가 있다면
                    // start -> mid -> end 경로도 true
                    if (graph[mid][end]) {
                        graph[start][end] = true;
                    }
                }
            }
        }

        // 질의(사건 쌍)
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // x가 y보다 먼저 일어났으면 -1
            if (graph[x][y]) {
                sb.append(-1).append("\n");
            }
            // y가 x보다 먼저 일어났으면 1
            else if (graph[y][x]) {
                sb.append(1).append("\n");
            }
            // 유추할 수 없으면 0
            else {
                sb.append(0).append("\n");
            }
        }

        System.out.print(sb.toString());
    }
}
