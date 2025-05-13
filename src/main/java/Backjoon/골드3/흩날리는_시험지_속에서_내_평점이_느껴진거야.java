package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 흩날리는_시험지_속에서_내_평점이_느껴진거야 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        st = new StringTokenizer(br.readLine());
        long total = 0;
        for (int i = 0; i < N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            total += a[i];
        }

        long low = 0, high = total, answer = 0;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (canMakeGroups(a, N, K, mid)) {
                answer = mid; // mid 이상으로 최소 그룹 합을 보장할 수 있으면 가능
                low = mid + 1; // 더 큰 값도 시도
            } else {
                high = mid - 1; // mid가 너무 크면 줄이기
            }
        }

        System.out.println(answer);
    }

    // 주어진 최소합 minSum 을 만족하며 만들 수 있는 그룹 개수가 K 이상인지 확인
    private static boolean canMakeGroups(int[] a, int N, int K, long minSum) {
        int count = 0;
        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += a[i];
            if (sum >= minSum) {
                count++;
                sum = 0;
                if (count >= K) return true;
            }
        }
        return false;
    }
}
