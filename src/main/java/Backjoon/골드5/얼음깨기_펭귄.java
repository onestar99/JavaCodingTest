package Backjoon.골드5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 트리 문제
 * 특수 얼음 깨기 펭귄 게임에서 도도가 펭귄을 떨어뜨리지 않고 최대 몇 개의 얼음을 깰 수 있을까?
 *
 * 특수 얼음지지대 여부
 */

public class 얼음깨기_펭귄 {

    private static int N; // 얼음 블록의 개수
    private static int S; // 지지대 얼음 개수
    private static int P; // 펭귄 위치 인덱스
    private static List<Integer>[] tree;
    private static int[] distFromP;
    private static boolean[] isSupport;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        distFromP = new int[N + 1];
        isSupport = new boolean[N + 1];

        // 트리 인접리스트
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            tree[A].add(B);
            tree[B].add(A);
        }

        for (int i = 1; i <= S; i++) {
            isSupport[i] = true;
        }


        bfs(); // distFromP 다 채움


        int a, b;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= S; i++) {
            list.add(distFromP[i]);
        }
        Collections.sort(list);

        a = list.get(0);
        b = list.get(1);

        // 전체 - (최소 거리 2개 + P의 위치)
        int answer = N - (a + b + 1);
        System.out.println(answer);



    }

    private static void bfs() {

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(distFromP, -1);

        queue.add(P);
        visited[P] = true;
        distFromP[P] = 0;

        while(!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : tree[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    distFromP[next] = distFromP[current] + 1;
                    queue.add(next);
                }
            }
        }


    }

}
