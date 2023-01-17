package leetcode.dp;

/**
 *- `[0,0]`에서 **우측과 아래쪽으로만 이동할 수 있다.**
 * - 첫번째 행과 열을 0 또는 1로 채운다.(이동할 수 있는곳인지 판단)
 * - **그 후 `[1,1]` 부터 현재 위치의 `[row - 1][col] + [row][col - 1]`을 더해 나간다.**
 *   - *미리 채워놓은 첫번째 행과 열을 재사용하는것이다.*
 */

class Unique_Paths_2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) return 0;

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        obstacleGrid[0][0] = 1;
        for(int i = 1 ; i < row ; i++){
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        for(int i = 1 ; i < col ; i++){
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        for(int i = 1 ; i < row ; i++){
            for(int j = 1 ; j < col ; j++){
                if(obstacleGrid[i][j] == 0){
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                }
                else obstacleGrid[i][j] = 0;
            }
        }

        return obstacleGrid[row - 1][col - 1];
    }
}
