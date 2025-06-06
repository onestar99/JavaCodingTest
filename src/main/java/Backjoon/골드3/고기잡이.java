package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 고기잡이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 격자 크기
        int l = Integer.parseInt(st.nextToken());   // 그물 둘레 길이
        int M = Integer.parseInt(st.nextToken());   // 물고기 수

        // 물고기 좌표 저장 (1-indexed)
        int[][] fish = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            fish[i][0] = Integer.parseInt(st.nextToken()); // 행 (r)
            fish[i][1] = Integer.parseInt(st.nextToken()); // 열 (c)
        }

        // 그물의 가로(w) + 세로(h) = l/2 이므로 s = l/2
        int s = l / 2;
        int answer = 0;

        // 가능한 모든 (h, w) 쌍을 순회 (h = 1 .. s-1, w = s - h)
        for (int h = 1; h < s; h++) {
            int w = s - h;

            // r0 후보를 중복 없이 모으기 위해 TreeSet 사용
            // (자동 정렬되지만, 순서 상관 없으므로 HashSet+ArrayList로 해도 무방)
            TreeSet<Integer> r0Candidates = new TreeSet<>();

            // 각 물고기마다 두 가지 형태로 r0 후보 생성
            for (int i = 0; i < M; i++) {
                int r = fish[i][0];

                // (1) 사각형 상단을 물고기 행(r)과 일치시킬 때: r0 = r
                //     이 경우엔 r + h <= N 이어야 한다.
                if (r + h <= N) {
                    r0Candidates.add(r);
                }

                // (2) 사각형 하단을 물고기 행(r)과 일치시킬 때: r0 = r - h
                //     이 경우엔 r - h >= 1 이어야 한다.
                int r0b = r - h;
                if (r0b >= 1) {
                    // r0b + h = r 이므로 r <= N 은 항상 성립
                    r0Candidates.add(r0b);
                }
            }

            // 각 r0 후보마다 그물 세로 범위 [r0, r0 + h] 내 물고기를 걸러 열 좌표만 추출 후,
            // 너비 w로 투 포인터를 돌려 가장 많이 포함되는 개수 계산
            for (int r0 : r0Candidates) {
                // 이 r0에서 세로 범위 안에 있는 물고기들의 열(c)만 모아두기
                ArrayList<Integer> colsInBand = new ArrayList<>();
                int r1 = r0;
                int r2 = r0 + h; // 포함 범위: [r1, r2]

                for (int i = 0; i < M; i++) {
                    int r = fish[i][0];
                    if (r >= r1 && r <= r2) {
                        colsInBand.add(fish[i][1]);
                    }
                }

                if (colsInBand.isEmpty()) {
                    continue;
                }

                // 열 좌표 정렬
                Collections.sort(colsInBand);

                // 투 포인터: 열 너비 w 내에서 최대 물고기 개수
                int k = colsInBand.size();
                int rptr = 0;
                for (int lptr = 0; lptr < k; lptr++) {
                    // lptr 고정, rptr이 가리키는 좌표가 colsInBand.get(rptr) <= colsInBand.get(lptr) + w 인 동안 rptr++
                    while (rptr < k && colsInBand.get(rptr) <= colsInBand.get(lptr) + w) {
                        rptr++;
                    }
                    // [lptr, rptr-1] 구간에 포함된 개수 = rptr - lptr
                    answer = Math.max(answer, rptr - lptr);
                }
            }
        }

        System.out.println(answer);
    }

}
