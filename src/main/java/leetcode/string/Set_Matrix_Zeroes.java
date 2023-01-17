package leetcode.string;

class Set_Matrix_Zeroes {

    private void setZero(int[][] matrix , int xSize , int ySize , int x , int y){
        for(int i = 0 ; i < xSize ; i++) matrix[i][y] = 0;
        for(int i = 0 ; i < ySize ; i++) matrix[x][i] = 0;
    }

    public int[][] copyArr(int[][] arr){
        int[][] newArr = new int[arr.length][arr[0].length];
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = 0 ; j < arr[0].length ; j++){
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }

    public void setZeroes(int[][] matrix) {
        int xSize = matrix.length;
        int ySize = matrix[0].length;
        int[][] copyMatrix = copyArr(matrix);

        for(int i = 0 ; i < xSize ; i++){
            for(int j = 0 ; j < ySize ; j++){
                if(matrix[i][j] == 0 && copyMatrix[i][j] == 0){
                    setZero(matrix , xSize , ySize , i , j);
                }
            }
        }
    }
}
