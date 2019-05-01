package lzm.com.androidgitdemo.activity.treeView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.johnkil.print.PrintView;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.w3c.dom.Text;

import lzm.com.androidgitdemo.R;

public class AndroidTreeViewActivity2 extends AppCompatActivity {

    private LinearLayout linearLayout;
    private AndroidTreeView androidTreeView;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, AndroidTreeViewActivity2.class);
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
////        父节点
//        TreeNode parent = new TreeNode("我是祖宗");
        IconTreeItemHolder.IconTreeItem zzIconLayout = new IconTreeItemHolder.IconTreeItem(R.string.ic_arrow_drop_up,"我是祖宗");
        TreeNode parent = new TreeNode(zzIconLayout).setViewHolder(new IconTreeItemHolder(AndroidTreeViewActivity2.this));
//        根结点的点击事件
        parent.setClickListener(new TreeNode.TreeNodeClickListener() {
            @Override
            public void onClick(TreeNode node, Object value) {
//                如何能够改变里面的图标呢?
                PrintView printView = (PrintView) node.getViewHolder().getNodeView().findViewById(R.id.arrow_icon);
                TextView textView = (TextView) node.getViewHolder().getView().findViewById(R.id.node_value);
                textView.setText("i love you");
                printView.setIconText(R.string.ic_arrow_drop_down);
//                Toast.makeText(AndroidTreeViewActivity2.this,"parent被动了",Toast.LENGTH_LONG).show();
            }
        });
//        增加节点布局
//        TreeNode father = new TreeNode("我是爸爸");
        IconTreeItemHolder.IconTreeItem parentIconLayout = new IconTreeItemHolder.IconTreeItem(R.string.ic_arrow_drop_up,"我是爸爸");
        TreeNode father = new TreeNode(parentIconLayout).setViewHolder(new IconTreeItemHolder(AndroidTreeViewActivity2.this));
        father.setClickListener(new TreeNode.TreeNodeClickListener() {
            @Override
            public void onClick(TreeNode node, Object value) {
//                Toast.makeText(AndroidTreeViewActivity2.this,"father被动了",Toast.LENGTH_LONG).show();
            }
        });
        for(int i=0;i<100;i++){
            IconTreeItemHolder.IconTreeItem childIconLayout = new IconTreeItemHolder.IconTreeItem(R.string.ic_arrow_drop_up,"我是儿子" + i);
            TreeNode child = new TreeNode(childIconLayout);
            child.setViewHolder(new IconTreeItemHolder(AndroidTreeViewActivity2.this));
            father.addChild(child);
        }
//        TreeNode son = new TreeNode("我是儿子");
//        TreeNode daughter = new TreeNode("我是女儿");
//        father.addChild(son);
//        father.addChild(daughter);
        parent.addChildren(father);
        root.addChildren(parent);

//        TreeNode parent = new TreeNode("parent");
//        TreeNode child1 = new TreeNode("child1");
//        for (int i=0;i<100;i++){
//            TreeNode child1Down = new TreeNode("child1Down" +i);
//            child1.addChild(child1Down);
//        }
//        TreeNode child2 = new TreeNode("child2");
//        for (int i=0;i<100;i++){
//            TreeNode child1Down = new TreeNode("child2Down" +i);
//            child2.addChild(child1Down);
//        }
//        parent.addChildren(child1,child2);
//        root.addChild(parent);
        androidTreeView = new AndroidTreeView(AndroidTreeViewActivity2.this,root);
        linearLayout.addView(androidTreeView.getView());


    }
}
