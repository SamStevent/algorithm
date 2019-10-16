package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 分割回文串
	给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
	返回 s 所有可能的分割方案。
示例:
	输入: "aab"
	输出:
	[
	  ["aa","b"],
	  ["a","a","b"]
	]
 */
public class Partition {
	/*思路：
	 * 1、获取字符串中以每一个字符为起点能得到的回文串，存放到一个以该字符下标为key的map中
	 * 2、从下标为0的字符开始将其所拥有的回文串分别作为分割后的内容的一部分，之后根据最后一个字符的下标去获取下一个回文串*/
	public List<List<String>> partition(String s) {
		char[] cs = s.toCharArray();
        Map<Integer, List<String>> cacheMap = new HashMap<Integer, List<String>>();
        //获取从某个点起的所有回文
        for(int i = 0; i < s.length(); ++ i) {
        	List<String> strs = new ArrayList<String>();
        	strs.add(Character.toString(cs[i]));
        	for(int j = i + 1; j < cs.length; ++ j) {
        		if (check(cs, i, j)) {
					strs.add(new String(cs, i, j - i + 1));
				}
        	}
        	cacheMap.put(i, strs);
        }
        
        List<List<String>> result = new ArrayList<>();
        result.addAll(merg(cacheMap, new ArrayList<String>(), 0, s.length()));
        return result;
    }
	//检测是否回文
	public static boolean check(char[] cs, int start, int end) {
		while(start < end) {
			if (cs[start] != cs[end]) {
				return false;
			}
			++ start;
			-- end;
		}
		return true;
	}
	
	public List<List<String>> merg(Map<Integer, List<String>> cacheMap, List<String> post, int index, int length){
		List<List<String>> result = new ArrayList<>();
		List<String> cache = cacheMap.get(index);
		for (String string : cache) {
			List<String> cur = new ArrayList<>(post);
			cur.add(string);
			if (index + string.length() >= length) {
				result.add(cur);
			}else {
				result.addAll(merg(cacheMap, cur, index + string.length(), length));
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String s = "abbab";
		/*char[] cs = s.toCharArray();
		String s1 = new String(cs, 1, 2);
		System.out.println(s1);*/
		List<List<String>> result = new Partition().partition(s);
		for (List<String> list : result) {
			for (String string : list) {
				System.out.print(string + ", ");
			}
			System.out.println();
		}
	}

}
