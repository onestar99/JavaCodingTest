package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 하늘에서_별똥별이_빗발친다 {

    static class Star {
        int x, y;
        Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 구역 가로
        int M = Integer.parseInt(st.nextToken()); // 구역 세로
        int L = Integer.parseInt(st.nextToken()); // 트램펄린 한 변
        int K = Integer.parseInt(st.nextToken()); // 별똥별 수

        Star[] stars = new Star[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars[i] = new Star(x, y);
        }

        int maxCover = 0;

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                int startX = stars[i].x;
                int startY = stars[j].y;
                int endX = startX + L;
                int endY = startY + L;

                int count = 0;

                for (int k = 0; k < K; k++) {
                    int sx = stars[k].x;
                    int sy = stars[k].y;

                    if (sx >= startX && sx <= endX && sy >= startY && sy <= endY) {
                        count++;
                    }
                }

                maxCover = Math.max(maxCover, count);
            }
        }

        System.out.println(K - maxCover);
    }
}
