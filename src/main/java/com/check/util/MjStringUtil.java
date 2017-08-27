package com.check.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

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
    
    public static boolean isEmpty(Integer s){
        if(null == s){

            return true;
        }else{

            return false;
        }
    }
    
    public static boolean isEmpty(JSONObject s){
        if(null == s){

            return true;
        }else{

            return false;
        }
    }
    public static boolean isEmpty(JSONArray s){
        if(null == s){

            return true;
        }else{

            return false;
        }
    }
}
