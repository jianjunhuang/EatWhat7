package com.jinjunhuang.eatwhat.contract;

import com.demo.jianjunhuang.mvptools.mvp.IModel;
import com.demo.jianjunhuang.mvptools.mvp.IPresenter;
import com.demo.jianjunhuang.mvptools.mvp.IView;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/11/14.
 */

public interface LoginContract {
    interface View extends IView {
        void loginSuccess();

        void loginFailed(String reason);

        void signUpSuccess();

        void signUpFailed(String reason);

        void showInputEmailErr();

        void showNotInputEmailErr();

        void showInputPwdErr();

        void showNotInputPwdErr();

        void showInputSurePwdErr();

        void showNotInputSurePwdErr();
    }

    interface Model extends IModel {
        void login(String email, String pwd);

        void signUp(String email, String pwd, String surePwd);

        void setCallback(Callback mCallback);

        interface Callback {
            void onLoginSuccess();

            void onLoginFailed(String reason);

            void onSignUpSuccess();

            void onSignUpFailed(String reason);
        }
    }

    interface Presenter extends IPresenter {
        void login(String email, String pwd);

        void signUp(String email, String pwd, String surePwd);
    }
}
