package Programmers.level_1;

import java.util.Arrays;

public class 모의고사 {

    public static int[] solution(int[] answers) {

        int[][] spzs = {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        int[] spzsNum = {0, 0, 0};

        for (int i = 0; i < spzs.length; i++) {
            for (int j = 0; j < answers.length; j++) {
                if (spzs[i][j % spzs[i].length] == answers[j]) {
                    spzsNum[i]++;
                }
            }
        }

        int max = 0;
        int max_length = 0;
        for (int num: spzsNum) {
            if (num > max) {
                max = num;
                max_length = 1;
            } else if (num == max) {
                max_length++;
            }
        }
        int[] answer = new int[max_length];

        for (int i = 0, j = 0; i < spzsNum.length; i++) {
            if (max == spzsNum[i]) {
                answer[j] = i + 1;
                j++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] answers = {1, 2, 3, 4, 5};
//        int[] answers = {1,3,2,4,2};

        System.out.println(Arrays.toString(solution(answers)));

    }
}