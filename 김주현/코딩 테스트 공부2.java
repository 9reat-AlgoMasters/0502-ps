import java.util.*;

class Solution {
    static final int MAX_SIZE = 180;
    static final int EMPTY = -1;
    static final int ROW = 0;
    static final int COL = 1;
    
    static int N, M;
    static int[][] dp;
    static int[][] dpOver;
    static int[][] problems;
    
    public int solution(int alp, int cop, int[][] p) {
        int n = p.length;
        int m = p[0].length;
        problems = p;
        
        for (int i=0; i<n; i++) {
            N = Math.max(N, p[i][0]);
            M = Math.max(M, p[i][1]);
        }
        
        dpOver = new int[2][];
        dpOver[ROW] = new int[M];  // (maxRow + 1, col) : row 값이 over 되었을때
        Arrays.fill(dpOver[ROW], EMPTY);
        
        dpOver[COL] = new int[N]; // (row, maxCol+1) : col 값이 over 되었을때
        Arrays.fill(dpOver[COL], EMPTY);
        
        dp = new int[MAX_SIZE+1][MAX_SIZE+1];
        // dp = new int[N+1][M+1];
        
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
        if (x < N) {
            cost = Math.min(solveDP(x+1, y) + 1, cost);
        }
        if (y < M) {
            cost = Math.min(cost, solveDP(x, y+1) + 1);
        }
        
        // 2. 문제를 푸는 것
        for (int[] problem : problems) { // 각 문제를 풀고 목표에 도달하는 최소 비용
            // 풀 수 있으면 푼다.
            int thisCost = Integer.MAX_VALUE; // 이번 문제를 풀어서 도달하는 최소 비용
            if (x >= problem[0] && y >= problem[1]) {
                int nextX = x + problem[2];
                int nextY = y + problem[3];
                if (nextX >= N && nextY >= M) {
                    cost = Math.min(cost, problem[4]);
                    continue;
                }
                
                if (nextX >= N) { // row over의 경우
                    thisCost = problem[4] + solveOverDP(nextY, ROW);
                } else if (nextY >= M) { // col over의 경우
                    thisCost = problem[4] + solveOverDP(nextX, COL);
                } else {
                    thisCost = problem[4] + solveDP(x + problem[2], y + problem[3]);
                }
            }
            cost = Math.min(cost, thisCost);
        }
        
        dp[x][y] = cost;
        // System.out.printf("[OUT - all searched] (%d, %d)\n", x, y);
        return dp[x][y];
    }
    
    public int solveOverDP(int n, int side) { // (maxRow + 1, col)
        // System.out.printf("*OVER*[IN] n = %d, s : %d, side = %s\n", n, side, side == ROW ? "ROW" : "COL");
        if (side == ROW && n >= M) return 0;
        if (side == COL && n >= N) return 0;
        
        if (dpOver[side][n] != EMPTY) return dpOver[side][n];
        dpOver[side][n] = Integer.MAX_VALUE;
        
        // 1. row 를 1 증가 시킴
        dpOver[side][n] = Math.min(dpOver[side][n], solveOverDP(n+1, side) + 1);
        
        int req = 1 - side;
        int up = 3 - side;
        // 1, 3이 rowOver 일때 side = 0
        //0, 2 이 colOver일 때 side = 1
        
        // 2. 풀 수 있는 문제를 풀어보기
        for (int[] problem : problems) {
            int thisCost;
            if (n >= problem[req] && problem[up] > 0) {
                int nextY = n + problem[up];
                thisCost = problem[4] + solveOverDP(n + problem[up], side);
                dpOver[side][n] = Math.min(dpOver[side][n], thisCost);
            }
        }
        
        return dpOver[side][n];
    }
}