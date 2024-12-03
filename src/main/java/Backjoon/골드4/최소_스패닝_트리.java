package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소_스패닝_트리 {

    static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Edge>[] adj = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        int A = 0, B = 0, C = 0;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            adj[A].add(new Edge(B, C));
            adj[B].add(new Edge(A, C));
        }

        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        visited[A] = true;
        pq.addAll(adj[A]);

        int totalCost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (!visited[edge.to]) {
                visited[edge.to] = true;
                totalCost += edge.cost;
                pq.addAll(adj[edge.to]);
            }
        }

        System.out.println(totalCost);


    }
}
