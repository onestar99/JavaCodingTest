package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 말의 이동 방법 8가지를 미리 작성해 놓은 후 K번 움직여 보는 BFS를 사용한다.
 *
 */

public class 말이_되고픈_원숭이 {

    static int[][] horseDirection = {
            {-2, -1},
            {-1, -2},
            {2, -1},
            {1, -2},
            {-2, 1},
            {-1, 2},
            {2, 1},
            {1, 2}
    };
    static int[][] direction = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };
    static int[][] map;
    static int K, W, H;

    static class State {
        int x, y, count, remainingK;

        State(int x, int y, int count, int remainingK) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.remainingK = remainingK;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(getNumberOfMonkeyActon());

    }

    private static int getNumberOfMonkeyActon() {
        boolean[][][] visited = new boolean[H][W][K + 1];
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(0, 0, 0, K));
        visited[0][0][K] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // 도착점 도달
            if (current.x == H - 1 && current.y == W - 1) {
                return current.count;
            }

            // 일반 이동
            for (int[] d : direction) {
                int nx = current.x + d[0];
                int ny = current.y + d[1];

                if (isValid(nx, ny) && !visited[nx][ny][current.remainingK] && map[nx][ny] == 0) {
                    visited[nx][ny][current.remainingK] = true;
                    queue.offer(new State(nx, ny, current.count + 1, current.remainingK));
                }
            }

            // 말 이동
            if (current.remainingK > 0) {
                for (int[] d : horseDirection) {
                    int nx = current.x + d[0];
                    int ny = current.y + d[1];

                    if (isValid(nx, ny) && !visited[nx][ny][current.remainingK - 1] && map[nx][ny] == 0) {
                        visited[nx][ny][current.remainingK - 1] = true;
                        queue.offer(new State(nx, ny, current.count + 1, current.remainingK - 1));
                    }
                }
            }
        }


        return -1;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < H && y < W;
    }
}
