package Backjoon.골드3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * Priority Queue를 이용한 프림 알고리즘을 통해서 가장 짧은 거리를 가진 노드를 탐색한다.
 * 현재 노드와 연결된 노드가 성별 조건을 만족하면 Queue에 추가한다.
 * 방문한 노드 여부 체크하여 중복을 방지한다.
 */
public class 나만_안되는_연애 {

    static int N, M;
    static char[] genders;
    static List<List<Node>> graph;

    static class Node implements Comparable<Node> {
        int to, distance;

        public Node(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학교의 수
        M = Integer.parseInt(st.nextToken()); // 도로의 개수

        genders = new char[N + 1]; // 학교의 성별
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            genders[i] = st.nextToken().charAt(0);
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, d));
            graph.get(v).add(new Node(u, d));
        }

        System.out.println(prim());


    }

    public static int prim() {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0)); // 임의의 시작점(1번 노드)
        int totalDistance = 0;
        int visitedCount = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.to]) {
                continue;
            }

            visited[current.to] = true;
            totalDistance += current.distance;
            visitedCount++;

            // 방문 안했고, 다른 성격의 학교 일 경우 추가
            for (Node neighbor : graph.get(current.to)) {
                if (!visited[neighbor.to] && genders[current.to] != genders[neighbor.to]) {
                    pq.add(new Node(neighbor.to, neighbor.distance));
                }
            }
        }

        // 모든 노드를 방문하지 못했으면 -1 반환
        return visitedCount == N ? totalDistance : -1;
    }
}
