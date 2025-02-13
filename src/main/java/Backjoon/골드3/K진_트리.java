package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class K진_트리 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Long N = Long.parseLong(st.nextToken());// 필요 없을듯
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            System.out.println(findDistance(x, y, K));
        }
    }

    // K가 1인 경우는 특별 취급 (체인 형태이므로, 깊이 = x - 1)
    private static long getDepth(long x, int K) {
        if (K == 1) return x - 1; // 체인이라서
        long depth = 0;
        while (x != 1) {
            x = (x - 2) / K + 1;  // 부모로 이동
            depth++;
        }
        return depth;
    }


    private static long findDistance(long x, long y, int K) {
        // K가 1인 경우 단순 체인 구조이므로 두 노드 번호 차이가 거리
        if (K == 1) {
            return Math.abs(x - y);
        }

        long depthX = getDepth(x, K);
        long depthY = getDepth(y, K);
        long distance = 0;

        // 깊이 맞추기: 깊은 노드를 부모로 올리기
        while (depthX > depthY) {
            x = (x - 2) / K + 1;
            depthX--;
            distance++;  // 한 번 올릴 때마다 거리 1 증가
        }
        while (depthY > depthX) {
            y = (y - 2) / K + 1;
            depthY--;
            distance++;
        }

        // LCA 찾기, 두 노드가 같아질 때까지 동시에 부모로 이동
        while (x != y) {
            x = (x - 2) / K + 1;
            y = (y - 2) / K + 1;
            distance += 2;  // 둘 다 한 번씩 올리므로 2씩 증가
        }

        return distance;
    }
}
