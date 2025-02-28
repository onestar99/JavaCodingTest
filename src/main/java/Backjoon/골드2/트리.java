package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;

public class 트리 {

    // 전위, 중위 순회 배열과 중위 순회의 인덱스를 빠르게 찾기 위한 맵
    static int[] pre, in;
    static Map<Integer, Integer> inIndex;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            pre = new int[n];
            in = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                pre[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                in[i] = Integer.parseInt(st.nextToken());
            }

            // 중위 순회의 각 원소의 인덱스를 저장
            inIndex = new HashMap<>();
            for (int i = 0; i < n; i++) {
                inIndex.put(in[i], i);
            }

            // 재귀함수를 이용해 후위 순회 구하기
            solve(0, n - 1, 0, n - 1, sb);
            sb.append("\n");
        }

        System.out.print(sb);
    }

    /**
     * 재귀적으로 후위 순회 결과를 구하는 함수
     *
     * @param preStart 전위 순회 배열에서 현재 서브트리의 시작 인덱스
     * @param preEnd   전위 순회 배열에서 현재 서브트리의 끝 인덱스
     * @param inStart  중위 순회 배열에서 현재 서브트리의 시작 인덱스
     * @param inEnd    중위 순회 배열에서 현재 서브트리의 끝 인덱스
     * @param sb       후위 순회 결과를 저장할 StringBuilder
     */
    static void solve(int preStart, int preEnd, int inStart, int inEnd, StringBuilder sb) {
        if (preStart > preEnd || inStart > inEnd) return;

        // 전위 순회 배열의 첫 원소가 현재 서브트리의 루트
        int root = pre[preStart];
        // 중위 순회에서의 루트 위치
        int rootIndex = inIndex.get(root);
        // 왼쪽 서브트리의 원소 개수
        int leftCount = rootIndex - inStart;

        // 왼쪽 서브트리 재귀 처리
        solve(preStart + 1, preStart + leftCount, inStart, rootIndex - 1, sb);
        // 오른쪽 서브트리 재귀 처리
        solve(preStart + leftCount + 1, preEnd, rootIndex + 1, inEnd, sb);

        // 후위 순회이므로 마지막에 루트를 출력
        sb.append(root).append(" ");
    }
}
