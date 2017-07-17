package com.check.util;

/**
 * Created by yongg on 2017/5/18.
 */
public class MjStringUtil {

    public static boolean isEmpty(String s){
        if((null == s) || ("".equals(s.trim()))){

            return true;
        }else{

            return false;
        }
    }
}
