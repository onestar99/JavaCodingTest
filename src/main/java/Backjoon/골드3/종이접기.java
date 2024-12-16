package Backjoon.골드3;

import java.io.*;
import java.util.*;

public class 종이접기 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        int size = (int) Math.pow(2, k);

        String[] foldSequence = br.readLine().split(" ");
        int h = Integer.parseInt(br.readLine());

        // Initialize the grid with a single hole
        int[][] grid = new int[1][1];
        grid[0][0] = h;

        // Process folds in reverse order
        for (int i = foldSequence.length - 1; i >= 0; i--) {
            String fold = foldSequence[i];
            grid = unfold(grid, fold);
        }

        // Print the resulting grid
        StringBuilder sb = new StringBuilder();
        for (int[] row : grid) {
            for (int cell : row) {
                sb.append(cell).append(" ");
            }
            sb.setLength(sb.length() - 1); // Remove trailing space
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static int[][] unfold(int[][] grid, String fold) {
        int size = grid.length;
        int[][] newGrid = new int[size * 2][size * 2];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (fold.equals("D")) {
                    newGrid[i][j] = grid[i][j];
                    newGrid[size * 2 - 1 - i][j] = (grid[i][j] ^ 1);
                } else if (fold.equals("U")) {
                    newGrid[size * 2 - 1 - i][j] = grid[i][j];
                    newGrid[i][j] = (grid[i][j] ^ 1);
                } else if (fold.equals("R")) {
                    newGrid[i][j] = grid[i][j];
                    newGrid[i][size * 2 - 1 - j] = (grid[i][j] ^ 1);
                } else if (fold.equals("L")) {
                    newGrid[i][size * 2 - 1 - j] = grid[i][j];
                    newGrid[i][j] = (grid[i][j] ^ 1);
                }
            }
        }
        return newGrid;
    }
}
