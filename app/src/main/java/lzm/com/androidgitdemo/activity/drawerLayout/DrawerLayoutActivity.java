package lzm.com.androidgitdemo.activity.drawerLayout;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import lzm.com.androidgitdemo.R;

public class DrawerLayoutActivity extends AppCompatActivity {

    private ListView lv;
    private String[] lists = {"首页","运动","会员"};
    private DrawerLayout mDrawerLayout;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DrawerLayoutActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rawer_layout);
        initView();
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DrawerLayoutActivity.this,android.R.layout.simple_list_item_1,lists);
        lv.setAdapter(adapter);
//        设置侧滑弹出的listview点击事件,点击后关闭侧滑
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                关闭侧滑listView
                mDrawerLayout.closeDrawer(lv);
//                弹出提示
                Toast.makeText(DrawerLayoutActivity.this,lists[position],Toast.LENGTH_LONG).show();
            }
        });
//        设置侧滑框关闭和打开的监听器
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                super.onDrawerSlide(drawerView,slideOffset);
                Log.i("===show==","onDrawerSlide");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
                Log.i("================","onDrawerOpened");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.i("================","onDrawerClosed");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.i("================","onDrawerStateChanged");
            }
        });
    }
}
