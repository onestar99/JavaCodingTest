package Backjoon.골드5;

import java.util.Scanner;

public class Z {

    static int[][] direction;
    static int result = 0;

    public static void main(String[] args) {

        //한수는 크기가 2^N × 2^N인 2차원 배열을 Z모양으로 탐색하려고 한다. 예를 들어, 2×2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.

        // 2 3 1
        //  N, r, c

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int r = scanner.nextInt();
        int c = scanner.nextInt();

        int rowLength = (int) Math.pow(2, N);
        int colLength = rowLength;
        int[][] arr = new int[rowLength][colLength];


        direction = new int[][]{{0, 1}, {1, -1}, {0, 1}};
        dfs(arr, r, c, N);
    }

    // dfs 써가지고 4등분씩 해서 n이 1이 될때까지 자름
    public static void dfs(int[][] arr, int targetRow, int targetCol, int n) {

        if (n == 1) {
            
        }

        // arr 4등분해서 dfs 넣기


    }
}
