package com.zuowen.magic.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zuowen.magic.R;
import com.zuowen.magic.bean.model.ArtListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tool on 16/1/16.
 * 文章列表
 */
public class ArtListAdapter extends BaseAdapter{
    private List<ArtListModel> mData;

    public void addData(List<ArtListModel> data){
        if(mData==null){
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addClearData(List<ArtListModel> data){
        if(mData==null){
            return;
        }
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public ArtListAdapter(){
        mData=new ArrayList<>();
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_artlist_item_layout, parent, false);
            holder=new ViewHolder();
            holder.title=(TextView)convertView.findViewById(R.id.info_text);
            holder.author=(TextView)convertView.findViewById(R.id.tv_author);
            holder.data=(TextView)convertView.findViewById(R.id.tv_data);
            holder.content=(TextView)convertView.findViewById(R.id.tv_content);

            holder.tag1=(TextView)convertView.findViewById(R.id.tv_tag1);
            holder.tag2=(TextView)convertView.findViewById(R.id.tv_tag2);
            holder.tag3=(TextView)convertView.findViewById(R.id.tv_tag3);
            holder.tag4=(TextView)convertView.findViewById(R.id.tv_tag4);

            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.author.setText(mData.get(position).author);
        holder.title.setText(mData.get(position).title);
        holder.data.setText(mData.get(position).date);
        holder.content.setText(mData.get(position).intro);
        List<String> strs=mData.get(position).tags;

        holder.tag1.setVisibility(View.GONE);
        holder.tag2.setVisibility(View.GONE);
        holder.tag3.setVisibility(View.GONE);
        holder.tag4.setVisibility(View.GONE);

        for(int i=0;i<strs.size();i++) {
            String str=strs.get(i);
            if (i==0){
                holder.tag1.setText(str);
                holder.tag1.setVisibility(View.VISIBLE);
            }
            if (i==1){
                holder.tag2.setText(str);
                holder.tag2.setVisibility(View.VISIBLE);
            }
            if (i==2){
                holder.tag3.setText(str);
                holder.tag3.setVisibility(View.VISIBLE);
            }
            if (i==3){
                holder.tag4.setText(str);
                holder.tag4.setVisibility(View.VISIBLE);
            }

        }

        return convertView;
    }

    static class ViewHolder{
        private TextView title;
        private TextView author;
        private TextView data;
        private TextView content;


        private TextView tag1;
        private TextView tag2;
        private TextView tag3;
        private TextView tag4;

    }
}
