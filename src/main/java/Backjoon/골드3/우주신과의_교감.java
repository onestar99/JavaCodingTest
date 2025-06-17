package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 크루스칼 알고리즘을 사용해서 이미 연결 된 간선들은 입력을 받은 뒤에
 * 나머지 간선들을 거리 오름차순으로 정렬해서 MST를 구성했음.
 */

public class 우주신과의_교감 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 우주신 수 (황선자 포함)
        int M = Integer.parseInt(st.nextToken());  // 이미 연결된 간선 수

        // 좌표 입력 1 ~ N
        int[][] coord = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            coord[i][0] = Integer.parseInt(st.nextToken());
            coord[i][1] = Integer.parseInt(st.nextToken());
        }

        UnionFind uf = new UnionFind(N);

        // 이미 연결된 간선을 Union-Find 반영
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            uf.union(a, b);
        }

        // 모든 점 사이의 간선 목록 생성 (완전 그래프)
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double dx = coord[i][0] - coord[j][0];
                double dy = coord[i][1] - coord[j][1];
                double dist = Math.sqrt(dx * dx + dy * dy);
                edges.add(new Edge(i, j, dist));
            }
        }

        // 가중치 기준으로 오름차순 정렬
        Collections.sort(edges);

        // 크루스칼 MST
        double total = 0;
        for (Edge e : edges) {
            // 사이클이 아니면 연결
            if (uf.find(e.u) != uf.find(e.v)) {
                uf.union(e.u, e.v);
                total += e.w;
            }
        }

        // 소수점 둘째 자리까지 결과 출력시킴
        System.out.printf("%.2f\n", total);

    }

    static class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n + 1];  // 1-based indexing
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }

        public void union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra != rb) {
                parent[rb] = ra;
            }
        }
    }

    // 간선 정보를 저장할 클래스
    static class Edge implements Comparable<Edge> {
        int u, v;
        double w;

        public Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.w, o.w);
        }
    }

}
