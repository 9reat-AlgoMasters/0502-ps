class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int len = arrayA.length;
        int gcdA = arrayA[0];
      //arrayA(철수) 카드를 모두 나눌 수 있는 수 중 제일 큰거
        for(int i = 1; i < len; i++){
            gcdA = gcd(gcdA, arrayA[i]);
        }
        //arrayB(영희) 카드를 모두 나눌 수 있는 수 중 제일 큰거
        int gcdB = arrayB[0];
        for(int i = 1; i < len; i++){
            gcdB = gcd(gcdB, arrayB[i]);
        }

      //일단 둘중 큰거 선택
        answer = Math.max(gcdA, gcdB);
        boolean con1 = true, con2 = true;
        for(int i = 0; i < len; i++){
          //영희 카드를 모두 나눌 수 있는데 철수 카드 중 하나라도 나눌 수 있음 => 탈락
            if(con2 && arrayA[i] % gcdB == 0){
                con2 = false;
                answer = gcdA;
            }
          //철수 카드를 모두 나눌 수 있는데 영희 카드 중 하나라도 나눌 수 있음 => 탈락
            if(con1 && arrayB[i] % gcdA == 0){
                con1 = false;
                answer = gcdB;
            }
        }
      //둘 다 탈락이면 0
        if(!con1 && !con2){
            answer = 0;
        }
        return answer;
    }
  //유클리드 호제법을 통한 최대공약수 구하기
    static int gcd(int a, int b){
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
}
