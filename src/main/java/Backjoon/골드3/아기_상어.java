package Backjoon.골드3;

import java.util.*;
import java.io.*;

public class 아기_상어 {

    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int size = 2;   // 아기 상어 초기 크기
    static int eat = 0;    // 현재 크기에서 먹은 물고기 수
    static int count = 0;  // 걸린 시간(결과)
    static int sharkX, sharkY;  // 아기 상어 위치
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    arr[i][j] = 0;  // 상어 위치는 0으로 바꿔놓음
                }
            }
        }

        // BFS 를 통해 먹을 수 있는 물고기가 더 이상 없을 때까지 반복
        while (true) {
            // 이번 턴(현재 상어 위치)에서 먹을 수 있는 물고기 리스트 얻기
            List<int[]> fishList = bfs(sharkX, sharkY);

            // 먹을 수 있는 물고기가 없으면 종료
            if (fishList.isEmpty()) {
                break;
            }

            // 거리 기준 오름차순, 행 기준 오름차순, 열 기준 오름차순
            fishList.sort((o1, o2) -> {
                if (o1[2] == o2[2]) {
                    if (o1[0] == o2[0]) {
                        return Integer.compare(o1[1], o2[1]);
                    }
                    return Integer.compare(o1[0], o2[0]);
                }
                return Integer.compare(o1[2], o2[2]);
            });

            // 가장 가까우면서, 가장 위/왼쪽인 물고기를 하나 선택
            int[] fish = fishList.get(0);
            int nx = fish[0];
            int ny = fish[1];
            int dist = fish[2];

            // 상어 이동 및 시간 추가
            sharkX = nx;
            sharkY = ny;
            count += dist;

            // 먹은 물고기 수 업데이트
            arr[nx][ny] = 0;
            eat++;
            if (eat == size) {
                size++;
                eat = 0;
            }
        }

        System.out.println(count);
    }

    // (sx, sy)에서 BFS 수행: 먹을 수 있는 물고기들의 (x, y, 거리)를 리스트로 반환
    static List<int[]> bfs(int sx, int sy) {
        visited = new boolean[N][N];
        int[][] distance = new int[N][N];
        Queue<int[]> queue = new LinkedList<>();

        visited[sx][sy] = true;
        queue.offer(new int[]{sx, sy});
        List<int[]> fishList = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                // 아직 방문 안 했고, 상어가 지나갈 수 있는 칸(크기가 상어 크기 이하)만 이동
                if (!visited[nx][ny] && arr[nx][ny] <= size) {
                    visited[nx][ny] = true;
                    distance[nx][ny] = distance[x][y] + 1;
                    queue.offer(new int[]{nx, ny});

                    // 먹을 수 있는 물고기라면 리스트에 추가
                    if (arr[nx][ny] < size && arr[nx][ny] > 0) {
                        fishList.add(new int[]{nx, ny, distance[nx][ny]});
                    }
                }
            }
        }
        return fishList;
    }
}
