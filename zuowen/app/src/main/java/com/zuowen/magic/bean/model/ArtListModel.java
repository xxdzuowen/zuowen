package com.zuowen.magic.bean.model;


import com.zuowen.magic.bean.BaseModel;

import java.util.List;

/**
 * Created by wodediannao on 15/12/25.
 */
public class ArtListModel extends BaseModel {
    public String id;
    public String pid;
    public String aid;
    public String title;
    public String url;
    public String intro;
    public String date;
    public List<String> tags;
    public String cid;
    public String author;
    public String classname;

    @Override
    public String toString() {
        return "ArtListModel{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", aid='" + aid + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", intro='" + intro + '\'' +
                ", date='" + date + '\'' +
                ", tags=" + tags +
                ", cid='" + cid + '\'' +
                ", author='" + author + '\'' +
                ", classname='" + classname + '\'' +
                '}';
    }
}
