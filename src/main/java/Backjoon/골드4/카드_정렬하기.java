package Backjoon.골드4;

import java.util.*;

public class 카드_정렬하기 {

    public static void main(String[] args) {


        /**
         * 우선순위 큐를 사용하여 정렬하면서 문제 풀기.
         * 이렇게 풀면 더 커진 값도 정렬하면서 들어가기 때문에 가능
         */

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        PriorityQueue<Integer> cardSizes = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            cardSizes.add(scanner.nextInt());
        }

        int result = 0;
        // 카드집이 한 개만 남을 때까지
        while (cardSizes.size() > 1) {
            int firstNum = cardSizes.poll();
            int secondNum = cardSizes.poll();
            int sum = firstNum + secondNum;
            result += sum;
            cardSizes.add(sum);
        }

        System.out.println(result);








//        /**
//         * 생각을 잘못한 접근.
//         * 한번에 정렬한 후 순차적으로 앞에서부터 합치면 될거라 생각했음.
//         * 이건 앞에서부터 정렬하여 값이 더해지면 작은수 끼리 비교하여 최소화를 결국 하지 못함.
//         * ex) 3, 4, 5, 6이면  3 + 4 = 7 이므로 이미 그 다음 수 들 중 최소 작은 수들인 5, 6을 넘겨버림.
//         */
//        Scanner scanner = new Scanner(System.in);
//        int N = scanner.nextInt();
//        List<Integer> cardSizes = new ArrayList<>();
//        for (int i = 0; i < N; i++) {
//            cardSizes.add(scanner.nextInt());
//        }
//        Collections.sort(cardSizes);
//
//        List<Integer> cals = new ArrayList<>();
//        int result = 0;
//        if (N == 1) {
//            System.out.println(0);
//        } else {
//            int cal = cardSizes.get(0);
//            for (int i = 1; i < N; i++) {
//                cal += cardSizes.get(i);
//                result += cal;
//            }
//        }
//        System.out.println(result);
    }
}
