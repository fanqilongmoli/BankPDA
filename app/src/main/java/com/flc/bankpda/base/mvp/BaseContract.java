package com.flc.bankpda.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by fanqilong on 2018/4/23.
 * 基础契约类
 */

public interface BaseContract {

    interface BaseView{
        /**
         * 请求出现错误
         * @param msg 错误信息
         * @param isLoadMore 是否加载更多，用来区分刷新出错还是加载更多出错
         */
        void showError(String msg, boolean isLoadMore);

        /**
         * 请求完成
         */
        void complete();

        /**
         * 是否显示进度条
         * @param isShow 是否显示
         */
        void showProgressUI(boolean isShow);
    }

    interface BasePresenter<T>{
        /**
         * 绑定
         * @param view
         * @param context
         */
        void attachView(T view, Context context);

        /**
         * 解绑
         */
        void detachView();

        /**
         * Presenter被创建后调用
         * @param savedState
         */
        void onCreatePresenter(@Nullable Bundle savedState);

        /**
         * Presenter被销毁的时候调用
         */
        void onDestroyPresenter();

        /**
         * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
         * 时机相同
         * @param outState
         */
        void onSaveInstanceState(Bundle outState);


    }
}
