package leetcode.array;

class Rotate_Image {
    public void rotate(int[][] matrix) {
        int size = matrix.length;
        int[][] copy = new int[size][size];
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                copy[i][j] = matrix[i][j];
            }
        }

        String format = "(%s,%s) %s -> (%s,%s) %s";
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                // System.out.println(String.format(format , size - j - 1, i , matrix[size - j - 1][i] , i , j , copy[i][j]));
                matrix[i][j] = copy[size - j - 1][i];
            }
        }
    }
}