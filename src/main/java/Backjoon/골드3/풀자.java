package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dfs로 풀고 가지치기
 * 보통 두 번씩 넘기는게 최소 값에 가까우므로 위치 조정
 */

public class 풀자 {

    private static int answer;
    private static int N;
    private static int V;
    private static int[] interesting;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        interesting = new int[N];
        visited = new boolean[51][1001][1001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            interesting[i] = Integer.parseInt(st.nextToken());
        }
        answer = N;
        dfs(0, interesting[0], interesting[0], 1);

        System.out.println(answer);
    }

    private static void dfs(int idx, int max, int min, int cnt) {

        if (visited[idx][max][min]) { // 방문했던 곳이면 가지치기
            return;
        }
        visited[idx][max][min] = true;
        if (cnt >= answer) { // 해당 경우의 수가 이미 최소 문제 수를 넘기면 가지치기
            return;
        }
        if (max - min >= V) { // 갱신하고 return (정답을 찾은 경우)
            answer = Math.min(answer, cnt);
            return;
        }
        if (idx + 2 < N) { // 앞으로 두 칸을 내다 봄
            int nMax = Math.max(max, interesting[idx + 2]);
            int nMin = Math.min(min, interesting[idx + 2]);
            dfs(idx + 2, nMax, nMin, cnt + 1);
        }
        if (idx + 1 < N) { // 앞으로 한 칸을 내다 봄
            int nMax = Math.max(max, interesting[idx + 1]);
            int nMin = Math.min(min, interesting[idx + 1]);
            dfs(idx + 1, nMax, nMin, cnt + 1);
        }
    }
}
