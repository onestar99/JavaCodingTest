package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class 거울_설치 {

    static final int INF = Integer.MAX_VALUE;
    static int N;
    static char[][] map;
    static int[][][] dist;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        List<int[]> doors = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '#') {
                    doors.add(new int[]{i, j});
                }
            }
        }

        dist = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        // 시작문 위치
        int sx = doors.get(0)[0];
        int sy = doors.get(0)[1];
        // 도착문 위치
        int ex = doors.get(1)[0];
        int ey = doors.get(1)[1];

        // 0-1 BFS 준비
        Deque<int[]> deque = new ArrayDeque<>();
        // 시작 위치에서 네 방향 모두 0개의 거울로 출발
        for (int d = 0; d < 4; d++) {
            dist[sx][sy][d] = 0;
            deque.offer(new int[]{sx, sy, d});
        }

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            int x = cur[0], y = cur[1], dir = cur[2];
            int cost = dist[x][y][dir];

            int nx = x + dx[dir];
            int ny = y + dy[dir];
            // 범위 벗어나거나 벽이면 스킵
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (map[nx][ny] == '*') continue;

            // 직진: 거울 추가 비용 없음, 덱 앞에 삽입
            if (dist[nx][ny][dir] > cost) {
                dist[nx][ny][dir] = cost;
                deque.offerFirst(new int[]{nx, ny, dir});
            }

            // 느낌표 위치에서 거울 설치(비용 +1) -> 뒤에 삽입
            if (map[nx][ny] == '!') {
                // '/' 슬래쉬 거울 반사: dir ^ 3 (XOR)
                int dSlash = dir ^ 3;
                if (dist[nx][ny][dSlash] > cost + 1) {
                    dist[nx][ny][dSlash] = cost + 1;
                    deque.offerLast(new int[]{nx, ny, dSlash});
                }
                // '\' 역슬래쉬 거울 반사: dir ^ 2 (XOR)
                int dBack  = dir ^ 2;
                if (dist[nx][ny][dBack] > cost + 1) {
                    dist[nx][ny][dBack] = cost + 1;
                    deque.offerLast(new int[]{nx, ny, dBack});
                }
            }
        }

        // 도착 위치에서 네 방향 중 최소 거울 수
        int answer = INF;
        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, dist[ex][ey][d]);
        }
        System.out.println(answer);
    }
}
