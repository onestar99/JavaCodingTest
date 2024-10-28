package Backjoon.골드4;

import java.util.Scanner;

public class 좁은_미술전시관 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[][] map = new int[N][2];
        boolean[][] closed = new boolean[N][2];
        scanner.nextLine();

        for (int i = 0; i < N; i++) {
            String str = scanner.nextLine();
            String[] strs = str.split(" ");
            map[i][0] = Integer.parseInt(strs[0]);
            map[i][1] = Integer.parseInt(strs[1]);
        }


        // 숫자 작은것들부터 총 K개 찾아서 줄여버리기 반복하다가 검증함수에서 가능하다고(true) 나오면
        // 그 값이 최대합





    }

    // map 검증 함수 이게 조건에서 가능한지?

    public static boolean validateMap(boolean[][] closed) {




        return false;
    }
}
