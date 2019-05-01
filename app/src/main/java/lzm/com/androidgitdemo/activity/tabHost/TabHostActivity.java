package lzm.com.androidgitdemo.activity.tabHost;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import lzm.com.androidgitdemo.R;

public class TabHostActivity extends AppCompatActivity {
//注意的是有TabHost和FragmentTabHost
    private FragmentTabHost tabHost;
    private LayoutInflater mInflater;
    private RadioGroup radioGroup;
    private RadioButton rb_home;
    private RadioButton rb_store;
    private RadioButton rb_vip;
    private RadioButton rb_info;
    private Resources resources;

    private Class[] fragments = {TabHostFragmentOne.class,TabHostFragmentTwo.class};
    private String[] txt = {"新闻","体育"};
//    图标
    private int[] imags ={R.drawable.ic_accessible_black_48dp,R.drawable.ic_account_balance_black_48dp};

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TabHostActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);
        initview();
    }

    private void initview() {
        resources = getResources();
        // 实例化TabHost对象，得到TabHost
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
//        tabHost.setup();
        // 实例化布局对象
        mInflater = LayoutInflater.from(this);
//        得到所有fragment的数量
        int count = fragments.length;
        for (int i=0;i<count;i++){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(txt[i]).setIndicator(txt[i]);
            tabHost.addTab(tabSpec,fragments[i],null);
        }
        rb_store = (RadioButton) findViewById(R.id.guide_store);
        rb_vip = (RadioButton) findViewById(R.id.guide_vip);
        rb_home = (RadioButton) findViewById(R.id.guide_home);
        rb_info = (RadioButton) findViewById(R.id.guide_info);

//        绑定radioButton的事件
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.guide_home:
                        rb_home.setBackgroundColor(resources.getColor(R.color.colorBlue));
                        rb_store.setBackgroundColor(resources.getColor(R.color.colorWhite));
                        rb_info.setBackgroundColor(resources.getColor(R.color.colorWhite));
                        rb_vip.setBackgroundColor(resources.getColor(R.color.colorWhite));
//                        切换fragment
                        tabHost.setCurrentTab(0);
                        break;
                    case R.id.guide_vip:
                        rb_vip.setBackgroundColor(resources.getColor(R.color.colorBlue));
                        rb_store.setBackgroundColor(resources.getColor(R.color.colorWhite));
                        rb_info.setBackgroundColor(resources.getColor(R.color.colorWhite));
                        rb_home.setBackgroundColor(resources.getColor(R.color.colorWhite));
                        tabHost.setCurrentTab(1);
                }
            }
        });
    }
    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = mInflater.inflate(R.layout.item_tab, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_icon);
        if (imageView != null) {
            imageView.setImageResource(imags[index]);
        }
        TextView textView = (TextView) view.findViewById(R.id.text_name);
        textView.setText(txt[index]);
        return view;
    }
    /**
     * 给Tab选项卡设置内容（每个内容是一个Activity）
     */
//    private Intent getTabItemIntent(int index) {
//        Intent intent = new Intent(this, tabClassArrays[index]);
//        return intent;
//    }
}
