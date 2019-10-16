package leetcode;

/**
 搜索二维矩阵 II
 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
	每行的元素从左到右升序排列。
	每列的元素从上到下升序排列。

示例:
现有矩阵 matrix 如下：
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。
给定 target = 20，返回 false。
 */
public class SearchMatrix {
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if (0 == matrix.length || 0 == matrix[0].length) {
			return false;
		}
		return function(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }
	/*思路：
	 * 定位到右上角，大于目标则j减一，小于目标则i加一*/
	public boolean function1(int[][] matrix, int target) {
		if(matrix.length ==0) return false;
			int i = 0;
	        int j = matrix[0].length - 1;
	        while( i < matrix.length && j >= 0){
	            if(matrix[i][j] == target){
	                return true;
	            }else if(matrix[i][j] > target) {
	                j--;
	            }else if(matrix[i][j] < target) {
	                i++;
	            }
	        }
	        return false;
	    }
	
	/*思路：
	 * 沿对角线查找第一个不小于目标的数，如果该数不等于目标则已改数与上一个对角线上的数将矩阵分成4个部分，
	 * 其中左上角的数据小于目标，右下角的数据大于目标，之后以迭代的方式对左下角和右上角的数据进行查找*/
	public boolean function(int[][] matrix, int target, int xStart, int xEnd, int yStart, int yEnd) {
		//如果只有一行就直接遍历
		if (xStart == xEnd) {  
			for(int i = yStart; i <= yEnd; ++i) {
				if (target == matrix[xStart][i]) {
					return true;
				}
			}
			return false;
		}
		if (yStart == yEnd) {
			for(int i = xStart; i <= xEnd; ++i) {
				if (target == matrix[i][yStart]) {
					return true;
				}
			}
			return false;
		}
		
		int i = xStart;
		int j = yStart;
		while(i < xEnd && j < yEnd && matrix[i][j] < target) {  //沿对角线找到第一个不比目标小的数据
			++ i;
			++ j;
		}
		if (matrix[i][j] == target) {
			return true;
		}
		if (matrix[i][j] > target) {
			if (i == xStart && j == yStart) {  //避免目标小于最小值进入死循环
				return false;
			}
			if (function(matrix, target, i, xEnd, yStart, j - 1)) {
				return true;
			}
			
			if (function(matrix, target, xStart, i - 1, j, yEnd)) {
				return true;
			}
		}
		if (matrix[i][j] < target) {  //退出查找循环的时候如果最后一个数据小于目标证明至少有一个到边了
			if (i == xEnd && j != yEnd) {
				if (function(matrix, target, xStart, xEnd, j + 1, yEnd)) {
					return true;
				}
			}
			if (j == yEnd && i != xEnd) {
				if (function(matrix, target, i + 1, xEnd, yStart, yEnd)) {
					return true;
				}
			}
			return false;
		}
		
		return false;
	}
	public static void main(String[] args) {
		int[][] matrix = new int[][] {
			{1,   4,  7, 11, 15},
			  {2,   5,  8, 12, 19},
			  {3,   6,  9, 16, 22},
			  {10, 13, 14, 17, 24},
			  {18, 21, 23, 26, 30}
		};
		/*int[][] matrix = new int[][] {
			{1,   4},
			  {2,   5}
		};*/
		/*int[][] matrix = new int[][] {
			{1,2,3,4,5},
			{6,7,8,9,10},
			{11,12,13,14,15},
			{16,17,18,19,20},
			{21,22,23,24,25}
		};*/
//		System.out.println(new SearchMatrix().searchMatrix(matrix, 3));
		for(int i = 0; i <= 31; ++i) {
			System.out.println(i + "\t" + new SearchMatrix().searchMatrix(matrix, i));
		}
	}

}
