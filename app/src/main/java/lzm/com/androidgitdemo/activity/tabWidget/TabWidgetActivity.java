package lzm.com.androidgitdemo.activity.tabWidget;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import lzm.com.androidgitdemo.R;
import lzm.com.androidgitdemo.activity.tabHost.TabHostActivity;
import lzm.com.androidgitdemo.activity.tabHost.TabHostActivityOne;
import lzm.com.androidgitdemo.activity.tabHost.TabHostActivityTwo;

//注意是继承了TabHostActivity而不是Activity

public class TabWidgetActivity extends TabActivity {
    private TabHost mTabHost;
    private Resources mResources;
    private LayoutInflater mLayoutInflater;
    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context,TabWidgetActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_widget);
        initView();
    }

    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);
        mResources = getResources();
//        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost = getTabHost();
        TabHost.TabSpec tabSpecOne = mTabHost.newTabSpec("one").
                setIndicator(getTabItemView(1)).
                setContent(new Intent(this, TabHostActivityOne.class));
        mTabHost.addTab(tabSpecOne);
        TabHost.TabSpec tabSpecTwo = mTabHost.newTabSpec("two").
                setIndicator(getTabItemView(2)).
                setContent(new Intent(this, TabHostActivityTwo.class));
        mTabHost.addTab(tabSpecTwo);

//        设置背景色
//        mTabHost.setBackgroundColor(mResources.getColor(android.R.color.holo_blue_bright));
        mTabHost.setBackgroundColor(ContextCompat.getColor(this,android.R.color.holo_blue_bright));
//        默认选择第一个tab
        mTabHost.setTag(0);
//        定义触发切换页面的触发事件
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(TabWidgetActivity.this,"id:"+tabId,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.item_tab,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_icon);
        TextView textView = (TextView) view.findViewById(R.id.text_name);
        switch (index){
            case 1:
                imageView.setImageResource(R.drawable.ic_accessible_black_48dp);
                textView.setText("照片");
                break;
            case 2:
                imageView.setImageResource(R.drawable.ic_account_balance_black_48dp);
                textView.setText("视频");
                break;
        }
        return view;

    }
}
