package Backjoon.골드3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 전형적인 시뮬레이션 구현 문제
 * K년이 지난 후 상도의 땅에 살아있는 나무의 개수를 구하는 프로그램을 작성
 */
public class 나무_재태크 {

    static int N; // 땅 크기
    static int M; // 나무 개수
    static int K; // K년
    static int[][] ground; // 땅의 양분 값
    static int[][] A; // S2D2 양분 추가 값
    static List<Tree> trees; // 나무 리스트
    static Queue<Tree> deadTrees; // 죽은 나무 저장용 큐
    static Queue<Tree> newTrees; // 새로운 나무 저장용 큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ground = new int[N][N];
        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ground[i][j] = 5;
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        trees = new ArrayList<>();
        deadTrees = new LinkedList<>();
        newTrees = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, age));
        }

        for (int i = 0; i < K; i++) {
            simulation();
        }

        System.out.println(trees.size());
    }

    private static void simulation() {
        spring();
        summer();
        fall();
        winter();
    }

    private static void spring() {
        Collections.sort(trees, (a, b) -> a.age - b.age);
        List<Tree> aliveTrees = new ArrayList<>();
        
        for (Tree tree : trees) {
            if (ground[tree.x][tree.y] >= tree.age) {
                ground[tree.x][tree.y] -= tree.age;
                tree.age++;
                aliveTrees.add(tree);
            } else {
                deadTrees.add(tree);
            }
        }
        trees = aliveTrees;
    }

    private static void summer() {
        while (!deadTrees.isEmpty()) {
            Tree tree = deadTrees.poll();
            ground[tree.x][tree.y] += tree.age / 2;
        }
    }

    private static void fall() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (Tree tree : trees) {
            if (tree.age % 5 == 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = tree.x + dx[i];
                    int ny = tree.y + dy[i];
                    
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        newTrees.add(new Tree(nx, ny, 1));
                    }
                }
            }
        }
        
        while (!newTrees.isEmpty()) {
            trees.add(newTrees.poll());
        }
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j] += A[i][j];
            }
        }

    }


    private static class Tree {
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}
