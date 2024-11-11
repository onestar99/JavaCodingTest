package Backjoon.골드4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 녹색_옷_입은_애가_젤다지 {

    // 동서남북
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    /**
    최소 금액을 찾는다. -> BFS

     최소 금액이라 다익스트라로 갱신 가능할 듯.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int count = 0;
        while (true) {
            count++;
            int n = Integer.parseInt(scanner.nextLine());

            if (n == 0) return;

            // 각 시행 시작
            int result = 0; // 결과

            int[][] map = new int[n][n]; // 맵
            int[][] minRupee = new int[n][n]; // 방문

            // map 채우기
            for (int i = 0; i < n; i++) {
                map[i] = Arrays.stream(scanner.nextLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            // 최소 비용 배열 초기화 (최댓값으로)
            for (int i = 0; i < n; i++) {
                Arrays.fill(minRupee[i], Integer.MAX_VALUE);
            }


            /** BFS
             1. 첫 시작 지점의 노드를 넣는다.
             2. queue가 빌 때까지 node를 poll한다.
             3. 가져온 노드를 기반한 x, y값을 가지고 상하좌우 또는 동서남북 nx, ny를 계산한다.
             4. if문을 통해 nx, ny가 범위안에 있는지, 유효한 위치인지 확인한다.
             5. 이전 이동했던 경로상의 루피값들을 모두 더한 값과 새로운 위치의 루피값을 더하여 계산한다.
             6. 그 값이 최소맵의 값보다 작으면 갱신시키고 그 경로를 queue에 추가한다.
            */
            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(0, 0)); // 시작지점 추가
            minRupee[0][0] = map[0][0];

            while (!queue.isEmpty()) {


                Node current = queue.poll();
                int x = current.x;
                int y = current.y;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < n && ny <n) {
                        int newCost = minRupee[x][y] + map[nx][ny];

                        if (minRupee[nx][ny] > newCost) {
                            minRupee[nx][ny] = newCost;
                            queue.add(new Node(nx, ny));
                        }
                    }
                }
            }
            result = minRupee[n - 1][n - 1];
            System.out.println("Problem " + count + ": " + result);
        }

    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
