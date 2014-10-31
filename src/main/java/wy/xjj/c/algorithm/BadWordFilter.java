package wy.xjj.c.algorithm;

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
 *@Description:关键词屏蔽
 */
public class BadWordFilter {
	    private  Set<String> badWords = new HashSet<String>();
        private  BitSet firstCheckChar = new BitSet(Character.MAX_VALUE);
        private  BitSet allCheckChar = new BitSet(Character.MAX_VALUE);
        private  int maxLength=0;
        public void  init(){
        	 String[]  words = new String[]{"法轮功","周永康","64事件","tiananmen","靠"};
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
        		for(int j=1;j<=Math.min(maxLength, text.length()-index);j++){
        			if(!allCheckChar.get(text.charAt(index+j-1)))
        				break;
        			String badWord = text.substring(index, index+j);
        			if(badWords.contains(badWord)){
        				for(int n=0;n<j;n++){
        					respaceStr+="*";
        				}
        				newTextBuilder.replace(index, index+j, respaceStr);
        			}
        		}
        		index++;
        	}
        	return newTextBuilder.toString();
        }
       public  static void main(String[] args){
    	   String  text ="测试屏蔽程序";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	   text+="fa法轮功测试放大ti a nan 靠men放大23223432    64事件";
    	  
    	   BadWordFilter  filter = new BadWordFilter();
    	   filter.init();
    	   long startTime= System.currentTimeMillis();
    	   for(int i=0;i<1000;i++){
    		   String newText= filter.filterBadWords(text);
    		   System.out.println(newText);
    	   }
    	   long endTime= System.currentTimeMillis();
    	   System.out.println("耗时"+(endTime-startTime)+"ms");
       }
}
