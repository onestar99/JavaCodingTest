package Backjoon.골드4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 뱀 {
    public static void main(String[] args) {

        /**
         *  TODO 몸통 저장 어떻게 함?
         *
         */

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        boolean[][] board = new boolean[N + 1][N + 1];

        int K = scanner.nextInt();

        // 사과 위치 저장
        for (int i = 0; i < K; i++) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            board[row][col] = true;
        }

        // 시간 방향 저장
        int L = scanner.nextInt();
        scanner.nextLine();
        List<Direction> directions = new ArrayList<>();
        for (int i = 0; i < L; i++) {
            String[] str = scanner.nextLine().split(" ");
            directions.add(new Direction(Integer.parseInt(str[0]), str[1].charAt(0)));
        }

        int cRow = 1;
        int cCol = 1;
        int cTime = 0;

        // 몸통 저장 어떻게 함?

        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 오른쪽, 아래, 왼쪽, 위쪽
        int directionIndex = 0; // 현재 방향 인덱스 (초기에는 오른쪽)



        // 이 구역 안에서 이제 뱀을 조종해야함.
        while (cRow >= 1 && cCol >= 1 && cRow <= N && cCol <= N) {
            cTime++;

            //현재 방향 이동
            cRow += direction[directionIndex][0];
            cCol += direction[directionIndex][1];

            if (cRow < 1 || cCol < 1 || cRow > N || cCol > N) {
                break;
            }

            // 방향 바꾸기
            if (!directions.isEmpty() && directions.get(0).time == cTime) {
                Direction nextDirection = directions.remove(0);
                if (nextDirection.direction == 'L') { // 왼쪽 회전
                    directionIndex = (directionIndex - 1 + 4) % 4;
                } else { // 오른쪽 회전
                    directionIndex = (directionIndex + 1) % 4;
                }
            }

        }

        System.out.println(cTime);


    }
}

class Direction {
    int time;
    char direction;

    public Direction(int time, char direction) {
        this.time = time;
        this.direction = direction;
    }
}
