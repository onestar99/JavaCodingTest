package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ABBC {

    static int answer = 0;
    static char[] s;
    static int n;

    public static void main(String[] args) throws IOException {
        /*
        * A, B, C
        * A 뒤에 있는 B 선택해서 둘다 제거 가능
        * B 뒤에 있는 C 선택해서 둘다 제거 가능
        * */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        s = S.toCharArray();
        n = s.length;


        // DFS인가? DP인가?
        boolean[] removed = new boolean[n];
        dfs(removed, 0);
        System.out.println(answer);

    }

    private static void dfs(boolean[] removed, int cnt) {

        boolean canRemove = false;
        for (int i = 0; i < n; i++) {
            if (removed[i]) continue;
            for (int j = 1; j < n; j++) {
                if(removed[j]) continue;

                if ((s[i] == 'A' && s[j] == 'B') || (s[i] == 'B' && s[j] == 'C')) {
                    removed[i] = true;
                    removed[j] = true;
                    dfs(removed, cnt + 1);
                    removed[i] = false;
                    removed[j] = false;
                    canRemove = true; // 문자 제거가 발생
                }
            }
        }

        if (!canRemove) { // 문자 제거 못하면 answer 갱신 시도
            answer = Math.max(answer, cnt);
        }
    }
}
