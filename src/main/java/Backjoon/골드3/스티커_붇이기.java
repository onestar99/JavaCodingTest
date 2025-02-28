package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 그래프 행렬로 N * M 만들기
 * 2. 순서대로 모형을 규칙에 맞추어 행렬에 넣는게 가능한지 여부 체크하기
 * 3. 위쪽 체크 -> 왼쪽 체크 -> 90도 회전 체크 -> 180도 회전 체크 -> 270도 회전 체크 -> 버리기 -> 다음 도형
 * 4. 행렬에서 체크되어 있는 부분 개수 확인 (결과)
 *
 *
 * 회전 어떻게 할래?
 */

public class 스티커_붇이기 {

    static int N;
    static int M;
    static int K;
    static int[][] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[R][C];

            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            boolean isAttached = false;

            // 스티커를 최대 4번(0도, 90도, 180도, 270도) 회전하며 붙이기 시도
            for (int rotation = 0; rotation < 4; rotation++) {
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        if (canAttach(graph, sticker, r, c)) { // 붙여보기
                            attachSticker(graph, sticker, r, c);
                            isAttached = true;
                            break;
                        }
                    }
                    if (isAttached) break;
                }

                if (isAttached) break;
                sticker = rotateSticker(sticker); // 스티커 회전
            }
        }

        System.out.println(countFilledCells(graph)); // 결과 출력
    }

    // 스티커 회전
    static int[][] rotateSticker(int[][] sticker) {
        int rows = sticker.length;
        int cols = sticker[0].length;
        int[][] rotated = new int[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotated[c][rows - 1 - r] = sticker[r][c];
            }
        }
        return rotated;
    }

    // 스티커 붙이기 가능 여부 확인
    static boolean canAttach(int[][] graph, int[][] sticker, int startRow, int startCol) {
        int rows = sticker.length;
        int cols = sticker[0].length;

        if (startRow + rows > graph.length || startCol + cols > graph[0].length) {
            return false;
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (sticker[r][c] == 1 && graph[startRow + r][startCol + c] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    // 스티커 붙이기
    static void attachSticker(int[][] graph, int[][] sticker, int startRow, int startCol) {
        int rows = sticker.length;
        int cols = sticker[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (sticker[r][c] == 1) {
                    graph[startRow + r][startCol + c] = 1;
                }
            }
        }
    }

    // 스티커가 붙은 칸 수 계산
    static int countFilledCells(int[][] graph) {
        int count = 0;

        for (int r = 0; r < graph.length; r++) {
            for (int c = 0; c < graph[0].length; c++) {
                if (graph[r][c] == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}
