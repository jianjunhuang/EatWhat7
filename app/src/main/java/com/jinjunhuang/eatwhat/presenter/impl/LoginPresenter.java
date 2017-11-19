package com.jinjunhuang.eatwhat.presenter.impl;

import android.text.TextUtils;

import com.jinjunhuang.eatwhat.contract.LoginContract;
import com.jinjunhuang.eatwhat.model.imp.LoginModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        mModel.setCallback(new LoginContract.Model.Callback() {
            @Override
            public void onLoginSuccess() {
                mView.loginSuccess();
            }

            @Override
            public void onLoginFailed(String reason) {
                mView.loginFailed(reason);
            }

            @Override
            public void onSignUpSuccess() {
                mView.signUpSuccess();
            }

            @Override
            public void onSignUpFailed(String reason) {
                mView.signUpFailed(reason);
            }
        });
    }

    @Override
    public void onDestroy() {
        mView = null;
        mModel = null;
    }

    @Override
    public void login(String usr, String pwd) {
        boolean tag = true;
        if (!isEmail(usr)) {
            mView.showInputEmailErr();
            tag = false;
        }
        if (TextUtils.isEmpty(pwd)) {
            mView.showInputPwdErr();
            tag = false;
        }
        if (!tag) {
            return;
        }

        mModel.login(usr, pwd);
    }

    @Override
    public void signUp(String usr, String pwd, String surePwd) {
        boolean tag = true;
        if (!isEmail(usr)) {
            mView.showInputEmailErr();
            tag = false;
        }
        if (TextUtils.isEmpty(pwd) || pwd.length() < 8) {
            mView.showNotInputPwdErr();
            tag = false;
        }
        if (TextUtils.isEmpty(surePwd) || surePwd.length() < 8) {
            mView.showNotInputSurePwdErr();
            tag = false;
        }

        if (!tag) {
            return;
        }

        if (!pwd.equals(surePwd)) {
            mView.showInputSurePwdErr();
            tag = false;
        }

        if (!tag) {
            return;
        }

        mModel.signUp(usr, pwd, surePwd);
    }

    private boolean isEmail(String email) {
        email.trim();
        String patterStr = "[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.[a-z]+";
        Pattern pattern = Pattern.compile(patterStr);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
