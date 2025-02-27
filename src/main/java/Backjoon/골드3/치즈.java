package Backjoon.골드3;

import java.io.*;
import java.util.*;

/**
 * 1. 바깥쪽 외부 공기에서 BFS를 통해 외부 공기를 -1로 변경.
 * 2. 각 치즈 부분에서 4방향으로 2방향이상 -1인 곳을 찾아서 따로 저장해놓고 한번에 변경
 * 3. 모든 치즈가 녹을 때 까지 반복
 */

public class 치즈 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시뮬레이션 실행
        int result = simulation();
        System.out.println(result);
    }

    public static int simulation() {
        int time = 0;

        while (true) {
            // 외부 공기 표시
            markOutsideAir();
            // 녹을 치즈 찾기
            List<int[]> toMelt = findCheeseToMelt();

            // 치즈 없으면 종료
            if (toMelt.isEmpty()) {
                break;
            }
            // 치즈 녹이기
            meltCheese(toMelt);
            time++;
        }

        return time;
    }

    static void markOutsideAir() {
        visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        map[0][0] = -1; // 외부 공기 표시

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] <= 0) {
                    visited[nx][ny] = true;
                    map[nx][ny] = -1; // 외부 공기 표시
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    static List<int[]> findCheeseToMelt() {
        List<int[]> toMelt = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) { // 치즈인 경우
                    int airCount = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (map[nx][ny] == -1) {
                            airCount++;
                        }
                    }
                    // 외부 공기 2개 이상이면 제거를 위해 추가
                    if (airCount >= 2) {
                        toMelt.add(new int[]{i, j});
                    }
                }
            }
        }

        return toMelt;
    }

    static void meltCheese(List<int[]> toMelt) {
        for (int[] pos : toMelt) {
            int x = pos[0];
            int y = pos[1];
            map[x][y] = 0;
        }
    }
}
