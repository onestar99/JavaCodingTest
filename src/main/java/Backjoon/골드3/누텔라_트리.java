package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 *
 */

public class 누텔라_트리 {

    static int N;
    static List<Integer>[] adj;
    static char[] color;
    static int[] compId, compSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        String s = br.readLine().trim();
        color = new char[N+1];
        for (int i = 1; i <= N; i++) {
            color[i] = s.charAt(i-1);
        }

        // 빨간색 연결 요소 탐색
        compId   = new int[N+1];
        Arrays.fill(compId, -1);
        compSize = new int[N+1];
        int compCount = 0;

        for (int i = 1; i <= N; i++) {
            if (color[i] == 'R' && compId[i] == -1) {
                // BFS로 새로운 연결 요소 탐색
                Queue<Integer> q = new ArrayDeque<>();
                q.add(i);
                compId[i] = compCount;
                int size = 1;

                while (!q.isEmpty()) {
                    int u = q.poll();
                    for (int v : adj[u]) {
                        if (color[v] == 'R' && compId[v] == -1) {
                            compId[v] = compCount;
                            q.add(v);
                            size++;
                        }
                    }
                }
                compSize[compCount] = size;
                compCount++;
            }
        }

        // 검은색 정점마다 이웃 빨간색 경로 합산
        long result = 0;
        for (int u = 1; u <= N; u++) {
            if (color[u] == 'B') {
                for (int v : adj[u]) {
                    if (color[v] == 'R') {
                        result += compSize[ compId[v] ];
                    }
                }
            }
        }

        System.out.println(result);
    }

}
