package Backjoon.골드4;

import java.util.*;

public class LCS_3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String str1 = scan.next();
        String str2 = scan.next();
        String str3 = scan.next();

        int len1 = str1.length();
        int len2 = str2.length();
        int len3 = str3.length();

        int[][][] dp = new int[len1 + 1][len2 + 1][len3 + 1];

        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                for (int k = 1; k < len3 + 1; k++) {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1) && str2.charAt(j - 1) == str3.charAt(k - 1)) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                }
            }
        }
        System.out.println(dp[len1][len2][len3]);


        // 밑에서부터는 부분 수열을 출력하기 위한 방법
        List<Character> arr = new ArrayList<>();

        int current = dp[len1][len2][len3];
        while (current != 0) {

            // 각 방향으로 역 추적하여 DP 값이 같은 값이면 이동
            if (dp[len1 - 1][len2][len3] == current) {
                len1--;
            } else if (dp[len1][len2 - 1][len3] == current) {
                len2--;
            } else if (dp[len1][len2][len3 - 1] == current) {
                len3--;
            } else { // 같은 값이 없다면 대각선 이동 후 기존 위치에 있던 문자 추가
                arr.add(str1.charAt(len1 - 1));
                len1--;
                len2--;
                len3--;
            }
            current = dp[len1][len2][len3];
        }

        Collections.reverse(arr);
        StringBuilder sb = new StringBuilder();
        for (Character c : arr) {
            sb.append(c);
        }
        System.out.println(sb);

    }
}
