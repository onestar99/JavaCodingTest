package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 스도쿠 {

    static int[][] pan = new int[9][9];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            String[] strs = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                pan[i][j] = Integer.parseInt(strs[j]);
            }
        }

        solveSudoku(0, 0);

        // 결과 출력
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(pan[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 스도쿠 (백트래킹)
    private static boolean solveSudoku(int row, int col) {
        if (col == 9) {
            row++;
            col = 0;
        }

        if (row == 9) {
            return true;
        }

        // 이미 채워진 칸이면 다음 칸으로 이동
        if (pan[row][col] != 0) {
            return solveSudoku(row, col + 1);
        }

        // 1~9 모두 시도
        for (int num = 1; num <= 9; num++) {

            // 해당 숫자를 넣을 수 있으면 숫자 넣기
            if (isValid(row, col, num)) {
                pan[row][col] = num;
                if (solveSudoku(row, col + 1)) {
                    return true;
                }
                pan[row][col] = 0;
            }
        }
        return false;
    }

    // 숫자 넣을 수 있는지 확인
    private static boolean isValid(int row, int col, int num) {
        // 행 검사
        for (int i = 0; i < 9; i++) {
            if (pan[row][i] == num) {
                return false;
            }
        }

        // 열 검사
        for (int i = 0; i < 9; i++) {
            if (pan[i][col] == num) {
                return false;
            }
        }

        // 3x3 박스 검사
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (pan[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

}
