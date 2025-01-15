package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 서울_지하철_2호선 {

    static ArrayList<Integer>[] graph;
    static int[] result;
    static int N;
    static boolean[] visited;

    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        result = new int[N + 1];
        Arrays.fill(result, -1);  // 아직 방문하지 않은 곳 -1

        // 사이클 찾기
        for (int i = 1; i <= N; i++) {
            if (result[i] == -1) {
                visited = new boolean[N + 1];
                ArrayList<Integer> cycle = new ArrayList<>();
                findCycle(i, i, cycle);
            }
        }

        // BFS로 나머지 정점들의 거리 계산
        for (int i = 1; i <= N; i++) {
            if (result[i] == -1) {
                calculateDistance(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }

    // 사이클 찾기 DFS
    static boolean findCycle(int current, int start, ArrayList<Integer> path) {
        visited[current] = true;
        path.add(current);

        for (int next : graph[current]) {
            if (!visited[next]) {
                if (findCycle(next, start, path)) {
                    return true;
                }
            }
            // 다음 정점이 시작 정점이고, 경로의 길이가 2 이상인 경우 사이클 발견
            else if (next == start && path.size() > 2) {
                // 사이클에 속한 모든 정점의 결과값을 0으로 설정
                for (int node : path) {
                    result[node] = 0;
                }
                return true;
            }
        }

        path.remove(path.size() - 1);  // 백트래킹
        return false;
    }

    // BFS 최단 거리 계산
    static void calculateDistance(int start) {
        Queue<int[]> queue = new LinkedList<>();  // [정점 번호, 거리]
        boolean[] visited = new boolean[N + 1];

        queue.offer(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];

            // 사이클에 속한 정점을 찾으면 거리 저장 후 종료
            if (result[node] == 0) {
                result[start] = distance;
                return;
            }

            for (int next : graph[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, distance + 1});
                }
            }
        }
    }
}