package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 고기잡이 {

    static int N;
    static int I;
    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 모눈종이의 크기
        I = Integer.parseInt(st.nextToken()); // 그물의 길이
        M = Integer.parseInt(st.nextToken()); // 물고기의 수
        int[][] fish = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            fish[i][0] = Integer.parseInt(st.nextToken());
            fish[i][1] = Integer.parseInt(st.nextToken());
        }

        List<int[]> shapes = getShapes(I);
        int maxCaught = 0;

        // 가능한 그물 모양마다 모든 시작 위치에서 탐색
        for (int[] net : shapes) {
            int width = net[0];
            int height = net[1];

            for (int x = 1; x <= N - width + 1; x++) {
                for (int y = 1; y <= N - height + 1; y++) {
                    int x1 = x, x2 = x + width - 1;
                    int y1 = y, y2 = y + height - 1;

                    int count = 0;
                    for (int[] f : fish) {
                        int fx = f[0], fy = f[1];
                        if (x1 <= fx && fx <= x2 && y1 <= fy && fy <= y2) {
                            count++;
                        }
                    }

                    maxCaught = Math.max(maxCaught, count);
                }
            }
        }

        System.out.println(maxCaught);
    }


    private static List<int[]> getShapes(int I) {
        List<int[]> shapes = new ArrayList<>();
        int sum = I / 2;

        for (int width = 1; width < sum; width++) {
            int height = sum - width;
            shapes.add(new int[]{width, height});
        }

        return shapes;
    }
}
