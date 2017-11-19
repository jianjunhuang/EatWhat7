package com.jinjunhuang.eatwhat.contract;

import com.demo.jianjunhuang.mvptools.mvp.IModel;
import com.demo.jianjunhuang.mvptools.mvp.IPresenter;
import com.demo.jianjunhuang.mvptools.mvp.IView;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/11/19.
 */

public interface RestPwdContract {
    interface View extends IView {

        void restPwdSuccess();

        void restPwdFailed(String reason);

        void showInputEmailErr();

        void showNotInputEmailErr();

        void showInputPwdErr();

        void showNotInputPwdErr();

        void showInputSurePwdErr();

        void showNotInputSurePwdErr();
    }

    interface Model extends IModel {

    }

    interface Presenter extends IPresenter {

    }


}
