import java.util.*;
class Solution {
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static char[][] map;
    static int R, C;
    public int[] solution(String[] maps) {
        R = maps.length;
        C = maps[0].length();
        map = new char[R][C];
        
        for(int i = 0; i < R; i++){
            map[i] = maps[i].toCharArray();
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue();
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] != 'X'){
                  //bfs로 탐색한 무인도 식량 총합을 pq에 저장
                    pq.add(bfs(i, j));
                }
            }
        }
        
        int size = pq.size();
        int[] answer;
        if(size == 0){
            answer = new int[]{-1};
        }else{
            answer = new int[size];
            for(int i = 0; i < size; i++){
                answer[i] = pq.poll();
            }
        }
       
        
        return answer;
    }
    static int bfs(int r, int c){
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        int sum = map[r][c] - '0';
        map[r][c] = 'X';
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            for(int i = 0; i < 4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];
              //지도 범위 내에 있고 X가 아닌 곳만 큐에 저장
                if(checkRange(nr, nc) && map[nr][nc] != 'X'){
                    sum += (map[nr][nc] - '0');
                    map[nr][nc] = 'X';
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return sum;
    }
    static boolean checkRange(int r, int c){
        return r >= 0 && r < R && c >= 0 && c < C; 
    }
}
