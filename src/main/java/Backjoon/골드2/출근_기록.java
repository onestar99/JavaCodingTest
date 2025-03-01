package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시간 초과나서 힌트보고 DP 추가해서 풀음
 */

public class 출근_기록 {

    static String answer = null;
    static boolean[][][][][] dp = new boolean[51][51][51][2][3];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int cntA = 0, cntB = 0, cntC = 0;
        for (char c : input) {
            if (c == 'A') cntA++;
            else if (c == 'B') cntB++;
            else if (c == 'C') cntC++;
        }

        dfs(cntA, cntB, cntC, 0, 0, new StringBuilder());
        if (answer == null) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }


    // 백트레킹 + DFS + Sequence로 저장 후 찾으면 바로 종료
    static void dfs(int cntA, int cntB, int cntC, int coolB, int coolC, StringBuilder seq) {
        // 모든 문자를 사용했다면 정답 저장 후 종료
        if (cntA + cntB + cntC == 0) {
            answer = seq.toString();
            return;
        }

        if (dp[cntA][cntB][cntC][coolB][coolC]) return;

        // 분기마다 쿨다운을 하루씩 감소시키는 효과를 위해, 각 선택 시점에 현재 쿨다운 값을 업데이트
        // A는 제약 x 매일 가능
        if (cntA > 0) {
            int nextCoolB = (coolB > 0) ? coolB - 1 : 0;
            int nextCoolC = (coolC > 0) ? coolC - 1 : 0;
            seq.append('A');
            dfs(cntA - 1, cntB, cntC, nextCoolB, nextCoolC, seq);
            if (answer != null) return;
            seq.deleteCharAt(seq.length() - 1);
        }

        // B 쿨타임 돌면 가능
        if (cntB > 0 && coolB == 0) {
            int nextCoolB = 1;  // B를 사용하면 다음 날 B는 사용 불가 (쿨다운 1)
            int nextCoolC = (coolC > 0) ? coolC - 1 : 0;
            seq.append('B');
            dfs(cntA, cntB - 1, cntC, nextCoolB, nextCoolC, seq);
            if (answer != null) return;
            seq.deleteCharAt(seq.length() - 1);
        }

        // C 쿨타임 돌면 가능
        if (cntC > 0 && coolC == 0) {
            int nextCoolB = (coolB > 0) ? coolB - 1 : 0;
            int nextCoolC = 2;  // C를 사용하면 다음 두 날 C는 사용 불가 (쿨다운 2)
            seq.append('C');
            dfs(cntA, cntB, cntC - 1, nextCoolB, nextCoolC, seq);
            if (answer != null) return;
            seq.deleteCharAt(seq.length() - 1);
        }

        dp[cntA][cntB][cntC][coolB][coolC] = true;

    }
}
