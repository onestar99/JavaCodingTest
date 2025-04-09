package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 망가진_계산기 {

    static int D, P;
    static long maxResult = -1;
    static long limit;
    static HashSet<Long>[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        limit = (long) Math.pow(10, D);

        // 방문 여부 체크
        visited = new HashSet[P + 1];
        for (int i = 0; i <= P; i++) {
            visited[i] = new HashSet<>();
        }


        dfs(0, 1);
        System.out.println(maxResult);

    }

    // 연산 횟수, 현재 값
    private static void dfs(int opCount, long current) {

        // 연산횟수에 이미 현재값이 있으면 가지치기
        if (visited[opCount].contains(current)) {
            return;
        }
        visited[opCount].add(current);

        if (opCount == P) {
            maxResult = Math.max(maxResult, current);
            return;
        }

        // 2부터 9까지 곱해보면서 재귀 호출
        for(int i = 2; i <= 9; i++) {
            long next = current * i;
            if(next < limit) { // 자릿수 제한 내이면 재귀 호출 진행
                dfs(opCount + 1, next);
            }
        }

    }
}
