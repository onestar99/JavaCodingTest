package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 크게_만들기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String number = br.readLine();

        Deque<Character> stack = new ArrayDeque<>();
        int removeCount = K;

        for (char digit : number.toCharArray()) {
            // 스택이 비어있지 않고, 제거할 숫자가 남아있으며, 현재 숫자가 스택의 마지막 숫자보다 크다면 제거
            while (!stack.isEmpty() && removeCount > 0 && stack.peekLast() < digit) {
                stack.pollLast(); // 마지막 숫자 제거
                removeCount--; // 제거 카운트 감소
            }
            stack.addLast(digit); // 현재 숫자 추가
        }

        // 초과된 숫자 제거
        while (stack.size() > N - K) {
            stack.pollLast();
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        System.out.println(result.toString());
    }
}
