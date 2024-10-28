package Backjoon.골드5;

import java.util.Scanner;

public class Z {

    /**
     * 재귀를 통해서 그래프를 완성할 수 있는 문제.
     * 하지만 그렇게 되면 해당 답을 찾는 것에서 n의 값이 커지면 메모리가 커지면서
     * 배열을 생성할 수 없게된다.
     *
     * 그래프를 생성하는 것이 아닌 (r, c) 방향으로 이동하며 값만 찾아내는 문제.
     *
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int r = scanner.nextInt();
        int c = scanner.nextInt();

        int size = (int) Math.pow(2, n);
//        answerMap = new int[size][size];
//
//        slashHarf(size, 0, 0);
//
//        System.out.println(answerMap[r][c]);

//        for (int i = 0; i < answerMap.length; i++) {
//            String test = Arrays.toString(answerMap[i]);
//            System.out.println(test);
//        }


        int result = findOrder(size, r, c);
        System.out.println(result);



    }

//    // TODO, 4등분 하는 함수 만들기
//    public static void slashHarf(int size, int row, int col) {
//
//        if (size == 1) {
//            answerMap[row][col] = count++;
//            return;
//        }
//        int newSize = size / 2;
//        slashHarf(newSize, row, col); // 2사분면
//        slashHarf(newSize, row, col + newSize); // 1사분면
//        slashHarf(newSize, row + newSize, col); // 3사분면
//        slashHarf(newSize, row + newSize, col + newSize); // 4사분면
//    }


    // TODO
    public static int findOrder(int size, int row, int col) {
        if (size == 1) {
            return 0;
        }

        int newSize = size / 2;
        if (row < newSize && col < newSize) {
            return findOrder(newSize, row, col);
        } else if (row < newSize && col >= newSize) {
            return 1 * newSize * newSize + findOrder(newSize, row, col - newSize);
        } else if (row >= newSize && col < newSize){
            return 2 * newSize * newSize + findOrder(newSize, row - newSize, col);
        } else {
            return 3 * newSize * newSize + findOrder(newSize, row - newSize, col - newSize);
        }
    }
}
