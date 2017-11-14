package com.jinjunhuang.eatwhat.view.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.demo.jianjunhuang.mvptools.integration.BaseActivity;
import com.demo.jianjunhuang.mvptools.utils.ToastUtils;
import com.jinjunhuang.eatwhat.R;
import com.jinjunhuang.eatwhat.contract.LoginContract;
import com.jinjunhuang.eatwhat.presenter.impl.LoginPresenter;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/22.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContract.View {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private FloatingActionButton signUpFab;

    private TextInputLayout emailInputLayout;
    private TextInputLayout pwdInputLayout;
    private TextInputLayout pwdSureInputLayout;
    private TextInputLayout usrnameInputLayout;

    private TextInputEditText emailInputEdt;
    private TextInputEditText pwdInputEdt;
    private TextInputEditText pwdSureEdt;
    private TextInputEditText usrnameEdt;

    private Button loginBtn;

    private boolean isLoginStatus = true;

    private LoginContract.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView() {

        mPresenter = new LoginPresenter(this);
        mPresenter.onStart();

        signUpFab = findView(R.id.login_sign_up_btn);
        mCollapsingToolbarLayout = findView(R.id.login_collapsing_toolbar_layout);
        initToolbar();
        emailInputLayout = findView(R.id.login_email_text_input_layout);
        emailInputEdt = findView(R.id.login_email_text_input_edt);
        pwdInputLayout = findView(R.id.login_pwd_text_input_layout);
        pwdInputEdt = findView(R.id.login_pwd_text_input_edt);
        pwdSureEdt = findView(R.id.login_pwd_sure_text_input_edt);
        pwdSureInputLayout = findView(R.id.login_pwd_sure_text_input_layout);
        usrnameEdt = findView(R.id.login_usr_name_text_input_edt);
        usrnameInputLayout = findView(R.id.login_usr_name_text_input_layout);
        loginBtn = findView(R.id.login_btn);
    }

    private void initToolbar() {
        mToolbar = findView(R.id.login_toolbar);
        mAppBarLayout = findView(R.id.login_appbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private static final String TAG = "LoginActivity";

    @Override
    protected void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.i(TAG, "onOffsetChanged: " + verticalOffset);
            }
        });

        signUpFab.setOnClickListener(this);

        loginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_sign_up_btn: {
                if (isLoginStatus) {
                    changeToSignUpStatus();
                } else {
                    changeToLoginStatus();
                }
                break;
            }
            case R.id.login_btn: {
                ToastUtils.show("login");
                break;
            }
        }
    }

    private void changeToLoginStatus() {
        isLoginStatus = true;
        pwdSureInputLayout.setVisibility(View.GONE);
        loginBtn.setText(R.string.login);
        signUpFab.setImageResource(R.drawable.ic_add_white_24dp);
    }

    private void changeToSignUpStatus() {
        isLoginStatus = false;
        pwdSureInputLayout.setVisibility(View.VISIBLE);
        loginBtn.setText(R.string.sign_up);
        signUpFab.setImageResource(R.drawable.ic_perm_identity_white_24dp);
    }

    @Override
    public void onBackPressed() {
        if (!isLoginStatus) {
            changeToLoginStatus();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginFailed() {

    }

    @Override
    public void signUpSuccess() {

    }

    @Override
    public void signUpFailed() {

    }
}
