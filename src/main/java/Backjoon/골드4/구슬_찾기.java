package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 구슬_찾기 {

    static int N, M;
    static ArrayList<Integer>[][] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        list = new ArrayList[N + 1][2];
        for (int i = 1; i <= N; i++) {
            list[i][0] = new ArrayList<>(); // i 보다 가벼운 구슬들
            list[i][1] = new ArrayList<>(); // i 보다 무거운 구슬들
        }

        for (int i = 1; i <= M; i++) {
            s = br.readLine().split(" ");
            int heavy = Integer.parseInt(s[0]);
            int light = Integer.parseInt(s[1]);
            list[heavy][0].add(light);
            list[light][1].add(heavy);
        }

        int result = 0;
        int mid = (N + 1) / 2;
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            int countHeavy = dfs(i, 0);

            visited = new boolean[N + 1];
            int countLight = dfs(i, 1);

            if (countHeavy >= mid || countLight >= mid) {
                result++;
            }
        }
        System.out.println(result);

    }

    // 개수 체크
    private static int dfs(int cur, int flag) {
        int cnt = 0;
        for (int next : list[cur][flag]) {
            if (!visited[next]) {
                visited[next] = true;
                cnt += 1 + dfs(next, flag);
            }
        }
        return cnt;
    }
}
