package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 암호_만들기 {
    static int[] check;
    static char[] print;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken()); // 출력 문자 개수
        int C = Integer.parseInt(st.nextToken()); // 문자 총 개수
        check = new int[L];
        print = new char[L];
        String[] strArr = br.readLine().split(" ");
        if(strArr.length > 0){
            Arrays.sort(strArr);
            recursivePrint(strArr , 0 , 0);
        }
    }

    public static void recursivePrint(String[] strArr , int count , int index){
        if(count == print.length){
            if(alphabetChk()){
                for(char ch : print){
                    System.out.print(ch);
                }
                System.out.println();
            }
        }
        else{
            for(int i = index ; i < strArr.length ; i++){
                if(check[count] == 0){
                    check[count] = 1;
                    print[count] = strArr[i].toCharArray()[0];
                    recursivePrint(strArr , count + 1 , i + 1);
                    check[count] = 0;
                }
            }
        }
    }

    public static boolean alphabetChk(){
        boolean mo = false;
        int jaCnt = 0;
        // a, e, i, o, u
        for(char ch : print){
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ){
                mo = true;
            }
            else jaCnt++;
        }
        if(mo && jaCnt >= 2){
            return true;
        }
        else return false;
    }
}
