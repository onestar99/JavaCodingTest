package Backjoon.플래티넘5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 전통적으로 이런 문제는 싹 다 하드코딩...
 *
 * U : w  윗 면은 흰색
 * D : y  아랫 면은 노란색
 * F : r  앞 면은 빨간색
 * B : o  뒷 면은 오렌지색
 * L : g  왼쪽 면은 초록색
 * R : b  오른쪽 면은 파란색
 *
 */

public class 큐빙 {

    static char[][][] cube;
    static char[] colors = new char[] {'w', 'y', 'r', 'o', 'g', 'b'};
    static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            initCube(); // 큐브 초기화
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                String command = st.nextToken();
                char face = command.charAt(0);
                char direction = command.charAt(1);
                rotate(face, direction);
            }

            printUpFace(); // 큐브 윗 면 출력
        }

    }

    private static void initCube() {
        cube = new char[6][3][3];
        for (int i = 0; i < 6; i++) { // U, D, F, B, L, R
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = colors[i];
                }
            }
        }
    }

    private static void printUpFace() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(cube[U][i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
    }

    // 한 면 자체를 회전
    private static void rotateFace(int face, char direction) {
        char[][] tmp = new char[3][3];
        // tmp에 원본 면 복사
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tmp[i][j] = cube[face][i][j];
            }
        }
        if (direction == '+') { // 시계방향 회전
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cube[face][j][2-i] = tmp[i][j];
                }
            }
        } else { // 반시계방향 회전
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cube[face][2-j][i] = tmp[i][j];
                }
            }
        }
    }

    private static void rotate(char face, char direction) {
        // 먼저 해당 면 자체 회전
        int faceIdx = 0;
        switch(face) {
            case 'U': faceIdx = U; break;
            case 'D': faceIdx = D; break;
            case 'F': faceIdx = F; break;
            case 'B': faceIdx = B; break;
            case 'L': faceIdx = L; break;
            case 'R': faceIdx = R; break;
        }
        rotateFace(faceIdx, direction);

        // 인접 면의 변을 이동시키는 로직
        char[] temp;
        switch(face) {
            case 'U':
                // U면 회전 시: F, L, B, R의 윗줄이 이동
                temp = new char[3];
                for (int i = 0; i < 3; i++) {
                    temp[i] = cube[F][0][i];
                }
                if (direction == '+') {
                    for (int i = 0; i < 3; i++) {
                        cube[F][0][i] = cube[R][0][i];
                        cube[R][0][i] = cube[B][0][i];
                        cube[B][0][i] = cube[L][0][i];
                        cube[L][0][i] = temp[i];
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        cube[F][0][i] = cube[L][0][i];
                        cube[L][0][i] = cube[B][0][i];
                        cube[B][0][i] = cube[R][0][i];
                        cube[R][0][i] = temp[i];
                    }
                }
                break;
            case 'D':
                // D면 회전 시: F, R, B, L의 아랫줄 이동
                temp = new char[3];
                for (int i = 0; i < 3; i++) {
                    temp[i] = cube[F][2][i];
                }
                if (direction == '+') {
                    for (int i = 0; i < 3; i++) {
                        cube[F][2][i] = cube[L][2][i];
                        cube[L][2][i] = cube[B][2][i];
                        cube[B][2][i] = cube[R][2][i];
                        cube[R][2][i] = temp[i];
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        cube[F][2][i] = cube[R][2][i];
                        cube[R][2][i] = cube[B][2][i];
                        cube[B][2][i] = cube[L][2][i];
                        cube[L][2][i] = temp[i];
                    }
                }
                break;
            case 'F':
                // F면 회전 시: U의 아랫줄, R의 왼쪽 열, D의 윗줄, L의 오른쪽 열이 이동
                temp = new char[3];
                for (int i = 0; i < 3; i++) {
                    temp[i] = cube[U][2][i];
                }
                if (direction == '+') {
                    for (int i = 0; i < 3; i++) {
                        cube[U][2][i] = cube[L][2-i][2];
                        cube[L][2-i][2] = cube[D][0][2-i];
                        cube[D][0][2-i] = cube[R][i][0];
                        cube[R][i][0] = temp[i];
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        cube[U][2][i] = cube[R][i][0];
                        cube[R][i][0] = cube[D][0][2-i];
                        cube[D][0][2-i] = cube[L][2-i][2];
                        cube[L][2-i][2] = temp[i];
                    }
                }
                break;
            case 'B':
                // B면 회전 시: U의 윗줄, L의 왼쪽 열, D의 아랫줄, R의 오른쪽 열 이동
                temp = new char[3];
                for (int i = 0; i < 3; i++) {
                    temp[i] = cube[U][0][i];
                }
                if (direction == '+') {
                    for (int i = 0; i < 3; i++) {
                        cube[U][0][i] = cube[R][i][2];
                        cube[R][i][2] = cube[D][2][2-i];
                        cube[D][2][2-i] = cube[L][2-i][0];
                        cube[L][2-i][0] = temp[i];
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        cube[U][0][i] = cube[L][2-i][0];
                        cube[L][2-i][0] = cube[D][2][2-i];
                        cube[D][2][2-i] = cube[R][i][2];
                        cube[R][i][2] = temp[i];
                    }
                }
                break;
            case 'L':
                // L면 회전 시: U의 왼쪽 열, F의 왼쪽 열, D의 왼쪽 열, B의 오른쪽 열 이동
                temp = new char[3];
                for (int i = 0; i < 3; i++) {
                    temp[i] = cube[U][i][0];
                }
                if (direction == '+') {
                    for (int i = 0; i < 3; i++) {
                        cube[U][i][0] = cube[B][2-i][2];
                        cube[B][2-i][2] = cube[D][i][0];
                        cube[D][i][0] = cube[F][i][0];
                        cube[F][i][0] = temp[i];
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        cube[U][i][0] = cube[F][i][0];
                        cube[F][i][0] = cube[D][i][0];
                        cube[D][i][0] = cube[B][2-i][2];
                        cube[B][2-i][2] = temp[i];
                    }
                }
                break;
            case 'R':
                // R면 회전 시: U의 오른쪽 열, B의 왼쪽 열, D의 오른쪽 열, F의 오른쪽 열 이동
                temp = new char[3];
                for (int i = 0; i < 3; i++) {
                    temp[i] = cube[U][i][2];
                }
                if (direction == '+') {
                    for (int i = 0; i < 3; i++) {
                        cube[U][i][2] = cube[F][i][2];
                        cube[F][i][2] = cube[D][i][2];
                        cube[D][i][2] = cube[B][2-i][0];
                        cube[B][2-i][0] = temp[i];
                    }
                } else {
                    for (int i = 0; i < 3; i++) {
                        cube[U][i][2] = cube[B][2-i][0];
                        cube[B][2-i][0] = cube[D][i][2];
                        cube[D][i][2] = cube[F][i][2];
                        cube[F][i][2] = temp[i];
                    }
                }
                break;
        }
    }
}