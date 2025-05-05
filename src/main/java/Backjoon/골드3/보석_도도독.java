package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 보석_도도독 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 보석 정보 저장
        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }

        // 가방 정보 저장
        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels, Comparator.comparingInt(j -> j.m)); // 가벼운 보석순
        Arrays.sort(bags); // 작은 가방순

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long total = 0;
        int j = 0;

        for (int i = 0; i < K; i++) {
            while (j < N && jewels[j].m <= bags[i]) {
                pq.offer(jewels[j].v);
                j++;
            }

            if (!pq.isEmpty()) {
                total = total + pq.poll();
            }
        }

        System.out.println(total);


    }

    private static class Jewel {
        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
}