package Backjoon.실버1;
import java.util.*;

// #BFS, #그래프
public class 효율적인_해킹 {


    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int n, m;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        // 그래프 노드 초기화
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 간선 추가
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            graph[b].add(a);
        }

        int maxHacking = 0;
        ArrayList<Integer> list = new ArrayList<>(); // 해킹 많이 가능한 노드 리스트
        // BFS를 통해서 각 번호 돌려서 해킹을 가장 많이한 번호들만 리스트에 넣기
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            int hackingCount = bfs(i);
            if (hackingCount > maxHacking) { // 새롭게 높은 번호 나오면 리스트 초기화 후 넣어주기
                maxHacking = hackingCount;
                list.clear();
                list.add(i);
            } else if (hackingCount == maxHacking) {
                list.add(i);
            }
        }

        Collections.sort(list); // 오름차순 정렬
        for (int num : list) {
            System.out.print(num + " ");
        }
    }

    static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (int neighbor : graph[currentNode]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                    count++;
                }
            }
        }

        return count;
    }
}
