package Backjoon.실버4;
import java.util.*;

public class 요세푸스_문제 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        boolean[] visited = new boolean[N];
        List<Integer> resultList = new ArrayList<>();

        int k = -1; // 카운트
        int point = -1; // 현재 위치
        for (int i = 0; i < N; i++) {
            k = -1;
            while (k < K - 1) {
                point++; // 먼저 포인트를 움직입니다.
                if (point == N) { // 포인트를 움직였더니 끝에 지점이라면 초기값으로 바꿔줍니다.
                    point = 0;
                }
                if (!visited[point]) { // 방문하지 않은 곳이라면 카운트를 올려줍니다.
                    k++;
                }
            }
            // 포인트가 지정이 완료되면 true 처리하고 resultList에 추가해줍니다.
            visited[point] = true;
            resultList.add(point + 1);
        }

        System.out.print("<");
        for (int i = 0; i < resultList.size() - 1; i++) {
            System.out.print(resultList.get(i) + ", ");
        }
        System.out.print(resultList.get(resultList.size() - 1) + ">");

    }
}
