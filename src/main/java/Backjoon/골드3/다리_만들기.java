package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

/**
 * 섬 구분하는데 BFS 한 번
 * 다리 놓는데 BFS 한 번 더
 * 최단 거리 계산하기
 */

public class 다리_만들기 {

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        // 섬 번호 매기기, 번호 2부터 시작
        int islandId = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    markIsland(i, j, islandId++);
                }
            }
        }

        // 최단 거리 찾기
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 1) {
                    minDistance = Math.min(minDistance, findShortestBridge(i, j, map[i][j]));
                }
            }
        }

        System.out.println(minDistance);
    }

    // BFS 섬 구분
    static void markIsland(int x, int y, int islandId) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = islandId;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] dir : direction) {
                int nx = current[0] + dir[0];
                int ny = current[1] + dir[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && map[nx][ny] == 1 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    map[nx][ny] = islandId;
                }
            }
        }
    }

    // BFS 섬 간 최단 거리 찾기
    static int findShortestBridge(int x, int y, int islandId) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visitedForBridge = new boolean[n][n];
        queue.offer(new int[]{x, y, 0});
        visitedForBridge[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            int distance = current[2];

            for (int[] dir : direction) {
                int nx = cx + dir[0];
                int ny = cy + dir[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visitedForBridge[nx][ny]) {
                    // 다른 섬에 도착한 경우
                    if (map[nx][ny] > 0 && map[nx][ny] != islandId) {
                        return distance;
                    }

                    // 바다인 경우 계속 진행
                    if (map[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny, distance + 1});
                        visitedForBridge[nx][ny] = true;
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }

}
