package Programmers.티멕스;

import java.util.ArrayList;
import java.util.List;

public class 벌집 {

    public static void main(String[] args) {

        System.out.println(solution(2)); // [1, 6, 2, 7, 5, 3, 4]
        System.out.println(solution(3)); // [1, 12, 2, 11, 13, 3, 18, 14, 10, 19, 4, 17, 15, 9, 16, 5, 8, 6, 7]
        System.out.println(solution(4)); // [1, 18, 2, 17, 19, 3, 16, 30, 20, 4, 29, 31, 21, 15, 36, 32, 5, 28
                                            // ,37, 22, 14, 35, 33, 6, 27, 34, 23, 13, 26, 24, 7, 12, 25, 8, 11, 9, 10]
    }

    public static List<Integer> solution(int n) {

        // 처음 1 추가
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(new ArrayList<>());
        dp.get(0).add(1);

        if (n > 1) {
            // 1보다 크면 여러 층 구성하기 2 ~ n까지
            for (int i = 2; i <= n; i++) {
                int nowMax = 6 * (i - 1);

                // 각 값에 6 * (i - 1)을 더하기
                for (List<Integer> row : dp) {
                    for (int j = 0; j < row.size(); j++) {
                        row.set(j, row.get(j) + 6 * (i - 1));
                    }
                }

                // 왼쪽 구성
                List<List<Integer>> left = new ArrayList<>();
                left.add(new ArrayList<>());
                left.get(0).add(1);
                left.add(new ArrayList<>());
                left.get(1).add(nowMax);
                left.get(1).add(2);

                // 오른쪽 구성
                List<List<Integer>> right = new ArrayList<>();
                right.add(new ArrayList<>());
                right.get(0).add(nowMax / 2 + 2);
                right.get(0).add(nowMax / 2);
                right.add(new ArrayList<>());
                right.get(1).add(nowMax / 2 + 1);

                // 중앙 리스트 양 끝에 숫자 채우기
                int start = 3;
                int end = nowMax - 1;
                // 시작 숫자 뒤에 추가, 끝 숫자 앞에 추가
                for (List<Integer> row : dp) {
                    if (row.size() == i - 1) continue; // 최대 크기 도달한 줄은 패스
                    row.add(start++);
                    row.add(0, end--);
                }

                // dp로 합치기
                dp.addAll(0, left);
                dp.addAll(right);
            }
        }

        // 리스트 합쳐서 반환
        List<Integer> result = new ArrayList<>();
        for (List<Integer> row : dp) {
            result.addAll(row);
        }

        return result;
    }
}
