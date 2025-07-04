package Backjoon.골드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 모노미노도미노2 {

    /**
     * 1. 판을 2차원 배열로 만들고, 블록 종류별 상대 좌표 정의
     * 2. 녹판에 블록을 떨어뜨리고 난 뒤 한 줄 가득 찼는지 확인 -> 제거 -> 점수 추가
     * 3. 연한 구역 확인 -> 바닥에서부터 행 제거
     * 4. 파란판은 블록 좌표를 90도 회전 변환한 뒤 녹판과 동일 로직 적용
     * 5. N번 반복 후, 점수와 남은 블록 개수 출력
     */

    private static int N;
    private static int score;
    private static int[][] green = new int[6][4]; // 녹판: 6 * 4
    private static int[][] blue  = new int[4][6]; // 파란판: 4 * 6

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 입력 횟수
        score = 0; // 초기 점수 0

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken()); // 블록 종류
            int x = Integer.parseInt(st.nextToken()); // 좌표 x
            int y = Integer.parseInt(st.nextToken()); // 좌표 y

            // 1) 녹판에 블록 떨어뜨리기
            dropGreen(t, x, y);
            // 2) 파란판에 블록 떨어뜨리기 (블록 회전 후)
            int tb = (t == 1) ? 1 : (t == 2 ? 3 : 2); // 블록 형태 회전 매핑
            int xb = y; // x좌표 -> y로 매핑
            dropBlue(tb, xb);

            // 3) 전판(초록+파랑) 가득 찬 행/열 삭제 & 점수 추가
            boolean removed;
            do {
                removed = false;
                if (clearGreenOnce()) {
                    score++;
                    removed = true;
                }
                if (clearBlueOnce()) {
                    score++;
                    removed = true;
                }
            } while (removed);

            // 4) 녹판 연한 구역(0~1행)에 블록이 있으면 바닥에서부터 행 제거 및 당김
            handleGreen();

            // 5) 파란판 연한 구역(0~1열)에 블록이 있으면 오른쪽 끝에서부터 열 제거 및 당김
            handleBlue();
        }

        int remain = countBlocks();
        System.out.println(score); // 점수
        System.out.println(remain); // 남은 블록 개수
    }

    /**
     * 녹판에 블록을 아래로 떨어뜨림
     * t: 블록 종류, x,y: 초기 좌표
     */
    private static void dropGreen(int t, int x, int y) {
        int r = 0;
        // 더 이상 내릴 수 없을 때까지 r 증가
        while (canMoveGreen(t, x, y, r+1)) {
            r++;
        }
        placeGreen(t, x, y, r); // 최종 위치에 블록 배치
    }

    // 녹판에서 해당 위치로 블록 이동 가능 여부 확인
    private static boolean canMoveGreen(int t, int x, int y, int r) {
        for (int[] d : shape(t)) {
            int nr = r + d[0];
            int nc = y + d[1];
            // 범위 벗어나거나 이미 블록이 있으면 false
            if (nr < 0 || nr >= 6 || nc < 0 || nc >= 4 || green[nr][nc] == 1) return false;
        }
        return true;
    }

    // 녹판에 블록 실제 배치
    private static void placeGreen(int t, int x, int y, int r) {
        for (int[] d : shape(t)) {
            green[r + d[0]][y + d[1]] = 1;
        }
    }

    /**
     * 파란판에 블록을 오른쪽으로 떨어뜨림
     * 파란판은 녹판을 90도 회전시킨 형태
     */
    private static void dropBlue(int t, int x) {
        int c = 0;
        while (canMoveBlue(t, x, c+1)) {
            c++;
        }
        placeBlue(t, x, c);
    }

    // 파란판에서 이동 가능 여부 확인
    static boolean canMoveBlue(int t, int x, int c) {
        for (int[] d : shape(t)) {
            int nr = x + d[0];
            int nc = c + d[1];
            if (nr < 0 || nr >= 4 || nc < 0 || nc >= 6 || blue[nr][nc] == 1) return false;
        }
        return true;
    }

    // 파란판에 블록 실제 배치
    private static void placeBlue(int t, int x, int c) {
        for (int[] d : shape(t)) {
            blue[x + d[0]][c + d[1]] = 1;
        }
    }

    // 블록 종류에 따른 상대 좌표(shape 정의)
    private static int[][] shape(int t) {
        if (t == 1) return new int[][]{{0,0}};        // 1 * 1
        if (t == 2) return new int[][]{{0,0},{0,1}}; // 1 * 2 (가로)
        return new int[][]{{0,0},{1,0}};             // 2 * 1 (세로)
    }

    // 한 번만 가득 찬 녹판 행을 찾아 지우고 true 반환
    private static boolean clearGreenOnce() {
        for (int i = 5; i >= 0; i--) {
            boolean full = true;
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 0) { full = false; break; }
            }
            if (full) {
                // 제거된 행 위의 모든 행을 아래로 한 칸씩 이동
                for (int r = i; r > 0; r--) {
                    green[r] = green[r-1].clone();
                }
                green[0] = new int[4];
                return true;
            }
        }
        return false;
    }

    // 한 번만 가득 찬 파란판 열을 찾아 지우고 true 반환
    private static boolean clearBlueOnce() {
        for (int j = 5; j >= 0; j--) {
            boolean full = true;
            for (int i = 0; i < 4; i++) {
                if (blue[i][j] == 0) { full = false; break; }
            }
            if (full) {
                // 제거된 열 왼쪽의 모든 열을 오른쪽으로 한 칸씩 이동
                for (int c = j; c > 0; c--) {
                    for (int r = 0; r < 4; r++) {
                        blue[r][c] = blue[r][c-1];
                    }
                }
                for (int r = 0; r < 4; r++) blue[r][0] = 0;
                return true;
            }
        }
        return false;
    }

    /**
     * 녹판 연한 구역(0~1행)에 블록이 있으면 바닥에서부터 행 제거 및 당김
     */
    private static void handleGreen() {
        int cnt = 0;
        // 0~1행 중 하나라도 블록 있으면 cnt++
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 1) { cnt++; break; }
            }
        }
        // cnt만큼 바닥 행 제거 및 당김
        while (cnt-- > 0) {
            for (int r = 5; r > 0; r--) green[r] = green[r-1].clone();
            green[0] = new int[4];
        }
    }

    /**
     * 파란판 연한 구역(0~1열)에 블록이 있으면 오른쪽 끝에서부터 열 제거 및 당김
     */
    private static void handleBlue() {
        int cnt = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                if (blue[i][j] == 1) { cnt++; break; }
            }
        }
        while (cnt-- > 0) {
            for (int c = 5; c > 0; c--) {
                for (int r = 0; r < 4; r++) blue[r][c] = blue[r][c-1];
            }
            for (int r = 0; r < 4; r++) blue[r][0] = 0;
        }
    }

    // 남아 있는 녹판 + 파란판 블록 개수 합산
    private static int countBlocks() {
        int sum = 0;
        for (int[] row : green) for (int v : row) sum += v;
        for (int[] row : blue)  for (int v : row) sum += v;
        return sum;
    }
}
