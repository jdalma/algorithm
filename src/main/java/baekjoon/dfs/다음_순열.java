package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 다음_순열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numberArr = new int[size];
        for(int i = 0 ; i < size ; i++){
            numberArr[i] = Integer.parseInt(st.nextToken());
        }

        if(nextPermutation(numberArr)){
            for(int value : numberArr) System.out.print(value + " ");
        }
        else System.out.println(-1);
    }

    public static boolean nextPermutation(int[] numberArr){
        int firstIndex = numberArr.length - 1;
        int secondIndex = numberArr.length - 1;

        // firstIndex -> 뒤에서 firstIndex - 1이 firstIndex 보다 같거나 큰 경우 찾음
        while(firstIndex > 0 &&  numberArr[firstIndex - 1] >= numberArr[firstIndex]) firstIndex--;
        if(firstIndex <= 0) return false;

        // secondIndex -> 다시 뒤에서 secondIndex가 firstIndex - 1 보다 같거나 큰 경우 찾음
        while(numberArr[firstIndex - 1] > numberArr[secondIndex]) secondIndex--;

        // firstIndex 와 secondIndex 스왑
        int tmpValue = numberArr[firstIndex - 1];
        numberArr[firstIndex - 1] = numberArr[secondIndex];
        numberArr[secondIndex] = tmpValue;

        // firstIndex 부터 끝까지 오름차순 정렬
//        System.out.println("firstIndex - " + firstIndex);
//        System.out.println("secondIndex - " + secondIndex);
        Arrays.sort(numberArr , firstIndex , numberArr.length);
        return true;
    }
}
