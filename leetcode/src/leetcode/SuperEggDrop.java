package leetcode;

/**
 鸡蛋掉落
	你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
	每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
	你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
	每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
	你的目标是确切地知道 F 的值是多少。
	无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
示例 1：
	输入：K = 1, N = 2
	输出：2
	解释：
	鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
	否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
	如果它没碎，那么我们肯定知道 F = 2 。
	因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。	
示例 2：
	输入：K = 2, N = 6
	输出：3
示例 3：
	输入：K = 3, N = 14
	输出：4	
提示：
	1 <= K <= 100
	1 <= N <= 10000
 */
public class SuperEggDrop {
	/*动态规划常规写法，时间复杂度为O(n^3)通不过LeetCode*/
	public int superEggDrop(int K, int N) {
       int[][] cache = new int[N + 1][K + 1];
       for(int i = 1; i <= N; ++i) {
    	   cache[i][1] = i;
       }
       for(int j = 2; j <= K; ++j) {
    	   for(int i = 1; i <= N; ++i) {
    		   if (j > i) {
				cache[i][j] = cache[i][i];
    		   }else {
    			   /*在有j个鸡蛋，i层楼的情况下，在每一层楼做尝试，记录所以楼层中得到的最坏结果最小的值*/
    			   int min = i;    //记录在每一层扔后得到的最佳结果，初始化为层数
    			   for(int x = 1; x <= i; ++ x) {
    				   int max = cache[x - 1][j - 1] + 1;  //蛋破了，接下来在1到x-1层尝试，鸡蛋少了一个
    				   if (max < cache[i - x][j] + 1) {
    					   max = cache[i - x][j] + 1;    //蛋没破，接下来从x+1层到i层尝试，仍然有j个鸡蛋
    				   }
    				   if (min > max) {
    					   min = max;
    				   }
    			   }
    			   cache[i][j] = min;
    		   }
    	   }
       }
       return cache[N][K];
    }
	
	/*计算K个鸡蛋移动m次最多可以查多少层的数据
	 * 假设从n0+1层丢下鸡蛋,
		1,鸡蛋破了
			剩下x-1次机会和k-1个鸡蛋,可以检测n0层楼
		2, 鸡蛋没破
			剩下x-1次机会和k个鸡蛋,可以检测n1层楼
    	那么 临界值层数F在[1,n0+n1+1]中的任何一个值,都都能被检测出来
	归纳的状态转移方程式为:f(x,k) = f(x-1,k-1)+f(x-1,k)+1,即x次移动的函数值可以由x-1的结果推导*/
	public int superEggDrop1(int K, int N) {
		if (K < 1) {
			return 0;
		}
		if (N == 1) {
			return 1;
		}
		int[] cache = new int[K + 1];
		int m = 1;       //总共扔的次数
		for(int i = 1; i <= K; ++ i) {  //只能扔一次的情况下，无论有多少个鸡蛋都只能检测一层
			cache[i] = 1;
		}
		while(cache[K] < N) {
			for(int i = K; i > 0; -- i) {
				cache[i] = cache[i] + cache[i - 1] + 1;//等号左边的是总共可以扔m次时可测试的层数，右边是可以扔m-1次时的层数
			}
			++ m;
		}
		return m;
	}

	public static void main(String[] args) {
		System.out.println(new SuperEggDrop().superEggDrop1(3, 14));
	}

}
