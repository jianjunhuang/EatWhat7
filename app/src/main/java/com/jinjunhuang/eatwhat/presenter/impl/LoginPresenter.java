package com.jinjunhuang.eatwhat.presenter.impl;

import com.jinjunhuang.eatwhat.contract.LoginContract;
import com.jinjunhuang.eatwhat.model.LoginModel;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/11/14.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private LoginContract.Model mModel;

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void onStart() {
        mModel = new LoginModel();
    }

    @Override
    public void onDestroy() {
        mView = null;
        mModel = null;
    }

    @Override
    public void login(String usr, String pwd) {

    }

    @Override
    public void signUp(String usr, String pwd, String surePwd) {

    }
}
