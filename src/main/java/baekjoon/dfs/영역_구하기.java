package baekjoon.dfs;

import java.util.*;

public class 영역_구하기 {
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static int m , n;
    static List<Integer> resultList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        int[][] arr = new int[m][n];

        int count = sc.nextInt();
        for(int i = 0 ; i < count ; i++){
            int ldX = sc.nextInt();
            int ldY = sc.nextInt();
            int ruX = sc.nextInt();
            int ruY = sc.nextInt();
            int calcLdX = ldY;
            int calcLdY = ldX;
            int calcRuX = ruY - 1;
            int calcRuY = ruX - 1;
            for(int x = 0 ; x < m ; x++){
                for(int y = 0 ; y < n ; y++){
                    if(x >= calcLdX && x <= calcRuX){
                        if(y >= calcLdY && y <= calcRuY){
                            arr[x][y] = 2;
                        }
                    }
                }
            }
        }
        for(int x = 0 ; x < m ; x++){
            for(int y = 0 ; y < n ; y++){
                if(arr[x][y] == 0){
                    arr[x][y] = 1;
                    resultList.add(solution(arr , x , y ,1));
                }
            }
        }
//        for(int x = 0 ; x < m ; x++){
//            for(int y = 0 ; y < n ; y++){
//                System.out.print(arr[x][y] + " ");
//            }
//            System.out.println();
//        }
        Collections.sort(resultList);
        System.out.println(resultList.size());
        for(int value : resultList){
            System.out.print(value + " ");
        }
    }

    public static int solution(int[][] arr , int x , int y , int count){
//        System.out.println(x + " " + y + " " + count);
        for(int i = 0 ; i < 4 ; i++){
            int moveXpos = x + moveX[i];
            int moveYpos = y + moveY[i];
            if(moveXpos >= 0 && moveYpos >= 0 && moveXpos < m && moveYpos < n){
                if(arr[moveXpos][moveYpos] == 0){
                    arr[moveXpos][moveYpos] = 1;
                    count = solution(arr , moveXpos , moveYpos , count + 1);
                }
            }
        }
        return count;
    }
}
