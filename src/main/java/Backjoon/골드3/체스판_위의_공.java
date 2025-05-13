package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 체스판_위의_공 {

    static int R, C;
    static int[] parent;
    static int[][] A;
    // 8방향
    static int[] dr = {-1,-1,-1,0,0,1,1,1};
    static int[] dc = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        A = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int N = R * C;
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        // 각 칸마다 이동할 다음 칸 결정 후 union
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int cur = A[r][c];
                int minVal = cur;
                int nr = r, nc = c;
                // 8방향 검사
                for (int d = 0; d < 8; d++) {
                    int rr = r + dr[d];
                    int cc = c + dc[d];
                    if (rr < 0 || rr >= R || cc < 0 || cc >= C) continue;
                    if (A[rr][cc] < minVal) {
                        minVal = A[rr][cc];
                        nr = rr; nc = cc;
                    }
                }
                if (minVal < cur) {
                    int u = r * C + c;
                    int v = nr * C + nc;
                    parent[u] = v;
                }
            }
        }

        // 각 칸에서 최종 싱크 찾기
        int[] cnt = new int[N];
        for (int i = 0; i < N; i++) {
            int root = find(i);
            cnt[root]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(cnt[r * C + c]).append(c == C-1 ? "" : " ");
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    // 경로 압축을 포함한 find
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
