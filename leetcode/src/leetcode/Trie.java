package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxl
 * @since 2019-11-01
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 */
public class Trie {
	public class Node{
		public char value;
		public boolean isWord;
		public Map<Character, Node> childNodes;
		public Node(){
			this(' ');
		}
		
		public Node(char value){
			this(value, false);
		}
		
		public Node(char value, boolean isWord){
			this(value, isWord, null);
		}
		
		public Node(char value, boolean isWord, Map<Character, Node> childNodes){
			this.value = value;
			this.isWord = isWord;
			this.childNodes = childNodes;
		}
		
		
	}
	
	public final Node root;
	public Node cur;
    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        this.cur = this.root;
        for(char c : chars){
        	Map<Character, Node> childNodes = this.cur.childNodes;
        	if(null == childNodes){
        		childNodes = new HashMap<Character, Node>();
        		this.cur.childNodes = childNodes;
        	}
        	if(!childNodes.containsKey(c)){
        		Node newNode = new Node(c);
        		childNodes.put(c, newNode);
        	}
        	this.cur = childNodes.get(c);
        }
        this.cur.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
    	this.cur = this.root;
    	if(!check(word)){
    		return false;
    	}
    	return this.cur.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	this.cur = this.root;
    	return check(prefix);
    }
    
    private boolean check(String word){
    	char[] chars = word.toCharArray();
    	for(char c : chars){
    		Map<Character, Node> childNodes = this.cur.childNodes;
    		if(null == childNodes){
    			return false;
    		}
    		this.cur = childNodes.get(c);
    		if(null == this.cur){
    			return false;
    		}
    	}
    	return true;
    }
    
    public static void main(String[] args){
    	Trie obj = new Trie();
    	obj.insert("abc");
    	obj.insert("sss");
    	obj.insert("dfdsf");
    	obj.insert("aklf");
    	System.out.println(obj.search("a"));
    	System.out.println(obj.search("abc"));
    	System.out.println(obj.search("dfdsf"));
    	System.out.println(obj.startsWith("a"));
    	System.out.println(obj.startsWith("akl"));
    	System.out.println(obj.startsWith("b"));
    	
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */