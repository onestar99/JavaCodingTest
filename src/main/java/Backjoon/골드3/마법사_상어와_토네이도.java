package Backjoon.골드3;

import java.io.*;
import java.util.*;

public class 마법사_상어와_토네이도 {
    static int N;
    static int[][] map;
    static int outSand = 0;

    // 방향: 좌, 하, 우, 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    // 각 방향별 모래 비율 위치와 비율 (dx, dy, 비율%)
    static int[][][] sandSpread = {
            // 좌
            {{-1, 1, 1}, {1, 1, 1}, {-1, 0, 7}, {1, 0, 7}, {-1, -1, 10}, {1, -1, 10}, {-2, 0, 2}, {2, 0, 2}, {0, -2, 5}},
            // 하
            {{-1, -1, 1}, {-1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {1, -1, 10}, {1, 1, 10}, {0, -2, 2}, {0, 2, 2}, {2, 0, 5}},
            // 우
            {{-1, -1, 1}, {1, -1, 1}, {-1, 0, 7}, {1, 0, 7}, {-1, 1, 10}, {1, 1, 10}, {-2, 0, 2}, {2, 0, 2}, {0, 2, 5}},
            // 상
            {{1, -1, 1}, {1, 1, 1}, {0, -1, 7}, {0, 1, 7}, {-1, -1, 10}, {-1, 1, 10}, {0, -2, 2}, {0, 2, 2}, {-2, 0, 5}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simulateTornado();
        System.out.println(outSand);
    }

    public static void simulateTornado() {
        int x = N/2;
        int y = N/2;
        int dir = 0;
        int moveCount = 1;

        while (true) {
            // 같은 거리를 2번 이동
            for (int i = 0; i < 2; i++) {
                // moveCount만큼 이동
                for (int j = 0; j < moveCount; j++) {
                    x += dx[dir];
                    y += dy[dir];
                    if (x < 0 || y < 0) return;
                    spreadSand(x, y, dir);
                }
                dir = (dir + 1) % 4;
            }
            moveCount++;
        }
    }

    static void spreadSand(int x, int y, int dir) {
        int totalSand = map[x][y];
        map[x][y] = 0;

        int spreadSum = 0; // 흩날린 모래의 총합

        for (int[] s : sandSpread[dir]) {
            int nx = x + s[0];
            int ny = y + s[1];
            int spreadAmount = (totalSand * s[2]) / 100;

            if (isInBounds(nx, ny)) {
                map[nx][ny] += spreadAmount;
            } else {
                outSand += spreadAmount; // 격자 밖으로 나가는 모래
            }
            spreadSum += spreadAmount; // 흩날린 모래 누적
        }

        // 남은 모래(a)를 알파 위치에 배치
        int alphaSand = totalSand - spreadSum;
        int alphaX = x + dx[dir];
        int alphaY = y + dy[dir];

        if (isInBounds(alphaX, alphaY)) {
            map[alphaX][alphaY] += alphaSand;
        } else {
            outSand += alphaSand; // 격자 밖으로 나가는 모래
        }
    }

    static boolean isInBounds(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
