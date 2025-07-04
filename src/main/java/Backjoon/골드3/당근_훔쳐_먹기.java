package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 중간 중간 당근의 수치가 변경되기에 DP는 사용 못 할듯
 * 그리디로 풀었을 경우 첫 당근 수확량이 영양제 맞은 당근 보다 큰 경우는 없으니까 가능해짐.
 */

public class 당근_훔쳐_먹기 {

    private static int[] W; // 첫 당근 영양가
    private static int[] P; // 영양분 공급량
    private static Integer[] PIdx; // Pi 수치를 오름차순으로 정렬한 인덷ㄱ스
    private static int[] ground; // 현재 땅의 영양분 공급량
    private static int N; // N개의 당근
    private static int T; // T일
    private static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        W = new int[N + 1];
        P = new int[N + 1];
        ground = new int[N + 1];
        PIdx = new Integer[N + 1];
        result = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < P.length; i++) {
            PIdx[i] = i;
        }
        Arrays.sort(PIdx, Comparator.comparingInt((Integer i) -> P[i]));

        int eatCount = 0;
        for (int i = 1; i <= T; i++) {
            plantingCarrot();

            if (T - N < i) { // 최대한 남은 날짜가 땅의 당근 개수만큼 남아있을 때까지 영양분을 준 후 공급 영양분이 적은 것부터 먹기
                eatCount++;
                eatingCarrot(eatCount);
            }
        }
        System.out.println(result);

    }

    // 오리가 당근 농사 짓기
    private static void plantingCarrot() {
        for (int i = 1; i <= N; i++) {
            if (ground[i] == 0) { // 빈 땅이면 당근 심기
                ground[i] = W[i];
            } else { // 빈 땅이 아니면 당근 영양분 추가
                ground[i] += P[i];
            }
        }
    }

    // 토끼가 당근 먹기
    private static void eatingCarrot(int eatCount) {
        result += ground[PIdx[eatCount]]; // 낮은 Wi를 가진 그라운드의 당근 영양분 만큼 결과값 더하기
        ground[PIdx[eatCount]] = 0; // 당근 먹은 부분 0 초기화
    }

}
