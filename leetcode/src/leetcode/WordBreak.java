package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 单词拆分
	给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
说明：
	拆分时可以重复使用字典中的单词。
	你可以假设字典中没有重复的单词。
示例 1：
	输入: s = "leetcode", wordDict = ["leet", "code"]
	输出: true
	解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 */
public class WordBreak {
	List<String> result = new ArrayList<>();
	public List<String> wordBreak(String s, List<String> wordDict) {
		Map<Character, ArrayList<String>> wdMap = new HashMap<Character, ArrayList<String>>();
		for (String word : wordDict) {
			if (wdMap.containsKey(word.charAt(0))) {
				wdMap.get(word.charAt(0)).add(word);
			}else {
				ArrayList<String> list = new ArrayList<>();
				list.add(word);
				wdMap.put(word.charAt(0), list);
			}
		}
        Map<Integer, ArrayList<String>> map = new HashMap<>();
        for(int i = 0; i < s.length(); ++ i) {   //记录可以从某个下标开始匹配字符串的词汇
        	Character c = s.charAt(i);
        	if (wdMap.containsKey(c)) {
        		ArrayList<String> list = wdMap.get(c);
        		for(String word : list) {
        			if (s.length() >= i + word.length() && word.equals(s.substring(i, i + word.length()))) {
						if (map.containsKey(i)) {
							map.get(i).add(word);
						}else {
							ArrayList<String> ws = new ArrayList<>();
							ws.add(word);
							map.put(i, ws);
						}
					}
        		}
			}
        }
        append(map, "", 0, s.length());
        return result;
    }
	public void append(Map<Integer, ArrayList<String>> map, String ori, int index, int length) {
		ArrayList<String> words= map.get(index);
		if (null != words && words.size() > 0) {
			for (String word : words) {
				StringBuffer sb = new StringBuffer(ori);
				sb.append(" ").append(word);
				if (index + word.length() >= length) {
					result.add(sb.substring(1));
					System.out.println(sb.substring(1));
				}else {
					append(map, sb.toString(), index + word.length(), length);
				}
			}
		}
	}
	
	/*改进方法*/
	private Map<String, List<String>> map = new HashMap<>();
    public List<String> wordBreak1(String s, List<String> wordDict) {
        if (map.containsKey(s)) //如果包含 则直接返回s
            return map.get(s);
        List<String> list = new ArrayList<>();
        if (s.length() == 0) {
            list.add("");
            return list;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {//判断s是否含有word的前缀
                List<String> tmpList = wordBreak1(s.substring(word.length()), wordDict);
                for (String tmp : tmpList)
                    list.add(word + (tmp.equals("") ? "" : " ") + tmp);//空的话则""结尾    
            }
        }
        map.put(s, list);//记录可以拆分的字符串，并且记录拆分的方法
        return list;
    }
        
	public static void main(String[] args) {
//		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("a");
		wordDict.add("aa");
		wordDict.add("aaa");
		wordDict.add("aaaa");
		wordDict.add("aaaaa");
		wordDict.add("aaaaaa");
		wordDict.add("aaaaaaa");
		wordDict.add("aaaaaaaa");
		wordDict.add("aaaaaaaaa");
		wordDict.add("aaaaaaaaaa");
		List<String> list = new WordBreak().wordBreak1(s, wordDict);
		for (String string : list) {
			System.out.println(string);
		}
	}

}
