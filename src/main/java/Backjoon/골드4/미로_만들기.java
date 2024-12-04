package Backjoon.골드4;

import java.io.*;
import java.util.*;

public class 미로_만들기 {

    static boolean[][] map;
    static int[][] dist;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new boolean[n][n];
        dist = new int[n][n]; // 거리

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) == '1';
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        System.out.println(bfs(n));
    }

    // 0 - 1 BFS
    static int bfs(int n) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0, 0}); // 시작점
        dist[0][0] = 0;

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int x = curr[0];
            int y = curr[1];

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    // 가중치: 검은 방 - 1, 흰 방 - 0
                    int weight = map[nx][ny] ? 0 : 1;
                    if (dist[x][y] + weight < dist[nx][ny]) { // 최소 거리 갱신 가능
                        dist[nx][ny] = dist[x][y] + weight;
                        if (weight == 0) { // 가중치 작은거를 앞쪽으로
                            deque.addFirst(new int[]{nx, ny});
                        } else { // 가중치 높은거를 뒤쪽으로
                            deque.addLast(new int[]{nx, ny});
                        }
                    }
                }
            }
        }

        return dist[n - 1][n - 1]; // 끝방까지의 최소 가중치
    }
}
