package Backjoon.골드3;
import java.io.*;
import java.util.*;

/**
 * 드래곤 커브
 * 0   1   2  3
 * 우  상  좌  하
 *
 * 0세대 -> 하나의 선분
 * 1세대 -> 이전 세대의 끝 점에서 90도 회전 선분
 * 2세대 -> ...
 * 3...
 * 반복
 *
 */

public class 드래곤_커브 {

    static boolean[][] grid = new boolean[101][101];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            createDragonCurve(x, y, d, g);
        }

        System.out.println(countSquares());
    }

    static void createDragonCurve(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        for (int i = 0; i < g; i++) { // 드래곤 커브 세대 반복
            for (int j = directions.size() - 1; j >= 0; j--) { // 역순으로 순회
                directions.add((directions.get(j) + 1) % 4); // 90도 시계 방향 회전
            }
        }

        grid[x][y] = true;
        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            grid[x][y] = true;
        }
    }

    // 격자 유효범위 최대치까지 계산 0 ~ 101, 네군대 꼭짓점 있으면 count+
    static int countSquares() {
        int count = 0;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (grid[x][y] && grid[x + 1][y] && grid[x][y + 1] && grid[x + 1][y + 1]) { // 사각형
                    count++;
                }
            }
        }
        return count;
    }
}
