package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * ArrayList에 저장하기 vs 애초에 배열을 10만개로 잡기
 */

public class 싸지방에_간_준하 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<int[]> users = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            users.add(new int[]{start, end});
        }

        users.sort(Comparator.comparingInt(user -> user[0]));
        PriorityQueue<int[]> inUse = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // (종료 시각, 자리 번호) 종료 시각 기준으로 정렬
        PriorityQueue<Integer> available = new PriorityQueue<>(); // 빈 자리 번호

        int[] usageCount = new int[N + 1]; // 사용 횟수 기록
        int nextSeat = 1;

        for (int[] u : users) {
            int start = u[0];
            int end = u[1];

            // 종료된 pc 해제
            while (!inUse.isEmpty() && inUse.peek()[0] <= start) {
                int freeSeat = inUse.poll()[1]; // 지울 자리 번호
                available.add(freeSeat);
            }

            // 자리 배정
            int seatNo;
            if (!available.isEmpty()) {
                seatNo = available.poll();
            } else {
                seatNo = nextSeat++;
            }

            usageCount[seatNo]++;
            inUse.offer(new int[]{end, seatNo}); // 사용 중 pc에 추가
        }

        StringBuilder sb = new StringBuilder();
        sb.append(nextSeat - 1).append('\n');
        for (int i = 1; i < nextSeat; i++) {
            sb.append(usageCount[i]);
            if (i < nextSeat - 1) sb.append(' ');
        }
        System.out.println(sb);
    }
}
