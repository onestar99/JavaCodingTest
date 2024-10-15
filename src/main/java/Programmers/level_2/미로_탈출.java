package Programmers.level_2;
import java.util.Queue;
import java.util.LinkedList;
public class 미로_탈출 {

    public int solution(String[] maps) {
        int answer = 0;

        // 미로를 탈출하는데 필요한 최소 시간 -> BFS 또는 다익스트라
        // BFS로 일단 풀어봄
        // BFS를 두번 사용하여 레버 지점까지의 최소거리 + 레버로부터 목표 지점까지의 최소거리

        // 필요 함수 : bfs(start, target, time)

        // mapArray 만들기.
        int rows = maps.length;
        int cols = maps[0].length();
        char[][] charArray = new char[rows][cols];
        int[] start = null;
        int[] lever = null;
        int[] exit = null;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                charArray[i][j] = maps[i].charAt(j);
                if (charArray[i][j] == 'S') {
                    start = new int[]{i, j};
                } else if (charArray[i][j] == 'L') {
                    lever = new int[]{i, j};
                } else if (charArray[i][j] == 'E') {
                    exit = new int[]{i, j};
                }
            }
        }

        // start -> lever 조사
        int timeToGoLever = bfs(start, lever, charArray);
        if (timeToGoLever == -1) return -1;
        // lever -> exit 조사
        int timeToGoExit = bfs(lever, exit, charArray);
        if (timeToGoExit == -1) return -1;

        // 값을 더한 뒤 리턴.
        return timeToGoLever + timeToGoExit;
    }

    private int bfs(int[] start, int[] target, char[][] charArray) {
        int rows = charArray.length;
        int cols = charArray[0].length;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 동서남북
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        queue.add(new int[]{start[0], start[1], 0}); // row, col, 시간
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            // queue에서 빼기
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int time = current[2];

            // 목표 달성시 걸린 시간 반환
            if (row == target[0] && col == target[1]) {
                return time;
            }

            // 각 방향 탐색
            for (int[] direction : directions) {
                int nRow = row + direction[0];
                int nCol = col + direction[1];

                // 범위 안에 존재하고, 방문하지 않았으며, 벽이 아니라면 queue 추가하고 방문 체크
                if (nRow >= 0 && nRow < rows && nCol >= 0 && nCol < cols && !visited[nRow][nCol] && charArray[nRow][nCol] != 'X') {
                    queue.add(new int[]{nRow, nCol, time + 1});
                    visited[nRow][nCol] = true;
                }
            }
        }
        return -1;
    }
}