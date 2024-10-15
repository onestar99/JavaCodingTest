package Backjoon.골드4;

import java.util.Scanner;

public class 공룡게임 {


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        System.out.println(countValidMaps(N));


    }

    private static int countValidMaps(int N) {

        int[][] dp = new int[N + 1][3];

        dp[1][0] = 1;

        return 1;
    }
}
