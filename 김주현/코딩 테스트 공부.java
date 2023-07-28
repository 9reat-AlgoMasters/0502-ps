import java.util.*;

class Solution {
    static final int MAX_SIZE = 450;
    static final int EMPTY = -1;
    
    static int N, M;
    static int[][] dp;
    static int[][] problems;
    static int maxCost;
    
    public int solution(int alp, int cop, int[][] p) {
        int n = p.length;
        int m = p[0].length;
        problems = p;
        
        for (int i=0; i<n; i++) {
            N = Math.max(N, p[i][0]);
            M = Math.max(M, p[i][1]);
        }
        
        maxCost = (N - alp) + (M - cop);
        
        dp = new int[MAX_SIZE+1][MAX_SIZE+1];
        
        // System.out.printf("N : %d, M : %d\n", N, M);
        
        for (int i=0; i<=MAX_SIZE; i++) {
            Arrays.fill(dp[i], EMPTY);
        }
        
        return solveDP(alp, cop);
    }
    
    public int solveDP(int x, int y) {
        // System.out.printf("[IN] (%d, %d)\n", x, y);
        if (x>=N && y >= M) {
            // System.out.printf("[OUT - achieved!] (%d, %d)\n", x, y);
            return 0;
        }
        
        if(dp[x][y] != EMPTY) {
            // System.out.printf("[OUT - already searched] (%d, %d)\n", x, y);
            return dp[x][y];
        }
        
        int cost = Integer.MAX_VALUE;
        
        // 1. 1씩 늘리는 것
        if (x+1 < MAX_SIZE && x < N) {
            cost = Math.min(solveDP(x+1, y) + 1, cost);
        }
        if (y+1 < MAX_SIZE && y < M) {
            cost = Math.min(cost, solveDP(x, y+1) + 1);
        }
        
        // 2. 문제를 푸는 것
        for (int[] problem : problems) {
            // 풀 수 있으면 푼다.
            int thisCost;
            if (x >= problem[0] && y >= problem[1]) {
                int nextX = x + problem[2];
                int nextY = y + problem[3];
                if (nextX < MAX_SIZE && nextY < MAX_SIZE) {
                    thisCost = problem[4] + solveDP(x + problem[2], y + problem[3]);
                    cost = Math.min(cost, thisCost);
                }
            }
        }
        
        dp[x][y] = cost;
        // System.out.printf("[OUT - all searched] (%d, %d)\n", x, y);
        return dp[x][y];
    }
}