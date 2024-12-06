package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 소문난_칠공주 {

    static char[][] board = new char[5][5];
    static int result = 0;
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            board[i] = br.readLine().toCharArray();
        }

        // 모든 학생들의 위치 리스트 생성
        List<int[]> positions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                positions.add(new int[]{i, j});
            }
        }

        // 조합
        comb(positions, new ArrayList<>(), 0, 0);
        System.out.println(result);
    }

    // 조합으로 7명 선택
    static void comb(List<int[]> positions, List<int[]> selected, int index, int count) {
        if (count == 7) {
            if (isValid(selected)) { // 전체 7명이고 이다솜파가 4명이상, 7명이 인접.
                result++;
            }
            return;
        }

        // 조합 외워버리기
        for (int i = index; i < positions.size(); i++) {
            selected.add(positions.get(i));
            comb(positions, selected, i + 1, count + 1);
            selected.remove(selected.size() - 1);
        }
    }

    // 선택한 사람 조건 맞는지 확인
    static boolean isValid(List<int[]> selected) {
        int somCount = 0;
        boolean[][] visited = new boolean[5][5];

        // 이다솜 군단 수 카운트
        for (int[] pos : selected) {
            if (board[pos[0]][pos[1]] == 'S') {
                somCount++;
            }
        }

        // 이다솜 군단이 4명보다 작으면 망
        if (somCount < 4) return false;

        // BFS 써서 연결 확인
        Queue<int[]> queue = new LinkedList<>();

        // 첫번째 queue 초기화
        queue.add(selected.get(0));
        visited[selected.get(0)[0]][selected.get(0)[1]] = true;

        int connectedCount = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            connectedCount++;

            for (int[] dir : directions) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];

                for (int[] pos : selected) {
                    if (nx == pos[0] && ny == pos[1] && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        return connectedCount == 7;
    }
}
