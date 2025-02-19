package Backjoon.플래티넘4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class 휴대폰_자판 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue;
            int N = Integer.parseInt(line);
            Node root = new Node();
            String[] words = new String[N];

            for (int i = 0; i < N; i++) {
                words[i] = br.readLine();
                root.insert(words[i]);
            }

            long totalPress = 0;
            for (String word : words) {
                totalPress += root.countKeyPress(word);
            }

            // 계산
            double average = (double) totalPress / N;
            System.out.printf("%.2f\n", average);
        }
    }

    private static class Node {
        TreeMap<Character, Node> children = new TreeMap<>();
        boolean isEnd = false;

        public void insert(String word) {
            Node current = this;
            for (char ch : word.toCharArray()) {
                current.children.putIfAbsent(ch, new Node()); // 처음 배움 Map 에서 사용할 수 있는 방식 없으면 새로 만들기
                current = current.children.get(ch);
            }
            current.isEnd = true; // 단어의 끝
        }

        public int countKeyPress(String word) {
            Node current = this;
            int count = 0;

            // 중간에 한번씩 멈춰서 횟수 세주기
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (i == 0) {
                    count++;
                } else {
                    // 노드의 자식이 둘 이상이거나, 현재 노드가 단어의 끝이면 카운트++
                    if (current.children.size() > 1 || current.isEnd) {
                        count++;
                    }
                }
                current = current.children.get(ch);
            }
            return count;
        }

    }
}
