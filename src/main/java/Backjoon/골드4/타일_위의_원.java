package Backjoon.골드4;

import java.util.Scanner;

public class 타일_위의_원 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = N / 2;
        double center = r;

        int count = 0;
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < r; y++) {
                double minDist2 = dist2(x, y, center);
                minDist2 = Math.min(minDist2, dist2(x + 1, y, center));
                minDist2 = Math.min(minDist2, dist2(x, y + 1, center));
                minDist2 = Math.min(minDist2, dist2(x + 1, y + 1, center));

                double maxDist2 = dist2(x, y, center);
                maxDist2 = Math.max(maxDist2, dist2(x + 1, y, center));
                maxDist2 = Math.max(maxDist2, dist2(x, y + 1, center));
                maxDist2 = Math.max(maxDist2, dist2(x + 1, y + 1, center));

                double R2 = r * r;

                if (minDist2 < R2 && maxDist2 > R2) {
                    count++;
                }

            }
        }

        System.out.println(count * 4);
    }

    private static double dist2(double x, double y, double center) {
        double dx = x - center;
        double dy = y - center;
        return dx * dx + dy * dy;
    }

}
