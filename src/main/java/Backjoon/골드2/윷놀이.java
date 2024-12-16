package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 윷 위치 노드로 정의하기
 * DFS를 통해서 max 찾아내기
 */

public class 윷놀이 {

    static class Node {
        int score;
        Node next; // 빨강
        Node alternate; // 분기점

        public Node(int score) {
            this.score = score;
        }
    }

    static Node start;
    static int maxScore = 0;
    static int[] dice; // 주사위 입력 10개
    static Node[] pieces; // 말

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dice = new int[10];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        initializeBoard();
        pieces = new Node[4];
        Arrays.fill(pieces, start);

        dfs(0, 0);

        System.out.println(maxScore);
    }

    private static void dfs(int turn, int currentScore) {
        if (turn == 10) {
            maxScore = Math.max(maxScore, currentScore);
            return;
        }

        for (int i = 0; i < 4; i++) {
            Node originalPosition = pieces[i];
            if (originalPosition == null) continue;

            Node newPosition = move(originalPosition, dice[turn]);

            // 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다
            // 도착하지 않았고, 이동한 칸에 다른 말이 이미 있는 경우
            if (newPosition != null && isOccupied(newPosition, i)) {
                continue;
            }

            pieces[i] = newPosition;
            dfs(turn + 1, currentScore + (newPosition != null ? newPosition.score : 0));
            pieces[i] = originalPosition;
        }
    }

    // 주사위만큼 이동
    private static Node move(Node position, int steps) {
        Node current = position;

        if (current == null) return null;

        // 경로 분기점
        if (current.alternate != null) {
            current = current.alternate;
            steps--;
        }

        while (steps > 0 && current != null) {
            current = current.next;
            steps--;
        }

        // 도착하면 null
        if (current != null && current.next == null) {
            return null;
        }

        return current;
    }

    // 이동 칸에 다른 말 있는지 체크
    private static boolean isOccupied(Node position, int currentIndex) {
        if (position == null) return false; // 도착 칸에서는 점유 체크 x
        for (int i = 0; i < 4; i++) {
            if (i != currentIndex && pieces[i] == position) {
                return true;
            }
        }
        return false;
    }

    private static void initializeBoard() {
        start = new Node(0);
        Node current = start;
        Node end = new Node(0); // 도착칸

        // 메인 경로 빨강: 0 ~40까지 노드 추가
        for (int i = 2; i <= 40; i += 2) {
            current.next = new Node(i);
            current = current.next;
        }
        current.next = end;

        // 공통 경로: 25 -> 30 -> 35 -> 40 -> end
        Node node25 = new Node(25);
        Node node30 = new Node(30);
        Node node35 = new Node(35);
        Node node40 = new Node(40);
        node25.next = node30;
        node30.next = node35;
        node35.next = node40;
        node40.next = end;

        // 파란 경로
        // 10 경로: 13 -> 16 -> 19 -> 25 -> ...
        Node blue10 = new Node(13);
        blue10.next = new Node(16);
        blue10.next.next = new Node(19);
        blue10.next.next.next = node25;

        // 20 경로: 22 -> 24 -> 25 -> ..
        Node blue20 = new Node(22);
        blue20.next = new Node(24);
        blue20.next.next = node25;

        // 30 경로: 28 -> 27 -> 26 -> 25 -> ..
        Node blue30 = new Node(28);
        blue30.next = new Node(27);
        blue30.next.next = new Node(26);
        blue30.next.next.next = node25;

        // 메인 경로에서 10, 20, 30 노드를 찾아 alternate 설정
        Node node10 = start;
        for (int i = 0; i < 5; i++) {
            node10 = node10.next; // 0->10 5번
        }

        Node node20 = start;
        for (int i = 0; i < 10; i++) {
            node20 = node20.next; // 0->20 10번
        }

        Node node30Main = start;
        for (int i = 0; i < 15; i++) {
            node30Main = node30Main.next; // 0->30 15번
        }

        node10.alternate = blue10;
        node20.alternate = blue20;
        node30Main.alternate = blue30;
    }

}
