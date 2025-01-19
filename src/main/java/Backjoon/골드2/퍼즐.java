package Backjoon.골드2;

import java.io.*;
import java.util.*;

/**
 * A* 사용해서 최대한 가지치기
 * 맨헤튼 거리 사용해서 휴리스틱으로 최적의 경로 유도
 *
 * 상태 여부 조건식 걸기
 * 시작 상태에서 현재 상태까지의 이동 횟수
 * f(n) = g(n) + h(n)
 */

public class 퍼즐 {

    private static final String TARGET = "123456780";
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder initial = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                initial.append(st.nextToken());
            }
        }

        String start = initial.toString();

        if (!isSolvable(start)) {
            System.out.println(-1);
            return;
        }

        int result = aStar(start);
        System.out.println(result);
    }

    // 퍼즐 풀수있는지 여부
    private static boolean isSolvable(String state) {
        int inversions = 0;
        for (int i = 0; i < 9; i++) {
            if (state.charAt(i) == '0') continue;
            for (int j = i + 1; j < 9; j++) {
                if (state.charAt(j) != '0' && state.charAt(i) > state.charAt(j)) {
                    inversions++;
                }
            }
        }
        return inversions % 2 == 0;
    }

    // A* 알고리즘
    private static int aStar(String start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        Map<String, Integer> visited = new HashMap<>();
        pq.offer(new Node(start, 0, heuristic(start)));
        visited.put(start, 0);

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.state.equals(TARGET)) {
                return current.moves;
            }

            int zeroIndex = current.state.indexOf('0'); // 빈칸
            int x = zeroIndex / 3;
            int y = zeroIndex % 3;

            for (int[] dir : DIRECTIONS) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                    int newZeroIndex = newX * 3 + newY;
                    String nextState = swap(current.state, zeroIndex, newZeroIndex);
                    int newMoves = current.moves + 1;

                    if (!visited.containsKey(nextState) || visited.get(nextState) > newMoves) {
                        visited.put(nextState, newMoves);
                        pq.offer(new Node(nextState, newMoves, newMoves + heuristic(nextState)));
                    }
                }
            }
        }

        return -1; // 목표 상태 도달x
    }

    // 맨해튼 거리 계산
    private static int heuristic(String state) {
        int distance = 0;
        for (int i = 0; i < 9; i++) {
            if (state.charAt(i) == '0') continue;
            int value = state.charAt(i) - '1';
            int targetX = value / 3;
            int targetY = value % 3;
            int currentX = i / 3;
            int currentY = i % 3;
            distance += Math.abs(targetX - currentX) + Math.abs(targetY - currentY);
        }
        return distance;
    }

    // 칸 위치 교환
    private static String swap(String state, int i, int j) {
        char[] chars = state.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    // 상태 클래스
    private static class Node {
        String state;
        int moves; // 시작 상태에서 현재 상태까지의 이동 횟수
        int cost;  // f(n) = g(n) + h(n)

        Node(String state, int moves, int cost) {
            this.state = state;
            this.moves = moves;
            this.cost = cost;
        }
    }
}
