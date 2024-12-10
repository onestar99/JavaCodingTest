package Backjoon.골드3;

import java.io.*;
import java.util.*;

public class IQ_Test {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] sequence = new int[N];

        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            System.out.println("A");
            return;
        }

        if (N == 2) {
            if (sequence[0] == sequence[1]) {
                System.out.println(sequence[0]);
            } else {
                System.out.println("A");
            }
            return;
        }

        // 시퀀스 3개 이상
        Integer a = null, b = null;
        boolean valid = true;

        for (int i = 0; i < N - 2; i++) {
            int x1 = sequence[i], x2 = sequence[i + 1], x3 = sequence[i + 2];
            if (x1 == x2) {
                valid = false;
                break;
            }
        }

        // 앞 수 * a + b
        int next = sequence[N - 1] * a + b;
        System.out.println(next);
    }
}
