package leetcode;

/**
 合并两个有序数组
 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
说明:
	初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
	你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
示例:
	输入:
	nums1 = [1,2,3,0,0,0], m = 3
	nums2 = [2,5,6],       n = 3
	
	输出: [1,2,2,3,5,6]
 */
public class Merge {
	/*思路：从后往前获取最大的数据填到nums1对应的位置*/
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (0 == m) {
			for(int i = 0; i < n; ++i) {
				nums1[i] = nums2[i];
			}
		}
		if (0 == n) {
			return;
		}
		int i = m - 1;
		int j = n - 1;
		for(int s = m + n - 1; s >= 0; --s) {
			if (i < 0) {
				nums1[s] = nums2[j --];
				continue;
			}
			if (j < 0) {
				nums1[s] = nums1[i --];
				continue;
			}
			/*找到两数组最后一个数比较大小，将较大的填入nums1对应的位置*/
			if (nums1[i] > nums2[j]) {
				nums1[s] = nums1[i --];
			}else {
				nums1[s] = nums2[j --];
			}
			
		}
	}
	public static void main(String[] args) {
		int[] nums1 = new int[] {1,2,3,0,0,0};
		int[] nums2 = new int[] {2,5,6};
		new Merge().merge(nums1, 3, nums2, 3);
		for (int i : nums1) {
			System.out.print (i + "  ");
		}
	}

}
