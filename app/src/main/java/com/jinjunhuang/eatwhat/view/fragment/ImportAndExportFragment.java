package com.jinjunhuang.eatwhat.view.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.demo.jianjunhuang.mvptools.integration.BaseFragment;
import com.demo.jianjunhuang.mvptools.utils.ToastUtils;
import com.jinjunhuang.eatwhat.R;

import java.net.URISyntaxException;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/17.
 */

public class ImportAndExportFragment extends BaseFragment implements View.OnClickListener {

    private Button importBtn;
    private Button exportBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.import_export_fragment;
    }

    @Override
    protected void initView(View view) {
        importBtn = findView(R.id.import_food_btn);
        exportBtn = findView(R.id.export_food_btn);
    }

    @Override
    protected void initListener() {
        importBtn.setOnClickListener(this);
        exportBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "select a file"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Uri uri = data.getData();

            try {
                ToastUtils.show(getPath(uri));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    private static final String TAG = "ImportAndExportFragment";

    public String getPath(Uri uri) throws URISyntaxException {
        Log.i(TAG, "getPath: " + uri.getScheme());
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor = null;
            try {
                cursor = getContext().getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                // Eat it  Or Log it.
                e.printStackTrace();
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return "failed";
    }
}

