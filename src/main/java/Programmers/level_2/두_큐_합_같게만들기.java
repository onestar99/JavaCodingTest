package Programmers.level_2;

import java.util.*;

public class 두_큐_합_같게만들기 {
    public int solution(int[] queue1, int[] queue2) {

        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();

        long sum1 = 0, sum2 = 0;
        for (int num : queue1) {
            q1.add(num);
            sum1 += num;
        }
        for (int num : queue2) {
            q2.add(num);
            sum2 += num;
        }

        // 두 큐의 합이 같아야 하는 목표 값
        long totalSum = sum1 + sum2;
        if (totalSum % 2 != 0) {
            return -1; // 합이 홀수 -> 불가능
        }
        long target = totalSum / 2;

        int maxOperations = queue1.length * 3; // 최대 작업 횟수 제한
        int operations = 0;

        // 두 큐의 합이 같아질 때까지 연산 수행
        while (sum1 != target && operations <= maxOperations) {
            if (sum1 > target) {
                // q1 pop, q2 insert
                int value = q1.pollFirst();
                q2.addLast(value);
                sum1 -= value;
                sum2 += value;
            } else {
                // q2 pop, q1 insert
                int value = q2.pollFirst();
                q1.addLast(value);
                sum2 -= value;
                sum1 += value;
            }
            operations++;
        }

        return (sum1 == target) ? operations : -1;
    }

    public static void main(String[] args) {
        두_큐_합_같게만들기 solution = new 두_큐_합_같게만들기();

        // 테스트 케이스
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};
        System.out.println(solution.solution(queue1, queue2)); // 2

        int[] queue1_2 = {1, 2, 1, 2};
        int[] queue2_2 = {1, 10, 1, 2};
        System.out.println(solution.solution(queue1_2, queue2_2)); // 7

        int[] queue1_3 = {1, 1};
        int[] queue2_3 = {1, 5};
        System.out.println(solution.solution(queue1_3, queue2_3)); // -1
    }
}
