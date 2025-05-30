package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 엔터프라이즈호_탈출 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            Map<Character, Integer> klingonTimes = new HashMap<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                char clazz = st.nextToken().charAt(0);
                int time = Integer.parseInt(st.nextToken());
                klingonTimes.put(clazz, time);
            }

            char[][] map = new char[H][W];
            int startX = 0, startY = 0;

            for (int i = 0; i < H; i++) {
                String line = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'E') {
                        startX = i;
                        startY = j;
                        map[i][j] = 'X'; // E는 경로에서 제외시키고 나중에 처리
                        klingonTimes.put('X', 0); // 시작 지점은 무력화 시간이 0
                    }
                }
            }

            int result = dijkstra(startX, startY, W, H, map, klingonTimes);
            System.out.println(result);
        }

    }

    private static class Node implements Comparable<Node> {
        int x, y, cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }


    private static int dijkstra(int startX, int startY, int W, int H, char[][] map, Map<Character, Integer> klingonTimes) {
        int[][] dist = new int[H][W];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startX, startY, 0));
        dist[startX][startY] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.x][cur.y] < cur.cost) continue;

            // 가장자리 도착
            if (cur.x == 0 || cur.y == 0 || cur.x == H - 1 || cur.y == W - 1) {
                return cur.cost;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx >= 0 && ny >= 0 && nx < H && ny < W) {
                    char klingon = map[nx][ny];
                    int nextCost = cur.cost + klingonTimes.get(klingon);

                    if (nextCost < dist[nx][ny]) {
                        dist[nx][ny] = nextCost;
                        pq.offer(new Node(nx, ny, nextCost));
                    }
                }
            }
        }

        return -1;
    }
}
