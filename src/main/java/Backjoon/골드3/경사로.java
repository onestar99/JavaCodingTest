package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경사로 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        // 가로 길 체크
        for (int i = 0; i < N; i++) {
            if (canPass(map[i], L)) {
                count++;
            }
        }

        // 세로 길 체크
        for (int j = 0; j < N; j++) {
            int[] col = new int[N];
            for (int i = 0; i < N; i++) {
                col[i] = map[i][j];
            }
            if (canPass(col, L)) {
                count++;
            }
        }

        System.out.println(count);
    }

    // 길을 지나갈 수 있는지 확인
    private static boolean canPass(int[] line, int L) {
        int N = line.length;
        boolean[] used = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            // 높이가 같으면 통과
            if (line[i] == line[i + 1]) {
                continue;
            }

            // 높이 차이가 1을 초과하면 불가능
            if (Math.abs(line[i] - line[i + 1]) > 1) {
                return false;
            }

            // 오르막길 경사로 설치
            if (line[i] < line[i + 1]) {
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || line[j] != line[i] || used[j]) {
                        return false;
                    }
                    used[j] = true; // 경사로 설치
                }
            } else { // 내리막길 경사로 설치
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || line[j] != line[i + 1] || used[j]) {
                        return false;
                    }
                    used[j] = true;
                }
            }
        }

        return true;
    }
}
