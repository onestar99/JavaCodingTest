package Backjoon.골드4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 전구와_스위치 {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] init = br.readLine().trim().toCharArray();
        char[] target = br.readLine().trim().toCharArray();

        // 첫 전구를 뒤집는 것, 안 뒤집는 것 시뮬해서 최소값으로 리턴
        int ans = Math.min(simulate(init, target, false), simulate(init, target, true));

        System.out.println(ans == INF ? -1 : ans);
    }

    private static int simulate(char[] init, char[] target, boolean firstPress) {
        int N = init.length;
        // 복사
        char[] cur = new char[N];
        System.arraycopy(init, 0, cur, 0, N);
        int cnt = 0;

        // 첫 스위치 처리
        if (firstPress) {
            press(cur, 0);
            cnt++;
        }

        // 왼쪽에서부터 맞추기
        for (int i = 1; i < N; i++) {
            // 이전 전구가 다르면, i+1번 스위치를 눌러서 맞춤
            if (cur[i - 1] != target[i - 1]) {
                press(cur, i);
                cnt++;
            }
        }

        // 마지막 전구 확인해서 다르면 불가능, 가능하면 리턴
        if (cur[N - 1] != target[N - 1]) {
            return INF;
        }
        return cnt;
    }

    // 양쪽 전구 변화시키기
    private static void press(char[] arr, int i) {
        toggle(arr, i);
        if (i - 1 >= 0) toggle(arr, i - 1);
        if (i + 1 < arr.length) toggle(arr, i + 1);
    }

    private static void toggle(char[] arr, int i) {
        arr[i] = (arr[i] == '0') ? '1' : '0';
    }
}
