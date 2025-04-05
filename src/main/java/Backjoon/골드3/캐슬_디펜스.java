package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 캐슬_디펜스 {

    /**
     * 조합을 얻은 뒤, 시뮬레이션
     *
     */
    static int N, M, D;
    static int[][] map;
    static int result = 0;
    static int[] picked = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 조합
        pick(0, 0);
        System.out.println(result);
    }

    // 몇개 뽑을지, 어디서 시작할 지
    private static void pick(int cnt, int start) {
        if (cnt == 3) {
            result = Math.max(result, game());
            return;
        }
        for (int i = start; i < M; i++) {
            picked[cnt] = i;
            pick(cnt + 1, i + 1);
        }
    }

    public static int game() {
        int count = 0;
        int[][] status = new int[N][M];

        for (int line = N; line > 0; line--) {
            for (int pick : picked) {
                for (int d = 1; d <= D; d++) { // 거리가 가까운 적부터 처치
                    int cnt = attack(status, d, line, pick);
                    if (cnt < 0) {
                        continue;
                    }
                    count += cnt;
                    break;
                }
            }
        }
        return count;
    }

    private static int attack(int[][] status, int d, int line, int pick) {
        int nn; // location (nn, nm)
        for (int nm = pick - d; nm <= pick + d; nm++) {
            nn = line - (d - Math.abs(nm - pick));
            if (nn < 0 || nn >= line || nm < 0 || nm >= M) {
                continue;
            }
            if (map[nn][nm] == 0) {
                continue;
            }
            if (status[nn][nm] == 0) {
                status[nn][nm] = line;
                return 1;
            } else if (status[nn][nm] == line) {
                return 0;
            }
        }
        return -1;
    }


}
