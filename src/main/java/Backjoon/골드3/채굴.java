package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 이분탐색 + Bfs
 */

public class 채굴 {

    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] air;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        int left = 1;
        int right = max;
        int answer = max;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    // d로 K개 이상 채굴 가능한지 체크
    private static boolean check(int d) {
        visited = new boolean[N][M];
        air = new boolean[N][M];

        // 공기 영역 구하기
        Queue<int[]> queue = new LinkedList<>();
        for (int j = 0; j < M; j++) {
            if (map[0][j] <= d && !air[0][j]) {
                air[0][j] = true;
                queue.add(new int[]{0, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = now[0] + dx[dir];
                int ny = now[1] + dy[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (air[nx][ny]) continue;
                if (map[nx][ny] > d) continue;

                air[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }

        // 공기와 맞닿은 광물만 채굴 BFS
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (air[i][j] && !visited[i][j] && map[i][j] <= d) {
                    count += bfs(i, j, d);
                    if (count >= K) return true;
                }
            }
        }

        return count >= K;
    }

    private static int bfs(int x, int y, int d) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int mined = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = now[0] + dx[dir];
                int ny = now[1] + dy[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] > d) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
                mined++;
            }
        }

        return mined;
    }
}
