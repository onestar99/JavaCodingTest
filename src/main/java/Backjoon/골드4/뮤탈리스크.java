package Backjoon.골드4;

import java.io.*;
import java.util.*;

public class 뮤탈리스크 {
    // 뮤탈리스크가 한 번의 공격에서 감소시키는 체력 조합
    static int[][] ATTACKS = {
            {9, 3, 1},
            {9, 1, 3},
            {3, 9, 1},
            {3, 1, 9},
            {1, 9, 3},
            {1, 3, 9}
    };

    static boolean[][][] visited = new boolean[61][61][61];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] scv = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bfs(scv));
    }

    // BFS를 이용하여 최소 공격 횟수 계산
    private static int bfs(int[] scv) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {scv[0], scv[1], scv[2], 0}); // 체력과 공격 횟수
        visited[scv[0]][scv[1]][scv[2]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int hp1 = current[0];
            int hp2 = current[1];
            int hp3 = current[2];
            int count = current[3];

            // 모든 SCV가 파괴된 경우
            if (hp1 <= 0 && hp2 <= 0 && hp3 <= 0) {
                return count;
            }

            // 큐에 추가
            for (int[] attack : ATTACKS) {
                int nextHp1 = Math.max(0, hp1 - attack[0]);
                int nextHp2 = Math.max(0, hp2 - attack[1]);
                int nextHp3 = Math.max(0, hp3 - attack[2]);

                if (!visited[nextHp1][nextHp2][nextHp3]) {
                    visited[nextHp1][nextHp2][nextHp3] = true;
                    queue.add(new int[] {nextHp1, nextHp2, nextHp3, count + 1});
                }
            }
        }

        return -1;
    }
}
