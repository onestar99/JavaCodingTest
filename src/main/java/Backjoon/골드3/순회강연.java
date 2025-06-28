package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 순회강연 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        // 강연 요청을 [deadline, pay] 쌍으로 저장
        int[][] lectures = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lectures[i][0] = Integer.parseInt(st.nextToken()); // pay
            lectures[i][1] = Integer.parseInt(st.nextToken()); // deadline
        }

        // 마감일 오름차순 정렬
        Arrays.sort(lectures, (a, b) -> a[1] - b[1]);

        // 최소힙: 현재까지 선택한 강연들의 pay 중 최소를 빠르게 제거하기 위함
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 그리디 순회
        for (int i = 0; i < n; i++) {
            int pay = lectures[i][0];
            int deadline = lectures[i][1];

            minHeap.offer(pay);
            // 가능 일수(deadline) 초과 시, 가장 작은 pay 제거
            if (minHeap.size() > deadline) {
                minHeap.poll();
            }
        }

        // 남은 힙 요소들의 합이 최대 강연료
        long result = 0;
        while (!minHeap.isEmpty()) {
            result += minHeap.poll();
        }

        System.out.println(result);
    }
}
