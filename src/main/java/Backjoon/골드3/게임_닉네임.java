package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class 게임_닉네임 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashSet<String> usedPrefixes = new HashSet<>();
        HashMap<String, Integer> nicknameCount = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String nickname = br.readLine();
            String temp = null;

            int count = nicknameCount.getOrDefault(nickname, 0);
            nicknameCount.put(nickname, count + 1);

            // 접두사 찾기
            for (int j = 1; j <= nickname.length(); j++) {
                String prefix = nickname.substring(0, j);
                if (!usedPrefixes.contains(prefix)) {
                    temp = prefix;
                    break;
                }
            }

            // 접두사로 사용 불가하면 기존 닉네임 or 기존 닉네임 + 숫자
            if (temp == null) {
                if (count == 0) {
                    temp = nickname;
                } else {
                    temp = nickname + (count + 1);
                }
            }

            for (int j = 1; j <= nickname.length(); j++) {
                usedPrefixes.add(nickname.substring(0, j));
            }
            sb.append(temp).append("\n");
        }
        System.out.println(sb.toString().trim());
    }
}
