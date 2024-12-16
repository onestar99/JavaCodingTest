package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 음수 가중치가 포함된 그래프의 최단 거리 -> 벨만 포드 알고리즘
 * 일반 edge는 방향이 없고, 웜홀은 방향이 존재하며 음수의 가중치를 가짐
 * 시간초과남ㅋㅋ;
 */

public class 웜홀 {

    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            String[] firstLine = br.readLine().split(" ");
            int N = Integer.parseInt(firstLine[0]); // 지점의 수
            int M = Integer.parseInt(firstLine[1]); // 도로의 개수
            int W = Integer.parseInt(firstLine[2]); // 웜홀의 개수

            List<Edge> edges = new ArrayList<>();

            // edge 추가
            for (int i = 0; i < M; i++) {
                String[] roadInfo = br.readLine().split(" ");
                int S = Integer.parseInt(roadInfo[0]);
                int E = Integer.parseInt(roadInfo[1]);
                int T = Integer.parseInt(roadInfo[2]);
                edges.add(new Edge(S, E, T));
                edges.add(new Edge(E, S, T));
            }

            // 웜홀 추가, 단방향, 음수 가중치
            for (int i = 0; i < W; i++) {
                String[] wormholeInfo = br.readLine().split(" ");
                int S = Integer.parseInt(wormholeInfo[0]);
                int E = Integer.parseInt(wormholeInfo[1]);
                int T = Integer.parseInt(wormholeInfo[2]);

                edges.add(new Edge(S, E, -T));
            }

            if (hasNegativeCycle(N, edges)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.print(sb.toString());
    }

    private static boolean hasNegativeCycle(int N, List<Edge> edges) {
        int[] dist = new int[N + 1];

        // 모든 지점을 시작점으로 테스트
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[j] = Integer.MAX_VALUE;
            }
            // 시작지점 거리 0
            dist[i] = 0;

            // 벨만 포드
            for (int j = 0; j < N - 1; j++) {
                for (Edge edge : edges) {
                    // 현재 간선의 시작 지점까지의 거리가 무한대가 아니라면, 도착 지점의 거리 갱신
                    if (dist[edge.from] != Integer.MAX_VALUE && dist[edge.to] > dist[edge.from] + edge.weight) {
                        dist[edge.to] = dist[edge.from] + edge.weight;
                    }
                }
            }

            // 음수 사이클 체크
            // 모든 간선을 다시 확인하여 거리가 갱신되면 음수 사이클이 존재하는 것
            for (Edge edge : edges) {
                if (dist[edge.from] != Integer.MAX_VALUE && dist[edge.to] > dist[edge.from] + edge.weight) {
                    return true;
                }
            }
        }

        return false;
    }
}
