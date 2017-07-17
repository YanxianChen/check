package com.check.controller;

import com.alibaba.fastjson.JSONObject;
import com.check.util.MjJSONUtil;
import com.check.util.RespMsgUtil;
import org.hibernate.HibernateException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yongg on 2017/5/10.
 */
public class BaseController {

    /**
     * 基础list方法封装
     * @param repository
     * @return
     */
    protected <T> JSONObject baseList(JpaRepository repository, T entity){
        List<T> resultList = repository.findAll();

        return RespMsgUtil.getSuccessResponseJoWithData(resultList);
    }


    /**
     * 基础添加方法封装
     * @param repository
     * @param jsonEntity
     * @param entity
     * @param <T>
     * @return
     */
    protected <T> JSONObject baseAdd(JpaRepository repository, JSONObject jsonEntity, T entity){
        JSONObject jo ;
        try{
            entity = (T)repository.save(MjJSONUtil.jsonToBean(jsonEntity, entity.getClass()));
            jo = RespMsgUtil.getSuccessResponseJoWithData(entity);
        }catch (IOException e){
            jo = RespMsgUtil.getFailResponseJoErrParamCannotResolve();
            e.printStackTrace();
        }catch (HibernateException e){
            jo = RespMsgUtil.getFailResponseJoErrHibernate();
            e.printStackTrace();
        }

        return jo;
    }

    /**
     * 基础的删除方法封装
     * @param repository
     * @param id
     * @return
     */
    protected <T> JSONObject baseDelete(JpaRepository repository, Serializable id, T entity){
        JSONObject jo;
        try {
            entity = (T)repository.findOne(id);
            repository.delete(id);
            jo = RespMsgUtil.getSuccessResponseJoWithData(entity);
        }catch (EmptyResultDataAccessException e){
            jo = RespMsgUtil.getFailResponseJoErrDeleteNotExist();
            e.printStackTrace();
        }

        return jo;
    }

    /**
     * 基础更新方法封装
     * @param repository
     * @param jsonEntity
     * @param entity
     * @return
     */
    protected <T> JSONObject baseUpdate(JpaRepository repository, Serializable id, JSONObject jsonEntity, T entity){
        JSONObject jo ;
        try{
            entity = (T)repository.findOne(id);
            if(null == entity){
                jo = RespMsgUtil.getFailResponseJoErrUpdateNotExist();
            }else{
                entity = (T)repository.save(MjJSONUtil.jsonToBean(jsonEntity, entity.getClass()));
                jo = RespMsgUtil.getSuccessResponseJoWithData(entity);
            }
        }catch (IOException e){
            jo = RespMsgUtil.getFailResponseJoErrParamCannotResolve();
            e.printStackTrace();
        }catch (HibernateException e){
            jo = RespMsgUtil.getFailResponseJoErrHibernate();
            e.printStackTrace();
        }

        return jo;

    }

    /**
     * 基础的查询方法封装
     * @param repository
     * @param id
     * @param entity
     * @param <T>
     * @return
     */
    protected <T> JSONObject baseGet(JpaRepository repository, Serializable id, T entity){
        JSONObject jo;
        try {
            entity = (T)repository.findOne(id);
            if(null == entity){
                jo = RespMsgUtil.getFailResponseJoErrGetNotExist();
            }else{
                jo = RespMsgUtil.getSuccessResponseJoWithData(entity);
            }
        }catch (Exception e){
            jo = RespMsgUtil.getFailResponseJoErrHibernate();
            e.printStackTrace();
        }

        return jo;
    }
}
