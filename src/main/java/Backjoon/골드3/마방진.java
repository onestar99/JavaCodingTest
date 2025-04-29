package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마방진 {

    /**
     * 0이 하나만 있는 가로, 세로, 대각선을 찾는다
     * 나머지 두 숫자의 합을 빼서 0인 자리에 채운다
     * 반복해서 모든 0을 채운다
     * 가장 먼저 완성된 줄의 합을 목표 sum으로 삼는다
     */

    static int[][] board = new int[3][3];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 마방진의 합 구하기
        int sum = findTargetSum();

        // 0이 있는 곳을 하나씩 채워나가기
        while (true) {
            boolean filled = false;

            // 가로줄 체크
            for (int i = 0; i < 3; i++) {
                int zeroCount = 0, zeroIndex = -1, rowSum = 0;
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        zeroCount++;
                        zeroIndex = j;
                    } else {
                        rowSum += board[i][j];
                    }
                }
                if (zeroCount == 1) {
                    board[i][zeroIndex] = sum - rowSum;
                    filled = true;
                }
            }

            // 세로줄 체크
            for (int j = 0; j < 3; j++) {
                int zeroCount = 0, zeroIndex = -1, colSum = 0;
                for (int i = 0; i < 3; i++) {
                    if (board[i][j] == 0) {
                        zeroCount++;
                        zeroIndex = i;
                    } else {
                        colSum += board[i][j];
                    }
                }
                if (zeroCount == 1) {
                    board[zeroIndex][j] = sum - colSum;
                    filled = true;
                }
            }

            // 대각선 체크 (좌상단 -> 우하단)
            int diag1ZeroCount = 0, diag1ZeroIndex = -1, diag1Sum = 0;
            for (int i = 0; i < 3; i++) {
                if (board[i][i] == 0) {
                    diag1ZeroCount++;
                    diag1ZeroIndex = i;
                } else {
                    diag1Sum += board[i][i];
                }
            }
            if (diag1ZeroCount == 1) {
                board[diag1ZeroIndex][diag1ZeroIndex] = sum - diag1Sum;
                filled = true;
            }

            // 대각선 체크 (우상단 -> 좌하단)
            int diag2ZeroCount = 0, diag2ZeroIndex = -1, diag2Sum = 0;
            for (int i = 0; i < 3; i++) {
                if (board[i][2 - i] == 0) {
                    diag2ZeroCount++;
                    diag2ZeroIndex = i;
                } else {
                    diag2Sum += board[i][2 - i];
                }
            }
            if (diag2ZeroCount == 1) {
                board[diag2ZeroIndex][2 - diag2ZeroIndex] = sum - diag2Sum;
                filled = true;
            }

            if (!filled) break;

        }

        // 출력
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + " " + board[i][1] + " " + board[i][2]);
        }

    }

    private static int findTargetSum() {
        // 0이 없는 줄 하나 찾아서 그 합을 마방진 목표 합으로 삼는다
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][1] != 0 && board[i][2] != 0) {
                return board[i][0] + board[i][1] + board[i][2];
            }
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != 0 && board[1][j] != 0 && board[2][j] != 0) {
                return board[0][j] + board[1][j] + board[2][j];
            }
        }

        return 1557;
    }
}
