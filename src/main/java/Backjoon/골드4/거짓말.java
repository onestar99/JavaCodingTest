package Backjoon.골드4;

import java.util.*;

/**
 * 유니온 파인드
 */

public class 거짓말 {

    static int[] parent;
    static List<List<Integer>> parties = new ArrayList<>();
    static Set<Integer> truthRoots = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 사람 수
        int m = sc.nextInt(); // 파티 수

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        int t = sc.nextInt(); // 진실을 아는 사람 수
        List<Integer> truthPeople = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            truthPeople.add(sc.nextInt());
        }

        for (int i = 0; i < m; i++) {
            int cnt = sc.nextInt();
            List<Integer> party = new ArrayList<>();
            for (int j = 0; j < cnt; j++) {
                party.add(sc.nextInt());
            }
            parties.add(party);

            // 유니온 연결
            for (int j = 1; j < party.size(); j++) {
                union(party.get(0), party.get(j));
            }
        }

        // 진실을 아는 사람들의 루트 기록
        for (int person : truthPeople) {
            truthRoots.add(find(person));
        }

        int answer = 0;
        for (List<Integer> party : parties) {
            boolean canLie = true;
            for (int person : party) {
                if (truthRoots.contains(find(person))) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) answer++;
        }

        System.out.println(answer);
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra != rb) parent[rb] = ra;
    }
}
