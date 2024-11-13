package Programmers.티멕스;

public class 통신사 {
    public static void main(String[] args) {
        int[] x = {5};
        int[] y = {5};
        int[] r = {5};
        int[] v = {92, 83, 14, 45, 66, 37, 28, 9, 10, 81};
        System.out.println(solution(x, y, r, v)); // 80

        int[] x2 = {3, 4};
        int[] y2 = {3, 5};
        int[] r2 = {2, 3};
        int[] v2 = {12, 83, 54, 35, 686, 337, 258, 95, 170, 831, 8, 15};
        System.out.println(solution(x2, y2, r2, v2)); // 28
    }

    public static int solution(int[] x, int[] y, int[] r, int[] v) {

        // 직사각형 범위 구하기
        int lx = Integer.MAX_VALUE; // 왼쪽 x좌표
        int rx = Integer.MIN_VALUE; // 오른쪽 x좌표
        int by = Integer.MAX_VALUE; // 아래쪽 y좌표
        int ty = Integer.MIN_VALUE; // 위쪽 y좌표

        for (int i = 0; i < x.length; i++) {
            lx = Math.min(lx, x[i] - r[i]);
            rx = Math.max(rx, x[i] + r[i]);
            by = Math.min(by, y[i] - r[i]);
            ty = Math.max(ty, y[i] + r[i]);
        }

        // 직사각형의 넓이 계산을 위해 가로, 세로 길이 구하기
        int width = rx - lx;
        int height = ty - by;

        // x, y 좌표 분리 위한 카운트
        int pointCount = v.length / 2;
        int insideCount = 0;

        // 난수를 사용하여 점을 생성하고 원 내부에 있는지 확인합니다.
        for (int i = 0; i < pointCount; i++) {
            int randX = lx + (v[2 * i] % width);
            int randY = by + (v[2 * i + 1] % height);

            // 점이 하나의 원 안에 있는지 확인
            for (int j = 0; j < x.length; j++) {
                // 두 점 사이의 거리 계산
                int dx = randX - x[j];
                int dy = randY - y[j];
                // 크아악 싯팔 바로 피타고라스 정리화
                // 점과 원의 중심 사이의 거리가 원의 반지름 이하인 경우 (점이 원 안에 있다)
                if (Math.pow(dx, 2) + Math.pow(dy, 2) <= Math.pow(r[j], 2)) {
                    insideCount++;
                    break;
                }
            }
        }

        // 기지국 영역 내부의 존재하는 점의 비율
        double ratio = (double) insideCount / pointCount;

        // 면적 계산 (비율 * 직사각형 넓이)
        return (int) (ratio * width * height);
    }
}
