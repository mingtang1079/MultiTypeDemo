package com.example.administrator.multitypedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    List<BaseModel> mDatas;
    Context mContext;

    int SHUCAI=1;
    int FRUIT=2;
    int LINSHI=3;

    public Adapter(List<BaseModel> mDatas, Context mContext) {
        this.mDatas = mDatas;
       this.mContext=mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==SHUCAI)
            return new ShuCaiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vegitables,parent,false));
        else  if (viewType==FRUIT)
            return new ShuiGuoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit,parent,false));
          else
            return  new LinshiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linshi,parent,false));

    }




    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof  ShuCaiViewHolder)
        {

             ShuCaiViewHolder   mViewHolder= (ShuCaiViewHolder) holder;
            蔬菜 m蔬菜= (蔬菜) mDatas.get(position);

            Glide.with(mContext).load(m蔬菜.url1).crossFade().into(mViewHolder.mImageView1);
            Glide.with(mContext).load(m蔬菜.url2).crossFade().into(mViewHolder.mImageView1);

        }
        else  if (holder instanceof  ShuiGuoViewHolder)
        {

            ShuiGuoViewHolder mShuiGuoViewHolder= (ShuiGuoViewHolder) holder;
            水果 m水果= (水果) mDatas.get(position);

            Glide.with(mContext).load(m水果.url1).crossFade().into(mShuiGuoViewHolder.mImageView1);
            Glide.with(mContext).load(m水果.url2).crossFade().into(mShuiGuoViewHolder.mImageView2);
            Glide.with(mContext).load(m水果.url3).crossFade().into(mShuiGuoViewHolder.mImageView3);

        }

        else

        {

            LinshiViewHolder mLinshiViewHolder= (LinshiViewHolder) holder;
             零食 m零食= (零食) mDatas.get(position);
            Glide.with(mContext).load(m零食.url1).crossFade().into(mLinshiViewHolder.mImageView1);

        }


    }





    @Override
    public int getItemViewType(int position) {

        if (mDatas.get(position) instanceof  蔬菜)
          return SHUCAI;
        else if (mDatas.get(position) instanceof 水果)
            return  FRUIT;
        else
            return LINSHI;

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }




    class ShuCaiViewHolder extends RecyclerView.ViewHolder
    {

        ImageView mImageView1;
        ImageView mImageView2;

        public ShuCaiViewHolder(View view)
        {
            super(view);
            mImageView1 = (ImageView) view.findViewById(R.id.shucai1);
            mImageView2 = (ImageView) view.findViewById(R.id.shucai2);
        }

    }
    class ShuiGuoViewHolder extends RecyclerView.ViewHolder
    {

        ImageView mImageView1;
        ImageView mImageView2;
        ImageView mImageView3;

        public ShuiGuoViewHolder(View view)
        {
            super(view);
            mImageView1 = (ImageView) view.findViewById(R.id.shuiguo1);
            mImageView2 = (ImageView) view.findViewById(R.id.shuiguo2);
            mImageView3= (ImageView) view.findViewById(R.id.shuiguo3);
        }
    }
    class LinshiViewHolder extends RecyclerView.ViewHolder
    {

        ImageView mImageView1;


        public LinshiViewHolder(View view)
        {
            super(view);
            mImageView1 = (ImageView) view.findViewById(R.id.linshi);
        }
    }




}
