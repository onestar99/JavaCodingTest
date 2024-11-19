package Backjoon.골드4;

import java.util.*;
import java.util.Scanner;

public class 운동 {

    static List<Edge>[] graph;
    static int V;
    static int E;

    public static void main(String[] args) {

        /**
         *
         * 몰라 걍 다익스트라 써~
         */
        Scanner scanner = new Scanner(System.in);
        V = scanner.nextInt(); // 마을의 개수
        E = scanner.nextInt(); // 도로의 개수

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }


        int minCycle = Integer.MAX_VALUE;
        // 그래프 입력
        for (int i = 0; i < E; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            graph[a].add(new Edge(b, c));

            if (a == b) {
                minCycle = Math.min(minCycle, c);
            }
        }

        // 다익스트라써서 최소 사이클 찾기
        for (int i = 1; i <= V; i++) {
            minCycle = Math.min(minCycle, dijkstra(i));
        }

        // 결과 출력
        if (minCycle == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(minCycle);
        }

        scanner.close();
    }

    // 다익스트라 알고리즘 변형: 시작 노드로 돌아오는 최소 사이클 찾기
    static int dijkstra(int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.to;
            int d = current.weight;

            // 현재 노드까지의 거리가 이미 기록된 거리보다 크면 무시
            if (d > dist[u]) continue;

            // 인접한 노드 탐색
            for (Edge edge : graph[u]) {
                int v = edge.to;
                int newDist = d + edge.weight;

                // 시작 노드로 돌아오면 사이클 발견, 길이 리턴
                if (v == start) {
                    return newDist;
                }

                // 현재 노드를 경유하여 v까지의 최단 거리 갱신
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer(new Edge(v, newDist));
                }
            }
        }

        return Integer.MAX_VALUE; // 시작 노드로 돌아오는 사이클이 없는 경우
    }

    static class Edge {
        int to; // 도착지
        int weight; // 거리

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
