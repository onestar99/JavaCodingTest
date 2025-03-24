package Backjoon.플래티넘5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 비숍 {

    static int[][] map;
    static int N;
    static int result = 0;
    static boolean[] leftDiag, rightDiag;

    public static void main(String[] args) throws IOException {

        // 이거 어떻게 푸나요?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        leftDiag = new boolean[2 * N];
        rightDiag = new boolean[2 * N];

        dfs(0, 0);

        System.out.println(result);

    }

    // 해당 좌표에서 비숍이 있을 때의 최대 값 구하기
    private static void dfs(int pos, int count) {
        if (pos >= N * N) {
            result = Math.max(result, count);
            return;
        }

        int x = pos / N;
        int y = pos % N;

        if (map[x][y] == 1 && !leftDiag[x + y] && !rightDiag[x - y + (N - 1)]) {
            leftDiag[x + y] = true;
            leftDiag[x - y + N - 1] = true;
            dfs(pos + 1, count + 1);
            leftDiag[x + y] = false;
            leftDiag[x - y + N - 1] = false;
        }
        // 현재 칸에 놓지 않는 경우
        dfs(pos + 1, count);
    }
}
