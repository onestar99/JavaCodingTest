package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 조합해서 눈사람 만들고 비교
 */

public class 같이_눈사람_만들래 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] H = new int[N];
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }

        List<Snowman> snowmen = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                snowmen.add(new Snowman(H[i] + H[j], i, j));
            }
        }
        Collections.sort(snowmen);

        int minDiff = Integer.MAX_VALUE;

        // 인접한 눈사람 키 차이 비교 (겹치지 않는 경우만)
        for (int i = 0; i < snowmen.size(); i++) {
            for (int j = i + 1; j < snowmen.size(); j++) {
                Snowman a = snowmen.get(i);
                Snowman b = snowmen.get(j);

                if (a.i != b.i && a.i != b.j && a.j != b.i && a.j != b.j) {
                    int diff = Math.abs(a.height - b.height);
                    minDiff = Math.min(minDiff, diff);
                    break; // 더 이상은 height가 커지므로 다음 i로 넘어가도 됨
                }

            }
        }
        System.out.println(minDiff);
    }

    static class Snowman implements Comparable<Snowman> {
        int height;
        int i, j;

        Snowman(int height, int i, int j) {
            this.height = height;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Snowman other) {
            return this.height - other.height;
        }
    }
}
