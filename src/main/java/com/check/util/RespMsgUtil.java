package com.check.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by yongg on 2017/5/5.
 */
public class RespMsgUtil {
    public static String RESULT_KEY = "result";

    public static String DATA_KEY = "data";

    public static String ERR_KEY = "err";

    public static String SUCCESS = "success";

    public static String FAIL = "fail";

    public static String ERR_EMPTY_RESULT = "没有查询到任何结果！";

    public static String ERR_PARAM_CANNOT_RESOLVE = "参数无法解析！";

    public static String ERR_BEAN_TO_JSON = "JSON转换出错！";

    public static String ERR_HIBERNATE = "数据操作异常！";

    public static String ERR_GET_NOT_EXIST = "查询失败，数据不存在！";

    public static String ERR_UPDATE_NOT_EXIST = "更新失败，数据不存在！";

    public static String ERR_INSERT_EXIST = "添加失败，数据已存在，无法重复添加!";

    public static String ERR_DELETE_NOT_EXIST = "删除失败，数据不存在！";

    /**
     * 获取一个基础的成功响应JSON
     * @return
     */
    public static JSONObject getSuccessResponseJo(){
        JSONObject jo = new JSONObject();
        jo.put(RESULT_KEY, SUCCESS);
        jo.put(ERR_KEY, "");

        return jo;
    }

    /**
     * 获取一个基础的成功响应JSON
     * @return
     */
    public static JSONObject getSuccessResponseJoWithData(Object data){
        JSONObject jo = new JSONObject();
        try {
            jo.put(RESULT_KEY, SUCCESS);
            if((data instanceof  JSONObject) || (data instanceof JSONArray)){
                jo.put(DATA_KEY, data);
            }else if(data instanceof List){
                jo.put(DATA_KEY, MjJSONUtil.listToJson((List)data));
            }else{
                jo.put(DATA_KEY, MjJSONUtil.beanToJson(data));
            }

            jo.put(ERR_KEY, "");
        } catch (IOException e) {
            jo.put(RESULT_KEY, FAIL);
            jo.put(DATA_KEY, "");
            jo.put(ERR_KEY, "JSON转换出错！");
            e.printStackTrace();
        }

        return jo;
    }

    /**
     * 获取一个基础的失败响应JSON
     * @return
     */
    public static JSONObject getFailResponseJo(){
        JSONObject jo = new JSONObject();
        jo.put(RESULT_KEY, FAIL);
        jo.put(DATA_KEY, "");

        return jo;
    }

    /**
     * 获取一个带errmsg的响应JSON
     * @return
     */
    public static JSONObject getFailResponseJoWithErrMsg(String errMsg){
        JSONObject jo = new JSONObject();
        jo.put(RESULT_KEY, FAIL);
        jo.put(DATA_KEY, "");
        jo.put(ERR_KEY, errMsg);

        return jo;
    }

    /**
     * 获取一个ERR_EMPTY_RESULT响应JSON
     * @return
     */
    public static JSONObject getFailResponseJoErrEmptyResult(){

        return getFailResponseJoWithErrMsg(ERR_EMPTY_RESULT);
    }

    /**
     * 获取一个ERR_JSON_TO_BEAN响应JSON
     * @return
     */
    public static JSONObject getFailResponseJoErrParamCannotResolve(){

        return getFailResponseJoWithErrMsg(ERR_PARAM_CANNOT_RESOLVE);
    }

    /**
     * 获取一个ERR_BEAN_TO_JSON响应JSON
     * @return
     */
    public static JSONObject getFailResponseJoErrBeanToJSON(){

        return getFailResponseJoWithErrMsg(ERR_BEAN_TO_JSON);
    }

    /**
     * 获取一个ERR_HIBERNATE响应JSON
     * @return
     */
    public static JSONObject getFailResponseJoErrHibernate(){

        return getFailResponseJoWithErrMsg(ERR_HIBERNATE);
    }

    /**
     * 获取一个ERR_GET_NOT_EXIST响应JSON
     * 获取不存在的实体
     * @return
     */
    public static JSONObject getFailResponseJoErrGetNotExist(){

        return getFailResponseJoWithErrMsg(ERR_GET_NOT_EXIST);
    }

    /**
     * 获取一个ERR_UPDATE_NOT_EXIST响应JSON
     * 更新不存在的实体
     * @return
     */
    public static JSONObject getFailResponseJoErrUpdateNotExist(){

        return getFailResponseJoWithErrMsg(ERR_UPDATE_NOT_EXIST);
    }

    /**
     * 获取一个ERR_INSERT_EXIST响应JSON
     * 插入已存在实体
     * @return
     */
    public static JSONObject getFailResponseJoErrInsertExist(){

        return getFailResponseJoWithErrMsg(ERR_INSERT_EXIST);
    }

    /**
     * 获取一个ERR_DELETE_NOT_EXIST响应JSON
     * 删除不存在实体
     * @return
     */
    public static JSONObject getFailResponseJoErrDeleteNotExist(){

        return getFailResponseJoWithErrMsg(ERR_DELETE_NOT_EXIST);
    }
}
