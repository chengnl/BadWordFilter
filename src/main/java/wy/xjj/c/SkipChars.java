package wy.xjj.c;
import java.util.HashSet;
import java.util.Set;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年10月31日 上午10:47:08
 *@version 1.0
 *@Description:跳过字符
 */
public class SkipChars {
	private static String skipChars = " 　═十▇▆■ ▓ 回 □ 〓≡ ╝╚╔ ╗╬ ═ ╓ ╩ ┠ ┨┯ ┷┏ ┓┗ ┛┳⊥『』┌ ┐└ ┘"
			+ "∟「」↑↓→←↘↙♀♂┇┅ ﹉﹊﹍﹎╭ ╮╰ ╯ *^_^* ^*^ ^-^ ^_^ ^（^ ∵∴∥｜ ｜︴"
			+ "﹏﹋﹌（）〔〕 【】〖〗＠：！/_< >`,．。≈{}~ ～()_-『』√ $ @ * & # ※ 卐 々"
			+ "∞Ψ ∪∩∈∏の℡ぁ§∮”〃ミ灬ξ№∑⌒ξζω＊??ㄨ ≮≯ ＋ －×÷＋－±／＝∫∮∝ ∞ "
			+ "∧∨ ∑ ∏ ∥∠ ≌ ∽ ≤ ≥ ≈＜＞じ ξζω□∮〓※∴ぷ▂▃▅▆█ ∏卐【】△√ ∩¤々♀♂"
			+ "∞①ㄨ≡↘↙▂ ▂ ▃ ▄ ▅ ▆ ▇ █┗┛╰☆╮ ≠ ▂ ▃ ▄ ▅ ";
	private static Set<Character>   skipCharSet  = new HashSet<Character>();
	private static void init(){
		for(char c:skipChars.toCharArray()){
			skipCharSet.add(c);		
		}
	}
	public static  boolean isSkipChar(char c){
		if(skipCharSet.isEmpty())
			init();
		if(skipCharSet.contains(c))
			return true;
		else
			return false;
	}
	
    public static void main(String args[]){
	   	 System.out.println(SkipChars.isSkipChar('卐'));
	   	 System.out.println(SkipChars.isSkipChar('√'));
	     System.out.println(SkipChars.isSkipChar('ㄨ'));
	     System.out.println(SkipChars.isSkipChar('?'));
	   	 System.out.println(SkipChars.isSkipChar(' '));
    }
}
