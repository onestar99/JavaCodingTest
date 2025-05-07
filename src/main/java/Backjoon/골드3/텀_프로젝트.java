package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 텀_프로젝트 {

    static int[] students;
    static boolean[] visited;
    static boolean[] done;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            students = new int[n + 1];
            visited = new boolean[n + 1];
            done = new boolean[n + 1];
            count = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                students[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            System.out.println(n - count); // 팀을 이룬 학생 수를 제외한 나머지가 정답
        }
    }

    static void dfs(int current) {
        visited[current] = true;
        int next = students[current];

        if (!visited[next]) {
            dfs(next);
        } else if (!done[next]) {
            // 사이클을 발견한 경우
            count++;
            for (int i = next; i != current; i = students[i]) {
                count++;
            }
        }

        done[current] = true;
    }
}