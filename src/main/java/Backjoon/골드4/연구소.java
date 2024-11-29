package Backjoon.골드4;

import java.util.Scanner;

public class 연구소 {
    static int n, m;
    static int[][] lab;
    static int[][] tempLab;
    static int maxSafeArea = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        lab = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                lab[i][j] = scanner.nextInt();
            }
        }

        // 벽을 세우는 모든 경우를 탐색
        buildWall(0);
        System.out.println(maxSafeArea);
    }

    // 벽 세우기 dfs
    static void buildWall(int wallCount) {
        if (wallCount == 3) {
            spreadVirus();
            maxSafeArea = Math.max(maxSafeArea, calculateSafeArea());
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (lab[i][j] == 0) { // 빈 공간에 벽 세우기
                    lab[i][j] = 1;
                    buildWall(wallCount + 1);
                    lab[i][j] = 0; // 원상 복구 (백트래킹)
                }
            }
        }
    }

    // 바이러스 퍼뜨리기 dfs
    static void spreadVirus() {
        tempLab = new int[n][m];
        // tempLab 복사
        for (int i = 0; i < n; i++) {
            System.arraycopy(lab[i], 0, tempLab[i], 0, m);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempLab[i][j] == 2) {
                    dfs(i, j);
                }
            }
        }
    }

    // 4방향 바이러스 퍼뜨리기
    static void dfs(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m && tempLab[nx][ny] == 0) {
                // 바이러스 퍼진 곳 체크
                tempLab[nx][ny] = 2;
                // 다음 위치로 이동
                dfs(nx, ny);
            }
        }
    }

    // 안전 영역 계산
    static int calculateSafeArea() {
        int safeArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (tempLab[i][j] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }
}