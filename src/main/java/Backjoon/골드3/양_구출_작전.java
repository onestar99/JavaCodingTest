package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 섬을 인접 리스트 형태로 구성하여 연결 체크
 * 섬의 정보를 따로 저장
 * 루트에서 시작하여 dfs를 통해 각 서브트리를 구성하여 양의 개수 파악 후 더하기
 */

public class 양_구출_작전 {

    static int N;
    static List<List<Integer>> adj; // 인접 리스트를 통한 섬의 연결 체크
    static List<Island> map; // 실제 섬들과 동물 정보를 가지는 부분

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }

        // 섬 정보 입력하고 각 섬들간 간선 추가
        map = new ArrayList<>(N + 1);
        map.add(null); // 0번 섬 비우기
        map.add(new Island('S', 0)); // 1번 섬 추가
        for(int i = 2; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char animalName = st.nextToken().charAt(0);
            int animalNum = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            map.add(new Island(animalName, animalNum));
            adj.get(parent).add(i);
         }

        long answer = dfs(1);
        System.out.println(answer);

    }

    private static long dfs(int u) {
        long totalFromChildren = 0;

        // 자식 섬들로 내려가서 그 서브트리에서 살아남은 양 수를 모두 더함
        for (int v : adj.get(u)) {
            totalFromChildren += dfs(v);
        }

        Island isl = map.get(u);
        if (isl.animalName == 'S') { // 해당 섬이 양섬이면 자식섬에서 온 양 + 이 섬의 양
            return totalFromChildren + isl.animalNum;
        } else { // 늑대 섬이면 자식섬에서 온 양에서 늑대 수 빼기
            long survivedSheep = totalFromChildren - isl.animalNum;
            if (survivedSheep < 0) // 양의 수가 음수가 되면 0 처리
                return 0;
            else
                return survivedSheep;
            // Math.max(0, survivedSheep);
        }
    }

    private static class Island {
        char animalName; // 동물 이름
        int animalNum; // 동물 개수

        public Island(char animalName, int animalNum) {
            this.animalName = animalName;
            this.animalNum = animalNum;
        }
    }
}
