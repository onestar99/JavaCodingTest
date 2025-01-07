package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * 인접리스트를 일단 구현한다..
 * dp를 이해 못한다..
 */

public class 사회망_서비스SNS {

    static List<List<Integer>> graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 입력
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // DP 테이블과 방문 배열 초기화
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        // DFS 실행
        dfs(1);

        // 최소 얼리 아답터 수 출력
        System.out.println(Math.min(dp[1][0], dp[1][1]));


    }

    // 이해 못함
    static void dfs(int node) {
        visited[node] = true;

        // 초기값 설정
        dp[node][0] = 0; // 현재 노드가 얼리 아답터가 아닐 때
        dp[node][1] = 1; // 현재 노드가 얼리 아답터일 때

        for (int child : graph.get(node)) {
            if (!visited[child]) {
                dfs(child);

                // 현재 노드가 얼리 아답터가 아닐 경우, 자식은 반드시 얼리 아답터
                dp[node][0] += dp[child][1];

                // 현재 노드가 얼리 아답터일 경우, 자식은 얼리 아답터일 수도 있고 아닐 수도 있음
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
    }
}
