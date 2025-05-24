package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파일_합치기3 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            for (int i = 0; i < K; i++) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            long totalCost = 0;

            // 파일 1개 남을때까지 반복
            while (pq.size() > 1) {
                long first = pq.poll();
                long second = pq.poll();

                long merged = first + second;
                totalCost += merged;
                pq.add(merged);
            }

            sb.append(totalCost).append('\n');
        }

        System.out.print(sb);
    }
}
