package Backjoon.실버3;

import java.util.*;

public class 바이러스 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        List<Integer>[] graph = new ArrayList[n + 1];

        // 그래프 노드 초기화
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 간선 추가
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        boolean[] visited = new boolean[n + 1];

        Queue<Integer> queue = new LinkedList<>();
        int currentNode = 1;
        queue.offer(currentNode);
        visited[currentNode] = true;
        int result = 0;
        while(!queue.isEmpty()) {
            currentNode = queue.poll();
            for (int neighbor : graph[currentNode]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                    result++;
                }
            }
        }

        System.out.println(result);

    }
}
