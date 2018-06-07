package com.flc.bankpda.base.mvp.factory;


import com.flc.bankpda.base.mvp.BaseContract;

/**
 * Created by fanqilong on 2018/4/23.
 */

public class PresenterMvpFactoryImpl<V extends BaseContract.BaseView,P extends BaseContract.BasePresenter<V>> implements PresenterMvpFactory<V,P> {

    private final Class<P> mPresenterClass;

    public static <V extends BaseContract.BaseView,P extends BaseContract.BasePresenter<V>> PresenterMvpFactoryImpl<V,P> createFactory(Class<?> viewClazz){
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if (annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new PresenterMvpFactoryImpl<V, P>(aClass);
    }

    private PresenterMvpFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }

    @Override
    public P createPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter创建失败!，检查是否声明了@CreatePresenter(xx.class)注解");
        }
    }
}
