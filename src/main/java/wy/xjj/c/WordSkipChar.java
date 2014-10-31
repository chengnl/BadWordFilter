package wy.xjj.c;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年10月31日 上午11:11:06
 *@version 1.0
 *@Description:将词里面的跳过字符过滤掉，返回正常词
 */
public class WordSkipChar {
	   /**
	    * 将词里面的跳过字符过滤掉，并且大写转小写，返回正常词 
	    * @param word
	    * @return
	    */
       public static  String  toSkipChar(String word){
    	   StringBuilder  sb = new StringBuilder();
    	   for(char c :  word.toCharArray()){
    		   if(!SkipChars.isSkipChar(c))
    			   sb.append(CharToLower.toLower(c));
    	   }
    	  return sb.toString();
      }
       
       public static void main(String args[]){
  	   	 System.out.println(WordSkipChar.toSkipChar("法 轮 功"));
  	     System.out.println(WordSkipChar.toSkipChar("法?轮?功"));
  	     System.out.println(WordSkipChar.toSkipChar("法ㄨ轮ㄨA功"));
      }
}
