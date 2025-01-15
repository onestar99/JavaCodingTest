package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 인덴트를 줄이거나(D), 늘리거나(U) 하는 식으로 현재 값과 다음 값의 방향성을 투 포인터로 찾아 낸 뒤, 방향이 같으면 같은 라인에 있는
 * 값들 중 가장 큰 값을 찾아낸다. 만약 방향이 전환되거나 끝의 값에 도달했다면 남은 값을 더하는 식으로 처리한다.
 * 
 * 
 * 
 * 틀림
 */

public class 코딩은_예쁘게 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] currentTab = new int[N];
        for (int i = 0; i < N; i++) {
            currentTab[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] goodTab = new int[N];
        for (int i = 0; i < N; i++) {
            goodTab[i] = Integer.parseInt(st.nextToken());
        }

        int result = getMinTab(currentTab, goodTab, N);

        System.out.println(result);
    }

    private static int getMinTab(int[] currentTab, int[] goodTab, int N) {
        int result = 0; // 최소 편집 횟수
        int tempVal = 0; // 현재 그룹에서의 누적량
        boolean upOrDown = currentTab[0] <= goodTab[0]; // 첫 줄 방향성


        for (int i = 0; i < N; i++) {

            int diff = Math.abs(goodTab[i] - currentTab[i]);
            boolean tempUpOrDown = currentTab[i] <= goodTab[i];

            if (upOrDown == tempUpOrDown) {
                // 방향이 같으면 그룹 내 최대 누적량 갱신
                tempVal = Math.max(tempVal, diff);
//                tempVal += diff;

            } else {
                // 방향이 바뀌면 이전 그룹의 누적량을 결과 추가
                result += tempVal;
                tempVal = diff;
                upOrDown = tempUpOrDown;
            }

            // 마지막 숫자 처리
            if (i == N - 1) {
                result += tempVal; // 마지막 그룹의 작업량 추가
            }

        }
        return result;
    }
}
