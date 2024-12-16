package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS, 9방향
 * 왼쪽아래(7, 0) -> 오른쪽 위(0, 7)
 * 움직인 후 벽을 움직이기
 * 벽 움직이고 방문 배열 초기화
 */

public class 움직이는_미로_탈출 {

    static final int N = 8;
    static char[][] board;
    static int[][] direction = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}, {0, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs() ? 1 : 0);
    }

    static boolean bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{7, 0, 0}); // 왼쪽 아래 시작

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                int y = cur[0], x = cur[1], time = cur[2];

                if (time > 8) return true;

                // 현재 위치에 벽이 있으면 패스
                if (board[y][x] == '#') continue;

                // 9방향 탐색
                for (int[] dir : direction) {
                    int ny = y + dir[0];
                    int nx = x + dir[1];

                    // 범위 확인
                    if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;

                    // 방문 확인 및 빈 칸 여부 확인
                    if (board[ny][nx] == '.') {
                        queue.add(new int[]{ny, nx, time + 1});
                    }
                }
            }

            // 벽 이동
            moveWalls();

        }

        return false;
    }

    static void moveWalls() {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                if (i == 0) board[i][j] = '.';
                else board[i][j] = board[i - 1][j];
            }
        }
    }
}
