class Solution {
    static int N;
    static int[] info;
    static int[] result;
    static int[] answer;
    static int maxDiff = 0;
    
    public int[] solution(int n, int[] i) {
        N = n;
        info = i;
        result = new int[11];
        answer = new int[11];
        dfs(0, n, 0, 0);
        
        if (maxDiff == 0) {
            return new int[]{-1};
        } else {
            return answer;
        }
    }
    
    public void dfs(int cnt, int arrow, int score1, int score2) {
        if (cnt == 11) {
            int lastValue = result[10];
            result[10] += arrow;
            
            if (score2 - score1 > maxDiff) {
                maxDiff = score2 - score1;
                System.arraycopy(result, 0, answer, 0, 11);
            } else if (score2 - score1 == maxDiff) {
                for (int i=10; i>=0; i--) {
                    if (result[i] == answer[i]) continue;
                    if (result[i] > answer[i]) {
                        System.arraycopy(result, 0, answer, 0, 11);
                        break;
                    } else break;
                }
            }
            
            result[10] = lastValue;
            return;
        }
        
        int targetScore = 10 - cnt;
        
        // 1. 과녁에 대한 점수를 가져가는 경우
        if (arrow > info[cnt]) { // 상대가 쏜 화살보다 더 많이 가지고 있어야 한다.
            result[cnt] = info[cnt]+1;
            dfs(cnt+1, arrow - info[cnt] - 1, score1, score2+targetScore);
            result[cnt] = 0;
        }
        
        // 2. 과녁에 대한 점수를 가져가지 않는 경우
        dfs(cnt+1, arrow, 
            info[cnt] > 0 ? score1 + targetScore : score1, 
            score2);
        
    }
}