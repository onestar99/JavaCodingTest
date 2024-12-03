package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 감시 {

    /**
     * CCTV 정보(방향) 저장해놓기
     * 사각 지대의 최소 크기를 구하라 -> 모든 경우를 구하여 0의 개수를 체크하여 가장 작은 것을 구하라
     * DFS와 백트래킹을 사용하여 모든 경우의 수 체크하며 0의 개수 min 으로 갱신하기
     *
     */

    static int N, M;
    static int minBlindSpot = Integer.MAX_VALUE;
    static int[][] map;
    static List<int[]> cctvs = new ArrayList<>();
    static int[][][] directions = {
            {},
            {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}, // 1번
            {{1, 0, -1, 0}, {0, 1, 0, -1}}, // 2번
            {{1, 0, 0, 1}, {0, 1, -1, 0}, {-1, 0, 0, -1}, {0, -1, 1, 0}}, // 3번 동남, 남서, 서북, 북동
            {{-1, 0, 0, -1, 1, 0}, {0, -1, 1, 0, 0, 1}, {1, 0, 0, 1, -1, 0}, {0, 1, -1, 0, 0, -1}}, // 4번 서북동, 북동남, 동남서, 남서북
            {{1, 0, -1, 0, 0, 1, 0, -1}} // 5번
    };

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
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new int[]{i, j, map[i][j]});
                }
            }
        }
        dfs(0);
        System.out.println(minBlindSpot);
    }

    static void dfs(int depth) {
        if (depth == cctvs.size()) {
            minBlindSpot = Math.min(minBlindSpot, countBlindSpots());
            return;
        }

        int[] cctv = cctvs.get(depth);
        int x = cctv[0], y = cctv[1], type = cctv[2];

        for (int[] dir : directions[type]) {
            // 감시 영역 표시
            mark(x, y, dir, true);
            // 다음 CCTV로 이동
            dfs(depth + 1);
            // 감시 영역 복구
            mark(x, y, dir, false);
        }
    }

    static void mark(int x, int y, int[] dir, boolean add) {
        for (int d = 0; d < dir.length; d += 2) {
            int nx = x, ny = y;
            while (true) {
                nx += dir[d];
                ny += dir[d + 1];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 6) break; // 벽 만나면 멈추기
                if (map[nx][ny] > 0) continue; // 다른 CCTV나 벽은 무시
                if (add) {
                    map[nx][ny] -= 1; // 감시 영역 표시
                } else {
                    map[nx][ny] += 1; // 감시 영역 복구
                }
            }
        }
    }


    static int countBlindSpots() {
        int count = 0;
        for (int[] row : map) {
            for (int cell : row) {
                if (cell == 0) count++;
            }
        }
        return count;
    }
}
