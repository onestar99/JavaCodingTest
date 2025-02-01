package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 괄호_추가하기 {

    static int N;
    static char[] expression;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        expression = br.readLine().toCharArray();

        dfs(0, 0);
        System.out.println(max);
    }

    private static void dfs(int index, int currentSum) {
        if (index >= N) {
            max = Math.max(max, currentSum);
            return;
        }

        char op = index == 0 ? '+' : expression[index - 1];
        int num = expression[index] - '0';

        // 괄호를 추가하지 않는 경우
        dfs(index + 2, calculate(currentSum, op, num));

        // 괄호를 추가하는 경우
        if (index + 2 < N) {
            int nextNum = expression[index + 2] - '0';
            char nextOp = expression[index + 1];
            int bracketSum = calculate(num, nextOp, nextNum);
            dfs(index + 4, calculate(currentSum, op, bracketSum));
        }
    }

    private static int calculate(int a, char op, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }
}
