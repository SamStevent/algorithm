package leetcode;
/**
 验证回文串
 
 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:
	输入: "A man, a plan, a canal: Panama"
	输出: true

示例 2:
	输入: "race a car"
	输出: false
 */
public class IsPalindrome {
	public boolean isPalindrome(String s) {
		s = s.toLowerCase();
        char[] cs = s.toCharArray();
        int i = 0;
        int j = cs.length -1;
        while(i < j) {
        	if ((cs[i] > 'z' || cs[i] < 'a') && (cs[i] > '9' || cs[i] < '0')) {
				++ i;
				continue;
			}
        	if ((cs[j] > 'z' || cs[j] < 'a') && (cs[j] > '9' || cs[j] < '0')) {
				-- j;
				continue;
			}
        	if (cs[i] != cs[j]) {
				return false;
			}
        	++ i;
        	-- j;
        }
        return true;
	}
	
	/*利用Character提供的isLetterOrDigit判断是否是字母和数字，利用toLowerCase进行小写处理*/
	public boolean isPalindrome1(String s) {
        char[] cs = s.toCharArray();
        int i = 0;
        int j = cs.length -1;
        while(i < j) {
        	if (!Character.isLetterOrDigit(cs[i])) {
				++ i;
			}else if (!Character.isLetterOrDigit(cs[j])) {
				-- j;
			}else if (Character.toLowerCase(cs[i]) != Character.toLowerCase(cs[j])) {
				return false;
			}else {
				++ i;
	        	-- j;
			}
        }
        return true;
	}

	public static void main(String[] args) {
		System.out.println(new IsPalindrome().isPalindrome("A man, a plan, a canal: Panam"));
	}

}
