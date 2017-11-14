package com.jinjunhuang.eatwhat.common.application;

import android.util.Log;
import android.util.Xml;

import com.demo.jianjunhuang.mvptools.integration.BaseApplication;
import com.jinjunhuang.eatwhat.R;
import com.jinjunhuang.eatwhat.model.local.FoodDbOpenHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

import cn.bmob.v3.Bmob;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/17.
 */

public class EatWhatApplication extends BaseApplication {
    private static final String TAG = "EatWhatApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        FoodDbOpenHelper.init(this);
        initBmob();
    }

    private void initBmob() {
        String appId = "";
        XmlPullParser parser = this.getResources().getXml(R.xml.application_id);
        try {
            int event;
            while ((event = parser.next()) != XmlPullParser.END_DOCUMENT) {
                if (event == XmlPullParser.START_TAG) {
                    appId = parser.getAttributeValue(0);
                    Log.i(TAG, "onCreate: " + appId);
                }
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bmob.initialize(this, appId, "bmob");
    }
}
