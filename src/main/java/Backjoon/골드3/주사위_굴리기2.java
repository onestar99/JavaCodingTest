package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 주사위_굴리기2 {

    static int N, M, K;
    static int[][] map;
    static int x, y; // 현재 위치
    static int direction = 0; // 시계 방향 기준 % 4 == 동 남 서 북
    static int[] dice = {1, 6, 3, 4, 2, 5}; // front, back, left, right, top, bottom

    // {dx, dy} 동 남 서 북,  시계 방향
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int score = 0;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        x = 0;
        y = 0;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            move(); // 1 이동 및 주사위 굴림
            calculateScore(); // 2 도착한 칸 점수 계산
            adjustDirection(); // 3 방향 결정
        }

        System.out.println(score); // 최종 점수 출력

    }

    static void move() {
        int nx = x + dx[direction];
        int ny = y + dy[direction];

        // 맵 범위 안에 있을 때만 이동
        if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
            x = nx;
            y = ny;
        } else {
            // 방향 반대로 회전
            direction = (direction + 2) % 4;

            // 반대 방향으로 이동
            nx = x + dx[direction];
            ny = y + dy[direction];
            x = nx;
            y = ny;
        }

        rollDice(direction); // 방향에 맞게 주사위 굴림
    }

    static void rollDice(int dir) {
        int[] currentDice = dice.clone();

        switch(dir) {
            case 0: // 동쪽 이동
                dice[0] = currentDice[3];
                dice[1] = currentDice[2];
                dice[2] = currentDice[0];
                dice[3] = currentDice[1];
                break;
            case 1: // 남쪽
                dice[0] = currentDice[5];
                dice[5] = currentDice[1];
                dice[1] = currentDice[4];
                dice[4] = currentDice[0];
                break;
            case 2: // 서쪽 이동
                dice[0] = currentDice[2];
                dice[1] = currentDice[3];
                dice[2] = currentDice[1];
                dice[3] = currentDice[0];
                break;
            case 3: // 북쪽
                dice[0] = currentDice[4];
                dice[4] = currentDice[1];
                dice[1] = currentDice[5];
                dice[5] = currentDice[0];
                break;
        }
    }


    static void calculateScore() {
        int baseValue = map[x][y];
        boolean[][] visited = new boolean[N][M];
        int count = dfs(x, y, baseValue, visited);
        int currentScore = baseValue * count;
        score += currentScore;
    }

    static int dfs(int cx, int cy, int baseValue, boolean[][] visited) {
        // 범위 벗어나거나 이미 방문했거나 값이 다르면 0 반환
        if (cx < 0 || cx >= N || cy < 0 || cy >= M ||
                visited[cx][cy] || map[cx][cy] != baseValue) {
            return 0;
        }

        // 방문 표시
        visited[cx][cy] = true;
        int count = 1;

        // 4방향 탐색 (동, 남, 서, 북)
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        for (int[] dir : dirs) {
            int nx = cx + dir[0];
            int ny = cy + dir[1];
            count += dfs(nx, ny, baseValue, visited);
        }

        return count;
    }

    static void adjustDirection() {
        int bottom = dice[1];
        int cellValue = map[x][y];

        if (bottom > cellValue) {
            direction = (direction + 1) % 4; // 시계 방향 회전
        } else if (bottom < cellValue) {
            direction = (direction + 3) % 4; // 반시계 방향 회전
        }
    }
}
