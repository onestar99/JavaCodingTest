package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 불우이웃돕기 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Edge> edges = new ArrayList<>();
        int totalLength = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                int len = charToLength(line.charAt(j));
                totalLength += len;
                if (i != j && len > 0) {
                    edges.add(new Edge(i, j, len));
                }
            }
        }

        // 크루스칼
        Collections.sort(edges);
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        int usedLength = 0;
        int count = 0;

        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                usedLength += e.weight;
                count++;
                if (count == N - 1) break;
            }
        }

        if (count != N - 1) {
            System.out.println(-1);
        } else {
            System.out.println(totalLength - usedLength);
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false;
        parent[rootY] = rootX;
        return true;
    }

    private static int charToLength(char c) {
        if (c == '0') return 0;
        if (Character.isLowerCase(c)) return c - 'a' + 1;
        else return c - 'A' + 27;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
