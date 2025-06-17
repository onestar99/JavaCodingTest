package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Z36진수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] nums = new String[N];
        for (int i = 0; i < N; i++) {
            nums[i] = br.readLine();
        }
        int K = Integer.parseInt(br.readLine());

        BigInteger originalSum = BigInteger.ZERO;
        for (String s : nums) {
            originalSum = originalSum.add(new BigInteger(s, 36));
        }

        BigInteger[] gain = new BigInteger[36];
        Arrays.fill(gain, BigInteger.ZERO);



    }
}
