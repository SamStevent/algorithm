package leetcode;

/**
 求众数
 
 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
你可以假设数组是非空的，并且给定的数组总是存在众数。

示例 1:
	输入: [3,2,3]
	输出: 3

示例 2:
	输入: [2,2,1,1,1,2,2]
	输出: 2
 */
public class MajorityElement {
	/*当数组中有超过1/2的数为某个数的时候，将所有不等的数进行抵消，最终剩下的就是这个数*/
	public int majorityElement(int[] nums) {
        int i = 0;
        int zeroCount = 0;  //由于输入数据可能会有0，而这里清零的时候也是把数据改为0，所以需要记录数组中原来0的个数
        for(int j = 1; j < nums.length; ++j) {
        	if (0 == nums[j]) {
				++ zeroCount;
			}else if (nums[i] != nums[j]) {
				nums[i++] = 0;
				nums[j] = 0;
				while(0 == nums[i])
					++i;
			}
        }
        if (zeroCount >= (nums.length + 1) /2) {
			return 0;
		}
        for(int j = 0; j < nums.length; ++j) {
        	if (0 != nums[j]) {
				return nums[j];
			}
        }
        return 0;
    }
	/*摩尔投票法。由题目假设，众数一定存在，所以数组中至少有一半的元素相同*/
	public int majorityElement1(int[] nums) {
        // result表示众数，Count计数器
		int result = 0, Count = 0;
		for (int num : nums) {
			if (Count == 0) {
				
				//重新选择众数
				result = num;
				Count++;
			} else if (num == result) {
				Count++;
			} else {
				Count--;
			}
		}
		return result;
    }

	public static void main(String[] args) {
		int[] nums = new int[] {1, 0, 0};
		System.out.println(new MajorityElement().majorityElement(nums));
	}

}
