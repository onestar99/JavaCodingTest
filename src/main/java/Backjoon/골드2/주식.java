package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 주식 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] arr = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            ArrayList<Integer> list = new ArrayList<>();

            for (int y : arr) {
                if (list.isEmpty() || list.get(list.size() - 1) < y) {
                    list.add(y);
                } else { // 이분 탐색으로 위치 찾아서 넣기
                    int left = 0;
                    int right = list.size() - 1;
                    while (left <= right) {
                        int mid = (left + right) / 2;
                        if (list.get(mid) < y) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
                    list.set(left, y);
                }
            }

            System.out.println("Case #" + (i + 1));
            int result = (K <= list.size()) ? 1 : 0;
            System.out.println(result);
        }
    }
}
