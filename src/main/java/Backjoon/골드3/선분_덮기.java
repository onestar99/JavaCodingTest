package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 선분_덮기 {
    static class Segment implements Comparable<Segment> {
        int l, r;
        Segment(int l, int r) {
            this.l = l;
            this.r = r;
        }
        @Override
        public int compareTo(Segment o) {
            if (this.l != o.l) {
                return Integer.compare(this.l, o.l);
            }
            return Integer.compare(o.r, this.r);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        List<Segment> segs = new ArrayList<>();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (l == 0 && r == 0) {
                break;
            }
            segs.add(new Segment(l, r));
        }

        Collections.sort(segs);

        int count = 0;
        int covered = 0;
        int idx = 0;
        int n = segs.size();

        while (covered < M) {
            int farthest = covered;
            while (idx < n && segs.get(idx).l <= covered) {
                farthest = Math.max(farthest, segs.get(idx).r);
                idx++;
            }
            if (farthest == covered) {
                System.out.println(0);
                return;
            }
            covered = farthest;
            count++;
        }

        System.out.println(count);
    }
}
