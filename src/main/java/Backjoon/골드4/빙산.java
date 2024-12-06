package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 빙산 {

    static int n, m;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(simulate(board));
    }

    // 빙산 시뮬레이션
    public static int simulate(int[][] board) {
        int years = 0;

        while (true) {
            int icebergCount = countIcebergs(board);
            // 모두 녹음
            if (icebergCount == 0) {
                return 0;
            }
            // 분리됨
            if (icebergCount > 1) {
                return years;
            }

            // 한 해가 지났습니다. 모든 빙산들은 녹으십시오.
            meltIceberg(board);
            years++;
        }
    }

    // 빙산 높이 감소
    public static void meltIceberg(int[][] board) {
        int[][] melt = new int[n][m];

        // 각 칸의 바다에 접촉된 수 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0) {
                    int seaCount = 0;
                    for (int[] dir : directions) {
                        int nx = i + dir[0];
                        int ny = j + dir[1];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 0) {
                            seaCount++;
                        }
                    }
                    melt[i][j] = seaCount;
                }
            }
        }

        // 녹은 높이 적용
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = Math.max(0, board[i][j] - melt[i][j]);
            }
        }
    }

    // 빙산 덩어리 수 세기
    public static int countIcebergs(int[][] board) {
        boolean[][] visited = new boolean[n][m];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0 && !visited[i][j]) {
                    bfs(i, j, board, visited);
                    count++;
                }
            }
        }

        return count;
    }

    // BFS로 빙산 탐색
    public static void bfs(int x, int y, int[][] board, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int[] dir : directions) {
                int nx = cx + dir[0];
                int ny = cy + dir[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && board[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
