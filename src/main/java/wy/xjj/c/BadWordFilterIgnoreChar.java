package wy.xjj.c;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年10月30日 下午12:15:46
 *@version 1.0
 *@Description:关键词屏蔽  忽略屏蔽词之间的星号和空格
 */
public class BadWordFilterIgnoreChar {
        private  Set<String> badWords = new HashSet<String>();
        private  BitSet firstCheckChar = new BitSet(Character.MAX_VALUE);
        private  BitSet allCheckChar = new BitSet(Character.MAX_VALUE);
        private  int maxLength=0;
        public void  init(){
        	 String[]  words = new String[]{"法轮功","周永康","64事件","tiananmen"};
        	 for(String word : words){
        		  if(!badWords.contains(word)){
        			  badWords.add(word);
        			  maxLength=Math.max(maxLength, word.length());
        			  char[]  wordChars = word.toCharArray();
        			  firstCheckChar.set(wordChars[0]);
        			  for(char c :wordChars){
        				  allCheckChar.set(c);
        			  }
        		  }
        	 }
        }
        public String  filterBadWords(String text){
        	StringBuilder newTextBuilder= new StringBuilder(text);
        	int index=0;
        	while(index<text.length()){
        		if(!firstCheckChar.get(text.charAt(index))){
        			while(index<text.length()-1&&!firstCheckChar.get(text.charAt(++index)));
        		}
        		String respaceStr="";
        		for(int j=1,offset=j;j<=Math.min(maxLength, text.length()-index);j++,offset++){
        			while((index+offset-1)<text.length()&&(
        					text.charAt(index+offset-1)=='*'||text.charAt(index+offset-1)==' ')){
        				offset++;
        			}
    				if((index+offset-1)==text.length())
    					break;
        			if(!allCheckChar.get(text.charAt(index+offset-1)))
        				break;
        			String badWord = text.substring(index, index+offset);
        			badWord=	badWord.replaceAll("[* ]", "");
        			if(badWords.contains(badWord)){
        				for(int n=0;n<offset;n++){
        					respaceStr+="*";
        				}
        				newTextBuilder.replace(index, index+offset, respaceStr);
        			}
        		}
        		index++;
        	}
        	return newTextBuilder.toString();
        }
       public  static void main(String[] args){
    	   BadWordFilterIgnoreChar  filter = new BadWordFilterIgnoreChar();
    	   filter.init();
    	   System.out.println( filter.filterBadWords("fa法* 轮 *功测试放大ti a nan men放大23223432    6       4 事 件 "));
       }
}
