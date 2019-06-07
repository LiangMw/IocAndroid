package com.integrate.mylibrary.nethelper.nethelper;

/**
 * Created by 梁明伟 on 2019/6/1.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class BaseResponse {
    private String code;
    private String msg;

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
