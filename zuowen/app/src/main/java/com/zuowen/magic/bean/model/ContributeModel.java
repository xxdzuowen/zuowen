package com.zuowen.magic.bean.model;


import com.zuowen.magic.bean.BaseModel;

/**
 * Created by worm on 2016/1/12.
 */
public class ContributeModel extends BaseModel {
    public String id;
    public String title;

    @Override
    public String toString() {
        return "ContributeModel{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
