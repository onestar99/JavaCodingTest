package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 그리디
 * 최소 힙 사용해서 지금까지 선택한 문제들 중 컵라면 개수가 가장 적은 문제를 뽑기
 *
 */

public class 컵라면 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Problem[] problems = new Problem[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            problems[i] = new Problem(deadline, ramen);
        }

        // 데드라인 오름차순 정렬
        Arrays.sort(problems, (p1, p2) -> p1.deadline - p2.deadline);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 각 문제를 순회하면서 해결
        for (Problem p : problems) {
            pq.offer(p.ramen);
            // 데드라인을 초과하면, 가장 컵라면이 적은 문제 하나를 제거
            if (pq.size() > p.deadline) {
                pq.poll();
            }
        }

        // 남아있는 컵라면 수들의 총합
        long total = 0;
        while (!pq.isEmpty()) {
            total += pq.poll();
        }

        System.out.println(total);
    }

    private static class Problem {
        int deadline, ramen;

        public Problem(int deadline, int ramen) {
            this.deadline = deadline;
            this.ramen = ramen;
        }
    }

}
