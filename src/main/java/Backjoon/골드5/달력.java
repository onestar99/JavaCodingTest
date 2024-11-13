package Backjoon.골드5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 달력 {
    public static void main(String[] args) throws IOException {

        int[] calendar = new int[366];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] strings = br.readLine().split(" ");
            int start = Integer.parseInt(strings[0]);
            int end = Integer.parseInt(strings[1]);
            for (int j = start; j <= end; j++) {
                calendar[j]++;
            }
        }

        int totalResult = 0;
        boolean isStart = false;
        int count = 0; // 가로 길이 측정
        int maxNum = 0; // 세로 길이 측정
        for (int date : calendar) {

            // 시작 지점 찾기
            if (!isStart && date > 0) {
                isStart = true;
            }

            // 끝 지점 찾기
            if (isStart && date == 0) {
                isStart = false;
            }

            // 이어져 있으면 가로 길이 늘리고 세로 길이 찾아내기
            if (isStart) {
                count++;
                maxNum = Math.max(maxNum, date);
            }

            // 끝났으면 결과 추가하고 초기화
            if (!isStart) {
                totalResult += count * maxNum;
                count = 0;
                maxNum = 0;
            }
        }

        // 마지막 연속된 일정의 넓이 추가
        if (isStart) {
            totalResult += count * maxNum;
        }

        System.out.println(totalResult);
    }
}
