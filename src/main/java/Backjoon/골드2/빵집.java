package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 어렵게 생각하다가 담백하게 푸니까 성공
 * 기본적으로 선이 위에서부터 하나씩 연결된다고 하면 최대값이 보장될듯
 */

public class 빵집 {
    static int R, C;
    static char[][] grid;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rc = br.readLine().split(" ");
        R = Integer.parseInt(rc[0]);
        C = Integer.parseInt(rc[1]);
        grid = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // 각 행에서 파이프라인 설치 시도
        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) {
                result++;
            }
        }

        // 결과 출력
        System.out.println(result);
    }

    // DFS
    static boolean dfs(int x, int y) {
        // 마지막 열 도달
        if (y == C - 1) {
            return true;
        }

        // 3방향
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 체크, 건물 체크, 방문 여부 체크
            if (nx >= 0 && nx < R && ny >= 0 && ny < C && grid[nx][ny] == '.' && !visited[nx][ny]) {
                visited[nx][ny] = true;
                if (dfs(nx, ny)) {
                    return true;
                }
            }
        }
        return false;
    }
}
