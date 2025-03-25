package Backjoon.골드3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 신기한_키보드 {

    // 알파벳 그룹 정보를 담을 클래스
    static class Group {
        int left;   // 해당 알파벳이 처음 등장하는 인덱스
        int right;  // 해당 알파벳이 마지막에 등장하는 인덱스
        int count;  // 해당 알파벳의 등장 횟수

        public Group(int left, int right, int count) {
            this.left = left;
            this.right = right;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] lcd = br.readLine().toCharArray();
        int n = lcd.length;

        // 각 알파벳별 그룹 정보 초기화 (해당 알파벳이 존재하면 left, right, count 업데이트)
        Group[] groups = new Group[26];
        for (int i = 0; i < n; i++) {
            int idx = lcd[i] - 'a';
            if (groups[idx] == null) {
                groups[idx] = new Group(i, i, 1);
            } else {
                groups[idx].right = i;
                groups[idx].count++;
            }
        }

        // 알파벳 순으로 그룹을 모은 리스트 (존재하는 알파벳만)
        List<Group> groupList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (groups[i] != null) {
                groupList.add(groups[i]);
            }
        }

        // dp[i][0] : i번째 그룹 처리 후, 커서가 해당 그룹의 왼쪽 끝에 있을 때 최소 키 누름 수
        // dp[i][1] : i번째 그룹 처리 후, 커서가 해당 그룹의 오른쪽 끝에 있을 때 최소 키 누름 수
        int m = groupList.size();
        long[][] dp = new long[m][2];

        // 초기 상태: 시작 커서는 인덱스 0
        Group first = groupList.get(0);
        int L = first.left;
        int R = first.right;
        // 두 가지 방법 중 최소값 선택
        // 만약 커서가 오른쪽 끝으로 이동한 뒤 왼쪽 끝으로 이동하는 경우와,
        // 왼쪽 끝으로 이동한 후 오른쪽 끝까지 갔다가 다시 왼쪽으로 돌아오는 경우
        dp[0][0] = Math.min(Math.abs(0 - R) + (R - L), Math.abs(0 - L) + 2 * (R - L)) + first.count;
        dp[0][1] = Math.min(Math.abs(0 - L) + (R - L), Math.abs(0 - R) + 2 * (R - L)) + first.count;

        // DP 진행: 이전 그룹의 종료 위치에서 현재 그룹으로 이동
        for (int i = 1; i < m; i++) {
            Group cur = groupList.get(i);
            L = cur.left;
            R = cur.right;
            long range = R - L; // 그룹 내 이동 거리
            dp[i][0] = Math.min(
                    dp[i - 1][0] + Math.min(Math.abs(groupList.get(i - 1).left - R) + range, Math.abs(groupList.get(i - 1).left - L) + 2 * range),
                    dp[i - 1][1] + Math.min(Math.abs(groupList.get(i - 1).right - R) + range, Math.abs(groupList.get(i - 1).right - L) + 2 * range)
            ) + cur.count;

            dp[i][1] = Math.min(
                    dp[i - 1][0] + Math.min(Math.abs(groupList.get(i - 1).left - L) + range, Math.abs(groupList.get(i - 1).left - R) + 2 * range),
                    dp[i - 1][1] + Math.min(Math.abs(groupList.get(i - 1).right - L) + range, Math.abs(groupList.get(i - 1).right - R) + 2 * range)
            ) + cur.count;
        }

        // 최종 결과: 마지막 그룹을 처리한 후 최소 키 입력 수
        long answer = Math.min(dp[m - 1][0], dp[m - 1][1]);
        System.out.println(answer);
    }
}
