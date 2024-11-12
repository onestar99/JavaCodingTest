package Backjoon.골드5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 리모컨 {

    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws IOException {

        // 1. 검색 채널을 직접 숫자 버튼을 눌러서 해결.
        // 2. 검색 채널을 + 버튼이나 - 버튼을 통해 해결
        // 3. 숫자 버튼으로 이동 가능한 채널 중 가장 가까운 숫자로 이동 후 +, - 버튼을 눌러 해결
        // 이 중 최소 방법.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 고장난 숫자 체크
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int btn = Integer.parseInt(st.nextToken());
                broken[btn] = true;
            }
        }

        // +, - 만을 눌러서 숫자 체크
        int minClicks = Math.abs(N - 100);

        for (int channel = 0; channel <= 1000000; channel++) {
            if (isValid(channel)) {
                int len = getLength(channel);
                int clicks = len + Math.abs(N - channel);
                if (clicks < minClicks) {
                    minClicks = clicks;
                }
            }
        }

        System.out.println(minClicks);


    }

    public static boolean isValid(int channel) {
        String str = String.valueOf(channel);

        for (int i = 0; i < str.length(); i++) {
            int num = Integer.parseInt(String.valueOf(str.charAt(i)));
            if (broken[num]) {
                return false;
            }
        }
        return true;
    }

    public static int getLength(int channel) {
        String str = String.valueOf(channel);
        return str.length();
    }
}
