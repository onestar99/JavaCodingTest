package Backjoon.실버2;

import java.util.Arrays;
import java.util.Scanner;

public class 한_줄로_서기 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] arr = new int[n];
        Arrays.fill(arr, -1);

        for (int height = 1; height <= n; height++) {
            int biggerLeft = scanner.nextInt();

            int count = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] == -1) {
                    if (count == biggerLeft) {
                        arr[i] = height;
                        break;
                    }
                    count++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i + " ");
        }
        System.out.println(sb.toString().trim());
    }
}
