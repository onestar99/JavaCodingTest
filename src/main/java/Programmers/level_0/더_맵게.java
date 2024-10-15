package Programmers.level_0;

import java.util.PriorityQueue;

public class 더_맵게 {

    public static int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int sco: scoville) {
            pq.add(sco);
        }

        while (pq.peek() < K) {
            answer += 1;
            if (pq.size() < 2) return -1;
            int mix = pq.poll() + pq.poll() * 2;
            pq.add(mix);
        }

        return answer;
    }


    public static void main (String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;
        int sol = solution(scoville, k);
        System.out.println("\n" + sol);

    }
}
