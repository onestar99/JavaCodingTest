package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 색종이_붙이기 {

    static int[] paperCnt = new int[6];
    static int[][] map = new int[10][10];
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        Arrays.fill(paperCnt, 5);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    // 큰거부터 덮고, 작은거 덮는 식으로, 다만 종이가 5개가 되면 다음걸로 넘어가도록
    private static void dfs(int x, int y, int count) {
        if (count >= ans) { // 최소 값 갱신 불가하므로 리턴
            return;
        }

        if (y >= 10) {
            dfs(x + 1, 0, count);
            return;
        }

        if (x >= 10) {
            ans = Math.min(ans, count);
            return;
        }

        if (map[x][y] == 0) {
            dfs(x, y + 1, count);
            return;
        }

        for (int size = 5; size >= 1; size--) {
            if (paperCnt[size] > 0 && canPlace(x, y, size)) {
                setPaper(x, y, size, 0); // 붙이기
                paperCnt[size]--;
                dfs(x, y + 1, count + 1);
                setPaper(x, y, size, 1); // 때기
                paperCnt[size]++;
            }
        }
    }

    private static boolean canPlace(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] != 1) return false;
            }
        }
        return true;
    }

    private static void setPaper(int x, int y, int size, int attach) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = attach;
            }
        }
    }

}
