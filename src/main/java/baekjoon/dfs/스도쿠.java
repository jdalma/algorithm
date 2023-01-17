package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class 스도쿠 {
    static class Position{
        int x;
        int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[][] sudoku = new int[9][9];
    static List<Position> zeroList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0 ; i < 9 ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < 9 ; j++){
                int value = Integer.parseInt(st.nextToken());
//                int value = 0;
                sudoku[i][j] = value;
                if(value == 0) zeroList.add(new Position(i , j));
            }
        }
        recursive(0);

    }

    public static void recursive(int index){
        if(index == zeroList.size()){
            System.out.println("@@@@@@@@@@@@@@");
            for(int i = 0 ; i < 9 ; i++){
                for(int j = 0 ; j < 9 ; j++){
                    System.out.print(sudoku[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
//            return;
        }
        Position pos = zeroList.get(index);
        int posX = pos.x;
        int posY = pos.y;
        for(int i = 1 ; i < 10 ; i++){
            if(findWidth(posX , posY , i) && findVertical(posX , posY , i) && findQuad(posX , posY , i)){
                sudoku[posX][posY] = i;
                recursive(  index + 1);
                sudoku[posX][posY] = 0;
            }
        }
    }


    public static boolean findWidth(int x , int y , int val){
        for(int i = 0 ; i < 9 ; i++){
            if(sudoku[x][i] == val) return false;
        }
        return true;
    }

    public static boolean findVertical(int x , int y , int val){
        for(int i = 0 ; i < 9 ; i++){
            if(sudoku[i][y] == val) return false;
        }
        return true;
    }

    public static boolean findQuad(int x , int y , int val){
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for(int i = startX ; i < startX + 3 ; i++){
            for(int j = startY ; j < startY + 3 ; j++){
                if(sudoku[i][j] == val) return false;
            }
        }
        return true;
    }
}