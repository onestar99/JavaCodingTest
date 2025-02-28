package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 레이어 구하기
 * 레이어의 시작값 구하기
 * (r,c) 위치에 따른 증가치 계산 (0,1)시작 -> 반시계방향 u,l,d,r
 * 구간 출력 시 왼쪽 정렬 후 출력
 */

public class 소용돌이_예쁘게_출력하기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        int[][] map = new int[r2 - r1 + 1][c2 - c1 + 1];
        int maxNum = 0;

        // 소용돌이 값 채우기 및 최대값 찾기
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                map[i - r1][j - c1] = getValueAt(i, j);
                maxNum = Math.max(maxNum, map[i - r1][j - c1]);
            }
        }

        // 출력
        int maxLen = String.valueOf(maxNum).length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                sb.append(getSpaces(maxLen - String.valueOf(map[i][j]).length()));
                sb.append(map[i][j]);
                if (j < map[0].length - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static String getSpaces(int count) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    private static int getValueAt(int r, int c) {
        int layer = Math.max(Math.abs(r), Math.abs(c));
        // 중심(0,0)
        if (layer == 0) {
            return 1;
        }

        int startVal = (2 * layer - 1) * (2 * layer - 1) + 1;
        int side = 2 * layer;

        // 반시계 순서: (0, layer) → 위쪽(-layer) → 왼쪽(-layer) → 아래쪽(layer)
        // 각 변에 대해 offset을 계산해서 startVal에 더해준다.
        if (c == layer && r > -layer) {
            int offset = (layer - r - 1);
            return startVal + offset;
        }

        if (r == -layer && c >= -layer) {
            int offset = (side - 1) + (layer - c);
            return startVal + offset;
        }

        if (c == -layer && r <= layer) {
            int offset = (side - 1) + side + (r + layer);
            return startVal + offset;
        }

        int offset = (side - 1) + 2*side + (c + layer);
        return startVal + offset;

    }
}