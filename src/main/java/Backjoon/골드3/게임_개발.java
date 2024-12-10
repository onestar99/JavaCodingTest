package Backjoon.골드3;

import java.io.*;
import java.util.*;

/**
 * Node 만들기
 * 위상 정렬로 풀기
 * 의존 인접리스트 만들기
 */

public class 게임_개발 {

    static class Node {
        int index, time;

        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 건물의 수
        List<List<Node>> building = new ArrayList<>(); // 건물 정보 리스트
        int[] inDegree = new int[n + 1]; // 진입 차수
        int[] buildTime = new int[n + 1]; // 각 건물의 건축 시간
        int[] result = new int[n + 1]; // 최종 최소 시간

        for (int i = 0; i <= n; i++) {
            building.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken()); // 건물을 짓는 데 걸리는 시간
            buildTime[i] = time;

            while (true) {
                int dependency = Integer.parseInt(st.nextToken()); // 의존 건물 번호
                if (dependency == -1) {
                    break;
                }
                building.get(dependency).add(new Node(i, time)); // 의존 관계 추가
                inDegree[i]++;
            }
        }

        // 위상 정렬
        Queue<Integer> queue = new LinkedList<>();

        // 진입 차수가 0인 건물 queue에 추가
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                result[i] = buildTime[i];
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Node next : building.get(current)) {
                int nextBuilding = next.index;

                // 현재 건물 시간 + 다음 건물 시간 => max 시간 갱신
                result[nextBuilding] = Math.max(result[nextBuilding], result[current] + buildTime[nextBuilding]);

                // 다음 건물 진입차수 줄이기
                inDegree[nextBuilding]--;
                if (inDegree[nextBuilding] == 0) {
                    queue.offer(nextBuilding);
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }
}
