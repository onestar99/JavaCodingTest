package Programmers.level_1;

public class 최소직사각형 {

    public static int solution(int[][] sizes) {

        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] < sizes[i][1]) {
                int temp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = temp;
            }
        }

        int max1 = 0;
        int max2 = 0;
        for (int[] size: sizes) {
            if (max1 < size[0]) {
                max1 = size[0];
            }
            if (max2 < size[1]) {
                max2 = size[1];
            }
        }

        return max1 * max2;
    }


    public static int solution2(int[][] sizes) {

        int length = 0;
        int height = 0;
        for (int[] card: sizes) {
            length = Math.max(length, Math.max(card[0], card[1]));
            height = Math.max(height, Math.min(card[0], card[1]));
        }
        return length * height;
    }

    public static void main(String[] args) {

//        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        int[][] sizes = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};
        System.out.println(solution2(sizes));


    }
}