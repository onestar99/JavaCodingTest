package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class skeep_문자열 {

    /**
     * 그리디? 인가?
     */

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int result = 0;
        char[] stack = new char[N];
        int sp = 0;

        for (int i = 0; i < N; i++) {
            stack[sp++] = str.charAt(i);

            while (sp >= 5 && stack[sp - 5] == 's'
                    && (stack[sp - 4] == '1' || stack[sp - 4] == 'k')
                    && (stack[sp - 3] == '1' || stack[sp - 3] == 'e')
                    && (stack[sp - 2] == '1' || stack[sp - 2] == 'e')
                    && (stack[sp - 1] == '1' || stack[sp - 1] == 'p')) {

                sp -= 5;
                stack[sp++] = '1';
                result++;
            }
        }
        System.out.println(result);


//        str = str.replace("skeep", "1");
//        for (int i = 0; i < str.length(); i++) {
//
//            if (str.charAt(i) == '1') {
//                result++;
//            }
//
//            if (str.charAt(i) == 's' && i + 4 < str.length()) {
//                if ((str.charAt(i + 1) == '1' || str.charAt(i + 1) == 'k')
//                    && (str.charAt(i + 2) == '1' || str.charAt(i + 2) == 'e')
//                    && (str.charAt(i + 3) == '1' || str.charAt(i + 3) == 'e')
//                    && (str.charAt(i + 4) == '1' || str.charAt(i + 4) == 'p')) {
//
//                    result++;
//                }
//            }
//        }
//        System.out.println(str);
//        System.out.println(result);
    }
}
