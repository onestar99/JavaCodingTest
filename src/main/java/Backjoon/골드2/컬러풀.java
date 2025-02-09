package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;


/**
 *
 * 정렬 한 번만 하기
 *
 * 사실 하면서 느낀거지만 PQ가 필요 없을 것 같음. 그냥 정렬한번 해놓고 하면 될듯.
 * 그래서 그냥 바꿔버림
 */

public class 컬러풀 {

    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ArrayList<Ball> list = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            list.add(new Ball(i + 1, color, size));
        }

        ArrayList<Ball> sortedList = (ArrayList<Ball>) list.clone();
        // 사이즈로 오름차순 정렬
        sortedList.sort(Comparator.comparingInt((Ball b) -> b.size));


        for (int i = 0; i < N; i++) {

            Ball currentBall = list.get(i);
            int count = 0;
            for (Ball sortedBall : sortedList) {

                // 해당 볼은 제외
                if (currentBall.number == sortedBall.number) {
                    continue;
                }

                // 볼의 크기가 더 작고, 컬러가 다를 경우에만 값을 더하기
                if (currentBall.size > sortedBall.size && currentBall.color != sortedBall.color) {
                    count += sortedBall.size;
                }

                // 중간에 값이 같아지는 부분부터는 pass
                if (currentBall.size <= sortedBall.size) {
                    break;
                }

            }
            System.out.println(count);
        }
    }

    private static class Ball {

        int number;
        int color;
        int size;

        Ball(int number, int color, int size) {
            this.number = number;
            this.color = color;
            this.size = size;
        }

    }
}


