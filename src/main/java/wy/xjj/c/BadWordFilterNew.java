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
 *@Description:关键词屏蔽  新算法  
 */
public class BadWordFilterNew {
	    private  Set<String> badWords = new HashSet<String>();
        private  byte[] fastCheckChar = new byte[Character.MAX_VALUE];
        private  BitSet checkChar = new BitSet(Character.MAX_VALUE);
        private  int maxLength=0;
        private  int minLength=Integer.MAX_VALUE;
        public void  init(){
        	 String[]  words = new String[]{"法轮功","周永康","64事件","tiananmen","靠"};
        	 for(String word : words){
        		 maxLength=Math.max(maxLength, word.length());
        		 minLength=Math.min(minLength, word.length());
        		 for(int i=0;i<7&&i<word.length();i++){
        			 fastCheckChar[word.charAt(i)]|=(byte)(1<<i);
        		 }
        		 for(int i=7;i<word.length();i++){
        			 fastCheckChar[word.charAt(i)]|=0x80;
        		 }
        		 if(word.length()==1)
        			 checkChar.set(word.charAt(0));
        		 else
        			 badWords.add(word);
        	 }
        }
        public String  filterBadWords(String text){
        	StringBuilder newTextBuilder= new StringBuilder(text);
        	int index=0;
        	while(index<text.length()){
        		if((fastCheckChar[text.charAt(index)]&1)==0){
        			while(index<text.length()-1&&(fastCheckChar[text.charAt(++index)]&1)==0);
        		}
        		if(minLength==1&&checkChar.get(text.charAt(index))){
        			//有单个屏蔽字
        			newTextBuilder.replace(index, index+1, "*");
        		}
        		String respaceStr="";
        		for(int j=1;j<=Math.min(maxLength, text.length()-index-1);j++){
        			if((fastCheckChar[text.charAt(index+j)]&(1<<Math.min(j, 7)))==0)
        				break;
        			if(j+1>=minLength){
        				String badWord = text.substring(index, index+j+1);
        				if(badWords.contains(badWord)){
            				for(int n=0;n<j+1;n++){
            					respaceStr+="*";
            				}
        					newTextBuilder.replace(index, index+j+1, respaceStr);
        				}
        			}
        		}
        		index++;
        	}
        	return newTextBuilder.toString();
        }
       public  static void main(String[] args){
    	   String  text ="测试屏蔽程序";
    	   text+="fa法轮功测试放大tiananmen靠放大23223432    64事件";
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
    	  
    	   BadWordFilterNew  filter = new BadWordFilterNew();
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
