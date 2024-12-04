package Backjoon.골드4;

import java.util.*;

public class 가르침 {
    static int N, K;
    static List<String> words;
    static boolean[] learned;
    static int maxReadable = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 단어 개수
        K = sc.nextInt(); // 가르칠 수 있는 글자 수

        // 기본 문자의 개수 5개보다 부족하면 X
        if (K < 5) {
            System.out.println(0);
            return;
        }

        // 단어 입력
        words = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String word = sc.next();
            words.add(word.substring(4, word.length() - 4));
        }

        // 알파벳 26개 중 학습 여부를 나타낼 배열
        learned = new boolean[26];
        // 필수 문자 학습
        learnBasicChars();

        // 남은 K-5개의 문자 조합 탐색
        backtrack(0, 0);

        // 결과 출력
        System.out.println(maxReadable);
    }

    // a, n t, i, c 학습
    private static void learnBasicChars() {
        learned['a' - 'a'] = true; // learned[0]
        learned['n' - 'a'] = true;
        learned['t' - 'a'] = true;
        learned['i' - 'a'] = true;
        learned['c' - 'a'] = true;
    }

    // 백트래킹을 통해 조합 탐색
    private static void backtrack(int start, int depth) {
        // 남은 글자 K-5개를 모두 선택했다면
        if (depth == K - 5) {
            // 읽을 수 있는 단어 개수 계산
            int count = countReadableWords();
            maxReadable = Math.max(maxReadable, count);
            return;
        }

        // 알파벳 조합 생성
        // i -> 탐색하는 알파벳
        for (int i = start; i < 26; i++) {
            if (!learned[i]) {
                learned[i] = true;
                backtrack(i + 1, depth + 1);
                learned[i] = false; // 선택 복원
            }
        }
    }

    // 현재 배운 글자로 읽을 수 있는 단어 개수 계산
    private static int countReadableWords() {
        int count = 0;

        for (String word : words) {
            boolean canRead = true;
            for (int i = 0; i < word.length(); i++) {
                if (!learned[word.charAt(i) - 'a']) {
                    canRead = false;
                    break;
                }
            }
            if (canRead) {
                count++;
            }
        }

        return count;
    }
}
