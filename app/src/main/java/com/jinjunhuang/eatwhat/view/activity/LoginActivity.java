package com.jinjunhuang.eatwhat.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.jianjunhuang.mvptools.integration.BaseActivity;
import com.demo.jianjunhuang.mvptools.utils.DeviceUtils;
import com.jinjunhuang.eatwhat.R;
import com.jinjunhuang.eatwhat.contract.LoginContract;
import com.jinjunhuang.eatwhat.presenter.impl.LoginPresenter;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/22.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContract.View {

    private CoordinatorLayout mCoordinatorLayout;

    AlertDialog.Builder mBuilder;
    AlertDialog mDialog;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private AppBarLayout mAppBarLayout;
    private FloatingActionButton signUpFab;

    private TextInputLayout emailInputLayout;
    private TextInputLayout pwdInputLayout;
    private TextInputLayout pwdSureInputLayout;

    private TextInputEditText emailInputEdt;
    private TextInputEditText pwdInputEdt;
    private TextInputEditText pwdSureEdt;

    private TextView forgetPwdTv;

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
        mCoordinatorLayout = findView(R.id.login_coordinator_layout);
        emailInputLayout = findView(R.id.login_email_text_input_layout);
        emailInputEdt = findView(R.id.login_email_text_input_edt);
        pwdInputLayout = findView(R.id.login_pwd_text_input_layout);
        pwdInputEdt = findView(R.id.login_pwd_text_input_edt);
        pwdSureEdt = findView(R.id.login_pwd_sure_text_input_edt);
        pwdSureInputLayout = findView(R.id.login_pwd_sure_text_input_layout);
        loginBtn = findView(R.id.login_btn);
        forgetPwdTv = findView(R.id.login_forget_pwd_tv);
        initDialog();
    }

    private void initToolbar() {
        mToolbar = findView(R.id.login_toolbar);
        mAppBarLayout = findView(R.id.login_appbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDialog() {
        mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(R.string.tips);
        mBuilder.setPositiveButton(R.string.ok, null);
        mBuilder.setMessage(getString(R.string.sign_up_success) + "," + getString(R.string.confirm_tips));
        mDialog = mBuilder.create();
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

        forgetPwdTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clearErrShow();
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
                if (isLoginStatus) {
                    mPresenter.login(emailInputEdt.getText().toString(), pwdInputEdt.getText().toString());
                } else {
                    mPresenter.signUp(emailInputEdt.getText().toString(), pwdInputEdt.getText().toString(), pwdSureEdt.getText().toString());
                }
                break;
            }
            case R.id.login_forget_pwd_tv: {
                Intent intent = new Intent(LoginActivity.this, RestPwdActivity.class);
                startActivity(intent);
                break;
            }
        }
        DeviceUtils.hideSoftKeyboard(this, mCoordinatorLayout);
    }

    private void changeToLoginStatus() {
        isLoginStatus = true;
        pwdSureInputLayout.setVisibility(View.GONE);
        loginBtn.setText(R.string.login);
        signUpFab.setImageResource(R.drawable.ic_add_white_24dp);
        forgetPwdTv.setVisibility(View.VISIBLE);
    }

    private void changeToSignUpStatus() {
        isLoginStatus = false;
        pwdSureInputLayout.setVisibility(View.VISIBLE);
        loginBtn.setText(R.string.sign_up);
        signUpFab.setImageResource(R.drawable.ic_perm_identity_white_24dp);
        forgetPwdTv.setVisibility(View.GONE);
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
        Snackbar.make(mCoordinatorLayout, R.string.login_success, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void loginFailed(String reason) {
        Snackbar.make(mCoordinatorLayout, reason, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void signUpSuccess() {
        Snackbar.make(mCoordinatorLayout, R.string.sign_up_success, Snackbar.LENGTH_INDEFINITE).show();
        mDialog.show();
    }

    @Override
    public void signUpFailed(String reason) {
        Snackbar.make(mCoordinatorLayout, reason, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showInputEmailErr() {
        emailInputLayout.setError(getString(R.string.email_failed));
    }

    @Override
    public void showNotInputEmailErr() {
        emailInputLayout.setError(getString(R.string.email_failed));
    }

    @Override
    public void showInputPwdErr() {
        pwdInputLayout.setError(getString(R.string.pwd_failed));
    }

    @Override
    public void showNotInputPwdErr() {
        pwdInputLayout.setError(getString(R.string.pwd_failed));
    }

    @Override
    public void showInputSurePwdErr() {
        pwdInputLayout.setError(getString(R.string.sure_pwd_not_input_failed));
    }

    @Override
    public void showNotInputSurePwdErr() {
        pwdInputLayout.setError(getString(R.string.sure_pwd_failed));
    }

    private void clearErrShow() {
        emailInputLayout.setError("");
        pwdInputLayout.setError("");
        pwdSureInputLayout.setError("");
    }
}
