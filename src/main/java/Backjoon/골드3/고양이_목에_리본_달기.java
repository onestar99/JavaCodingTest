package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP 문제
 */

public class 고양이_목에_리본_달기 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] prev = new int[K];
        int[] curr = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < K; j++) {
            prev[j] = Integer.parseInt(st.nextToken());
        }

        // 나머지 고양이들 처리
        for (int i = 1; i < N; i++) {
            // prev 배열에서 최대값과 두 번째 최대값, 최대값의 인덱스 찾기
            int max1 = Integer.MIN_VALUE, idx1 = -1;
            int max2 = Integer.MIN_VALUE;
            for (int j = 0; j < K; j++) {
                int v = prev[j];
                if (v > max1) {
                    max2 = max1;
                    max1 = v;
                    idx1 = j;
                } else if (v > max2) {
                    max2 = v;
                }
            }

            // 다음 고양이 만족도 읽고 계산
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                int sat = Integer.parseInt(st.nextToken());
                // 이전 고양이에서 같은 리본을 선택하지 않아야 하므로
                if (j == idx1) {
                    curr[j] = sat + max2;
                } else {
                    curr[j] = sat + max1;
                }
            }

            // prev, curr배열 swap
            int[] tmp = prev;
            prev = curr;
            curr = tmp;
        }

        int result = 0;
        for (int j = 0; j < K; j++) {
            result = Math.max(result, prev[j]);
        }
        System.out.println(result);
    }
}
