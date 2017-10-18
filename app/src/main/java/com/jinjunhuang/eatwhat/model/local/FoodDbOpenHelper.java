package com.jinjunhuang.eatwhat.model.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/21.
 */

public class FoodDbOpenHelper extends SQLiteOpenHelper {

    private volatile static FoodDbOpenHelper helper;

    public static final String FOOD_TABLE = "food";
    public static final String FOOD_I_EAT_TABLE = "eat";

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
        String foodTableSql = "create table if not exists " + FOOD_TABLE + " (canteen integer , name varchar(64) , price float , kind integer , score float , id varchar(32) primary key)";
        String eatTableSql = "create table if not exists " + FOOD_I_EAT_TABLE + " (id varchar(32) , date varchar(32),score float,foreign key(id) references " + FOOD_TABLE + "(id) on delete cascade)";
        db.execSQL(foodTableSql);
        db.execSQL(eatTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
