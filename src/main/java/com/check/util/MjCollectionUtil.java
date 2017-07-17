package com.check.util;

import java.util.Collection;

/**
 * Created by yongg on 2017/5/14.
 */
public class MjCollectionUtil {

    public static boolean isEmpty(Collection<?> c){
        if((null == c) || (0 == c.size())){

            return true;
        }else{

            return false;
        }
    }
}
