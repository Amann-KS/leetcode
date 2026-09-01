class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = -1, startY = -1;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) Arrays.fill(row, -1);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);
                if (ch == 'S') {
                    startX = i;
                    startY = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        
        int targetMask = (1 << litterCount) - 1;
        if (targetMask == 0) return 0;
        
        int[][][] bestEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }
        
    
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY, 0, energy, 0});
        bestEnergy[startX][startY][0] = energy;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0], c = curr[1], mask = curr[2], e = curr[3], steps = curr[4];
            
            if (e <= 0 && classroom[r].charAt(c) != 'R') continue;
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') continue;
                
                int nextMask = mask;
                if (classroom[nr].charAt(nc) == 'L') {
                    nextMask |= (1 << litterId[nr][nc]);
                }
                
                int nextEnergy = e - 1;
                if (classroom[nr].charAt(nc) == 'R') {
                    nextEnergy = energy;
                }
                
                if (nextMask == targetMask) {
                    return steps + 1;
                }
                
                if (nextEnergy > bestEnergy[nr][nc][nextMask]) {
                    bestEnergy[nr][nc][nextMask] = nextEnergy;
                    q.offer(new int[]{nr, nc, nextMask, nextEnergy, steps + 1});
                }
            }
        }
        
        return -1;
    }
}