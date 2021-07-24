package com.imooc.reader.service.exception;

/**
 * @className: BussinessException
 * @author: 李绍宇
 * @description: 业务逻辑异常
 * @date: 2021/7/24 10:46
 * @version: 1.0
 */
public class BussinessException extends RuntimeException{
    private String code;
    private String msg;
    public BussinessException(String code , String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

