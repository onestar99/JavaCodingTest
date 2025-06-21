package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 쌍 힙 구조를 가져서 중앙값을 바로 가져올 수 있도록 설계
 * 현재 값이 내림차순 pq 값보다 작을경우
 * 작은 값들을 내림차순 pq에 넣으며 정렬, 큰 값들을 오름차순 pq에 넣으며 정렬
 * 힙의 사이즈가 중간값을 유지하도록 사이즈가 벌어지지 않게 양쪽으로 조절
 */

public class 가운데를_말해요 {

    public static void main(String[] args) throws IOException {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());

            // 힙 넣기
            if (maxHeap.isEmpty() || n <= maxHeap.peek()) {
                maxHeap.offer(n);
            } else {
                minHeap.offer(n);
            }

            // 힙 정렬
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            sb.append(maxHeap.peek()).append("\n");
        }

        System.out.println(sb);

    }
}
