package org.util;

import org.apache.commons.lang.StringUtils;

public class Utils {
	
	public static final String getAutoFill(String str,Integer addLen,Integer sit,String c){
		if(StringUtils.isNotBlank(str)){
			StringBuilder sb = new StringBuilder(str);
			for(int i = 0 ; i < addLen ; i++){
				sb.insert(0, c);
			}
			return sb.toString();
		}
		return null;
	}
}
