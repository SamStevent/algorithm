package leetcode;

import java.util.Stack;

/**
只出现一次的数字
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：
你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例1：
	输入: [2,2,1]
	输出: 1
	
示例2：
	输入: [4,1,2,1,2]
	输出: 4
*/
public class SingleNumber {
	
	public int solution(int[] nums) {
		return function1(nums, 0, nums.length - 1, false);
    }
	/*同一个数与自己进行异或后值为0*/
	public int function2(int[] nums) {
		if (nums.length <= 0) 
			return 0;
		int result = nums[0];
		for(int i = 1; i < nums.length - 1; ++i) {
			result ^= nums[i];
		}
		return result;
	}
	/*采用快速排序的思路，选第一个元素为基准元素与数组内的数据进行对比，把大于这个数的放右边，小于的数放左边，相等的数会改变hasChange标志，之后用最后一个数覆盖这个数，同时数组长度减一
	 * 如果整个过程中没有遇到相等的数则该数为唯一数，如果有再在长度为单数的那一边进行下一次查询*/
	public int function(int[] nums, int start, int end, boolean hasChange) {
		if (0 == end - start) {    //只有一个数据，则这个数据必定是目标数据
			hasChange = false;
			return nums[start];
		}
		
		int startValue = nums[start];
		int i = start;
		int j = end;
		while (i < j) {
			while(i < j && nums[j] > startValue) {
				--j;
			}
			if (j != start && nums[j] == startValue) {
				hasChange = true;
				nums[j] = nums[end];
				-- end;
				j = j <= end ? j : end;
				while(i < j && nums[j] > startValue) {
					--j;
				}
			}
			nums[i] = nums[j];
			while (i < j && nums[i] < startValue) {
				++i;
			}
			if (i != start && nums[i] == startValue) {
				hasChange = true;
				nums[i] = nums[start];
				++ start;
				i = i >= start ? i : start;
				while (i < j && nums[i] < startValue) {
					++i;
				}
			}
			nums[j] = nums[i];
		}
		if (!hasChange) {
			return startValue;
		}
		
		if (1 == (i - start) % 2) {
			return function(nums, start, i - 1, false);
		}else {
			return function(nums, i + 1, end, false);
		}
	}
	
	public int function1(int[] nums, int start, int end, boolean hasChange) {
		while(true) {
			if (0 == end - start) {    //只有一个数据，则这个数据必定是目标数据
				hasChange = false;
				return nums[start];
			}
			
			int startValue = nums[start];
			int i = start;
			int j = end;
			while (i < j) {
				while(i < j && nums[j] > startValue) {
					--j;
				}
				if (j != start && nums[j] == startValue) {
					hasChange = true;
					nums[j] = nums[end];
					-- end;
					j = j <= end ? j : end;
					while(i < j && nums[j] > startValue) {
						--j;
					}
				}
				nums[i] = nums[j];
				while (i < j && nums[i] < startValue) {
					++i;
				}
				if (i != start && nums[i] == startValue) {
					hasChange = true;
					nums[i] = nums[start];
					++ start;
					i = i >= start ? i : start;
					while (i < j && nums[i] < startValue) {
						++i;
					}
				}
				nums[j] = nums[i];
			}
			if (!hasChange) {
				return startValue;
			}
			
			hasChange = false;
			if (1 == (i - start) % 2) {
				end = i - 1;
			}else {
				start = i + 1;
			}
		}
		
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {10,186,-49,176,118,167,-61,189,6,-24,7,-93,71,112,187,45,-36,38,82,108,-46,112,51,165,-37,159,1,-53,7,38,90,181,-23,91,-42,172,-95,130,84,149,-47,68,126,-67,175,22,121,131,84,114,60,64,182,-75,-17,-71,69,-92,103,-91,-91,86,126,166,33,-36,-80,-37,118,-80,-18,127,36,-71,-82,-82,144,12,57,149,71,91,-83,-100,-30,45,186,16,-51,-72,-83,107,140,-97,-93,1,12,189,-61,-50,180,98,96,-29,193,167,57,-45,16,6,-76,4,109,-23,22,144,190,-97,193,-51,-99,-79,-47,142,107,175,109,121,190,90,34,32,63,-24,41,-53,41,89,130,-18,-99,103,86,127,-30,102,32,-49,181,-60,114,60,-29,182,-75,168,96,51,33,142,108,69,10,-57,166,48,82,161,-17,-50,102,63,-45,140,180,176,-95,36,-46,168,187,161,-72,-100,-42,165,-76,-67,89,159,64,34,98,4,-60,172,-79,68,48,131,-57};
		System.out.println(new SingleNumber().solution(nums));
	}

}
