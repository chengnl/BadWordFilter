package wy.xjj.c;
/**
 *@author chengnl
 *@E-mail:chengnengliang@vrvmail.com.cn
 *@date 2014年10月31日 上午10:26:47
 *@version 1.0
 *@Description:字符转小写
 */
public class CharToLower {
     public  static  char  toLower( char  c){
    	//将大写英文字母以及全/半角的英文字母转化为小写字母
    	    if ( c <= 90&& c >= 65) //字母A-Z
    	            return  (char)(c +32);
    	    else if ( c >= 65313&&c <= 65338)//全角大写A-Z
    	            return  (char)(c - 65313 + 97); 
	        else if ( c >= 65345&& c <= 65370) //全角小写a-z
	            return  (char)(c - 65345 + 97);
    	    return  c;
     }
     
     public static void main(String args[]){
    	 System.out.println(CharToLower.toLower('A'));
    	 System.out.println(CharToLower.toLower('b'));
    	 System.out.println(CharToLower.toLower('ａ'));
    	 System.out.println(CharToLower.toLower('Ａ'));
     }
}
