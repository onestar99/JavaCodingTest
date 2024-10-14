package Backjoon;

import java.util.Scanner;

public class 미친_로봇 {

    static double[] prob;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static double result;
    static int N;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        prob = new double[4];
        for (int i = 0; i < 4; i++) {
            prob[i] = (double) scan.nextInt() / 100;
        }

        visited = new boolean[30][30];
        visited[15][15] = true;
        result = 0.0;

        dfs(15, 15, 0, 1.0);
        System.out.println(result);
    }

    public static void dfs(int x, int y, int count, double probability) {

        // 끝나는 조건문 걸어주기, result 에 확률 더하기
        // count == N 이면 종료
        if (count == N) {
            result += probability;
            return;
        }

        // for 문으로 동서남북 지정
        // dx, dy 지정
        for (int i = 0; i < 4; i++) {

            if (prob[i] == 0.0) continue;

            int nx = x + dx[i];
            int ny = y + dy[i];

            // 이미 방문한 곳 탐색 x
            if (visited[nx][ny]) continue;

            // 방문처리 후 dfs 다음 dfs 탐색
            visited[nx][ny] = true;
            dfs(nx, ny, count + 1, probability * prob[i]);
            // 백트래킹
            visited[nx][ny] = false;
        }
    }
}
