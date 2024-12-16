package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BFS 사용, k번 교환 수행 후 최댓값 구하기
 * 모든 경우 탐색
 * k번 교환 -> BFS가 효율적
 *
 *
 * 초기화 한거, 안한거 비교하기
 *
 */

public class 교환 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int K = Integer.parseInt(st.nextToken());

        System.out.println(getMaxNumberAfterKSwaps(N, K));
    }

    // 스왑 최대값 계산
    private static int getMaxNumberAfterKSwaps(String N, int K) {
        Queue<State> queue = new LinkedList<>();
        Set<String>[] visited = new HashSet[K + 1]; // 교환 횟수별 방문 체크

        for (int i = 0; i <= K; i++) {
            visited[i] = new HashSet<>();
        }

        queue.add(new State(N, 0));
        visited[0].add(N);

        int max = -1;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // K번까지
            if (current.steps == K) {
                max = Math.max(max, Integer.parseInt(current.number));
                continue;
            }

            // 탐색
            for (int i = 0; i < current.number.length(); i++) {
                for (int j = i + 1; j < current.number.length(); j++) {
                    String swapped = swap(current.number, i, j);

                    // 해당 숫자의 첫글자가 0이 아니고 탐색된적 없으면 queue 추가
                    if (swapped.charAt(0) != '0' && !visited[current.steps + 1].contains(swapped)) {
                        queue.add(new State(swapped, current.steps + 1));
                        visited[current.steps + 1].add(swapped);
                    }
                }
            }
        }

        return max;
    }

    // 스왑
    private static String swap(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    // 현재 숫자, 교환 횟수
    static class State {
        String number;
        int steps;

        State(String number, int steps) {
            this.number = number;
            this.steps = steps;
        }
    }
}
