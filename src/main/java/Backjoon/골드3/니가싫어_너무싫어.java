package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 니가싫어_너무싫어 {

    static class Event implements Comparable<Event> {
        int time, delta;
        Event(int time, int delta) {
            this.time = time;
            this.delta = delta;
        }
        // 시간 오름차순, 같은 시간엔 퇴장(-1) 먼저
        public int compareTo(Event o) {
            if (this.time != o.time) return Integer.compare(this.time, o.time);
            return Integer.compare(this.delta, o.delta);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Event[] events = new Event[N * 2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int TE = Integer.parseInt(st.nextToken());
            int TX = Integer.parseInt(st.nextToken());
            events[2*i]   = new Event(TE, +1);
            events[2*i+1] = new Event(TX, -1);
        }
        Arrays.sort(events);

        // 1) 스윕 라인: 시간별 순서대로 그룹 처리
        int M = events.length;
        // times, counts 저장용 (최대 2N)
        int[] times = new int[M];
        int[] counts = new int[M];
        int idx = 0;
        int cur = 0, maxCnt = 0;

        for (int i = 0; i < M; ) {
            int t = events[i].time;
            int sumDelta = 0;
            while (i < M && events[i].time == t) {
                sumDelta += events[i].delta;
                i++;
            }
            cur += sumDelta;
            times[idx] = t;
            counts[idx] = cur;
            if (cur > maxCnt) maxCnt = cur;
            idx++;
        }

        // 2) 최대 겹침 구간 찾기
        int start = 0, end = 0;
        boolean found = false;
        for (int k = 0; k < idx - 1; k++) {
            if (!found && counts[k] == maxCnt) {
                start = times[k];
                found = true;
            }
            if (found && counts[k] != maxCnt) {
                end = times[k];
                break;
            }
        }
        // 끝까지 떨어지지 않았다면 마지막 이벤트 시간으로
        if (found && end == 0) {
            end = times[idx - 1];
        }

        String sb = String.valueOf(maxCnt) + '\n' +
                start + ' ' + end;
        System.out.print(sb);
    }
}
