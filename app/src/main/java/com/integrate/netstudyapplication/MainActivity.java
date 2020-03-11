package com.integrate.netstudyapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.integrate.mylibrary.nethelper.myrecyclerview.base.RViewAdapter;
import com.integrate.mylibrary.nethelper.nethelper.BaseResponse;
import com.integrate.mylibrary.nethelper.nethelper.IJsonDataListener;
import com.integrate.mylibrary.nethelper.nethelper.OkHttp;
import com.integrate.netstudyapplication.bean.UserInfo;
import com.integrate.netstudyapplication.util.MyRunnable;
import com.integrate.netstudyapplication.view.GradientDemoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

//    private String url = "http://114.115.217.252:8001/HighMallServer/homepage/guesslikeCompany?apitoken=1559029812852&sign=035ed12a926ea0d1684ad70cdac48ed9&guid=9b3d1f32cb3c45cdba1d0800c38bc582&nonce=1559029813360";
    private String url = "http://jkfhsjkdf";
    private List<UserInfo> datas = new ArrayList<>();
    private RViewAdapter adapter;
    private RecyclerView recyclerView;
    private Timer mTimer1;
    private TimerTask timerTask;
    private Handler handler  = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText(System.currentTimeMillis()+"");
        }
    };

    private TextView mTextView;
    private MyRunnable myRunnable;
    private GradientDemoView gradientDemoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        mTextView = findViewById(R.id.tv_click);
        gradientDemoView = findViewById(R.id.gradient_view);

//        Log.e("--m1-tag:",recyclerView+"");
//        initData();
//        setRequest();
//        helper = new RViewHelper.Builder(this, this).build();
//        initData();
//        if(adapter == null) {
//            adapter = new RViewAdapter(datas, new RViewItem<UserInfo>() {
//                @Override
//                public int getItemLayoutId() {
//                    return R.layout.layout_aitem;
//                }
//
//                @Override
//                public boolean isOpenClick() {
//                    return false;
//                }
//
//                @Override
//                public boolean isViewType(UserInfo enterty, int position) {
//                    return true;
//                }
//
//                @Override
//                public void convert(RViewHolder holder, UserInfo enterty, int position) {
//                    TextView t= holder.getView(R.id.tv_account);
//                    t.setText(enterty.getAccount());
//                }
//
//            });
//            adapter = new MulitAdapter(this,datas);
//
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setItemAnimator(new RecyclerView(this).getItemAnimator());
//            recyclerView.setAdapter(adapter);
//
//        }
//        setListener();

//        String a[] = new String[]{};
//        a[0] = "112";
//        a[1] = "4564";
        Random r = new Random();
        Log.e("---tag:",r.nextLong()+"");


    }

    public void Start(View view) {
        Random r = new Random();
        Log.e("---tag:",r.nextInt()+"");
//        Time();
        if(myRunnable == null) {
            myRunnable = new MyRunnable(mTextView,1000*6,handler);
            myRunnable.setOnStop(new MyRunnable.onStop() {
                @Override
                public void action() {
                    Log.e(" start",myRunnable+"88"+handler);
                    handler.removeCallbacks(myRunnable);
                    myRunnable = null;
                }
            });
            handler.postDelayed(myRunnable,0);
        }
    }

    private void Time(){
        if (mTimer1 == null && timerTask == null) {
            mTimer1 = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    Message message = handler.obtainMessage(1);
                    handler.sendMessage(message);
                }
            };
            mTimer1.schedule(timerTask, 0, 1000);
        }
    }

    private void initData() {
        if (datas.isEmpty()) {
            for (int i = 0; i < 105; i++) {
                UserInfo u = new UserInfo();
                if (i % 5 == 1) {
                    u.setAccount("ACCOUNT》》》》》" + i);
                    u.setType(1);
                } else if (i % 4 == 1) {
                    u.setAccount("ACCOUNT》》》》》" + i);
                    u.setType(2);
                } else if (i % 3 == 1) {
                    u.setAccount("ACCOUNT》》》》》" + i);
                    u.setType(3);
                } else  {
                    u.setAccount("ACCOUNT》》》》》" + i);
                    u.setType(4);
                }
                datas.add(u);
            }
        }
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

    public void Stop(View view) {
        handler.removeCallbacks(myRunnable);
        myRunnable = null;
    }

//    @Override
//    public void onRefresh() {
//        notifyAdapterDataSetChanged(datas);
//    }
//
//    @Override
//    public SwipeRefreshLayout createSwipRefresh() {
//        return findViewById(R.id.srl);
//    }
//
//    private void setListener(){
//
//        adapter.setItemListener(new ItemListener<UserInfo>() {
//            @Override
//            public void onItemClick(View view, UserInfo entity, int position) {
//                Toast.makeText(MainActivity.this,"点击了第一种类型",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, UserInfo entity, int position) {
//                return false;
//            }
//        });
//    }
//
//    @Override
//    public RecyclerView createRecyclerView() {
//        Log.e("--bm-tag:",findViewById(R.id.recyclerview)+"");
//        return findViewById(R.id.recyclerview);
//    }
//
//    @Override
//    public RecyclerView.LayoutManager createLayoutManager() {
//        return new LinearLayoutManager(this);
//    }
//
//    @Override
//    public RecyclerView.ItemDecoration createItemDecoration() {
//        return new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
//    }
//
//    @Override
//    public RViewAdapter createRecyclerViewAdapter() {
////        adapter = new RViewAdapter<UserInfo>(datas, new RViewItem<UserInfo>() {
////            @Override
////            public int getItemLayout() {
////                return R.layout.layout_aitem;
////            }
////
////            @Override
////            public boolean openClick() {
////                return true;
////            }
////
////            @Override
////            public boolean isItemView(UserInfo entity, int position) {
////                return true;
////            }
////
////            @Override
////            public void convert(RViewHolder holder, UserInfo entity, int position) {
////                TextView t= holder.getView(R.id.tv_account);
////                t.setText(entity.getAccount());
////            }
////        });
//        adapter = new MulitAdapter(this,datas);
//        return adapter;
////        return super.createRecyclerViewAdapter();
//    }
}
