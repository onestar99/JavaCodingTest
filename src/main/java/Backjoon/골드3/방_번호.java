package Backjoon.골드3;

import java.io.*;
import java.util.*;

public class 방_번호 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] cost = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());

        // 전체 숫자 중 가장 저렴한 가격, 숫자
        int minCost = Integer.MAX_VALUE;
        int minDigit = -1;
        for (int i = 0; i < N; i++) {
            if (cost[i] < minCost) {
                minCost = cost[i];
                minDigit = i;
            }
        }

        // 첫 자리는 0으로 시작할 수 없으므로, 0이 아닌 숫자 중에서 가장 저렴한 거 찾기
        int minCostNonZero = Integer.MAX_VALUE;
        int minNonZeroDigit = -1;
        for (int i = 1; i < N; i++) {
            if (cost[i] < minCostNonZero) {
                minCostNonZero = cost[i];
                minNonZeroDigit = i;
            }
        }

        // 만약 0이 아닌 숫자를 살 수 없다면 방 번호는 0
        if (minNonZeroDigit == -1 || M < minCostNonZero) {
            System.out.println(0);
            return;
        }

        // 최대 자릿수를 구하기
        int length = 1 + (M - minCostNonZero) / minCost;

        // 초기 방 번호
        int[] answer = new int[length];
        answer[0] = minNonZeroDigit; // 첫 자리에는 0이 아닌 가장 저렴한 숫자
        for (int i = 1; i < length; i++) {
            answer[i] = minDigit; // 나머지는 가장 싼 숫자
        }


        int usedMoney = minCostNonZero + (length - 1) * minCost;
        int leftover = M - usedMoney; // 남은돈

        // 왼쪽부터 최대한 큰 숫자로 업글
        for (int i = 0; i < length; i++) {
            for (int d = N - 1; d >= 0; d--) {
                if (i == 0 && d == 0) continue;
                int diff = cost[d] - cost[answer[i]];
                // 남은 금액과 현재 자리에 사용된 금액을 합쳐서 후보 숫자의 가격이 가능하면 교체
                if (diff <= leftover) {
                    leftover -= diff;
                    answer[i] = d;
                    break; // 더 큰 숫자로 바꿀 수 있다면, 바로 다음 자리로
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int digit : answer) {
            sb.append(digit);
        }
        System.out.println(sb.toString());
    }
}
