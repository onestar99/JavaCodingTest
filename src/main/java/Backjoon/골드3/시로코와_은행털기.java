package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 줄 세우기 해놓고
 * 중앙값 선택?
 *
 *
 * n개중에서 k개씩 짝을 만들어서 (조합을 만들어서) 각기 a, b의 Sum을 구해서 곱한 후 최대값 찾기.
 */

public class 시로코와_은행털기 {

    static List<List<TeamMember>> combinations = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 사람의 수
        int n = Integer.parseInt(st.nextToken());
        // 뽑을 인원
        int k = Integer.parseInt(st.nextToken());
        // 힘, 스피드 수치의 합
        int x = Integer.parseInt(st.nextToken());

        int result = Integer.MIN_VALUE;

        List<TeamMember> teams = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int power = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            teams.add(new TeamMember(power, speed));
        }
        generateCombinations(teams, k, 0, new ArrayList<>());

        for (List<TeamMember> team : combinations) {
            int powerSum = 0;
            int speedSum = 0;
            for (TeamMember member : team) {
                powerSum += member.power;
                speedSum += member.speed;
            }
            result = Math.max(result, powerSum * speedSum);
        }
        System.out.println(result);
    }

    static void generateCombinations(List<TeamMember> teams, int k, int start, List<TeamMember> current) {
        if (current.size() == k) {
            combinations.add(new ArrayList<>(current));
            combinations.add(current);
            return;
        }

        for (int i = start; i < teams.size(); i++) {
            current.add(teams.get(i));
            generateCombinations(teams, k, i + 1, current);
            current.remove(current.size() -1);
        }
    }

    static class TeamMember {
        int power;
        int speed;

        public TeamMember(int power, int speed) {
            this.power = power;
            this.speed = speed;
        }
    }
}

