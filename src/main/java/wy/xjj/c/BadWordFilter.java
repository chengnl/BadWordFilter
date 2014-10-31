package wy.xjj.c;

import java.util.BitSet;
import java.util.HashSet;
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
        	String[] words=LoadBadWords.loadBadWordsToString();
        	 for(String word : words){
        		 if(word==null||word.equals(""))
        			 continue;
        		  word = word.toLowerCase();
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
        		if(!firstCheckChar.get(CharToLower.toLower(text.charAt(index)))){
        			while(index<text.length()-1&&!firstCheckChar.get(CharToLower.toLower(text.charAt(++index))));
        		}
        		String respaceStr="";
        		for(int j=1,offset=j;j<=Math.min(maxLength, text.length()-index);j++,offset++){
        			while((index+offset-1)<text.length()&&
        					(SkipChars.isSkipChar(CharToLower.toLower(text.charAt(index+offset-1))))){
        				offset++;
        			}
    				if((index+offset-1)==text.length())
    					break;
        			if(!allCheckChar.get(CharToLower.toLower(text.charAt(index+offset-1))))
        				break;
        			String badWord = text.substring(index, index+offset);
        			badWord=	WordSkipChar.toSkipChar(badWord);
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
    	   String  text ="测试屏蔽程序";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	   text+="fa法ㄨ轮ㄨ功测试放大ti a nan 靠men放大23223432    6 4 运 动";
    	  
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
