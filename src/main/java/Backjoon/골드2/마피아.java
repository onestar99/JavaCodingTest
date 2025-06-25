package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *  DFS 백트레킹으로 모든 경우의 밤 시나리오 찾기
 *  밤: 은진이가 시민을 제거하고 그 시점에서 유죄지수를 갱신하고 백트레킹해서 복구함
 *  낮: 유죄지수가 가장 높은 사람을 제거
 *  은진이가 죽거나 마지막 한 명이 남을 때까지 반복하고 최대 밤의 수를 찾기
 */

public class 마피아 {

    static int N, eunJin;
    static int[] guilt;
    static int[][] R;
    static boolean[] alive;
    static int maxNight = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        guilt = new int[N]; // 유죄 지수
        R = new int[N][N]; // 참가자 간 반응 배열
        alive = new boolean[N];

        // 초기 유죄 지수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            guilt[i] = Integer.parseInt(st.nextToken());
            alive[i] = true;
        }
        // 반응 배열
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 은진이 번호
        eunJin = Integer.parseInt(br.readLine().trim());

        // 탐색 시작
        dfs(N, 0);
        System.out.println(maxNight);
    }

    static void dfs(int aliveCnt, int nightCnt) {
        // 종료시점: 은진이가 죽었거나, 시민 혹은 마피아 한쪽이 모두 사라진 경우
        if (!alive[eunJin] || aliveCnt == 1) {
            maxNight = Math.max(maxNight, nightCnt);
            return;
        }

        // 밤인지 낮인지 구분
        if (aliveCnt % 2 == 0) {
            // 밤: 은진이(마피아)가 시민 하나를 선택
            for (int i = 0; i < N; i++) {
                if (i == eunJin || !alive[i]) continue;
                // 상태 저장
                alive[i] = false;
                for (int j = 0; j < N; j++) {
                    if (alive[j]) {
                        guilt[j] += R[i][j];
                    }
                }
                // 재귀
                dfs(aliveCnt - 1, nightCnt + 1);
                // 복구 백트레킹
                for (int j = 0; j < N; j++) {
                    if (alive[j]) {
                        guilt[j] -= R[i][j];
                    }
                }
                alive[i] = true;
            }
        } else {
            // 낮: 유죄 지수가 가장 높은 사람 제거
            int target = -1;
            int maxG = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                if (alive[i] && guilt[i] > maxG) {
                    maxG = guilt[i];
                    target = i;
                }
            }
            // 제거
            alive[target] = false;
            dfs(aliveCnt - 1, nightCnt);
            alive[target] = true;
        }
    }
}
