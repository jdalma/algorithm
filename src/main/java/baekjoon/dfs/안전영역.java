package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class 안전영역 {
    static int[][] arr;
    static int[][] copyArr;
    static int[] moveX = {-1 , 0 , 1 , 0};
    static int[] moveY = {0 , 1 , 0 , -1};
    static int size;
    static int maxHeight = 0;
    static List<Integer> resultList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        arr = new int[size][size];
        copyArr = new int[size][size];
        for (int i = 0; i < size; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                int value = Integer.parseInt(line[j]);
                arr[i][j] = value;
                maxHeight = Math.max(maxHeight , value);
            }
        }
        for(int height = 1 ; height < maxHeight ; height++){
            arrayCopy(copyArr , arr);
            int areaCount = 0;

            for(int i = 0 ; i < size ; i++){
                for(int j = 0 ; j < size ; j++){
                    if(copyArr[i][j] > height){
                        areaCount++;
                        findArea(i , j , height);
                    }
                }
            }
            resultList.add(areaCount);
        }

//        for(int i = 0 ; i < resultList.size() ; i++){
//            System.out.println( (i + 1) + " : " + resultList.get(i));
//        }
        if(resultList.size() <= 0){
            System.out.println(1);
        }
        else{
            System.out.println(resultList.stream().max(Integer::compareTo).get());
        }
    }

    public static void findArea(int x , int y , int height){
        for(int i = 0 ; i < 4 ; i++){
            int moveXPos = x + moveX[i];
            int moveYPos = y + moveY[i];
            if(moveXPos >= 0 && moveXPos < size && moveYPos >= 0 && moveYPos < size){
                if(copyArr[moveXPos][moveYPos] > height){
                    copyArr[moveXPos][moveYPos] = -1;
                    findArea(moveXPos , moveYPos , height);
                }
            }
        }
    }

    public static void arrayCopy(int[][] a , int[][] b){
        for(int i = 0 ; i < size; i++){
            for(int j = 0 ; j < size ; j++){
                a[i][j] = b[i][j];
            }
        }
    }
}