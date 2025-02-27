package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * B = 현재 통나무 위치
 * E = 목표 통나무 위치
 * 이동 횟수 최소화 -> BFS
 */

public class 통나무_옮기기 {

    static char[][] map;
    static int N;
    static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0 ; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        List<Point> startPositions = new ArrayList<>();
        List<Point> endPositions = new ArrayList<>();
        // 통나무 위치 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'B') {
                    startPositions.add(new Point(i, j));
                } else if (map[i][j] == 'E') {
                    endPositions.add(new Point(i,j));
                }
            }
        }

        // x 좌표로 정렬, 같으면 y 좌표로 정렬
        startPositions.sort((a, b) -> a.x == b.x ? a.y - b.y : a.x - b.x);
        endPositions.sort((a, b) -> a.x == b.x ? a.y - b.y : a.x - b.x);

        // 중앙 좌표
        Point startCenter = startPositions.get(1);
        Point endCenter = endPositions.get(1);

        // 방향 판별하기 (행 좌표가 같으면 가로 (0), 열 좌표가 같으면 세로 (1))
        int startOrient = (startPositions.get(0).x == startPositions.get(1).x ? 0 : 1);
        int endOrient = (endPositions.get(0).x == endPositions.get(1).x ? 0 : 1);

        // BFS
        // x, y, orient 방문 체크
        boolean[][][] visited = new boolean[N][N][2];
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(startCenter.x, startCenter.y, startOrient, 0));
        visited[startCenter.x][startCenter.y][startOrient] = true;

        while (!queue.isEmpty()) {
            State currentState = queue.poll();

            // 목표 상태인지 체크하기 ,중앙 위치 값이 같고, 방향이 동일한지
            if (currentState.x == endCenter.x && currentState.y == endCenter.y && currentState.orient == endOrient) {
                System.out.println(currentState.movesCount);
                return;
            }

            // 4방향 이동
            for (int[] dir : direction) {
                int nx = currentState.x + dir[0];
                int ny = currentState.y + dir[1];
                List<Point> newPositions = getSliceTreePositions(new Point(nx, ny), currentState.orient);
                if (isValid(newPositions) && !visited[nx][ny][currentState.orient]) {
                    visited[nx][ny][currentState.orient] = true;
                    queue.offer(new State(nx, ny, currentState.orient, currentState.movesCount + 1));
                }
            }

            // 회전
            if (canRotate(new Point(currentState.x, currentState.y))) {
                int newOrient = 1 - currentState.orient;
                if (!visited[currentState.x][currentState.y][newOrient]) {
                    visited[currentState.x][currentState.y][newOrient] = true;
                    queue.offer(new State(currentState.x, currentState.y, newOrient, currentState.movesCount + 1));
                }
            }
        }
        System.out.println(0);
    }

    // 통나무 중앙 위치를 기반하여 현재 통나무의 모든 위치를 반환
    private static List<Point> getSliceTreePositions(Point center, int orient) {
        List<Point> positions = new ArrayList<>();
        int x = center.x;
        int y = center.y;
        if (orient == 0) {
            positions.add(new Point(x, y - 1));
            positions.add(new Point(x, y));
            positions.add(new Point(x, y + 1));
        } else if (orient == 1) {
            positions.add(new Point(x - 1, y));
            positions.add(new Point(x, y));
            positions.add(new Point(x + 1, y));
        } else {
            System.out.println("not found Point orient error");
        }
        return positions;
    }

    // 통나무 이동 할 때 범위 내에 있고, 장애물이 있는지 없는지 체크
    private static boolean isValid(List<Point> positions) {
        for (Point p : positions) {
            if (p.x < 0 || p.x >= N || p.y < 0 || p.y >= N || map[p.x][p.y] == '1')
                return false;
        }
        return true;
    }

    // 통나무 회전 가능한지 3*3 이 비어있는지 체크하기
    private static boolean canRotate(Point center) {
        int x = center.x;
        int y = center.y;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || i >= N || j < 0 || j >= N || map[i][j] == '1') {
                    return false;
                }
            }
        }
        return true;
    }


    // 통나무 위치를 찾기 위한 지점 클래스
    private static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // BFS 상태, 통나무의 중앙 좌표, 방향(0, 1), 이동 횟수
    private static class State {
        int x, y, orient, movesCount;
        public State (int x, int y, int orient, int movesCount) {
            this.x = x;
            this.y = y;
            this.orient = orient;
            this.movesCount = movesCount;
        }
    }
}

