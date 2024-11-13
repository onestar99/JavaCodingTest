package Backjoon.골드4;

import java.util.*;

public class 뱀 {
    public static void main(String[] args) {
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

        // 몸통 저장
        Deque<Position> snake = new LinkedList<>();
        snake.add(new Position(1, 1)); // 초기 뱀 위치
        boolean[][] snakeBody = new boolean[N + 1][N + 1]; // 현재 뱀의 몸 위치
        snakeBody[1][1] = true;

        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 오른쪽, 아래, 왼쪽, 위쪽
        int directionIndex = 0; // 현재 방향 인덱스 (초기에는 오른쪽)

        int cRow = 1;
        int cCol = 1;
        int cTime = 0;

        while (true) {
            cTime++;

            // 현재 방향으로 이동
            cRow += direction[directionIndex][0];
            cCol += direction[directionIndex][1];

            // 벽에 부딪히거나 몸에 부딪힌 경우 게임 종료
            if (cRow < 1 || cCol < 1 || cRow > N || cCol > N || snakeBody[cRow][cCol]) {
                break;
            }

            // 새로운 머리 위치 추가
            snake.addFirst(new Position(cRow, cCol));
            snakeBody[cRow][cCol] = true;

            // 사과가 없다면 꼬리를 줄여서 위치 갱신
            if (!board[cRow][cCol]) {
                Position tail = snake.removeLast();
                snakeBody[tail.row][tail.col] = false;
            } else {
                // 사과가 있다면 사과를 먹고 보드에서 제거
                board[cRow][cCol] = false;
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

class Position {
    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
