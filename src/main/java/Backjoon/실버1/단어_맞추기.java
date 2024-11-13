package Backjoon.실버1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class 단어_맞추기 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();  // 테스트 케이스의 개수
        scanner.nextLine();  // 개행 문자 소모

        for (int t = 0; t < T; t++) {
            String input = scanner.nextLine().trim();
            String nextWord = findNextPermutation(input);
            System.out.println(nextWord != null ? nextWord : input.replace("\n", ""));
        }
        scanner.close();
    }

    private static String findNextPermutation(String input) {
        // 모든 순열 구하기
        Set<String> permutations = new HashSet<>();
        generatePermutations("", input, permutations);

        // 사전 순으로 정렬
        List<String> sortedList = new ArrayList<>(permutations);
        Collections.sort(sortedList);

        // 다음 단어 찾기
        int index = sortedList.indexOf(input);
        if (index == -1 || index == sortedList.size() - 1) {
            return null; // 다음 단어가 없을 때
        } else {
            return sortedList.get(index + 1);
        }
        
    }

    // 재귀적으로 순열 생성
    private static void generatePermutations(String prefix, String str, Set<String> result) {
        int n = str.length();
        if (n == 0) {
            result.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                generatePermutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), result);
            }
        }
    }
}
