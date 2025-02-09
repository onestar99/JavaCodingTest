package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벌레컷 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] A = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        // 누적합 배열 P: P[0] = 0, P[i] = A[0] + ... + A[i-1] (1 ≤ i ≤ N)
        long[] P = new long[N + 1];
        P[0] = 0;
        for (int i = 1; i <= N; i++) {
            P[i] = P[i - 1] + A[i - 1];
        }
        long S = P[N]; // 전체 합

        long ans = 0;
        for (int X = 1; X <= N - 2; X++) {
            long lowerCandidate = (S + P[X]) / 2 + 1;
            long upperCandidate = S - P[X] - 1;

            // Y는 X+1 이상 N-1 이하여야 함 → P의 인덱스 [X+1, N) (P[N]은 전체합 S, Y == N은 안됨)
            int L = lowerBound(P, X + 1, N, lowerCandidate);
            int R = upperBound(P, X + 1, N, upperCandidate) - 1;

            if (L <= R && L < N && R < N) {
                ans += (R - L + 1);
            }
        }
        System.out.println(ans);
    }

    // lowerBound: [lo, hi) 구간에서 target 이상인 첫 인덱스를 찾음
    public static int lowerBound(long[] arr, int lo, int hi, long target) {
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    // upperBound: [lo, hi) 구간에서 target 초과인 첫 인덱스를 찾음
    public static int upperBound(long[] arr, int lo, int hi, long target) {
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

}
