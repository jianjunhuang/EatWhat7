package com.jinjunhuang.eatwhat.model;

import cn.bmob.v3.BmobObject;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/10/22.
 */

public class UsrFoodBean extends BmobObject {
    private String email;
    private Integer score;
    private String userId;
    private String foodId;

    public UsrFoodBean() {
        this.setTableName("usr_food_score");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }
}
