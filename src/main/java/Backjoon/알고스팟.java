package Backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 알고스팟 {

    static int[][] miro; // 미로
    static int[][] minWalls; // 해당 위치에 도달하기까지의 최소 벽 부순 횟수
    static boolean[][] visited; // 방문
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int M; // 가로
    static int N; // 세로
    static int result;


    public static void main(String[] args) throws IOException {

        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] dimensions = br.readLine().split(" ");
        // 입력값 받기
        M = Integer.parseInt(dimensions[0]);
        N = Integer.parseInt(dimensions[1]);
        result = 101;

        visited = new boolean[N][M];
        miro = new int[N][M];
        minWalls = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                miro[i][j] = line.charAt(j) - '0';
                minWalls[i][j] = Integer.MAX_VALUE;
            }
        }
        dfs(0, 0, 0);
        System.out.println(result);
    }


    public static void dfs(int x, int y, int brokenWalls) {
        // 종료 시점 체크 & 결과 도출
        if (x == M - 1 && y == N - 1) {
            result = Math.min(result, brokenWalls);
            return;
        }
        // 4방향
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 미로 벗어나지 않기
            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;

            // 방문하지 않았던 곳이나, 더 적은 벽을 부수고 이동할 수 있는 경우에만 탐색
            if (!visited[ny][nx] || minWalls[ny][nx] > brokenWalls + miro[ny][nx]) {
                visited[ny][nx] = true;
                // 부순 횟수 최소 경신하기
                minWalls[ny][nx] = brokenWalls + miro[ny][nx];
                // dfs
                dfs(nx, ny, brokenWalls + miro[ny][nx]);
                visited[ny][nx] = false; // 백트랰킹
            }
        }
    }
}
