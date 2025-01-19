package Backjoon.골드3;

import java.util.*;
import java.io.*;

public class 폴더_정리 {

    static Map<String, List<String>> folderMap = new HashMap<>();
    static Map<String, Integer> fileCountMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            String P = st.nextToken();
            String F = st.nextToken();
            int C = Integer.parseInt(st.nextToken());

            folderMap.putIfAbsent(P, new ArrayList<>());
            folderMap.get(P).add(F);

            if (C == 0) { // 파일이면 개수 체크
                fileCountMap.put(F, fileCountMap.getOrDefault(F, 0) + 1);
            }

        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            String query = br.readLine();
            countQuery(query.substring(query.lastIndexOf('/') + 1));
        }

    }

    private static void countQuery(String folder) {
        Set<String> uniqueFiles = new HashSet<>();
        int[] result = dfs(folder, uniqueFiles);

        int folderCount = result[0];
        int totalFileCount = result[1];

        System.out.println(folderCount + " " + totalFileCount);
    }

    static int[] dfs(String folder, Set<String> uniqueFiles) {
        if (!folderMap.containsKey(folder)) return new int[]{0, 0};

        int folderCount = 0;
        int totalFileCount = 0;

        for (String item : folderMap.get(folder)) {
            if (fileCountMap.containsKey(item)) {
                // 파일일 경우
                uniqueFiles.add(item);
                totalFileCount += fileCountMap.get(item);
            } else {
                // 폴더일 경우
                int[] subResult = dfs(item, uniqueFiles);
                folderCount += subResult[0] + 1; // 하위 폴더 + 현재 폴더
                totalFileCount += subResult[1];
            }
        }

        return new int[]{folderCount, totalFileCount};
}
}
