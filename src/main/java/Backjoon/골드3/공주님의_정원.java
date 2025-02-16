package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 공주님의_정원 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Flower> flowers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());
            flowers.add(new Flower(sm, sd, em, ed));
        }

        // 시작 날짜 기준 오름차순, 시작 날짜가 같으면 끝나는 날짜 기준 내림차순
        Collections.sort(flowers);

        int current = 301; // mmdd
        int target = 1130;
        int count = 0;
        int idx = 0;
        int maxEnd = 0;

        while (current <= target) {
            boolean found = false;
            // 현재 날짜 이전이나 같은 날짜에 피기 시작하는 꽃들 중에서 가장 늦게 지는 꽃 찾기
            while (idx < flowers.size() && flowers.get(idx).start <= current) {
                maxEnd = Math.max(maxEnd, flowers.get(idx).end);
                idx++;
                found = true;
            }

            // 현재 날짜를 커버할 수 있는 꽃을 찾지 못하면 조건 만족x
            if (!found) {
                System.out.println(0);
                return;
            }

            count++;
            current = maxEnd;
            if (maxEnd > target) {
                break;
            }
        }

        System.out.println(count);
    }

    // 시작 날짜와 끝 날짜를 mmdd 형식의 정수로 저장
    static class Flower implements Comparable<Flower> {
        int start, end;

        public Flower(int sm, int sd, int em, int ed) {
            this.start = sm * 100 + sd;
            this.end = em * 100 + ed;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start == o.start) {
                return o.end - this.end;
            }
            return this.start - o.start;
        }
    }
}
