import java.util.*;

class Solution {
    static final int[][] DIR = {{1,0} ,{0, 1}, {-1, 0}, {0, -1}};
    static final char SEA = 'X';

    static int N, M;
    static char[][] map;
    static boolean[][] visited;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();

        map = new char[N][N];
        for (int i=0; i<N; i++) {
            map[i] = maps[i].toCharArray();
        }

        /*for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }*/
        visited = new boolean[N][M];

        List<Integer> islands = new ArrayList<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (visited[i][j] || map[i][j] == SEA) continue;
                islands.add(bfs(i, j));
            }
        }
        if (islands.isEmpty()) {
            islands.add(-1);
        } else {
            Collections.sort(islands);
        }

        return islands.stream().mapToInt(Integer::intValue).toArray();
    }

    private static Integer bfs(int x, int y) {
        Deque<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));
        visited[x][y] = true;
        int answer = map[x][y] - '0';
//        System.out.println(answer);

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int[] d : DIR) {
                int nextX = now.x + d[0];
                int nextY = now.y + d[1];

                if (isInRange(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] != SEA) {
                    visited[nextX][nextY] = true;
                    answer += map[nextX][nextY] - '0';
                    q.add(new Point(nextX, nextY));
                }
            }
        }

        return answer;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}