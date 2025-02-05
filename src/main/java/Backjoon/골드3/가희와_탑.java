package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가희와_탑 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int maxBuilding = Math.max(A, B); // 가장 높은 건물 높이
        int[] answer = new int[N];
        int index = 0;

        // 1 ~ (a-1)까지 건물 세우기
        for (int i = 1; i < A && index < N; i++) {
            answer[index++] = i;
        }

        // 가장 높은 건물 세우기
        if (index < N) {
            answer[index++] = maxBuilding;
        }

        // (b-1) ~ 1까지 건물 세우기
        for (int i = B - 1; i > 0 && index < N; i--) {
            answer[index++] = i;
        }

        // 세운 건물 수가 n보다 크면 불가능
        if (index > N) {
            System.out.println("-1");
            return;
        }

        // 부족한 건물 개수만큼 1을 추가
        while (index < N) {
            answer[index++] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb.toString().trim());

    }

}
