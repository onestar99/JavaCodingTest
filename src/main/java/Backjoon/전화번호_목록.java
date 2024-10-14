package Backjoon;

import java.util.Arrays;
import java.util.Scanner;

public class 전화번호_목록 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();

        for (int i = 0; i < t; i++) {

            int n = scan.nextInt();
            // 입력 값 넣기
            String[] strs = new String[n];
            for (int j = 0; j < n; j++) {
                strs[j] = Long.toString(scan.nextLong());
            }
            // 사전순으로 정렬
            Arrays.sort(strs);

            // 일관성 유무
            boolean isCheck = true;
            // 두 번호 비교
            for (int j = 0; j < n - 1; j++) {
                // 사전순으로 정렬된 다음 문자에서 앞 문자로 시작이 된다면 일관성 x
                if (strs[j + 1].startsWith(strs[j])) {
                    isCheck = false;
                    break;
                }
            }
            System.out.println(isCheck ? "YES" : "NO");
        }
    }
}
