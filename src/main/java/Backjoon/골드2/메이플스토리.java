package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 메이플스토리 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사냥터 수
        int T = Integer.parseInt(st.nextToken()); // 총 시간

        int[] c = new int[N]; // 입장에 필요한 최소 경험치
        int[] e = new int[N]; // 1분마다 얻는 경험치

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            c[i] = Integer.parseInt(st.nextToken());
            e[i] = Integer.parseInt(st.nextToken());
        }

        int[][] t = new int[N][N]; // 사냥터 사이의 이동 시간
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                t[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 배열 초기화
        long[][] dp = new long[T + 1][N];
        for (int time = 0; time <= T; time++) {
            for (int loc = 0; loc < N; loc++) {
                dp[time][loc] = -1;
            }
        }

        // 시작 가능한 사냥터 설정
        for (int i = 0; i < N; i++) {
            if (c[i] == 0) {
                dp[0][i] = 0;
            }
        }

        // 동적 계획법 진행
        for (int time = 0; time < T; time++) {
            for (int loc = 0; loc < N; loc++) {
                if (dp[time][loc] == -1) continue; // 해당 시간과 위치에서 도달 불가능하면 패스

                long currExp = dp[time][loc];

                // 1. 현재 사냥터에서 사냥 계속하기
                if (time + 1 <= T) {
                    dp[time + 1][loc] = Math.max(dp[time + 1][loc], currExp + e[loc]);
                }

                // 2. 다른 사냥터로 이동하기
                for (int nextLoc = 0; nextLoc < N; nextLoc++) {
                    if (loc == nextLoc) continue;

                    int arrivalTime = time + t[loc][nextLoc];
                    if (arrivalTime > T) continue;

                    // 이동하려는 사냥터의 입장 조건 확인
                    if (currExp >= c[nextLoc]) {
                        dp[arrivalTime][nextLoc] = Math.max(dp[arrivalTime][nextLoc], currExp);
                    }
                }
            }
        }

        // 최대 경험치 계산
        long maxExp = 0;
        for (int time = 0; time <= T; time++) {
            for (int loc = 0; loc < N; loc++) {
                if (dp[time][loc] > maxExp) {
                    maxExp = dp[time][loc];
                }
            }
        }

        // 결과 출력
        System.out.println(maxExp);

    }
}
