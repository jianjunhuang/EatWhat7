package com.jinjunhuang.eatwhat.model;

/**
 * @author jianjunhuang.me@foxmail.com
 *         create on 2017/9/21.
 */

public class FilterBean {
    private int canteen = -1;
    private float startPrice = -1;
    private float endPrice = -1;
    private int kind = -1;

    public int getCanteen() {
        return canteen;
    }

    public void setCanteen(int canteen) {
        this.canteen = canteen;
    }

    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public float getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(float endPrice) {
        this.endPrice = endPrice;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
