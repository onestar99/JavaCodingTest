package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽_부수고_이동하기 {


    static class Node {
        int x, y, distance, wallBroken;

        Node(int x, int y, int distance, int wallBroken) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.wallBroken = wallBroken;
        }
    }

    /**
     * BFS 사용해서 최단 경로 구하기
     *
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // BFS
        // 방문 여부 (벽을 부수지 않고 방문했는지, 부수고 방문했는지)
        boolean[][][] visited = new boolean[n][m][2];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.x == n - 1 && current.y == m - 1) {
                System.out.println(current.distance);
                return;
            }

            for (int[] dir : direction) {
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];

                if (nx < 0 || ny  < 0 || nx >= n || ny >= m) {
                    continue;
                }

                // 이동 가능한 경우
                if (map[nx][ny] == 0) {
                    // 벽을 부수지 않고 방문
                    if (!visited[nx][ny][current.wallBroken]) {
                        visited[nx][ny][current.wallBroken] = true;
                        queue.add(new Node(nx, ny, current.distance + 1, current.wallBroken));
                    }
                }
                // 이동 불가(벽) 이고 아직 벽을 부수지 않은 상태
                else if (map[nx][ny] == 1 && current.wallBroken == 0) {
                    if (!visited[nx][ny][1]) {
                        visited[nx][ny][1] = true;
                        queue.add(new Node(nx, ny, current.distance + 1, 1));
                    }
                }
            }

        }

        System.out.println(-1);



        }
}
