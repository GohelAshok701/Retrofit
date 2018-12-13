package com.retrofit.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.retrofit.R;
import com.retrofit.model.Example;

import java.util.List;

/**
 * Created by Ashok on 12/13/2018.
 */

public class URLAdapter extends RecyclerView.Adapter<URLAdapter.MyURLHolder> {

    private Activity activity;
    private List<Example.File> files;

    public URLAdapter(Activity activity, List<Example.File> files) {
        this.activity=activity;
        this.files=files;
    }

    @Override
    public MyURLHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_url,parent,false);
        return new MyURLHolder(view);
    }

    @Override
    public void onBindViewHolder(MyURLHolder holder, int position) {
        holder.txt_url.setText(files.get(position).getPath());
    }

    @Override
    public int getItemCount() {
        return files==null?0:files.size();
    }

    class MyURLHolder extends RecyclerView.ViewHolder
    {

        private TextView txt_url;

        public MyURLHolder(View itemView) {
            super(itemView);
            txt_url=itemView.findViewById(R.id.txt_url);
        }
    }
}
