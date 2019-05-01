package lzm.com.androidgitdemo.activity.treeView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;
import com.unnamed.b.atv.model.TreeNode;

import lzm.com.androidgitdemo.R;

public class IconTreeItemHolder extends TreeNode.BaseNodeViewHolder<IconTreeItemHolder.IconTreeItem> {
    private PrintView mPrintView;
    private TextView mTextView;
    public IconTreeItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_icon_node,null,false);
        mPrintView = (PrintView) view.findViewById(R.id.arrow_icon);
        mTextView = (TextView) view.findViewById(R.id.node_value);
        mPrintView.setIconText(context.getResources().getString(value.icon));
        mTextView.setText(value.text);
        mPrintView.setPadding(20,10,10,10);
        if(node.isLeaf()){
            mPrintView.setVisibility(View.GONE);
        }
        mPrintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("****","clik node");
                tView.toggleNode(node);
            }
        });

        return view;
    }

    @Override
    public void toggle(boolean active) {
        if(active){
            Log.i("======","this is true" + mTextView.getText());
            mPrintView.setIconText(R.string.ic_email);
        }else{
            Log.i("========","this is false" + mTextView.getText()) ;
            mPrintView.setIconText(R.string.ic_arrow_back);
        }
//        mPrintView.setIconText(context.getResources().getString(active?R.string.ic_arrow_drop_down:R.string.ic_arrow_drop_up));
    }

    public static class IconTreeItem{
        public int icon;
        public String text;

        public IconTreeItem(int icon, String text) {
            this.icon = icon;
            this.text = text;
        }
    }
}