package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 */

public class 타일_위의_원 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine().trim());
        long r = N / 2;
        long r2 = r * r;
        long total = 0;

        // 사분면에서 u,v를 0부터 r-1까지 순회할 예정
        long vMax = r - 1;
        long vMin = r - 1;

        for (long u = 0; u < r; u++) {
            // 1) vMax 갱신: u^2 + vMax^2 < r^2 가 되도록 vMax를 내린다.
            while (vMax >= 0 && u * u + vMax * vMax >= r2) {
                vMax--;
            }

            // 2) vMin 갱신: (u+1)^2 + vMin^2 > r^2 이 되도록 vMin을 내린다.
            long u1 = u + 1;
            while (vMin > 0 && u1 * u1 + vMin * vMin > r2) {
                vMin--;
            }

            // 3) vMin <= v <= vMax 범위가 유효하다면, 그 개수를 더한다.
            if (vMax >= vMin) {
                total += (vMax - vMin + 1);
            }
        }

        System.out.println(total * 4);
    }
}
