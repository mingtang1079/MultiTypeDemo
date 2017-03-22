package com.example.administrator.multitypedemo;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Adapter mAdapter;
    List<BaseModel> mBaseModels=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView= (RecyclerView) findViewById(R.id.re);
        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mAdapter=new Adapter(mBaseModels,this);


        //模拟用rxjava从网络获取三种数据

        String linshi="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490112117267&di=988a7e4d3be975e21fae38a443dec78c&imgtype=0&sr" +
        "c=http%3A%2F%2Fi.zeze.com%2Fattachment%2Fforum%2F201505%2F17%2F160829tt2ezajyijs1m8dm.jpg";
        零食 m零食=new 零食(linshi);

        String shuiguo="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490112574488&di=b87c724e86a5a3bac0f8b06f186e71fc&imgtype=0&src=http%3A%2F%2Fwww.chunguowang.com%2Fprice%2F20160913%2F2016091309473932.jpg";
        String shuiguo2="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4021146374,2779463116&fm=206&gp=0.jpg";
        String shuiguo3="https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1193804845,2452422438&fm=206&gp=0.jpg";

        水果 m水果=new 水果(shuiguo,shuiguo2,shuiguo3);

        String shucai1="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490112637648&di=a3dffa0649ac35551157b1c7239d5eea&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F35%2F44%2F28858PICJga_1024.jpg";
        String shucai2="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490112667399&di=db96b0dd2f76016fb00e9953ced3d9d8&imgtype=0&src=http%3A%2F%2Fimg.msysg.com%2Fallimg%2F150325%2F150325231005-2303.jpg";
        蔬菜 m蔬菜=new 蔬菜(shucai1,shucai2);


        //模拟获取零食的数据,延迟2秒得到数据
      Observable<零食> m零食Observable=   Observable.just(m零食)
                .delay(2, TimeUnit.SECONDS);

        //模拟获取零食的数据
        Observable<水果> m水果Observable=   Observable.just(m水果)
                .delay(2, TimeUnit.SECONDS);

        //模拟获取零食的数据
        Observable<蔬菜> m蔬菜Observable=   Observable.just(m蔬菜)
                .delay(2, TimeUnit.SECONDS);


        //组合三个从网络获取的数据
        final ProgressDialog mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setMessage("请稍候……");
        mProgressDialog.setCanceledOnTouchOutside(false);

        
        Observable.zip(m零食Observable, m水果Observable, m蔬菜Observable, new Func3<零食, 水果, 蔬菜, List<BaseModel>>() {
            @Override
            public List<BaseModel> call(零食 m零食, 水果 m水果, 蔬菜 m蔬菜) {

             List<BaseModel> mBaseModels=   new ArrayList<>();
                mBaseModels.add(m零食);
                mBaseModels.add(m蔬菜);
                mBaseModels.add(m水果);
                return mBaseModels;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mProgressDialog.show();
                    }
                })
                .subscribe(new Action1<List<BaseModel>>() {
                    @Override
                    public void call(List<BaseModel> m) {
                        mProgressDialog.cancel();
                        mBaseModels.addAll(m);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });


    }














}
