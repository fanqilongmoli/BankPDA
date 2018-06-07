package com.flc.bankpda.base.mvp.factory;


import com.flc.bankpda.base.mvp.BaseContract;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by fanqilong on 2018/4/23.
 * 创建Presenter的注解
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenter {
    Class<? extends BaseContract.BasePresenter> value();
}
