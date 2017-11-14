package com.jinjunhuang.eatwhat.model.bean;

import java.util.Date;

import cn.bmob.v3.BmobObject;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/21.
 */

public class FoodBean extends BmobObject {

    private String id;
    private Integer kind;
    private String name;
    private Integer canteen;
    private Float price;
    private Float score;

    public FoodBean() {
        this.setTableName("food");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getCanteen() {
        return canteen;
    }

    public void setCanteen(int canteen) {
        this.canteen = canteen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

}
