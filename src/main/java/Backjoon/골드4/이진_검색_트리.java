package Backjoon.골드4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 이진_검색_트리 {

    static Node1 root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        List<Integer> preOrder = new ArrayList<>();

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            int value = Integer.parseInt(input);
            preOrder.add(value);
        }

        // 트리 구성
        root = null;
        for (int key : preOrder) {
            root = insert(root, key);
        }

        // 후위 순회
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);

        // 결과 출력
        for (int key : result) {
            System.out.println(key);
        }
    }


    // BST 노드 삽입
    public static Node1 insert(Node1 node, int key) {
        if (node == null) return new Node1(key);

        if (key < node.key) node.left = insert(node.left, key);
        else node.right = insert(node.right, key);

        return node;
    }

    // 후위 순회 결과 출력
    public static void postOrder(Node1 node, List<Integer> result) {
        if (node == null) return;

        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.key);
    }
}

class Node1 {
    int key;
    Node1 left, right;

    Node1(int key) {
        this.key = key;
        left = right = null;
    }
}
