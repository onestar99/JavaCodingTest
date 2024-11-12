package Backjoon.골드4;

import java.util.*;

/**
 * 다익스트라로 모든 방 체크.
 */

class Node implements Comparable<Node> {
    int room;
    int distance;

    Node(int room, int distance) {
        this.room = room;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.distance, other.distance);
    }

    public String toString() {
        return "room : " + room + ", distance : "+ distance;
    }
}
public class 비밀_모임 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();

            // 그래프 생성
            List<List<Node>> graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                int a = scanner.nextInt(); // a
                int b = scanner.nextInt(); // a에 연결된 룸
                int c = scanner.nextInt();
                graph.get(a).add(new Node(b, c));
                graph.get(b).add(new Node(a, c)); // 양방향 연결
            }

            // 친구놈들 위치 저장
            List<Integer> friends = new ArrayList<>();
            int K = scanner.nextInt();
            for (int i = 0; i < K; i++) {
                friends.add(scanner.nextInt());
            }
            friends.sort(Comparator.reverseOrder());


            // 친구별 방 최단 거리 계산
            int[][] friendDistances = new int[K][N + 1];
            for (int i = 0; i < K; i++) {
                int friendRoom = friends.get(i);
                friendDistances[i] = dijkstra(graph, N, friendRoom);
            }


            // 각 방을 모임 장소로 정해놓은 뒤 최단 거리의 방 계산
            int minTotalDistance = Integer.MAX_VALUE;
            int bestRoom = -1;
            // 모든 방을 모임 장소로 정하고 거리를 다 더함.
            for (int room = 1; room <= N; room++) {
                int totalDistance = 0;
                for (int i = 0; i < K; i++) {
                    totalDistance += friendDistances[i][room];
                }
                // 최소 값 갱신
                if (minTotalDistance > totalDistance) {
                    minTotalDistance = totalDistance;
                    bestRoom = room;
                }
            }
            System.out.println(bestRoom);
        }
        scanner.close();
    }


    // 다익스트라
    public static int[] dijkstra(List<List<Node>> graph, int N, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.room;
            int currentDistance = current.distance;

            // 최적화, 현재 노드의 거리가 기존 거리보다 크다면 넘겨버리기.
            if (currentDistance > dist[currentVertex]) continue;


            for (Node neighbor : graph.get(currentVertex)) {
                // 현재 노드까지의 거리 + 다음 노드까지의 거리
                int newDist = dist[currentVertex] + neighbor.distance;
                // 기존 거리보다 작을 경우
                if (newDist < dist[neighbor.room]) {
                    dist[neighbor.room] = newDist;
                    pq.add(new Node(neighbor.room, newDist));
                }
            }
        }
        return dist;
    }
}
