package Backjoon;

import java.util.Scanner;
import java.util.*;

public class 단어_수학 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.nextLine();
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = scan.nextLine();
        }

        // 각 문자에 자릿수 가중치 넣기
        Map<Character, Integer> charWeights = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int weight = (int) Math.pow(10, word.length() - i - 1);
                // c값이 없을 경우 weight 넣기, 있으면 그 값에 weight 더하기
                charWeights.put(c, charWeights.getOrDefault(c, 0) + weight);
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(charWeights.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue()); // 가중치가 큰 순서대로 정렬

        // 새로운 hashMap 만들어서 순서대로 key 받은 다음에 9부터 할당.
        Map<Character, Integer> hashMap = new HashMap<>();
        int num = 9;
        for (Map.Entry<Character, Integer> entry : list) {
            hashMap.put(entry.getKey(), num--);
        }

        // 모든 word를 받은 다음에 charAt(i)인 key를 value로 변환하여 숫자를 만든 후 answer에 계속 더해줌
        int answer = 0;
        for (String word : words) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(hashMap.get(word.charAt(i)));
            }
            answer += Integer.parseInt(sb.toString());
        }

        System.out.println(answer);
    }
}
