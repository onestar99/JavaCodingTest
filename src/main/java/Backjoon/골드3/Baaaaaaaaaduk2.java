package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baaaaaaaaaduk2 {

    static int n, m;
    static int[][] grid;
    static List<int[]> empties;
    static int maxCaptured = 0;
    // 4방 탐색을 위한 방향 배열
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        empties = new ArrayList<>();

        // 보드 입력 및 빈 칸 좌표 수집
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 0) {
                    empties.add(new int[]{i, j});
                }
            }
        }

        int E = empties.size();
        // 빈 칸 두 개를 선택하는 모든 조합 시도
        for (int i = 0; i < E - 1; i++) {
            int r1 = empties.get(i)[0], c1 = empties.get(i)[1];
            grid[r1][c1] = 1;  // 첫 번째 돌 놓기
            for (int j = i + 1; j < E; j++) {
                int r2 = empties.get(j)[0], c2 = empties.get(j)[1];
                grid[r2][c2] = 1;  // 두 번째 돌 놓기

                // 포획된 돌 수 계산
                int captured = calculateCaptured();
                if (captured > maxCaptured) {
                    maxCaptured = captured;
                }

                grid[r2][c2] = 0;  // 두 번째 돌 복구
            }
            grid[r1][c1] = 0;  // 첫 번째 돌 복구
        }

        // 결과 출력
        System.out.println(maxCaptured);
    }

    // 현재 배치 상태에서 포획된 상대 돌 수 계산
    static int calculateCaptured() {
        boolean[][] visited = new boolean[n][m];
        int total = 0;
        // BFS용 간이 큐 (크기는 최대 n*m)
        int[] queue = new int[n * m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 아직 탐색하지 않은 상대 돌 그룹 발견 시
                if (grid[i][j] == 2 && !visited[i][j]) {
                    int head = 0;
                    int tail = 0;
                    boolean hasLiberty = false;
                    int count = 0;

                    // BFS 시작
                    visited[i][j] = true;
                    queue[tail++] = i * m + j;

                    while (head < tail) {
                        int cur = queue[head++];
                        int r = cur / m, c = cur % m;
                        count++;

                        // 4방 탐색
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d], nc = c + dc[d];
                            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                            if (grid[nr][nc] == 0) {
                                hasLiberty = true;  // 빈 칸이 있으면 탈출 가능
                            } else if (grid[nr][nc] == 2 && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                queue[tail++] = nr * m + nc;
                            }
                        }
                    }

                    // 탈출구가 없는 그룹이면 죽은 돌 개수에 포함
                    if (!hasLiberty) {
                        total += count;
                    }
                }
            }
        }

        return total;
    }
}
