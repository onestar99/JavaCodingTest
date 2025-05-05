package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 미친_아두이노 {


    static int r, c;
    static char[][] board;
    static String jongsuAdLocation;
    static HashMap<String, Integer> cadLocation = new HashMap<>();

    public static void main(String[] args) throws IOException {

        /**
         * 1. 종수 AD가 먼저 움직인다. (지정된 움직임으로 이동) (이동횟수 증가)
         * 2. 종수 AD가 CAD에 닿았는지 확인한다. (닿았으면 끝) (kraj X 출력)
         * 3. CAD들을 움직인다. [r1 - r2] + [s1 - s2] 값이 가장 작아지는 방향으로 이동한다.
         * 4. CAD가 종수 AD에 닿았는지 확인한다. (닿았으면 끝) (kraj X 출력)
         * 5. CAD들이 서로 중복되는 곳에 있게 될 시 해당 CAD들을 모두 삭제한다.
         * 
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];
        int result = 0;

        jongsuAdLocation = "";

        for (int i = 0; i < r; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                board[i][j] = temp[j];
                if (temp[j] == '.') {
                    continue;
                } else if (temp[j] == 'I') { // 종수AD 위치 기억
                    jongsuAdLocation = i + "," + j;
                } else if (temp[j] == 'R') {
                    String tempLocation = i + "," + j;
                    cadLocation.put(tempLocation, cadLocation.getOrDefault(tempLocation, 0) + 1);
                }
            }
        }


        char[] inputData = br.readLine().toCharArray();

        for (char input : inputData) {
            result++;
            /**
             * 1. 종수 AD가 먼저 움직인다. (지정된 움직임으로 이동) (이동횟수 증가)
             * 2. 종수 AD가 CAD에 닿았는지 확인한다. (닿았으면 끝) (kraj X 출력)
             * 3. CAD들을 움직인다. [r1 - r2] + [s1 - s2] 값이 가장 작아지는 방향으로 이동한다.
             * 4. CAD가 종수 AD에 닿았는지 확인한다. (닿았으면 끝) (kraj X 출력)
             * 5. CAD들이 서로 중복되는 곳에 있게 될 시 해당 CAD들을 모두 삭제한다.
             *
             */

            // 1. 종수 AD가 먼저 움직인다. (지정된 움직임으로 이동) (이동횟수 증가)
            moveJongsuAD(input);
            // 2. 종수 AD가 CAD에 닿았는지 확인한다. (닿았으면 끝) (kraj X 출력)
            if (isEnd()) {
                System.out.println("kraj " + result);
                return;
            }
            // 3. CAD들을 움직인다. [r1 - r2] + [s1 - s2] 값이 가장 작아지는 방향으로 이동한다.
            moveCAD();
            // 4. 종수 AD가 CAD에 닿았는지 확인한다. (닿았으면 끝) (kraj X 출력)
            if (isEnd()) {
                System.out.println("kraj " + result);
                return;
            }
            // 5. CAD들이 서로 중복되는 곳에 있게 될 시 해당 CAD들을 모두 삭제한다.
            duplicateCadBoom();
        }
        System.out.println(boardPrint());


    }

    public static void duplicateCadBoom() {
        HashMap<String, Integer> temp = new HashMap<>();
        for (Map.Entry<String, Integer> entry : cadLocation.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            int r = Integer.parseInt(key.split(",")[0]);
            int c = Integer.parseInt(key.split(",")[1]);
            if (value == 1) {
                temp.put(key, value);
                board[r][c] = 'R';
            } else {
                board[r][c] = '.';
            }
        }
        cadLocation = temp;
    }

    public static void moveCAD() {
        String[] adLocation = jongsuAdLocation.split(",");
        int r1 = Integer.parseInt(adLocation[0]);
        int c1 = Integer.parseInt(adLocation[1]);
        HashMap<String, Integer> updatedCadLocation = new HashMap<>();

        for (Map.Entry<String, Integer> entry : cadLocation.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            String[] cadLoc = key.split(",");
            int r2 = Integer.parseInt(cadLoc[0]);
            int c2 = Integer.parseInt(cadLoc[1]);

            // [r1 - r2] + [s1 - s2] 값이 가장 작아지는 방향으로 이동 (map에 추가 및 board 표기)
            board[r2][c2] = '.'; // 기존 위치 삭제
            int[][] directions = {
                    {-1, 0},  // 위
                    {1, 0},   // 아래
                    {0, -1},  // 왼쪽
                    {0, 1},   // 오른쪽
                    {-1, -1}, // 왼쪽 위
                    {-1, 1},  // 오른쪽 위
                    {1, -1},  // 왼쪽 아래
                    {1, 1}    // 오른쪽 아래
            };
            int minDistance = Integer.MAX_VALUE;
            int nextR = r2, nextC = c2;
            for (int[] dir : directions) {
                int nr = r2 + dir[0];
                int nc = c2 + dir[1];

                // 경계 체크
                if (nr < 0 || nc < 0 || nr >= board.length || nc >= board[0].length) continue;

                int dist = Math.abs(r1 - nr) + Math.abs(c1 - nc);
                if (dist < minDistance) {
                    minDistance = dist;
                    nextR = nr;
                    nextC = nc;
                }
            }
            // 새 위치에 CAD 이동
//            board[nextR][nextC] = 'R';
            String newKey = nextR + "," + nextC;
            updatedCadLocation.put(newKey, updatedCadLocation.getOrDefault(newKey, 0) + 1);
        }
        cadLocation = updatedCadLocation;
    }

    // 종수 AD가 CAD에 닿았는지 확인한다. (닿았으면 끝)
    public static boolean isEnd() {
        return cadLocation.containsKey(jongsuAdLocation);
    }

    public static void moveJongsuAD(char input) {
        String[] location = jongsuAdLocation.split(",");
        int r = Integer.parseInt(location[0]);
        int c = Integer.parseInt(location[1]);

        int nR = r, nC = c;
        // 종수 AD 위치 (r, c)
        switch (input) {
            case '1':
                nR = r + 1;
                nC = c - 1;
                break;
            case '2':
                nR = r + 1;
                nC = c;
                break;
            case '3':
                nR = r + 1;
                nC = c + 1;
                break;
            case '4':
                nR = r;
                nC = c - 1;
                break;
            case '5':
                nR = r;
                nC = c;
                break;
            case '6':
                nR = r;
                nC = c + 1;
                break;
            case '7':
                nR = r - 1;
                nC = c - 1;
                break;
            case '8':
                nR = r - 1;
                nC = c;
                break;
            case '9':
                nR = r - 1;
                nC = c + 1;
                break;
        }
        board[r][c] = '.';
        board[nR][nC] = 'I';
        jongsuAdLocation = nR + "," + nC;
    }

    // board 출력
    public static String boardPrint() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
