package Backjoon.골드3;

import java.io.*;
import java.util.*;

/**
 * 1. 트리 구성
 * 2. DFS 써서 1번 노드에서 가장 먼 노드찾기 -> 트리 길이 구하기의 출발점
 * 3. 해당 노드에서 DFS 써서 트리 길이 측정
 */

public class 트리의_지름 {

    static class Node {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    static List<Node>[] tree;
    static boolean[] visited;
    static int maxDistance = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());

        tree = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());

            while (true) {
                int connectedNode = Integer.parseInt(st.nextToken());
                if (connectedNode == -1) break;

                int weight = Integer.parseInt(st.nextToken());
                tree[node].add(new Node(connectedNode, weight));
            }
        }

        visited = new boolean[V + 1];
        dfs(1, 0);

        visited = new boolean[V + 1];
        maxDistance = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDistance);
    }

    static void dfs(int current, int distance) {
        visited[current] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = current;
        }

        for (Node neighbor : tree[current]) {
            if (!visited[neighbor.vertex]) {
                dfs(neighbor.vertex, distance + neighbor.weight);
            }
        }
    }
}
