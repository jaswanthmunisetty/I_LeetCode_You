class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startR = 0, startC = 0;
        
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) Arrays.fill(row, -1);
        
        int litterCount = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterId[r][c] = litterCount++;
                }
            }
        }

        if (litterCount == 0) return 0;
        int targetMask = (1 << litterCount) - 1;
        int initialMask = 0;

        if (classroom[startR].charAt(startC) == 'L') {
            initialMask |= (1 << litterId[startR][startC]);
        }

        if (initialMask == targetMask) return 0;
        boolean[][][][] visited = new boolean[m][n][energy + 1][1 << litterCount];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{startR, startC, energy, initialMask, 0});
        visited[startR][startC][energy][initialMask] = true;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1], e = current[2], mask = current[3], moves = current[4];

            if (e == 0) continue;

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                    char cell = classroom[nr].charAt(nc);
                    int nextEnergy = (cell == 'R') ? energy : e - 1;
                    int nextMask = mask;

                    if (cell == 'L') {
                        nextMask |= (1 << litterId[nr][nc]);
                    }

                    if (nextMask == targetMask) {
                        return moves + 1;
                    }

                    if (!visited[nr][nc][nextEnergy][nextMask]) {
                        visited[nr][nc][nextEnergy][nextMask] = true;
                        queue.add(new int[]{nr, nc, nextEnergy, nextMask, moves + 1});
                    }
                }
            }
        }

        return -1;
    }
}