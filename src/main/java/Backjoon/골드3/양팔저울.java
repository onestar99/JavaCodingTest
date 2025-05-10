package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 양팔저울 {

    static int N;
    static int[] weights;
    static boolean[][] visited;
    static boolean[] possible;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        weights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            sum += weights[i];
        }

        visited = new boolean[N + 1][sum + 1];
        possible = new boolean[sum + 1];

        dfs(0, 0);

        int M = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int marble = Integer.parseInt(st.nextToken());
            // 구슬 무게가 sum보다 크면 불가능
            if (marble > sum) {
                sb.append("N");
            } else {
                sb.append(possible[marble] ? "Y" : "N");
            }
            if (i < M - 1) sb.append(" ");
        }

        System.out.println(sb);
    }

    // idx번째 추를 고려하면서, 현재 무게 차이를 diff로 두고 DFS
    static void dfs(int idx, int diff) {

        if (visited[idx][diff]) {
            return;
        }
        visited[idx][diff] = true;
        possible[diff] = true;

        // 모든 추를 사용했으면 종료
        if (idx == N) return;

        int w = weights[idx];
        // 현재 추를 왼쪽에 올리기 → diff + w
        dfs(idx + 1, diff + w);
        // 현재 추를 오른쪽에 올리기 → abs (diff - w)
        dfs(idx + 1, Math.abs(diff - w));
        // 현재 추를 사용하지 않기
        dfs(idx + 1, diff);
    }
}
