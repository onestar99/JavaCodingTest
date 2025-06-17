package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 입력된 파일명을 숫자 연속 부분은 NumberToken, 나머지 한 글자씩은 CharToken으로 분리
 * NumberToken은 BigInteger 값으로 비교하고, 값이 같으면 앞의 0 개수(zeroCount)로 순서 정하기.
 * CharToken은 알파벳 대문자 기준 사전순(A→B→…→Z) 후, 같은 글자면 대문자가 소문자보다 앞.
 * 두 문자열은 토큰 리스트를 앞에서부터 비교해 첫 차이가 나는 토큰 순으로, 동일하면 토큰 개수로 결정
 * Arrays.sort로 정렬한 뒤, WrappedString.original을 순서대로 출력
 */

public class 파일_탐색기 {

    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine().trim());
            WrappedString[] arr = new WrappedString[N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                arr[i] = new WrappedString(line);
            }
            Arrays.sort(arr);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(arr[i].original).append("\n");
            }
            System.out.print(sb.toString());

    }

    // 문자열 비교를 위한 토큰
    static abstract class Token implements Comparable<Token> {
    }

    // 숫자 토큰
    static class NumberToken extends Token {
        String raw;
        String trimmed;
        int zeroCount;
        BigInteger value;

        NumberToken(String s) {
            this.raw = s;
            // leading zero 제거
            int idx = 0;
            while (idx < s.length() && s.charAt(idx) == '0') {
                idx++;
            }
            if (idx == s.length()) {
                // 모두 '0'일 경우, 하나의 "0"으로 간주하고 zeroCount = 전체 길이 - 1
                this.trimmed = "0";
                this.zeroCount = s.length() - 1;
            } else {
                this.trimmed = s.substring(idx);
                this.zeroCount = idx;
            }
            this.value = new BigInteger(this.trimmed);
        }

        @Override
        public int compareTo(Token o) {
            if (!(o instanceof NumberToken)) {
                // 숫자 토큰은 알파벳 토큰보다 항상 앞
                return -1;
            }
            NumberToken other = (NumberToken) o;
            // 1) 숫자 값 비교
            int cmp = this.value.compareTo(other.value);
            if (cmp != 0) {
                return cmp;
            }
            // 2) 값이 같으면 leading zero 개수 비교
            if (this.zeroCount != other.zeroCount) {
                return Integer.compare(this.zeroCount, other.zeroCount);
            }
            // 3) 모두 같다면 동등
            return 0;
        }
    }

    // 문자(알파벳) 토큰 클래스
    static class CharToken extends Token {
        char ch; // 알파벳 한 글자

        CharToken(char c) {
            this.ch = c;
        }

        @Override
        public int compareTo(Token o) {
            if (o instanceof NumberToken) {
                // 문자는 숫자 토큰보다 항상 뒤
                return 1;
            }
            CharToken other = (CharToken) o;
            // 문제 요구: A < a < B < b < … < Z < z
            // 먼저 대문자 알파벳 순으로 비교
            char c1 = this.ch;
            char c2 = other.ch;
            char u1 = Character.toUpperCase(c1);
            char u2 = Character.toUpperCase(c2);

            if (u1 != u2) {
                // 대문자 기준으로 사전순 비교
                return u1 - u2;
            }
            // 같은 알파벳 기준, 대문자가 소문자보다 앞
            if (Character.isUpperCase(c1) && Character.isLowerCase(c2)) {
                return -1;
            }
            if (Character.isLowerCase(c1) && Character.isUpperCase(c2)) {
                return 1;
            }
            return 0; // 같은 문자
        }
    }

    // 원본 문자열과 그 토큰 리스트를 묶는 래퍼 클래스
    static class WrappedString implements Comparable<WrappedString> {
        String original;
        List<Token> tokens;

        WrappedString(String s) {
            this.original = s;
            this.tokens = tokenize(s);
        }

        @Override
        public int compareTo(WrappedString o) {
            List<Token> t1 = this.tokens;
            List<Token> t2 = o.tokens;
            int len1 = t1.size();
            int len2 = t2.size();
            int lim = Math.min(len1, len2);

            for (int i = 0; i < lim; i++) {
                Token tok1 = t1.get(i);
                Token tok2 = t2.get(i);
                int cmp = tok1.compareTo(tok2);
                if (cmp != 0) {
                    return cmp;
                }
            }
            // 앞쪽 토큰이 모두 같다면, 토큰 개수가 적은 쪽이 앞
            return Integer.compare(len1, len2);
        }
    }

    // 문자열을 Token 리스트로 분해하는 메서드
    static List<Token> tokenize(String s) {
        List<Token> tokens = new ArrayList<>();
        int i = 0, n = s.length();
        while (i < n) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int j = i;
                while (j < n && Character.isDigit(s.charAt(j))) {
                    j++;
                }
                String numStr = s.substring(i, j);
                tokens.add(new NumberToken(numStr));
                i = j;
            } else {
                // 알파벳은 한 글자씩 토큰화
                tokens.add(new CharToken(c));
                i++;
            }
        }
        return tokens;
    }
}
