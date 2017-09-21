package com.jinjunhuang.eatwhat.common.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/21.
 */

public class FoodDbOpenHelper extends SQLiteOpenHelper {

    private volatile static FoodDbOpenHelper helper;

    public static void init(Context context) {
        if (helper == null) {
            synchronized (FoodDbOpenHelper.class) {
                if (helper == null) {
                    helper = new FoodDbOpenHelper(context);
                }
            }
        }
    }

    private FoodDbOpenHelper(Context context) {
        super(context, "food.db", null, 1);
    }

    public static FoodDbOpenHelper getHelper() {
        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String foodTableSql = "create table if not exist food (canteen integer , name varchar(64) , price float , kind integer , score float , id varchar(32))";
        String eatTableSql = "create table if not exist eat (id varchar(32) , date varchar(32))";
        db.execSQL(foodTableSql);
        db.execSQL(eatTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
