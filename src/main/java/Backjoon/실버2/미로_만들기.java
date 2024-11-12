package Backjoon.실버2;

public class 미로_만들기 {

    public static void main(String[] args) {
        int a = -5 % 3;

        // 남쪽 바라보고 시계방향  인덱스 + 시키면 오른쪽 방향, - 시키면 왼쪽 방향
        int[] dy = new int[]{1, 0, -1, 0}; // 남 서 북 동
        int[] dx = new int[]{0, -1, 0, 1}; // 남 서 북 동

        int x = 0;
        int y = 0;
        int currentDirection = 0;

        // 좌표의 최소/최대값 추적
        int x_min = 0, x_max = 0, y_min = 0, y_max = 0;

        char r = 'R';
        char l = 'L';

        String s = "RLLFRRRLLLL";

        for (int i = 0; i < s.length(); i++) {
            if (r == s.charAt(i)) { // 오른쪽 방향
                currentDirection = (currentDirection + 1) % 4;
            } else if (l == s.charAt(i)) { // 왼쪽 방향
                currentDirection = (currentDirection - 1 + 4) % 4;
            } else { // 전진
                x += dx[currentDirection];
                y += dy[currentDirection];

                x_min = Math.min(x_min, x);
                x_max = Math.max(x_max, x);
                y_min = Math.min(y_min, y);
                y_max = Math.max(y_max, y);
            }
        }

        // 그래프 크기 계산
        int rows = x_max - x_min + 1;
        int cols = y_max - y_min + 1;

        // 그래프 초기화 (벽을 #로 초기화)
        char[][] maze = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = '#';
            }
        }

    }
}
