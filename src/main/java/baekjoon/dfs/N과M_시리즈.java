package baekjoon.dfs;

import java.io.*;
import java.util.*;

class N과M_시리즈 {
    static int[] nums;
    static int[] prints;
    static boolean[] checked;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numSize = Integer.parseInt(st.nextToken());
        int printSize = Integer.parseInt(st.nextToken());

        checked = new boolean[numSize + 1];
        prints = new int[printSize];

//        NM_1(numSize , printSize , 0);
//        NM_2(numSize , printSize , 0);
//        NM_3(numSize , printSize , 0);
//        NM_4(numSize , printSize , 0);

        nums = new int[numSize];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < numSize ; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

//        NM_5(numSize , printSize , 0);
//        NM_6(numSize , printSize , 0);
//        NM_7(numSize , printSize , 0);
//        NM_8(numSize , printSize , 0);
//        NM_9(numSize , printSize , 0);
//        NM_10(numSize , printSize , 0);
//        NM_11(numSize , printSize , 0);
        NM_12(numSize , printSize , 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void NM_1(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        for(int i = 1 ; i <= numSize ; i++){
            if(!checked[i]){
                prints[printIndex] = i;
                checked[i] = true;
                NM_1(numSize , printSize , printIndex + 1);
                checked[i] = false;
            }
        }
    }

    public static void NM_2(int numSize , int printSize , int printIndex){
        if(printIndex >= printSize){
            print();
            return;
        }
        int start = prints[printIndex] == 0 ? 1 : prints[printIndex - 1];
        for(int i = start; i <= numSize ; i++){
            if(!checked[i]){
                checked[i] = true;
                prints[printIndex] = i;
                NM_2(numSize , printSize , printIndex + 1);
                checked[i] = false;
            }
        }
    }

    public static void NM_3(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        for(int i = 1 ; i <= numSize ; i++){
            prints[printIndex] = i;
            NM_3(numSize , printSize , printIndex + 1);
        }
    }

    public static void NM_4(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        int start = prints[printIndex] == 0 ? 1 : prints[printIndex - 1];
        for(int i = start ; i <= numSize ; i++){
            prints[printIndex] = i;
            NM_4(numSize , printSize , printIndex + 1);
        }
    }

    public static void NM_5(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        for(int i = 0 ; i < numSize ; i++){
            if(!checked[i]){
                prints[printIndex] = nums[i];
                checked[i] = true;
                NM_5(numSize , printSize , printIndex + 1);
                checked[i] = false;
            }
        }
    }

    public static void NM_6(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        int prev = printIndex - 1 >= 0 ? prints[printIndex - 1] : prints[printIndex];
        for(int i = 0 ; i < numSize ; i++){
            if(prev < nums[i]){
                prints[printIndex] = nums[i];
                NM_6(numSize , printSize , printIndex + 1);
            }
        }
    }

    public static void NM_7(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        for(int i = 0 ; i < numSize ; i++){
            prints[printIndex] = nums[i];
            NM_7(numSize , printSize , printIndex + 1);
        }
    }

    public static void NM_8(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        int prev = printIndex - 1 >= 0 ? prints[printIndex - 1] : prints[printIndex];
        for(int i = 0 ; i < numSize ; i++){
            if(nums[i] >= prev){
                prints[printIndex] = nums[i];
                NM_8(numSize , printSize , printIndex + 1);
            }
        }
    }

    public static void NM_9(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        int prev = 0;
        for(int i = 0 ; i < numSize ; i++){
            if(!checked[i] && prev != nums[i]){
                prints[printIndex] = nums[i];
                checked[i] = true;
                prev = nums[i];
                NM_9(numSize , printSize , printIndex + 1);
                checked[i] = false;
            }
        }
    }

    public static void NM_10(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        int prev = 0;
        int printPrev = printIndex - 1 >= 0 ? prints[printIndex - 1] : prints[printIndex];
        for(int i = 0 ; i < numSize ; i++){
            if(printPrev <= nums[i] && !checked[i] && prev < nums[i]){
                prints[printIndex] = nums[i];
                prev = nums[i];
                checked[i] = true;
                NM_10(numSize , printSize , printIndex + 1);
                checked[i] = false;
            }
        }
    }

    public static void NM_11(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        int prev = 0;
        for(int i = 0 ; i < numSize ; i++){
            if(prev != nums[i]){
                prints[printIndex] = nums[i];
                prev = nums[i];
                NM_11(numSize , printSize , printIndex + 1);
            }
        }
    }

    public static void NM_12(int numSize , int printSize , int printIndex){
        if(printIndex == printSize){
            print();
            return;
        }
        int prev = 0;
        int printPrev = printIndex - 1 >= 0 ? prints[printIndex - 1] : prints[printIndex];
        for(int i = 0 ; i < numSize ; i++){
            if(printPrev <= nums[i] && prev != nums[i]){
                prints[printIndex] = nums[i];
                prev = nums[i];
                NM_12(numSize , printSize , printIndex + 1);
            }
        }
    }

    public static void print(){
        for(int printVal : prints){
            sb.append(printVal).append(" ");
        }
        sb.append("\n");
    }
}
