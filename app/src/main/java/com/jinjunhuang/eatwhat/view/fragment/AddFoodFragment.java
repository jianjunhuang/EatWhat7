package com.jinjunhuang.eatwhat.view.fragment;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.demo.jianjunhuang.mvptools.integration.BaseFragment;
import com.demo.jianjunhuang.mvptools.utils.UiUtils;
import com.jinjunhuang.eatwhat.R;
import com.jinjunhuang.eatwhat.model.local.FoodLocalDataOp;
import com.jinjunhuang.eatwhat.model.FoodBean;
import com.jinjunhuang.loadingcirclebtn.LoadingCircleBtn;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/17.
 */

public class AddFoodFragment extends BaseFragment implements Spinner.OnItemSelectedListener, View.OnClickListener {

    private Spinner canteenSp;
    private Spinner kindSp;
    private EditText nameEdt;
    private EditText priceEdt;
    private int canteenTag = -1;
    private int kindTag = -1;
    private LoadingCircleBtn addBtn;

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
        addBtn = findView(R.id.add_btn);
        canteenSp.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.canteen_array, android.R.layout.simple_spinner_dropdown_item));
        kindSp.setAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.kind_array, android.R.layout.simple_spinner_dropdown_item));
    }

    @Override
    protected void initListener() {
        canteenSp.setOnItemSelectedListener(this);
        kindSp.setOnItemSelectedListener(this);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.add_canteen_sp) {
            canteenTag = position;
        } else {
            kindTag = position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private static final String TAG = "AddFoodFragment";

    @Override
    public void onClick(View v) {

        if (addBtn.getStatus() == LoadingCircleBtn.STATUS_DEFAULT) {

            String name = nameEdt.getText().toString();
            String priceStr = priceEdt.getText().toString();
            if (UiUtils.isAllBlockChar(name)) {
                Snackbar.make(getView(), "请输入名称", Snackbar.LENGTH_LONG).show();
                return;
            }
            if (UiUtils.isAllBlockChar(priceStr)) {
                Snackbar.make(getView(), "请输入价格", Snackbar.LENGTH_LONG).show();
                return;
            }
            addBtn.setStatus(LoadingCircleBtn.STATUS_LOADING);

            FoodBean foodBean = new FoodBean();
            foodBean.setCanteen(canteenSp.getSelectedItemPosition());
            foodBean.setName(name);
            foodBean.setPrice(Float.valueOf(priceStr));
            foodBean.setKind(kindSp.getSelectedItemPosition());
            foodBean.setId(name + canteenSp.getSelectedItemPosition() + priceStr + kindSp.getSelectedItemPosition());

            FoodLocalDataOp dao = new FoodLocalDataOp();
            boolean tag = dao.addFood(foodBean);
            if (tag) {
                addBtn.setStatus(LoadingCircleBtn.STATUS_LOAD_SUCCESS);
            } else {
                addBtn.setStatus(LoadingCircleBtn.STATUS_LOAD_FAILED);
            }
        } else if (addBtn.getStatus() != LoadingCircleBtn.STATUS_LOADING) {
            addBtn.setStatus(LoadingCircleBtn.STATUS_DEFAULT);
        }
    }
}
