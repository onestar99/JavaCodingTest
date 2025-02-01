package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * -1 불 도달 x
 * 0 초기 불의 위치
 */

public class 불 {

    static int w, h;
    static char[][] map;
    static int[][] fireTime;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            fireTime = new int[h][w];
            for (int i = 0; i < h; i++) {
                Arrays.fill(fireTime[i], -1);
            }

            int startX = -1, startY = -1;
            Queue<Point> fireQ = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == '@') {
                        startX = i;
                        startY = j;
                    }
                    if (map[i][j] == '*') {
                        fireQ.offer(new Point(i, j));
                        fireTime[i][j] = 0; // 초기 불의 위치 시간은 0
                    }
                }
            }

            // 불의 확산 시간 계산하기
            while (!fireQ.isEmpty()) {
                Point cur = fireQ.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];

                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
                        continue;
                    }

                    if (map[nx][ny] == '#') continue;
                    // 이미 불이 도달한 적이 있으면 continue
                    if (fireTime[nx][ny] != -1) continue;

                    fireTime[nx][ny] = fireTime[cur.x][cur.y] + 1;
                    fireQ.offer(new Point(nx, ny));
                }
            }

            // bfs 이동
            int result = -1;
            int[][] visited = new int[h][w];
            for (int i = 0; i < h; i++) {
                Arrays.fill(visited[i], -1);
            }
            Queue<Point> personQ = new LinkedList<>();
            visited[startX][startY] = 0;
            personQ.offer(new Point(startX, startY));
            boolean escaped = false;

            while (!personQ.isEmpty() && !escaped) {
                Point cur = personQ.poll();
                int curTime = visited[cur.x][cur.y];

                // 현재 위치가 빌딩의 가장자리에 있으면 탈출
                if (cur.x == 0 || cur.y == 0 || cur.x == h - 1 || cur.y == w - 1) {
                    result = curTime + 1;
                    escaped = true;
                    break;
                }

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];
                    if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                    if (visited[nx][ny] != -1) continue;
                    if (map[nx][ny] == '#') continue;

                    int nextTime = curTime + 1;
                    // 불이 해당 좌표에 도달하는 시간이 nextTime 이하이면 이동 x
                    if (fireTime[nx][ny] != -1 && fireTime[nx][ny] <= nextTime) continue;

                    visited[nx][ny] = nextTime;
                    personQ.offer(new Point(nx, ny));
                }
            }

            if (escaped) {
                sb.append(result).append("\n");
            } else {
                sb.append("IMPOSSIBLE").append("\n");
            }
        }
        System.out.print(sb.toString());
    }


    private static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
