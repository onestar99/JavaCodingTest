package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. 네 개의 점 중 같은 위치의 점이 있다면 X
 * 2. 두 선분의 교차 여부
 * 2-1. 교차를 하더라도 교차 지점이 위 네 개의 점 중에 하나라면 X
 * 2-2. 해당하지 않는다면 O
 *
 * CCW 알고리즘 사용
 */

public class PIZZA_ALVOLOC {

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        Point[] points = new Point[4];
        for (int i = 0; i < 4; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new Point(x, y);
        }

        Point A = points[0], B = points[1], C = points[2], D = points[3];
        if (isIntersect(A, B, C, D) && !isIntersectionPointAmongPoints(A, B, C, D)) {
            System.out.println(1); // 교차
        } else {
            System.out.println(0);
        }
    }

    // CCW 계산 함수
    static int ccw(Point p1, Point p2, Point p3) {
        long value = (long) (p2.x - p1.x) * (p3.y - p1.y) - (long) (p2.y - p1.y) * (p3.x - p1.x);
        if (value > 0) return 1;  // 반시계 방향
        if (value < 0) return -1; // 시계 방향
        return 0;                 // 일직선
    }

    // 두 선분의 교차 여부 확인
    static boolean isIntersect(Point A, Point B, Point C, Point D) {
        // 선분 (a, b)를 기준으로 점 C가 어느 방향에 위치하는지 계산
        int ab_c = ccw(A, B, C);
        int ab_d = ccw(A, B, D);
        int cd_a = ccw(C, D, A);
        int cd_b = ccw(C, D, B);

        // CCW 조건을 이용한 교차 여부 판단
        // ab_c 와 ab_d의 곱이 음수라면 점 C와 점 D가 선분 (A, B)의 서로 반대쪽에 위치함을 의미
        // 둘다 확인하는건 두 선분이 동일한 직선 상에 있을 때 교차 여부를 확인할 수 없기 때문임.
        if (ab_c * ab_d < 0 && cd_a * cd_b < 0) return true;

        // 선분이 일직선 위에 있는 경우 (끝점 포함)
        return isOnSegment(A, B, C) || isOnSegment(A, B, D) ||
                isOnSegment(C, D, A) || isOnSegment(C, D, B);
    }

    // 점 P(확인하고자 하는 점)가 선분 (A, B) 위에 있는지 확인, x좌표와 y좌표 범위 안에 있는지 확인
    static boolean isOnSegment(Point A, Point B, Point P) {
        return Math.min(A.x, B.x) <= P.x &&
                P.x <= Math.max(A.x, B.x) &&
                Math.min(A.y, B.y) <= P.y &&
                P.y <= Math.max(A.y, B.y) &&
                // ccw 이용하여 P가 선분 위에 있는지 확인
                ccw(A, B, P) == 0;
    }

    // 교차 지점이 네 점 중 하나인지 확인
    static boolean isIntersectionPointAmongPoints(Point A, Point B, Point C, Point D) {
        // 두 선분의 교차 지점 계산
        long a1 = B.y - A.y;
        long b1 = A.x - B.x;
        long c1 = a1 * A.x + b1 * A.y;

        long a2 = D.y - C.y;
        long b2 = C.x - D.x;
        long c2 = a2 * C.x + b2 * C.y;

        long det = a1 * b2 - a2 * b1;
        if (det == 0) return false;

        long x = (c1 * b2 - c2 * b1) / det;
        long y = (a1 * c2 - a2 * c1) / det;
        // 교차 지점
        Point intersection = new Point((int) x, (int) y);

        for (Point p : new Point[]{A, B, C, D}) {
            if (intersection.x == p.x && intersection.y == p.y) {
                return true;
            }
        }
        return false;
    }

}
