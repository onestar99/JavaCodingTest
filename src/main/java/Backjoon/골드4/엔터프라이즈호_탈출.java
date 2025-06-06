package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 엔터프라이즈호_탈출 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        // 상하좌우 방향 배열
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            // 클래스별 무력화 시간 저장 (A=0, B=1, ... Z=25)
            int[] cost = new int[26];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                char cls = st.nextToken().charAt(0);
                int t = Integer.parseInt(st.nextToken());
                cost[cls - 'A'] = t;
            }

            char[][] grid = new char[H][W];
            int startY = -1, startX = -1;

            // 그리드 읽으면서 'E' 위치 찾기
            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    grid[i][j] = line.charAt(j);
                    if (grid[i][j] == 'E') {
                        startY = i;
                        startX = j;
                    }
                }
            }

            // Dijkstra 준비
            final int INF = Integer.MAX_VALUE;
            int[][] dist = new int[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    dist[i][j] = INF;
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            dist[startY][startX] = 0;
            pq.offer(new Node(startY, startX, 0));

            int answer = -1;
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                int cy = cur.y;
                int cx = cur.x;
                int cc = cur.cost;

                // 이미 더 짧은 경로가 발견된 경우 스킵
                if (cc > dist[cy][cx]) {
                    continue;
                }

                // 테두리 칸에 도달하면 정답 확정
                if (cy == 0 || cy == H - 1 || cx == 0 || cx == W - 1) {
                    answer = cc;
                    break;
                }

                // 4방향 탐색
                for (int dir = 0; dir < 4; dir++) {
                    int ny = cy + dy[dir];
                    int nx = cx + dx[dir];
                    if (ny < 0 || ny >= H || nx < 0 || nx >= W) {
                        continue;
                    }
                    // 도착 칸의 비용
                    int nc;
                    if (grid[ny][nx] == 'E') {
                        nc = 0;
                    } else {
                        nc = cost[grid[ny][nx] - 'A'];
                    }
                    int nd = cc + nc;
                    if (nd < dist[ny][nx]) {
                        dist[ny][nx] = nd;
                        pq.offer(new Node(ny, nx, nd));
                    }
                }
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb.toString());
    }

    static class Node implements Comparable<Node> {
        int y, x, cost;
        Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}
