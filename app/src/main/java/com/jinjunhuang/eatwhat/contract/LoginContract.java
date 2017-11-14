package com.jinjunhuang.eatwhat.contract;

import com.demo.jianjunhuang.mvptools.mvp.IModel;
import com.demo.jianjunhuang.mvptools.mvp.IPresenter;
import com.demo.jianjunhuang.mvptools.mvp.IView;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/11/14.
 */

public interface LoginContract {
    public interface View extends IView {
        void loginSuccess();

        void loginFailed();

        void signUpSuccess();

        void signUpFailed();
    }

    public interface Model extends IModel {
        void login(String usr, String pwd);

        void signUp(String usr, String pwd, String surePwd);
    }

    public interface Presenter extends IPresenter {
        void login(String usr, String pwd);

        void signUp(String usr, String pwd, String surePwd);
    }
}
