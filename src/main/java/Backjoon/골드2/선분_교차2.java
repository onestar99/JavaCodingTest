package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Edge Case 한 선분의 끝 점이 다른 선분이나 끝 점 위에 있는 것 Check
// x1, y1  x2, y2  (x2-x1)*(y2-y1) 하고 (x4-x3)*(y4-y3) 의 부호가 다르면 언젠가는 만남
// 16 -16
public class 선분_교차2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        long x4 = Long.parseLong(st.nextToken());
        long y4 = Long.parseLong(st.nextToken());

        int ccw1 = ccw(x1, y1, x2, y2, x3, y3);
        int ccw2 = ccw(x1, y1, x2, y2, x4, y4);
        int ccw3 = ccw(x3, y3, x4, y4, x1, y1);
        int ccw4 = ccw(x3, y3, x4, y4, x2, y2);

        // 일반적인 교차
        if (ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0) {
            System.out.println(1);
            return;
        }
        // 특수 케이스: 세 점이 일직선인 경우 (ccw 값이 0인 경우)
        if (ccw1 == 0 && isOnSegment(x1, y1, x2, y2, x3, y3)) {
            System.out.println(1);
            return;
        }
        if (ccw2 == 0 && isOnSegment(x1, y1, x2, y2, x4, y4)) {
            System.out.println(1);
            return;
        }
        if (ccw3 == 0 && isOnSegment(x3, y3, x4, y4, x1, y1)) {
            System.out.println(1);
            return;
        }
        if (ccw4 == 0 && isOnSegment(x3, y3, x4, y4, x2, y2)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    public static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        long result = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (result > 0) return 1; // 반시계 방향
        if (result < 0) return -1; // 시계 방향
        return 0; // 일직선
    }

    // 점 (px, py)가 선분 (x1, y1)-(x2, y2) 위에 존재하는지 판별하는 함수
    public static boolean isOnSegment(long x1, long y1, long x2, long y2, long px, long py) {
        return Math.min(x1, x2) <= px && px <= Math.max(x1, x2) &&
                Math.min(y1, y2) <= py && py <= Math.max(y1, y2);
    }
}
