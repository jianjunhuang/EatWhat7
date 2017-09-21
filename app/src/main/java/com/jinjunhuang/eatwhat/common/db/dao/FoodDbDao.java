package com.jinjunhuang.eatwhat.common.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jinjunhuang.eatwhat.common.db.helper.FoodDbOpenHelper;
import com.jinjunhuang.eatwhat.model.FilterBean;
import com.jinjunhuang.eatwhat.model.FoodBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/21.
 */

public class FoodDbDao implements IFoodDbDao<FoodBean, FilterBean> {

    private String foodTable = "food";
    private String eatTable = "eat";

    @Override
    public List<FoodBean> getSpecifyFoods(FilterBean bean) {
        SQLiteDatabase database = null;
        Cursor cursor = null;
        List<FoodBean> foodList = new ArrayList<>();
        StringBuffer sqlSb = new StringBuffer("select * from " + foodTable);
        if (bean != null) {
            sqlSb.append(" where ");
            if (bean.getCanteen() != -1) {
                sqlSb.append("canteen = ");
                sqlSb.append(bean.getCanteen());
            }
            if (bean.getStartPrice() != -1 && bean.getEndPrice() != -1) {
                sqlSb.append(" , ");
                sqlSb.append("price >= ");
                sqlSb.append(bean.getStartPrice() + " , ");
                sqlSb.append("price <= ");
                sqlSb.append(bean.getEndPrice());
            }
            if (bean.getKind() != -1) {
                sqlSb.append(" , ");
                sqlSb.append("kind = ");
                sqlSb.append(bean.getEndPrice());
            }
        }
        try {
            database = FoodDbOpenHelper.getHelper().getReadableDatabase();
            cursor = database.rawQuery(sqlSb.toString(), null);
            while (cursor.moveToNext()) {
                FoodBean foodBean = new FoodBean();
                foodBean.setCanteen(cursor.getInt(0));
                foodBean.setName(cursor.getString(1));
                foodBean.setPrice(cursor.getFloat(2));
                foodBean.setKind(cursor.getInt(3));
                foodBean.setScore(cursor.getFloat(4));
                foodBean.setId(cursor.getString(5));
                foodList.add(foodBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return foodList;
    }

    @Override
    public List<FoodBean> getAllFoods() {
        SQLiteDatabase database = null;
        List<FoodBean> foodList = new ArrayList<>();
        String sql = "select * from " + foodTable;
        Cursor cursor = null;
        try {
            database = FoodDbOpenHelper.getHelper().getReadableDatabase();
            cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                FoodBean foodBean = new FoodBean();
                foodBean.setCanteen(cursor.getInt(0));
                foodBean.setName(cursor.getString(1));
                foodBean.setPrice(cursor.getFloat(2));
                foodBean.setKind(cursor.getInt(3));
                foodBean.setScore(cursor.getFloat(4));
                foodBean.setId(cursor.getString(5));
                foodList.add(foodBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return foodList;
    }

    @Override
    public List<FoodBean> getIEatFoods() {
        SQLiteDatabase database = null;
        List<FoodBean> foodList = new ArrayList<>();
        String sql = "select * from food and eat";
        Cursor cursor = null;
        try {
            database = FoodDbOpenHelper.getHelper().getReadableDatabase();
            cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                FoodBean foodBean = new FoodBean();
                foodBean.setId(cursor.getString(cursor.getColumnIndex("id")));
                foodBean.setScore(cursor.getFloat(cursor.getColumnIndex("score")));
                foodBean.setKind(cursor.getInt(cursor.getColumnIndex("kind")));
                foodBean.setPrice(cursor.getFloat(cursor.getColumnIndex("price")));
                foodBean.setDate(cursor.getString(cursor.getColumnIndex("date")));
                foodBean.setCanteen(cursor.getInt(cursor.getColumnIndex("canteen")));
                foodBean.setName(cursor.getString(cursor.getColumnIndex("name")));
                foodList.add(foodBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        return foodList;
    }

    @Override
    public boolean addFood(FoodBean foodBean) {
        SQLiteDatabase database = null;
        boolean flag = false;
        String sql = "insert into food (canteen , name , price , kind , score , id )values(?,?,?,?,?,?)";
        try {
            database = FoodDbOpenHelper.getHelper().getWritableDatabase();
            database.execSQL(sql, new Object[]{foodBean.getCanteen(), foodBean.getName(), foodBean.getPrice(), foodBean.getKind(), foodBean.getScore(), foodBean.getId()});
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return flag;
    }
}
