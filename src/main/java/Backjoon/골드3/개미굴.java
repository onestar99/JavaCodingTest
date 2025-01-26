package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 자식 노드를 사전 순서로 정렬하기 위해 TreeMap 사용
 * 트리 구성후
 * 재귀 출력
 */

public class 개미굴 {

    static class Node {
        TreeMap<String, Node> children = new TreeMap<>();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Node root = new Node();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            Node current = root;

            for (int j = 0; j < k; j++) {
                String food = st.nextToken();
                current.children.putIfAbsent(food, new Node());
                current = current.children.get(food);
            }
        }

        printTree(root, sb, 0);
        System.out.print(sb);
    }

    // 출력
    private static void printTree(Node node, StringBuilder sb, int depth) {
        for (String key : node.children.keySet()) {
            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(key).append("\n");
            printTree(node.children.get(key), sb, depth + 1);
        }
    }
}
