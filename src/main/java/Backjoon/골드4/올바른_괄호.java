package Backjoon.골드4;

import java.util.Scanner;
import java.util.Stack;

public class 올바른_괄호 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine(); // ()(()
        int validCount = 0;

        // 괄호마다 하나씩 제거해가며 체크
        for (int i = 0; i < str.length(); i++) {
            if (isValidAfterRemovingOne(str, i)) {
                validCount++;
            }
        }
        System.out.println(validCount);
    }

    // 체크 후 비었으면 true
    private static boolean isValidAfterRemovingOne(String s, int removeIndex) {

        // 스택으로 괄호 체크
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (i == removeIndex) continue; // 해당 인덱스 괄호 건너뛰기
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
