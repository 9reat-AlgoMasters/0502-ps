class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        // 1. arrayA gcd로 검사
        int gA = gcdOfArray(arrayA);
        boolean isDisjointToA = isDisjointToArray(arrayB, gA);
        
        // 2. arrayB gcd로 검사
        int gB = gcdOfArray(arrayB);
        boolean isDisjointToB = isDisjointToArray(arrayA, gB);
        
        if (!isDisjointToA && !isDisjointToB) {
            return 0;
        } else if (isDisjointToA && isDisjointToB) {
            return Math.max(gA, gB);
        } else {
            return isDisjointToA ? gA : gB;
        }
    }
    
    public int gcdOfArray(int[] arr) {
        int g = arr[0];
        for (int n : arr) {
            g = gcd(g, n);
        }
        return g;
    }
    
    public boolean isDisjointToArray(int[] arr, int n) {
        if (n==1) return false;
        for (int num : arr) {
            if (num % n == 0) return false;
        }
        return true;
    }
    
    public int gcd(int big, int small) {
        return small == 0 ? big : gcd(small, big%small);
    }
}