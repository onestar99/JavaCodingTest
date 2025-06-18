package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class 사이좋은_형제 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        BigInteger a = new BigInteger(in[0]);
        BigInteger b = new BigInteger(in[1]);

        for (int n = 1; n <= 60; n++) {
            BigInteger twoPowN = BigInteger.ONE.shiftLeft(n);    // 2^n
            BigInteger D = twoPowN.subtract(BigInteger.ONE);     // 2^n - 1

            BigInteger x = a.multiply(D);
            if (!x.mod(b).equals(BigInteger.ZERO)) continue;     // 나누어떨어지지 않으면 패스
            x = x.divide(b);

            if (x.compareTo(BigInteger.ZERO) < 0 || x.compareTo(D) > 0) continue;

            // x를 n비트 이진수로 변환
            String bin = x.toString(2);
            if (bin.length() < n) {
                StringBuilder pad = new StringBuilder();
                for (int i = 0; i < n - bin.length(); i++) {
                    pad.append('0');
                }
                bin = pad.toString() + bin;
            }
            // 비트 → 패턴
            StringBuilder pattern = new StringBuilder();
            for (char c : bin.toCharArray()) {
                pattern.append(c == '1' ? '*' : '-');
            }
            System.out.println(pattern);
            return;
        }

        System.out.println(-1);
    }
}
