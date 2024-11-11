package Backjoon.실버1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 단축키_지정 {

    // TODO 실패


    /**
     * 1. 문자열을 라인으로 받은 뒤 split 공백으로 쪼개어 문자열 배열을 만든다.
     * 각 문자를 Character 배열로 쪼갠다.
     * 2. 각 문자의 첫번째를 확인하여 HashSet에서 값이 존재하는지 확인해본다.
     * 3. HashSet에 값이 존재하지 않으면 값을 넣은 뒤 해당 문자를 결과값과 같이 출력한다.
     * 4. 만약 모든 문자열 배열의 첫번째 문자가 이미 존재하면 첫번째 단어의 두번째 문자부터 값이 존재하는지 체크한다.
     * 5. 값이 없으면 해당문자를 결과값과 같이 출력한다.
     * 6. 값이 모두 존재한다면 그냥 출력한다.
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Set<Character> set = new HashSet<>();


        for (int i = 0; i < n; i++) {
            String[] words = scanner.nextLine().split(" ");
            boolean shortcutAssigned = false; // 단축키가 할당되었는지
            StringBuilder sb = new StringBuilder();

            // 첫 글자가 단축키로 지정 가능한지 검사
            for (int j = 0; j < words.length; j++) {
                char firstChar = Character.toLowerCase(words[j].charAt(0));

                if (!set.contains(firstChar) && !shortcutAssigned) {
                    set.add(firstChar);
                    sb.append("[").append(words[j].charAt(0)).append("]");
                    sb.append(words[j].substring(1)).append(" ");
                    shortcutAssigned = true;
                } else {
                    sb.append(words[j]).append(" ");
                }
            }

            // 첫 글자로 할당되지 못한 경우 나머지 글자들 중에서 단축키로 지정
            if (!shortcutAssigned) {
                sb = new StringBuilder();
                for (String word : words) {
                    boolean innerShortcutAssigned = false;
                    for (int k = 0; k < word.length(); k++) {
                        char ch = Character.toLowerCase(word.charAt(k));
                        if (!set.contains(ch) && !innerShortcutAssigned) {
                            set.add(ch);
                            sb.append("[").append(word.charAt(k)).append("]");
                            sb.append(word.substring(k + 1)).append(" ");
                            innerShortcutAssigned = true;
                            shortcutAssigned = true;
                            break;
                        } else {
                            sb.append(word.charAt(k));
                        }
                    }
                    if (!innerShortcutAssigned && shortcutAssigned) {
                        sb.append(word).append(" ");
                    }
                    sb.append(" ");
                }
            }

            System.out.println(sb.toString().trim());

        }


    }
}
