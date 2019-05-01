package lzm.com.androidgitdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import lzm.com.androidgitdemo.activity.GridViewActivity;
import lzm.com.androidgitdemo.activity.database.DataBaseActivity;
import lzm.com.androidgitdemo.activity.drawerLayout.DrawerLayoutActivity;
import lzm.com.androidgitdemo.activity.iconFont.IconFontActivity;
import lzm.com.androidgitdemo.activity.tabHost.TabHostActivity;
import lzm.com.androidgitdemo.activity.tabWidget.TabWidgetActivity;
import lzm.com.androidgitdemo.activity.treeView.AndroidTreeViewActivity;
import lzm.com.androidgitdemo.activity.treeView.AndroidTreeViewActivity2;
import lzm.com.androidgitdemo.adapter.MyExpandableListView;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private String[] groups = {"控件","素材","数据库"};
    private String[][] childs = {{"GridView","ListView","TabHost切换TAB","TabWidget切换tab",
            "DrawerLayout弹出抽屉","AndroidTreeView树形菜单","AndroidTreeView树形菜单2"},
            {"iconfont使用"},
            {"原生Sqlite数据库操作"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        从字符数组获取
        final String[] lists = getResources().getStringArray(R.array.listInfo);
        initView();

    }
    private void initView(){
//        这个控件是有只有2层的树结构的list
        expandableListView = (ExpandableListView) findViewById(R.id.main_expandable_lv);
//        定义专属adapter,有2个入参分别定义了父和子的数据结构
        MyExpandableListView myExpandableListView = new MyExpandableListView(MainActivity.this,groups,childs);
        expandableListView.setAdapter(myExpandableListView);

//        分组点击事件
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(){
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                允许展开
                return false;
            }
        });
        //        明细点击事件
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String activity = childs[groupPosition][childPosition];
                Intent intent = null;
                if(activity.equals("GridView")){
                    intent = GridViewActivity.getStartIntent(MainActivity.this);
                }else if(activity.equals("TabHost切换TAB")){
                    intent = TabHostActivity.getStartIntent(MainActivity.this);
                }else if(activity.equals("TabWidget切换tab")){
                    intent = TabWidgetActivity.getStartIntent(MainActivity.this);
                }else if(activity.equals("DrawerLayout弹出抽屉")){
                    intent = DrawerLayoutActivity.getStartIntent(MainActivity.this);
                }else if(activity.equals("AndroidTreeView树形菜单")){
                    intent = AndroidTreeViewActivity.getStartIntent(MainActivity.this);
                }else if(activity.equals("iconfont使用")){
                    intent = IconFontActivity.getStartIntent(MainActivity.this);
                }else if(activity.equals("AndroidTreeView树形菜单2")){
                    intent = AndroidTreeViewActivity2.getStartIntent(MainActivity.this);
                }else if(activity.equals("原生Sqlite数据库操作")){
                    intent = DataBaseActivity.getStartIntent(MainActivity.this);
                }
                startActivity(intent);
                return true;
            }
        });
    }
}
