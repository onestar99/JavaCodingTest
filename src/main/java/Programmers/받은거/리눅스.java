package Programmers.받은거;

import java.util.*;

public class 리눅스 {

    // 디렉토리의 원리인 트리셋으로 구현하면 쉬운 문제일듯?
    // 리스트에서 직접 forEach 쓸 수 있다는거 처음 암ㅋㅋ; 편하네
    private static Set<String> directories;

    // 디렉토리 생성
    private static void mkdir(String path) {
        // 디렉토리가 이미 존재하거나 상위 디렉토리가 없으면 생성하지 않음
        if (directories.contains(path) || !directories.contains(getParentDirectory(path))) {
            return;
        }
        directories.add(path); // 디렉토리 추가
    }

    // 상위 디렉토리 반환
    private static String getParentDirectory(String path) {
        // 마지막 슬래쉬 인덱스 찾은 뒤 루트 이면 루트로 반환, 아니라면 상위 디렉토리 반환
        int lastSlash = path.lastIndexOf("/");
        return (lastSlash == 0) ? "/" : path.substring(0, lastSlash); // 루트 디렉토리인 경우 "/" 반환
    }

    // 디렉토리 삭제 (하위 디렉토리 포함)
    private static void rm(String path) {
        // 주어진 경로와 그 하위 경로를 모두 삭제
        directories.removeIf(dir -> dir.equals(path) || dir.startsWith(path + "/"));
    }

    // 디렉토리 복사
    private static void cp(String source, String dest) {
        if (directories.contains(source) && directories.contains(dest)) {
            String parentSource = getParentDirectory(source); // 상위 소스
            List<String> toBeCopied = new ArrayList<>();

            // 디렉토리 모두 조사
            for (String dir : directories) {
                if (dir.equals(source) || dir.startsWith(source + "/")) {
                    String relativePath;
                    if (parentSource.equals("/")) { // 루트이면 맨 앞의 / 제거
                        relativePath = dir.substring(1); // 맨 앞의 "/" 제거
                    } else {
                        relativePath = dir.substring(parentSource.length() + 1);
                    }
                    // dest가 "/"인지 확인하여 슬래시 처리 (목적지가 루트이면 슬래시가 자꾸 중복되서 넣어버림)
                    String newDir = dest.equals("/") ? "/" + relativePath : dest + "/" + relativePath;
                    toBeCopied.add(newDir);
                }
            }
            directories.addAll(toBeCopied);
        }
    }



    public static void main(String[] args) {

        List<String> initialDirs = Arrays.asList("/", "/hello", "/hello/tmp", "/root", "/root/abcd", "/root/abcd/etc", "/root/abcd/hello");
        List<String> commands = Arrays.asList("mkdir /root/tmp", "mkdir /root1", "mkdir /root2", "mkdir /root3", "cp /hello /root/tmp", "rm /hello", "mkdir /root1/a", "mkdir /root1/b", "mkdir /root1/c");

//        List<String> initialDirs = Arrays.asList("/");
//        List<String> commands = Arrays.asList("mkdir /a", "mkdir /a/b", "mkdir /a/b/c", "cp /a/b /", "rm /a/b/c");

        directories = new TreeSet<>(initialDirs);

        commands.forEach(command -> {
            String[] parts = command.split(" "); // 명령어 부분
            switch (parts[0]) {
                case "mkdir": // mkdir 처리
                    mkdir(parts[1]);
                    break;
                case "rm": // rm 처리
                    rm(parts[1]);
                    break;
                case "cp": // cp 처리
                    cp(parts[1], parts[2]);
                    break;
            }
        });

        // 결과 출력
        List<String> result = new ArrayList<>(directories);
        result.forEach(System.out::println);
    }
}
