package Backjoon.골드3;

import java.io.*;
import java.util.*;

public class 마법사_상어와_파이어스톰 {

    static int N, Q, gridSize;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        gridSize = (int) Math.pow(2, N);
        grid = new int[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int q = 0; q < Q; q++) {
            int L = Integer.parseInt(st.nextToken());
            firestorm(L);
        }

        System.out.println(getTotalIce());
        System.out.println(getLargestIceChunk());
    }

    // 파이어스톰 시전
    static void firestorm(int L) {
        int subSize = (int) Math.pow(2, L);
        rotateGrid(subSize);
        reduceIce();
    }

    // 격자 회전
    static void rotateGrid(int subSize) {
        int[][] newGrid = new int[gridSize][gridSize];

        for (int x = 0; x < gridSize; x += subSize) {
            for (int y = 0; y < gridSize; y += subSize) {
                for (int i = 0; i < subSize; i++) {
                    for (int j = 0; j < subSize; j++) {
                        newGrid[x + j][y + subSize - 1 - i] = grid[x + i][y + j];
                    }
                }
            }
        }

        grid = newGrid;
    }

    // 얼음 감소
    static void reduceIce() {
        List<int[]> toReduce = new ArrayList<>();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] > 0) {
                    int adjacentIce = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && nx < gridSize && ny >= 0 && ny < gridSize && grid[nx][ny] > 0) {
                            adjacentIce++;
                        }
                    }
                    if (adjacentIce < 3) {
                        toReduce.add(new int[]{i, j});
                    }
                }
            }
        }

        for (int[] cell : toReduce) {
            grid[cell[0]][cell[1]]--;
        }
    }

    // 남아있는 얼음의 총합
    static int getTotalIce() {
        int total = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                total += grid[i][j];
            }
        }
        return total;
    }

    // 가장 큰 얼음 덩어리 크기
    static int getLargestIceChunk() {
        visited = new boolean[gridSize][gridSize];
        int maxChunkSize = 0;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (!visited[i][j] && grid[i][j] > 0) {
                    maxChunkSize = Math.max(maxChunkSize, bfs(i, j));
                }
            }
        }

        return maxChunkSize;
    }

    // BFS를 사용한 얼음 덩어리 탐색
    static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        int chunkSize = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            chunkSize++;

            for (int d = 0; d < 4; d++) {
                int nx = current[0] + dx[d];
                int ny = current[1] + dy[d];

                if (nx >= 0 && nx < gridSize && ny >= 0 && ny < gridSize &&
                        !visited[nx][ny] && grid[nx][ny] > 0) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        return chunkSize;
    }
}
