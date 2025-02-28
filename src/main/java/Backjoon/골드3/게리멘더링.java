package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 입력 받기
 * 2. 부분 집합으로 구역 쪼개기 (연결성, 인구 차이 체크)
 * 3. 연결성 확인 (BFS)
 * 4. 인구 차이 계산 후 min 갱신
 */

public class 게리멘더링 {

    static int N;
    static int[] population;
    static List<Integer>[] adjList;
    static boolean[] selected; // 각 구역이 어떤 그룹에 속하는지
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 구역 개수 입력
        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        adjList = new ArrayList[N + 1];
        selected = new boolean[N + 1];

        // 인구 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            adjList[i] = new ArrayList<>();
        }

        // 인접 리스트 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                adjList[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 부분 집합 생성
        divide(1);

        // 결과 출력
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    // 부분 집합 생성
    private static void divide(int idx) {
        if (idx == N + 1) {
            // 그룹 A와 B로 나누기
            List<Integer> groupA = new ArrayList<>();
            List<Integer> groupB = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                if (selected[i]) groupA.add(i);
                else groupB.add(i);
            }

            // 한 그룹이 비어 있는 경우 제외
            if (groupA.isEmpty() || groupB.isEmpty()) return;

            // 연결성 확인
            if (isConnected(groupA) && isConnected(groupB)) {
                // 인구 차이 계산
                int diff = Math.abs(getPopulation(groupA) - getPopulation(groupB));
                result = Math.min(result, diff);
            }
            return;
        }

        // 그룹 A에 포함
        selected[idx] = true;
        divide(idx + 1);

        // 그룹 B에 포함
        selected[idx] = false;
        divide(idx + 1);
    }

    // 연결성 확인 BFS
    private static boolean isConnected(List<Integer> group) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        // 그룹의 첫 번째 구역을 시작점으로
        queue.add(group.get(0));
        visited[group.get(0)] = true;

        int count = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int neighbor : adjList[current]) {
                if (!visited[neighbor] && group.contains(neighbor)) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                    count++;
                }
            }
        }

        // 방문한 구역의 개수와 그룹의 크기가 같은지 확인
        return count == group.size();
    }

    // 그룹의 인구 합 계산
    private static int getPopulation(List<Integer> group) {
        int sum = 0;
        for (int g : group) {
            sum += population[g];
        }
        return sum;
    }
}
