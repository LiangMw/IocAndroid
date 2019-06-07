package com.integrate.mylibrary.nethelper.nethelper;

import java.io.InputStream;

/**
 * Created by 梁明伟 on 2019/6/1.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public interface CallBackListener {

    void onSuccess(InputStream inputStream);
    void onFailure(String reason);

}
