package Backjoon.골드3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
public class 디스크_트리 {
    private static class Node { TreeMap<String, Node> children = new TreeMap<>();}
    private static Node root = new Node();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String path = br.readLine();
            String[] dirs = path.split("\\\\");
            insertPath(dirs);
        }
        printTrie(root, 0);
    }
    private static void insertPath(String[] dirs) {
        Node current = root;
        for (String dir : dirs) {
            if (!current.children.containsKey(dir)) current.children.put(dir, new Node());
            current = current.children.get(dir);
        }
    }
    private static void printTrie(Node node, int depth) {
        for (String key : node.children.keySet()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < depth; i++) sb.append(" ");
            sb.append(key);
            System.out.println(sb);
            printTrie(node.children.get(key), depth + 1);
        }
    }
}
