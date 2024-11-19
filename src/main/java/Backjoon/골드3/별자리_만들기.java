package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 별자리_만들기 {

    static class Star {
        double x, y;
        Star (double x, double y) {
            this.x = x;
            this.y = y;
        }
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    static class Edge implements Comparable<Edge> {
        int to;
        double cost;
        Edge (int to, double cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }

    }


    public static void main(String[] args) throws IOException {

        // 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Star[] stars = new Star[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Star(x, y);
        }

        // 모든 인접리스트 넣어주기
        List<Edge>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double distance = getDistance(stars[i], stars[j]);
                    adj[i].add(new Edge(j, distance));
                }
            }
        }

        //
        boolean[] visited = new boolean[n];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // 시작 지점 넣어주기
        visited[0] = true;
        pq.addAll(adj[0]);

        double totalCost = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (!visited[edge.to]) {
                visited[edge.to] = true;
                totalCost += edge.cost;
                pq.addAll(adj[edge.to]);
            }
        }

        // 출력 포맷팅
        System.out.printf("%.2f\n", totalCost);
    }

    // 별의 거리 구하기
    static double getDistance(Star a, Star b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

}
