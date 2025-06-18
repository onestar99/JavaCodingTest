package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 진욱이의_농장 {

    static int[][] ground;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ground = new int[N][N];

        // 각 땅에 과일(숫자) 표기하기, 씨앗 뿌리기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());

            for (int j = x; j < x + l; j++) {
                for (int k = y; k < y + l; k++) {
                    ground[k][j] = f;
                }
            }
        }

        // 과일 조합(1개 or 2개) 목록 생성
        List<int[]> combos = new ArrayList<>();
        // 단일 과일
        for (int t = 1; t <= 7; t++) {
            combos.add(new int[]{t});
        }
        // 두 종류 과일
        for (int i = 1; i <= 7; i++) {
            for (int j = i + 1; j <= 7; j++) {
                combos.add(new int[]{i, j});
            }
        }

        int maxLen = 0;
        // 각 조합별로 DP 수행
        for (int[] combo : combos) {
            // valid[r][c] = 이 칸의 과일이 조합에 속하면 true
            boolean[][] valid = new boolean[N][N];

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int fruit = ground[r][c];

                    // 0번 과일은 항상 제외
                    if (fruit == 0) continue;

                    // 이번 조합에 있는 과일 종류가 맞으면 true
                    for (int t : combo) {
                        if (fruit == t) {
                            valid[r][c] = true;
                            break;
                        }
                    }
                }
            }

            // 해당 조합의 DP
            int[][] dp = new int[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!valid[r][c]) { // 가져올수 없는 농장 영역 체크
                        dp[r][c] = 0;
                        continue;
                    }

                    if (r == 0 || c == 0) { // 왼쪽 끝줄, 윗줄이면서 씨앗이 뿌려진 경우
                        dp[r][c] = 1;
                    } else { // 왼쪽 위, 왼쪽, 위 비교해서 가장 작은 값에 + 1
                        dp[r][c] = Math.min(dp[r-1][c-1], Math.min(dp[r-1][c], dp[r][c-1])) + 1;
                    }
                    if (dp[r][c] > maxLen) { // Math.max(dp[r][c], maxLen)
                        maxLen = dp[r][c];
                    }
                }
            }
        }

        // 면적 출력
        System.out.println(maxLen * maxLen);
    }
}
