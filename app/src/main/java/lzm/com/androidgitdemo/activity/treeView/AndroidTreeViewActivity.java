package lzm.com.androidgitdemo.activity.treeView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import lzm.com.androidgitdemo.R;

public class AndroidTreeViewActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AndroidTreeViewActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_tree_view);
        initView();
    }

    private void initView() {
        linearLayout = (LinearLayout)findViewById(R.id.tree_view);
        TreeNode root = TreeNode.root();
        TreeNode parent = new TreeNode("parent");
        TreeNode child1 = new TreeNode("child1");
        for (int i=0;i<100;i++){
            TreeNode child1Down = new TreeNode("child1Down" +i);
            child1.addChild(child1Down);
        }
        TreeNode child2 = new TreeNode("child2");
        for (int i=0;i<100;i++){
            TreeNode child1Down = new TreeNode("child2Down" +i);
            child2.addChild(child1Down);
        }
        parent.addChildren(child1,child2);
        root.addChild(parent);
        AndroidTreeView tView = new AndroidTreeView(AndroidTreeViewActivity.this, root);
        linearLayout.addView(tView.getView());


    }
}
