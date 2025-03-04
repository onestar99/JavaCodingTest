package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * LIS 최장 증가 부분수열
 * 순서에 맞게 연결할 수 있는 최대한의 체인 찾기.
 * 입력된 수열에서 순서를 유지하면서 점점 커지는 수들을 선택하면, 그 길이가 곧 최대 체인의 길이가 된다.0
 * 이 알고리즘에서 유지하는 리스트는 실제 최장 증가 부분수열 그 자체가 아니라,
 * 각 길이의 증가 부분수열에서 마지막 원소가 가질 수 있는 최소값을 관리하기 위한 것
 */

public class 반도체_설계 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        ArrayList<Integer> list = new ArrayList<>();

        // y 값은 현재 대응하는 값
        for (int y : arr) {
            if (list.isEmpty() || list.get(list.size() - 1) < y) {
                list.add(y);
            } else {
                int left = 0;
                int right = list.size() - 1;
                // 이분 탐색으로 새로 들어온 값 넣을 위치 찾기
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
        System.out.println(list.size());

    }
}
