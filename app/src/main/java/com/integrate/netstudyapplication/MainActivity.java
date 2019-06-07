package com.integrate.netstudyapplication;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.integrate.mylibrary.nethelper.nethelper.BaseResponse;
import com.integrate.mylibrary.nethelper.nethelper.IJsonDataListener;
import com.integrate.mylibrary.nethelper.nethelper.OkHttp;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.base.RViewAdapter;
import com.integrate.mylibrary.nethelper.recyclerviewhelper.listener.ItemListener;
import com.integrate.netstudyapplication.bean.UserInfo;
import com.integrate.netstudyapplication.mulit.MulitAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseRecyclerViewActivity {

//    private String url = "http://114.115.217.252:8001/HighMallServer/homepage/guesslikeCompany?apitoken=1559029812852&sign=035ed12a926ea0d1684ad70cdac48ed9&guid=9b3d1f32cb3c45cdba1d0800c38bc582&nonce=1559029813360";
    private String url = "http://jkfhsjkdf";
    private List<UserInfo> datas = new ArrayList<>();
    private RViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recyclerView = findViewById(R.id.recyclerview);
//        Log.e("--m1-tag:",recyclerView+"");
//        initData();
//        setRequest();
        setListener();

    }



    //联网请求的测试
    private void setRequest(){
        OkHttp.setJsonRequeset(url, null, BaseResponse.class, new IJsonDataListener<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse m) {
                Log.e("====>",m.toString());
            }

            @Override
            public void onFailure() {

            }
        });

    }

    @Override
    public void onRefresh() {

    }

    private void setListener(){

        adapter.setItemListener(new ItemListener<UserInfo>() {
            @Override
            public void onItemClick(View view, UserInfo entity, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, UserInfo entity, int position) {
                return false;
            }
        });
    }

    @Override
    public RecyclerView createRecyclerView() {
        return null;
    }

    @Override
    public RViewAdapter createRecyclerViewAdapter() {
//        adapter = new RViewAdapter<UserInfo>(datas, new RViewItem<UserInfo>() {
//            @Override
//            public int getItemLayout() {
//                return R.layout.layout_aitem;
//            }
//
//            @Override
//            public boolean openClick() {
//                return true;
//            }
//
//            @Override
//            public boolean isItemView(UserInfo entity, int position) {
//                return true;
//            }
//
//            @Override
//            public void convert(RViewHolder holder, UserInfo entity, int position) {
//                TextView t= holder.getView(R.id.tv_account);
//                t.setText(entity.getAccount());
//            }
//        });
        adapter = new MulitAdapter(datas);
        return adapter;
    }
}
