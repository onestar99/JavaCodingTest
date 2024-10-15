package Backjoon.브론즈1;

import java.util.Scanner;

public class 명령_프롬프트 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.nextLine();

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = scan.nextLine();
        }

        // 문자가 1개만 온다면 그 즉시 출력 후 종료
        if (N == 1) {
            System.out.println(words[0]);
            return;
        }

        StringBuilder sb = new StringBuilder();
        String firstWord = words[0];

        // 각 단어들을 문자의 길이만큼 쭈욱 돈다.
        // 문자를 탐색할 때 첫번째 단어의 문자와 비교를 하는데, 다른 문자가 발견되면 그 즉시 ?를 추가하고 다음 문자로 이동한다.
        for (int i = 0; i < firstWord.length(); i++) { // 단어의 길이만큼
            char c = firstWord.charAt(i);
            for (int j = 1; j < N; j++) { // 단어들끼리의 개수 만큼
                if (c != words[j].charAt(i)) {
                    sb.append("?");
                    break;
                }
                if (j == N-1) sb.append(c);
            }
        }

        System.out.println(sb);

    }
}
