package com.jinjunhuang.eatwhat.view.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.demo.jianjunhuang.mvptools.integration.BaseFragment;
import com.jinjunhuang.eatwhat.R;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/17.
 */

public class AddFoodFragment extends BaseFragment implements Spinner.OnItemSelectedListener {

    private Spinner canteenSp;
    private Spinner kindSp;
    private EditText nameEdt;
    private EditText priceEdt;

    @Override
    protected int getLayoutId() {
        return R.layout.add_food_fragment;
    }

    @Override
    protected void initView(View view) {
        canteenSp = findView(R.id.add_canteen_sp);
        kindSp = findView(R.id.add_kind_sp);
        nameEdt = findView(R.id.add_input_edt);
        priceEdt = findView(R.id.add_price_edt);
        canteenSp.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.canteen_array, android.R.layout.simple_spinner_dropdown_item));
        kindSp.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.kind_array, android.R.layout.simple_spinner_dropdown_item));
    }

    @Override
    protected void initListener() {
        canteenSp.setOnItemSelectedListener(this);
        kindSp.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
