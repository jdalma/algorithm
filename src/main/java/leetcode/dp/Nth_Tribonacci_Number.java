package leetcode.dp;

class Nth_Tribonacci_Number {
    public int tribonacci(int n) {
        int first = 0;
        int second = 1;
        int third = 1;
        if(n == 0) return 0;
        else if(n == 1 || n == 2) return 1;
        else{
            for(int i = 3 ; i < n ; i++){
                int result = first + second + third;
                first = second;
                second = third;
                third = result;
            }
        }
        return first + second + third;
    }
}