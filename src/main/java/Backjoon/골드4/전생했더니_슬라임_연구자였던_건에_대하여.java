package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 전생했더니_슬라임_연구자였던_건에_대하여 {

    static final long MOD = 1000000007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스만큼 입력 받기
        while (T-- > 0) {
            // 입력 부분
            // 우선순위 큐에다가 작은 숫자를 우선적으로 정렬하면서 넣도록 한다.
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            // 만약에 슬라임이 1개밖에 없으면 답은 1
            if (N == 1) {
                sb.append(1).append('\n');
                continue;
            }

            long answer = 1L;
            // 앞에서부터 작은거 두 개씩 꺼내면서 합성하면서 비용을 곱함
            while (pq.size() > 1) {
                long x = pq.poll();
                long y = pq.poll();
                long cost = (x * y) % MOD; // 현재 단계에서의 전기에너지 계산
                answer = (answer * cost) % MOD; // 결과값에 현재 전기 곱해주기
                pq.add(x * y); // 합성한 슬라임을 우선순위 큐에 집어 넣기
            }

            sb.append(answer).append('\n');
        }

        System.out.println(sb);
    }
}
