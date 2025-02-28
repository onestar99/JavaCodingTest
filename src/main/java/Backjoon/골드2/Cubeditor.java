package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * 접미사 배열로 풀어보기
 * https://loosie.tistory.com/798
 */

public class Cubeditor {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Integer[] sa = getSuffixArray(s);
        int[] lcp = getLCP(s, sa);  // LCP 구할 때는 int[]로 만들어도 상관 없음

        // LCP 배열 중 최댓값이 정답
        int answer = 0;
        for (int val : lcp) {
            answer = Math.max(answer, val);
        }

        System.out.println(answer);
    }

    /**
     * 접미사 배열 생성 (O(N (logN)^2) 정도 방식)
     */
    private static Integer[] getSuffixArray(String s) {
        int n = s.length();
        // 접미사 시작 인덱스를 담을 배열(기본 int[]가 아닌 Integer[] 사용!)
        Integer[] sa = new Integer[n];
        int[] rank = new int[n];
        int[] tmp = new int[n];

        // 초기 rank: 각 문자 코드를 그대로 사용
        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = s.charAt(i);
        } 

        // 간격 gap을 두 배씩 늘려가며 정렬 반복
        for (int gap = 1;; gap <<= 1) {
            // 람다 비교식
            final int g = gap;
            Arrays.sort(sa, (a, b) -> {
                // 우선순위1: 현재 rank[a] vs rank[b]
                if (rank[a] != rank[b]) {
                    return Integer.compare(rank[a], rank[b]);
                }
                // 우선순위2: rank[a+gap] vs rank[b+gap] (인덱스 벗어나면 -1)
                int ra = (a + g < n) ? rank[a + g] : -1;
                int rb = (b + g < n) ? rank[b + g] : -1;
                return Integer.compare(ra, rb);
            });

            tmp[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                tmp[sa[i]] = tmp[sa[i - 1]]
                        + (compare(rank, sa[i - 1], sa[i], g, n) ? 1 : 0);
            }

            // rank 배열 갱신
            for (int i = 0; i < n; i++) {
                rank[i] = tmp[i];
            }

            // 모든 접미사의 순위가 0..n-1로 모두 달라졌다면 종료
            if (rank[sa[n - 1]] == n - 1) break;
        }

        return sa;
    }

    /**
     * rank 배열을 기준으로 sa[i-1], sa[i]가 서로 다른 순위를 가져야 하면 true
     */
    private static boolean compare(int[] rank, int a, int b, int gap, int n) {
        if (rank[a] != rank[b]) return true;
        int ra = (a + gap < n) ? rank[a + gap] : -1;
        int rb = (b + gap < n) ? rank[b + gap] : -1;
        return ra != rb;
    }

    /**
     * Kasai’s algorithm: LCP(Longest Common Prefix) 배열 구하기 (O(N))
     */
    private static int[] getLCP(String s, Integer[] sa) {
        int n = s.length();
        int[] lcp = new int[n];
        int[] rank = new int[n];

        // sa의 i번째 접미사가 문자열 전체에서 몇 번째로 정렬되어 있는지 rank에 기록
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1];
                while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h)) {
                    h++;
                }
                lcp[rank[i]] = h;
                if (h > 0) {
                    h--;
                }
            }
        }

        return lcp;
    }
}
