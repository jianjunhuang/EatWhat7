package com.jinjunhuang.eatwhat.model;

import com.jinjunhuang.eatwhat.contract.LoginContract;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/11/14.
 */

public class LoginModel implements LoginContract.Model {

    private LoginContract.Model.Callback mCallback;

    public void setCallback(Callback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    public void onDestroy() {
        mCallback = null;
    }

    @Override
    public void login(String usr, String pwd) {
        BmobUser user = new BmobUser();
        user.setUsername(usr);
        user.setEmail(usr);
        user.setPassword(pwd);
        user.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    if (mCallback != null) {
                        mCallback.onLoginSuccess();
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onLoginFailed(e.getMessage());
                    }
                }
            }
        });
    }

    @Override
    public void signUp(String email, String pwd, String surePwd) {
        BmobUser user = new BmobUser();
        user.setUsername(email);
        user.setEmail(email);
        user.setPassword(pwd);
        user.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    if (mCallback != null) {
                        mCallback.onSignUpSuccess();
                    }
                } else {
                    if (mCallback != null) {
                        mCallback.onSignUpFailed(e.getMessage());
                    }
                }
            }
        });
    }
}
