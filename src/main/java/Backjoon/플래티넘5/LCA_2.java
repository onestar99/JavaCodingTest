package Backjoon.플래티넘5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LCA_2 {

    static int N, M, LOG;
    static List<Integer>[] tree;
    static int[] depth;
    static int[][] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        M = Integer.parseInt(br.readLine());

        // LOG 값 계산 (2^LOG >= N)
        LOG = (int) Math.ceil(Math.log(N) / Math.log(2));
        depth = new int[N + 1];
        parent = new int[N + 1][LOG + 1];

        // DFS를 이용해 각 노드의 깊이와 부모를 구함 (루트는 1)
        dfs(1, 0);

        // Binary Lifting 전처리, 2^j번째 부모 계산
        for (int j = 1; j <= LOG; j++) {
            for (int i = 1; i <= N; i++) {
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(lca(u, v)).append("\n");
        }
        System.out.print(sb.toString());
    }

    // DFS, 현재 노드 cur와 이전 노드 parent를 인자로 받아 깊이와 1단계 부모를 설정
    static void dfs(int cur, int par) {
        depth[cur] = depth[par] + 1;
        parent[cur][0] = par;
        for (int next : tree[cur]) {
            if (next != par) {
                dfs(next, cur);
            }
        }
    }

    // LCA 계산 함수
    static int lca(int u, int v) {
        // u가 더 깊도록 맞춤
        if (depth[u] < depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        // 깊이 차이만큼 u를 위로 올림
        for (int i = LOG; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = parent[u][i];
            }
        }

        // 이미 같다면 v나 u 아무거나 LCA
        if (u == v) return u;

        // 두 노드가 같아질 때까지 동시에 위로 이동
        for (int i = LOG; i >= 0; i--) {
            if (parent[u][i] != parent[v][i]) {
                u = parent[u][i];
                v = parent[v][i];
            }
        }

        // 마지막에 level 1만 더 올라가면 LCA 도달
        return parent[u][0];
    }
}
