package Backjoon.골드4;

import java.util.Scanner;
import java.util.*;

public class 작업 {

    // TODO, 제발 다시 공부하자. 진짜 뭔지 모르겠다.

    /**
     * 작업의 선행 관계와 최소 작업 시간을 구하는 문제.
     * 사용하기 좋은 방법 : 위상 정렬, DP
     * 각 작업에 도달하는 최소 시작 시간을 구하는 문제.
     * 그래프 구조를 통해 각 노드가 작업을 완료하기 위해 걸리는 시간을 계산하는 과정 공부.
     * @param args
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        List<List<Integer>> graph = new ArrayList<>(); // 그래프 [1 ~ n]까지
        int[] time = new int[n + 1]; // 소요되는 시간  [1 ~ n]까지
        int[] inDegree = new int[n + 1]; // 각 노드 번호의 연결된 간선 개수.
        scan.nextLine();

        // graph 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < graph.size(); i++) {
            String[] strs = scan.nextLine().split(" ");
            time[i] = Integer.parseInt(strs[0]);
            int numPrereq = Integer.parseInt(strs[1]);
            for (int j = 0; j < numPrereq; j++) {
                graph.get(Integer.parseInt(strs[j + 2])).add(i);
                inDegree[i]++;
            }
        }

//        for (int i = 1; i <= n; i++) {
//            System.out.println(graph.get(i).toString());
//        }



//        System.out.println(Arrays.toString(inDegree));


        // 우선순위 큐에 작업 완료 시간을 기준으로 우선순위로 해서 정렬
        PriorityQueue<Task> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.time));
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) { // 작업 시작가능한 애들을 먼저 집어 넣음
                pq.offer(new Task(i, time[i]));
            }
        }

        int[] resultTime = new int[n + 1]; // 결과 시간 누적 배열, 마지막꺼가 최종 합산 답

        while (!pq.isEmpty()) {
            // 가능한 작업을 우선순위 큐에서 빼냄

//            for (Task task : pq) {
//                System.out.println(task.toString());
//            }

            Task currentTask = pq.poll();
            int currentId = currentTask.id;
            int currentTime = currentTask.time;
//            System.out.println(currentId + " , " + currentTime);

            //
            resultTime[currentId] = currentTime;

            for (int nextId : graph.get(currentId)) {
                inDegree[nextId]--;
                if (inDegree[nextId] == 0) {
                    pq.offer(new Task(nextId, currentTime + time[nextId]));
                }
            }
        }

//        System.out.println(Arrays.toString(resultTime));


        int result = 0;
        for (int num : resultTime) {
            result = Math.max(result, num);
        }

        System.out.println(result);

    }


    static class Task {
        int id;
        int time;
        Task(int id, int time) {
            this.id = id;
            this.time = time;
        }
        public String toString() {
            return "{id : " + id + ", time : " + time + "}";
        }
    }
}
