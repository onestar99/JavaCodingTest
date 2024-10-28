package Backjoon.골드4;
import java.util.*;

public class 부분합 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int S = scanner.nextInt();

        // 배열 초기화
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            // 부분합이 S 이상이 되면 minLength를 갱신시키고 start 포인트 지점을 한칸 땡김.
            while (sum >= S) {
                minLength = Math.min(minLength, i - start + 1);
                sum -= arr[start];
                start++;
            }
        }
        if (minLength == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLength);
        }
    }
}


