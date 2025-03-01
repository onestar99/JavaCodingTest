package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 뭔가 로직이 더 있나 싶은데, N, M 범위가 작아서 그냥 브루드포스 DFS 일단 시도.
 * DP 필요하다. 시간초과 난다.
 */

public class 게임 {

    static int[][] board;
    static boolean[][] visited; // 사이클 여부 확인을 위해
    static int N, M, moveCount;
    static boolean cycleFound;

    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];
        moveCount = -1;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char token = line.charAt(j);
                int tokenNum = (token == 'H') ? 0 : token - '0'; // H 입력은 0으로 받음
                board[i][j] = tokenNum;
            }
        }

        // dfs
        int resultCount = dfs(0, 0);
        if (cycleFound) {
            System.out.println(-1);
        }
        else {
            System.out.println(resultCount);
        }
    }

    private static int dfs(int r, int c) {
        // 범위 안에 있거나 구멍에 빠지면 종료
        if(r < 0 || r >= N || c < 0 || c >= M || board[r][c] == 0) {
            return 0;
        }
        if(visited[r][c]) {
            // 사이클이 존재하면 -1로 처리
            cycleFound = true;
            return 0;
        }

        visited[r][c] = true;
        int maxMoves = 0;
        int moveNumber = board[r][c];
        int[][] directions = {{0, moveNumber}, {0, -moveNumber}, {moveNumber, 0}, {-moveNumber, 0}};

        for (int[] direction : directions) {
            int nr = r + direction[0];
            int nc = c + direction[1];
            int moves = dfs(nr, nc) + 1;
            if (cycleFound) return 0;
            maxMoves = Math.max(maxMoves, moves);
        }

        visited[r][c] = false;
        return maxMoves;
    }
}
