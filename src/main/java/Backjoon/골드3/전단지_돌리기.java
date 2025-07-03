package Backjoon.골드3;

import java.io.*;
import java.util.*;

// https://velog.io/@roo333/Binary-lifting
// 바이너리 리프팅 사용해서 부모 조상 쿼리 효율화

public class 전단지_돌리기 {

    static int N, S, D;
    static List<Integer>[] adj;
    static int[] dist;
    static int LOG;
    static int[][] parent;
    static boolean[] marked;
    static long edgeCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken()) - 1;  // 0-index
        D = Integer.parseInt(st.nextToken());

        adj = new List[N];
        for (int i = 0; i < N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            adj[u].add(v);
            adj[v].add(u);
        }

        // BFS로 dist, parent[0] 계산
        dist = new int[N];
        Arrays.fill(dist, -1);
        LOG = 1;
        while ((1 << LOG) <= N) LOG++;
        parent = new int[LOG][N];
        for (int i = 0; i < LOG; i++) Arrays.fill(parent[i], -1);

        bfsBuildParents();

        // Binary lifting을 위한 parent 테이블 완성
        for (int k = 1; k < LOG; k++) {
            for (int v = 0; v < N; v++) {
                int mid = parent[k - 1][v];
                parent[k][v] = (mid < 0 ? -1 : parent[k - 1][mid]);
            }
        }

        // marked 배열 채우기
        marked = new boolean[N];
        for (int u = 0; u < N; u++) {
            if (dist[u] > D) {
                int x = getAncestor(u, D);
                marked[x] = true;
            }
        }

        // S를 루트로 하는 트리 구조 생성, BFS 트리
        List<Integer>[] tree = new List[N];
        for (int i = 0; i < N; i++) tree[i] = new ArrayList<>();
        for (int v = 0; v < N; v++) {
            int p = parent[0][v];
            if (p >= 0) {
                tree[p].add(v);
            }
        }

        // 트리 후위 순회로 edgeCount 계산
        postOrder(S, tree);

        System.out.println(edgeCount * 2);
    }

    // BFS로 dist, parent[0] 세팅
    static void bfsBuildParents() {
        Queue<Integer> q = new ArrayDeque<>();
        dist[S] = 0;
        q.add(S);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    parent[0][v] = u;
                    q.add(v);
                }
            }
        }
    }

    // u에서 k단계 위의 ancestor 반환
    static int getAncestor(int u, int k) {
        for (int i = 0; i < LOG && u >= 0; i++) {
            if ((k & (1 << i)) != 0) {
                u = parent[i][u];
            }
        }
        return u;
    }

    // 후위 순회: 자식 서브트리에 marked가 있으면 edge 포함
    static boolean postOrder(int u, List<Integer>[] tree) {
        boolean has = marked[u];
        for (int v : tree[u]) {
            if (postOrder(v, tree)) {
                has = true;
                edgeCount++;
            }
        }
        return has;
    }
}