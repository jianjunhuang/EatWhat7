package com.jinjunhuang.eatwhat.model.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jinjunhuang.eatwhat.model.bean.FilterBean;
import com.jinjunhuang.eatwhat.model.bean.FoodBean;
import com.jinjunhuang.eatwhat.model.IFoodDataOp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/21.
 */

public class FoodLocalDataOp implements IFoodDataOp {

    private String foodTable = FoodDbOpenHelper.FOOD_TABLE;
    private String eatTable = FoodDbOpenHelper.FOOD_I_EAT_TABLE;

    @Override
    public void getSpecifyFoods(FilterBean bean, LoadFoodCallback callback) {
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
            callback.onDataNotAvilable();
        } finally {
            if (database != null) {
                database.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        callback.onFoodLoaded(foodList);
    }

    @Override
    public void getAllFoods(LoadFoodCallback callback) {
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
            callback.onDataNotAvilable();
        } finally {
            if (database != null) {
                database.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        callback.onFoodLoaded(foodList);
    }

    @Override
    public void getIEatFoods(LoadFoodCallback callback) {
        SQLiteDatabase database = null;
        List<FoodBean> foodList = new ArrayList<>();
        String sql = "select * from " + foodTable + " and " + eatTable;
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
                foodBean.setCanteen(cursor.getInt(cursor.getColumnIndex("canteen")));
                foodBean.setName(cursor.getString(cursor.getColumnIndex("name")));
                foodList.add(foodBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callback.onDataNotAvilable();
        } finally {
            if (database != null) {
                database.close();
            }
            if (cursor != null) {
                cursor.close();
            }
        }
        callback.onFoodLoaded(foodList);
    }

    @Override
    public void addFood(FoodBean foodBean) {
        SQLiteDatabase database = null;
        String sql = "insert into " + foodTable + " (canteen , name , price , kind , score , id )values(?,?,?,?,?,?)";
        try {
            database = FoodDbOpenHelper.getHelper().getWritableDatabase();
            database.execSQL(sql, new Object[]{foodBean.getCanteen(), foodBean.getName(), foodBean.getPrice(), foodBean.getKind(), foodBean.getScore(), foodBean.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    @Override
    public void changeScore(FoodBean bean) {
        SQLiteDatabase database = null;
        String sql = "update " + foodTable + " (price)values(?) where id = ?";
        try {
            database = FoodDbOpenHelper.getHelper().getWritableDatabase();
            database.execSQL(sql, new Object[]{bean.getPrice(), bean.getId()});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }
}
