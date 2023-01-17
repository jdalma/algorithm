package baekjoon.structure.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 배열로 구현 시 시간초과

class implHeap{
    int[] arr;
    public implHeap(){
        arr = new int[100002];
    }

    public int get(){
        // 최대 노드를 반환 후 비운다.
        // 가장 마지막 노드를 루트노드로 옮긴 후
        // 자신보다 크고 왼쪽 , 오른쪽 자식 중 제일 큰 값과 바꿔나간다.
        int rootValue = arr[1];
        int lastChildIndex = findLastChild();
        arr[1] = arr[lastChildIndex];
        arr[lastChildIndex] = 0;
        moveBottom(1);

        return rootValue;
    }
    public void moveBottom(int rootIndex){
        int maxPos = 1;
        int rootValue = arr[rootIndex];
        int maxValue = 0;
        while(maxPos * 2 < arr.length){
            maxPos = maxPos * 2;
            if(maxPos + 1 < arr.length && arr[maxPos] < arr[maxPos + 1]){
                maxValue = arr[maxPos + 1];
                maxPos += 1;
            }
            if(rootValue > maxValue) break;
            swap(rootIndex , maxPos);
            rootIndex = maxPos;
        }
    }
    public int findLastChild(){
        int lastIndex = 1;
        for(int i = 1 ; i < arr.length ; i++){
            if(arr[i] != 0){
                lastIndex = i;
                break;
            }
        }
        return lastIndex;
    }

    public void push(int value){
        int pushIndex = 0;
        for(int i = 1 ; i < arr.length ; i++){
            if(arr[i] == 0) {
                arr[i] = value;
                pushIndex = i;
                break;
            }
        }
        moveTop(pushIndex);
    }

    public void moveTop(int pushIndex){
        // 2로 나눠 떨어진다면 왼쪽 자식 , 아니라면 오른쪽 자식
        int parentIndex = 0;
        while(true){
            if(pushIndex % 2 != 0) parentIndex = (pushIndex - 1) / 2;
            else parentIndex = pushIndex / 2;
            if(parentIndex > 0){
                if(arr[parentIndex] < arr[pushIndex]){
                    swap(parentIndex , pushIndex);
                }
                pushIndex = parentIndex;
            }
            else break;
        }
    }

    public void swap(int firstIndex , int secondIndex){
        int tmp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = tmp;
    }
}


class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        implHeap heap = new implHeap();
//        System.out.println("---------------------------------");
        for(int i = 0 ; i < size ; i++){
            int value = Integer.parseInt(br.readLine());
            if(value == 0) result.append(heap.get()).append("\n");
            else heap.push(value);
        }
        System.out.println(result.toString());
    }
}
 */

class implHeap{
    List<Integer> arr;
    public implHeap(){
        arr = new ArrayList<>();
        arr.add(999999);
    }

    public int get(){
        if(arr.size() - 1 < 1) return 0;

        int rootValue = arr.get(1);
        arr.set(1 , arr.get(arr.size() - 1));
        moveBottom();
        arr.remove(arr.size() - 1);
        return rootValue;
    }
    public void moveBottom(){
        int rootPos = 1 , rootValue = 0;
        int maxPos = 1 , maxValue = 0;
        while(maxPos * 2 < arr.size()){
            rootValue = arr.get(rootPos);
            maxPos *= 2;
//          최소 힙 - if(maxPos + 1 < arr.size() && arr.get(maxPos) > arr.get(maxPos + 1)) maxPos += 1;
            if(maxPos + 1 < arr.size() && arr.get(maxPos) < arr.get(maxPos + 1)) maxPos += 1;
            maxValue = arr.get(maxPos);
//          if(rootValue < maxValue) break;
            if(rootValue > maxValue) break;
            swap(rootPos , maxPos);
            rootPos = maxPos;
        }
    }

    public void push(int value){
        arr.add(value);
        moveTop();
    }

    public void moveTop(){
        int lastPos = arr.size() - 1;
//      최소 힙 - while(lastPos > 1 && arr.get(lastPos / 2) > arr.get(lastPos)){
        while(lastPos > 1 && arr.get(lastPos / 2) < arr.get(lastPos)){
            swap(lastPos / 2 , lastPos);
            lastPos = lastPos / 2;
        }
    }

    public void swap(int firstIndex , int secondIndex){
        int tmp = arr.get(firstIndex);
        arr.set(firstIndex , arr.get(secondIndex));
        arr.set(secondIndex , tmp);
    }
}


class 최대_최소_힙_구현하기 {
    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        implHeap heap = new implHeap();
//        System.out.println("---------------------------------");
        for(int i = 0 ; i < size ; i++){
            int value = Integer.parseInt(br.readLine());
            if(value == 0) result.append(heap.get()).append("\n");
            else heap.push(value);
        }
        System.out.println(result.toString());
    }
}