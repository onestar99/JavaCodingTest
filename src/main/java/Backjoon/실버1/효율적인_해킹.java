package Backjoon.실버1;
import java.util.*;

// #BFS, #그래프
public class 효율적인_해킹 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            graph[b].add(a);
        }

        System.out.println(graph[1].toString());
        System.out.println(graph[2].toString());
        System.out.println(graph[3].toString());
        System.out.println(graph[4].toString());
        System.out.println(graph[5].toString());


    }
}
