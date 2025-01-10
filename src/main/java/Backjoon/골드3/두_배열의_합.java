package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 두_배열의_합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int[] A = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // A와 B의 부분 배열 합 생성
        List<Integer> A_sums = getSubarraySums(A);
        List<Integer> B_sums = getSubarraySums(B);

        // A_sums 정렬
        Collections.sort(A_sums);

        long result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int sumB : B_sums) {
            map.put(sumB, map.getOrDefault(sumB, 0) + 1);
        }

        for (int sumA : A_sums) {
            int target = T - sumA;
            result += map.getOrDefault(target, 0);
        }

        System.out.println(result);
    }

    // 부분 배열 합 계산
    private static List<Integer> getSubarraySums(int[] arr) {
        List<Integer> subarraySums = new ArrayList<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                subarraySums.add(sum);
            }
        }

        return subarraySums;
    }
}
