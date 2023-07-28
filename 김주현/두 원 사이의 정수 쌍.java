class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long quad = 0;
        for (int i=1; i<r2; i++) {
            int temp = countPointBetween(r1, r2, i);
            quad += temp;
            // System.out.printf("x=%d --> %d\n", i, temp);
        }
        System.out.println(quad);
        quad += r2 - r1;
        answer = quad * 4;
        return answer;
    }
    
    public int countPointBetween(long r1, long r2, long x) {
        if (x > r1) {
            return (int) Math.floor(Math.sqrt(r2*r2 - x*x));
        }
        return (int) Math.floor(Math.sqrt(r2*r2 - x*x)) - (int) Math.ceil(Math.sqrt(r1*r1 - x*x)) + 1;
    }
}