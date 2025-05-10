package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나머지_합 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] count = new long[M]; // 나머지별 빈도 저장
        st = new StringTokenizer(br.readLine());

        long sum = 0;
        count[0] = 1; // 합이 바로 나누어 떨어지는 구간

        for (int i = 0; i < N; i++) {
            sum = (sum + Long.parseLong(st.nextToken())) % M;
            count[(int)sum]++;
        }

        long result = 0;
        // 같은 나머지끼리 조합 개수 계산
        for (int r = 0; r < M; r++) {
            if (count[r] > 1) {
                result += count[r] * (count[r] - 1) / 2;
            }
        }

        System.out.println(result);
    }
}
