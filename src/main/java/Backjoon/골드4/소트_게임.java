package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 소트_게임 {

    /**
     * 순열 문자열을 목표 문자열과 비교하여 BFS 를 통해서 최소 연산 횟수를 구한다.
     * 너무 느려서 최적화
     * hashSet을 사용해서 방문 체크 최적화 해보기
     */

    static class State {
        String permutation; // 순열 문자열
        int depth; // 현재까지의 연산 횟수

        public State(String permutation, int depth) {
            this.permutation = permutation;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        StringBuilder initial = new StringBuilder();
        for (int i = 0; i < N; i++) {
            initial.append(st.nextToken());
        }

        // 목표 상태 (오름차순 정렬된 상태)
        char[] targetArray = initial.toString().toCharArray();
        Arrays.sort(targetArray);
        String target = new String(targetArray);

        // 초기 상태가 이미 정렬되어 있으면 0 출력
        if (initial.toString().equals(target)) {
            System.out.println(0);
            return;
        }

        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new State(initial.toString(), 0));
        visited.add(initial.toString());

        // BFS 탐색
        while (!queue.isEmpty()) {
            State current = queue.poll();
            String currPermutation = current.permutation;
            int currDepth = current.depth;

            // 모든 시작 위치에서 K개의 숫자 뒤집
            for (int i = 0; i <= N - K; i++) {
                String nextPermutation = reverseSublist(currPermutation, i, i + K - 1);

                if (!visited.contains(nextPermutation)) {
                    if (nextPermutation.equals(target)) {
                        System.out.println(currDepth + 1);
                        return;
                    }
                    queue.add(new State(nextPermutation, currDepth + 1));
                    visited.add(nextPermutation);
                }
            }
        }

        // 오름차순으로 정렬x
        System.out.println(-1);
    }

    // 문자 범위 start ~ end 뒤집기
    private static String reverseSublist(String str, int start, int end) {
        char[] arr = str.toCharArray();
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return new String(arr);
    }
}
