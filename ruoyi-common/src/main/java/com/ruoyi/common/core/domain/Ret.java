package com.ruoyi.common.core.domain;

import java.io.Serializable;
import java.util.HashMap;

import com.ruoyi.common.constant.HttpStatus;

/**
 * 操作消息提醒
 * 
 * @author ruoyi
 */
public class Ret extends HashMap
{
    private static final long serialVersionUID = 1L;



    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";


    /**
     * 初始化一个新创建的 Ret 对象，使其表示一个空消息。
     */
    public Ret()
    {
    }

    public Ret(int code) {
        super.put(CODE_TAG,code);
    }


    /**
     * 初始化一个新创建的 Ret 对象
     * 
     * @param code 状态码
     * @param msg 返回内容
     */
    public Ret(int code, String msg)
    {
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
    }
    public Ret(int code, Object data) {
        super.put(CODE_TAG,code);
        super.put(DATA_TAG,data);
    }

    /**
     * 初始化一个新创建的 Ret 对象
     * 
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public Ret(int code, String msg, Object data)
    {
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
        super.put(DATA_TAG,data);
    }

    /**
     * 返回成功消息
     * 
     * @return 成功消息
     */
    public static Ret success()
    {
        return Ret.success("操作成功");
    }

    /**
     * 返回成功数据
     * 
     * @return 成功消息
     */
    public static Ret success(Object data)
    {
        return Ret.success("操作成功", data);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @return 成功消息
     */
    public static Ret success(String msg)
    {
        return Ret.success(msg, null);
    }

    /**
     * 返回成功消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static Ret success(String msg, Object data)
    {
        return new Ret(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @return
     */
    public static Ret error()
    {
        return Ret.error("操作失败");
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Ret error(String msg)
    {
        return Ret.error(msg, null);
    }

    /**
     * 返回错误消息
     * 
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static Ret error(String msg, Object data)
    {
        return new Ret(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     * 
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Ret error(int code, String msg)
    {
        return new Ret(code, msg, null);
    }


    public void setCode(int code){
        super.put(CODE_TAG,code);
    }

    public void setMsg(String msg){
        super.put(MSG_TAG,msg);
    }

    public void setData(Object data){
        super.put(DATA_TAG,data);
    }
}
