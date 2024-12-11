package Backjoon.골드3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 세 용액을 고르는 순열을 만들어서 특성값이 0에 가까운 용액들을 result에 담아두고 모든 순열을 찾는다.
 * List를 sort하여 출력한다.
 * 단 특성값이 0이 나오는 순열이 생기면 그 즉시 List를 sort하여 출력한다.
 */

/**
 * 시간 초과나서
 * 투 포인터 알고리즘 사용
 * 첫 번째 고정 값을 두고 그 숫자의 앞의 숫자를 또 고정, 뒤에서 부터 하나씩 합쳐서 값이 0에 가까운 것을 찾는다.
 */

/**
 * 39%에서 틀려서
 * sum의 int 범위를 벗어나서 Long으로 변경.
 */

public class 세_용액 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long sumMin = Long.MAX_VALUE;
        int[] result = new int[3];


//        // 세 가지 용액 선택
//        for (int i = 0; i < N - 2; i++) {
//            for (int j = i + 1; j < N - 1; j++) {
//                for (int k = j + 1; k < N; k++) {
//                    int sum = arr[i] + arr[j] + arr[k];
//
//                    // 0에 더 가까운 값을 갱신
//                    if (Math.abs(sum) < Math.abs(minSum)) {
//                        minSum = sum;
//                        result[0] = arr[i];
//                        result[1] = arr[j];
//                        result[2] = arr[k];
//                    }
//                }
//            }
//        }

        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = (long) arr[i] + (long) arr[left] + (long) arr[right];

                // 합이 0에 가까우면 갱신
                if (Math.abs(sum) < Math.abs(sumMin)) {
                    sumMin = sum;
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];
                }

                if (sum < 0) { // 합친 값이 0보다 작으면 left를 증가시켜서 값의 상승을 높이기
                    left++;
                } else if (sum > 0) { // 합친 값이 0보다 크면 right를 감소시켜서 값의 하락을 높이기
                    right--;
                } else { // 값이 0이면 return
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                    bw.write(arr[i] + " " + arr[left] + " " + arr[right]);
                    bw.flush();
                    return;
                }
            }
        }

        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(result[0] + " " + result[1] + " " + result[2]);
        bw.flush();
        bw.close();

    }
}
