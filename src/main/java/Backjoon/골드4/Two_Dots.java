package Backjoon.골드4;

import java.util.Scanner;

public class Two_Dots {

    static int N;
    static int M;
    static char[][] grid;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        M = scanner.nextInt();

        grid = new char[N][M];
        visited = new boolean[N][M];

        scanner.nextLine();
        for (int i = 0; i < N; i++) {
            String str = scanner.nextLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = str.charAt(j);
            }
        }
        System.out.println(isCycle() ? "Yes" : "No");
    }

    // 모든 부분 다 찾아서 사이클인지 파악하기.
    static boolean isCycle() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    if (dfs(i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean dfs(int x, int y, int px, int py, char color) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위 벗어나면 무시
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            // 다른 색이면 무시
            if (grid[nx][ny] != color) {
                continue;
            }

            // 방문체크가 되어 있는 곳이 부모 노드가 아니라면 사이클
            if (visited[nx][ny]) {
                if (nx != px || ny != py) {
                    return true;
                }
            } else {
                if (dfs(nx, ny, x, y, color)) {
                    return true;
                }
            }
        }
        return false;
    }
}
