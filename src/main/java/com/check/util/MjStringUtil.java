package com.check.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;

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
    
    public static boolean isEmpty(int s){
        if(0 == s){

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

    public static boolean isEmpty(JSONObject s){
        if(null == s){
            return  true;
        }else{
            Set<String> keys = s.keySet();
            for(String str:keys){
                if(str.equals("tno") || str.equals("cno") || str.equals("sno") ||
                        str.equals("ID") || str.equals("courseID") || str.equals("period") || str.equals("capacity")
                        || str.equals("number") || str.equals("classroom")){
                    if(isEmpty(s.getIntValue(str))){
                        return true;
                    }
                }
                else if(isEmpty(s.getString(str))){
                    return true;
                }
            }
            return false;
        }
    }
}
