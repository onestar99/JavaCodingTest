package Programmers.level_0;

import java.util.Arrays;

public class 배열에서_문자열_대소문자_변환하기 {

    public static String[] solution(String[] strArr) {

        for (int i = 0; i < strArr.length; i++) {
            if (i % 2 == 0) {
                // 소문자로
                strArr[i] = strArr[i].toLowerCase();
            } else {
                strArr[i] = strArr[i].toUpperCase();
            }
        }

        return strArr;
    }


    public static void main (String[] args) {

        String[] strArr = {"AAA","BBB","CCC","DDD"};

        System.out.println(Arrays.toString(solution(strArr)));

    }
}
