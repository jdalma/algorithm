package baekjoon.structure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 덱_구현하기 {
    static int rear = 0;
    static final int INIT_SIZE = 11;
    static int[] intArr;
    static StringBuilder resultString = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        intArr = new int[INIT_SIZE];
        int size = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < size; i++){
            String command = br.readLine();
            if(0 == command.indexOf("push_f")) push_front(Integer.parseInt(command.split(" ")[1]));
            else if(0 == command.indexOf("push_b")) push_back(Integer.parseInt(command.split(" ")[1]));
            else if(0 == command.indexOf("pop_f")) pop_front();
            else if(0 == command.indexOf("pop_b")) pop_back();
            else if(0 == command.indexOf("s")) size();
            else if(0 == command.indexOf("e")) empty();
            else if(0 == command.indexOf("f")) front();
            else if(0 == command.indexOf("b")) back();
        }
        System.out.println(resultString.toString());
    }
    public static void pushArrayShift(){
        increaseArrSize();
        for(int i = rear ; i > 0; i--){
            intArr[i] = intArr[i - 1];
        }
    }
    public static void pullArrayShift(){
        for(int i = 0 ; i < rear ; i++){
            intArr[i] = intArr[i + 1];
        }
    }
    public static void increaseArrSize(){
        if(rear == intArr.length - 1) {
            int[] tmpArr = Arrays.copyOf(intArr , intArr.length);
            intArr = new int[intArr.length * 2];
            System.arraycopy(tmpArr, 0, intArr, 0, tmpArr.length);
        }
    }
    public static void push_front(int x){
        if(intArr[0] != 0) pushArrayShift();
        intArr[0] = x;
        rear++;
    }
    public static void push_back(int x){
        increaseArrSize();
        intArr[rear] = x;
        rear++;
    }
    public static void pop_front(){
        if(intArr[0] == 0) resultString.append("-1\n");
        else{
            resultString.append(intArr[0]).append("\n");
            pullArrayShift();
            rear--;
        }
    }
    public static void pop_back(){
        if(rear - 1 < 0 || intArr[rear - 1] == 0) resultString.append("-1\n");
        else{
            resultString.append(intArr[rear - 1]).append("\n");
            intArr[rear - 1] = 0;
            rear--;
        }
    }
    public static void size(){
        resultString.append(rear).append("\n");
    }
    public static void empty(){
        if(rear - 1 < 0) resultString.append("1\n");
        else resultString.append("0\n");
    }
    public static void front(){
        if(intArr[0] == 0) resultString.append("-1\n");
        else resultString.append(intArr[0]).append("\n");
    }
    public static void back(){
        if(rear - 1 < 0 || intArr[rear - 1] == 0) resultString.append("-1\n");
        else resultString.append(intArr[rear - 1]).append("\n");
    }

    public static void printArr(){
        for(int i : intArr){
            System.out.print(i + " ");
        }
        System.out.println(" - " + rear);
        System.out.println();
    }
}