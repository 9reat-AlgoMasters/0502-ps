class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
      //1부터 큰 원의 반지름까지의 결과를 4배하면 됨
        for(int i = 1; i <= r2; i++){
            double rtval1;
            if(i <= r1){
                //직각 삼각형 공식으로 1사분면 내 x좌표가 정수인 두 원 위의 점의 y좌표를 구할 수 있음
              //두 y좌표 사이의 정수 개수 구하기
                long val1 = 1L * r1 * r1 - 1L * i * i;
                rtval1 = Math.sqrt(val1);
                if(rtval1 % 1 != 0){
                    rtval1 = Math.ceil(rtval1);
                }
            }
            else{
                rtval1 = 0;
            }
            
            long val2 = 1L * r2 * r2 - 1L * i * i;
            double rtval2 = Math.sqrt(val2);
            if(rtval2 % 1 != 0){
                rtval2 = Math.floor(rtval2);
            }
            
            answer += rtval2 - rtval1 + 1;
        }
        
        return 4 * answer;
    }
}
