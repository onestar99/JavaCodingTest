package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 해킹 {

    /**
     * 바이러스 의존 문제 -> BFS, 다익스트라
     */

    static class Node implements Comparable<Node> {
        int id, time;

        public Node(int id, int time) {
            this.id = id;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 인접 리스트로 구성
            List<List<Node>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Node(a, s)); // b -> a 의존
            }

            // 다익스트라로 뒤지기
            int[] dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            PriorityQueue<Node> pq = new PriorityQueue<>();
            dist[c] = 0;
            pq.add(new Node(c, 0));

            while (!pq.isEmpty()) {
                Node current = pq.poll();
                int now = current.id;
                int time = current.time;

                if (time > dist[now]) continue;

                for (Node next : graph.get(now)) {
                    int newTime = time + next.time;
                    if (newTime < dist[next.id]) {
                        dist[next.id] = newTime;
                        pq.add(new Node(next.id, newTime));
                    }
                }
            }

            // 감염된 컴퓨터 수와 걸리는 최대 시간 계산하기
            int infectedCount = 0;
            int maxTime = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    infectedCount++;
                    maxTime = Math.max(maxTime, dist[i]);
                }
            }

            sb.append(infectedCount).append(" ").append(maxTime).append("\n");
        }

        System.out.print(sb);
    }
}
