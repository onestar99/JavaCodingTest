package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 탐색하면서 1로 체크 후 1인 곳을 발견하면 사이클이 존재한다는 것. count++ 후 재귀 종료하면서 DFS 완료(2)로 변경
 * 다른 영역을 찾으며 반복
 */

public class 피리_부는_사나이 {

    static int[][] state; // 0 이면 미확인 상태, 1 이면 dfs 진행 중, 2 면 dfs 완료
    static char[][] directionMap;
    static int N, M, resultCount;

    public static void main(String[] args) throws IOException {

        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        state = new int[N][M];
        directionMap = new char[N][M];
        resultCount = 0;
        for (int i = 0; i < N; i++) {
            directionMap[i] = br.readLine().toCharArray();
        }
        //입력끝

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(state[i][j] == 0)
                    dfs(i, j);
            }
        }
        System.out.println(resultCount);
    }

    private static void dfs(int r, int c) {
        state[r][c] = 1;
        int[] nextDirection = getNextDirection(r, c);
        int nr = r + nextDirection[0];
        int nc = c + nextDirection[1];
        if (state[nr][nc] == 0) {
            dfs(nr, nc);
        } else if (state[nr][nc] == 1) { // 사이클 발견
            resultCount++;
        }
        state[r][c] = 2;
    }

    private static int[] getNextDirection(int r, int c) {
        char direction = directionMap[r][c];
        switch (direction) {
            case 'L': return new int[]{0, -1};
            case 'R': return new int[]{0, 1};
            case 'U': return new int[]{-1, 0};
            case 'D': return new int[]{1, 0};
        }
        return new int[]{0, 0};
    }
}
