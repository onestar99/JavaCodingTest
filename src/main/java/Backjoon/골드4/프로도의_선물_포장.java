package Backjoon.골드4;

import java.util.Scanner;

public class 프로도의_선물_포장 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        // T번의 테스트 케이스만큼 값을 구한 뒤 출력을 함
        for (int t = 0; t < T; t++) {
            int[][] gifts = new int[6][2]; // 앞에 3개는 기본 박스 모형 3개, 뒤에 3개는 그 모형들 가로 세로 바꾼거 (90도 회전)
            // 각 상자의 작은 부분과 큰 부분을 나눔
            for (int i = 0; i < 3; i++) {
                gifts[i][0] = scanner.nextInt();
                gifts[i][1] = scanner.nextInt();
                gifts[i + 3][0] = gifts[i][1];
                gifts[i + 3][1] = gifts[i][0];
            }
            System.out.println(findMinArea(gifts));
        }
        scanner.close();
    }

    // 모든 선물 상자 케이스 찾아서 최소 값 리턴
    static int findMinArea(int[][] gifts) {
        int minSize = Integer.MAX_VALUE;

        for (int box1 = 0; box1 < 6; box1++) {
            for (int box2 = 0; box2 < 6; box2++) {
                for (int box3 = 0; box3 < 6; box3++) {
                    // 같은 박스 경우의 수는 쳐냄   1==4 , 2==5, 3==6
                    if (box1 % 3 == box2 % 3 || box1 % 3 == box3 % 3 || box2 % 3 == box3 % 3) {
                        continue;
                    }
                    // 박스 일렬
                    int width = gifts[box1][0] + gifts[box2][0] + gifts[box3][0]; // 한 줄 길게 다 더하기
                    int height = Math.max(gifts[box1][1], Math.max(gifts[box2][1], gifts[box3][1])); // 큰 놈 고르기
                    minSize = Math.min(minSize, width * height);
                    // 박스 가로 2개  세로 1개
                    width = Math.max(gifts[box1][0], gifts[box2][0] + gifts[box3][0]); // 세로 1개의 width, 가로 2개의 길이 더한 width 비교하여 큰 값 width로 놓기
                    height = gifts[box1][1] + Math.max(gifts[box2][1], gifts[box3][1]); // 세로 1개가 이미 깔린 상태에서 height를 더하고 가로 2개쪽에서 height가 큰 값을 더하기
                    minSize = Math.min(minSize, width * height);
                }
            }
        }

        return minSize;
    }


}
