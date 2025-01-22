package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 3% 실패, 경로를 탐색하는 방향에서 모든 것을 탐색하는 것이 아닌 start 부분부터 하나씩 보기때문에 문제가 생기는 것 같음.
 */

public class 중량제한 {

    static int N, M;
    static List<Edge>[] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            map[A].add(new Edge(B, C));
            map[B].add(new Edge(A, C));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int result = bfs(start, end);
        System.out.println(result);
    }

    private static int bfs(int start, int end) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (Edge edge : map[start]) {
            pq.add(edge.weight);
        }

        while (!pq.isEmpty()) {
            int currentWeight = pq.poll();

            if(canWeight(start, end, currentWeight)) {
                return currentWeight;
            }
        }

        return 0;
    }

    private static boolean canWeight(int start, int end, int weightLimit) {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[N + 1];
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == end) {
                return true;
            }

            for (Edge edge : map[current]) {
                if (!visited[edge.to] && edge.weight >= weightLimit) {
                    visited[edge.to] = true;
                    queue.add(edge.to);
                }
            }
        }
        return false;
    }

    private static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
