package leetcode;

/**
 * @author chenxl
 * @since 2019-11-01
 * shopee面试的一道算法题
 * 有一个由0,1,2组成的无序数组，要求空间复杂度为O(1)的情况下通过一遍遍历对数组进行排序（升序）
 * 思路：遍历一遍，将数据为0的数全部移动到数组头，数据为2的数据全部移动到数组尾*/
public class ShopeeSort {
	public void function(int[] nums){
		int xIndex = 0, yIndex = nums.length - 1;  //当前最左侧不是0和最右侧不是2的数组下标，用于交换
		while(xIndex < yIndex && 0 == nums[xIndex]){
			++ xIndex;
		}
		while(xIndex < yIndex && 2 == nums[yIndex]){
			-- yIndex;
		}
		/*遍历中间无序的数组部分*/
		for(int i = xIndex; i <= yIndex;){
			switch(nums[i]){
			case 0:   //1要移到前面，同时xIndex要加大（实际上可以用while循环加大到下一个不为1的位置，不过需要控制几个参数的大小）
				if(i > xIndex){   //要保证 i >= xIndex。如果像2那样进行操作，可能出现错误排序
					int tmp = nums[i];
					nums[i] = nums[xIndex];
					nums[xIndex] = tmp;
				}else {
					++ i;
				}
				++ xIndex;
				break;
			case 1:    //1交换
				++ i;
				break;
			case 2:    //2要换到后边，同时yIndex要减小（实际上可以用while循环减小到下一个不为2的位置，不过需要控制几个参数的大小）
				int tmp = nums[i];
				nums[i] = nums[yIndex];
				nums[yIndex] = tmp;
				-- yIndex;
				break;
			default:   //只接受0,1,2三个数值，否则无法做到遍历一次完成排序
				throw new RuntimeException("this is an error number!");
			}
			
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[]{1, 2, 1, 0, 0, 0};
//		int[] nums = new int[]{2, 1, 2, 1, 0, 1, 2, 2, 1, 1, 0};
		ShopeeSort ss = new ShopeeSort();
		ss.function(nums);
		for(int num : nums){
			System.out.println(num);
		}
	}

}
