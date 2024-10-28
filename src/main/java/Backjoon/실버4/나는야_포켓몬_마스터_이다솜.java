package Backjoon.실버4;

import java.util.*;

public class 나는야_포켓몬_마스터_이다솜 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        for (int i = 0; i < N; i++) {
            String str = scanner.next();
            list.add(str);
            map.put(str, i + 1);
        }

        for (int i = 0; i < M; i++) {
            String str = scanner.next();
            if (isNumeric(str)) {
                System.out.println(list.get(Integer.parseInt(str) - 1));
            } else {
                System.out.println(map.get(str));
            }
        }
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
