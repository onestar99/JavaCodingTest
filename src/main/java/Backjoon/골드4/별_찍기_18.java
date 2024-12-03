package Backjoon.골드4;

import java.util.Arrays;
import java.util.Scanner;

public class 별_찍기_18 {

    /**
     *
     *                        *   공백 24,  *(1개)         1번째
     *                       * *  공백 23,  *(2개)         2번째
     *                      *****                         3번째
     *                     *     *                        4번째
     *                    * *   * *                       5번째
     *                   ***** *****                      6번째
     *                  *           *                     7번째
     *                 * *         * *                    8번째
     *                *****       *****                   9번째
     *               *     *     *     *                  10번째
     *              * *   * *   * *   * *                 11번째
     *             ***** ***** ***** *****                12번째
     *            *                       *               13번째
     *           * *                     * *              14번째
     *          *****                   *****             15번째
     *         *     *                 *     *            16번째
     *        * *   * *               * *   * *           17번째
     *       ***** *****             ***** *****           18번째: 5 * 4
     *      *           *           *           *          19번째: 1 * 4
     *     * *         * *         * *         * *         20번째: 2 * 4
     *    *****       *****       *****       *****        21번째: 5 * 4
     *   *     *     *     *     *     *     *     *       22번째: 1 * 8
     *  * *   * *   * *   * *   * *   * *   * *   * *      23번째: 2 * 8
     * ***** ***** ***** ***** ***** ***** ***** *****     24번째: 5 * 8
     */

    static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        map = new char[N][2 * N - 1];

        // 배열 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], ' ');
        }
        drawStar(0, N - 1, N);
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(map[i]);
            sb.append('\n');
        }
        System.out.println(sb);
    }

    // 재귀 돌리기
    static void drawStar(int x, int y, int N) {
        if (N == 3) {
            // 위에 별 찍기 (1개)
            map[x][y] = '*';
            // 두번째 줄 별 찍기 (2개)
            map[x + 1][y - 1] = '*';
            map[x + 1][y + 1] = '*';
            // 세번째 줄 별 찍기 (5개)
            for (int i = -2; i <= 2; i++) {
                map[x + 2][y + i] = '*';
            }
            return;
        }

        int half = N / 2;
        drawStar(x, y, half); // 위쪽 삼각형
        drawStar(x + half, y - half, half); // 왼쪽 아래 삼각형
        drawStar(x + half, y + half, half); // 오른쪽 아래 삼각형
    }
}
