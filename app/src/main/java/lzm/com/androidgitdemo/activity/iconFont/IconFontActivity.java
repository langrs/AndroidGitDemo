package lzm.com.androidgitdemo.activity.iconFont;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.github.johnkil.print.PrintView;

import lzm.com.androidgitdemo.R;

public class IconFontActivity extends AppCompatActivity {
    /*
}
    1.首先是切换成project模式
    2.在路径src/main下面通过新建-folder-assets来新建资源目录assets
        2.1assets作用可以用来实现html5+javascript+android的混合开发中，一般html5和javascript以及相关资源可以存放在Assets文件夹内。
        2.2可以放一些资源文件
        3.3 在res目录下面存在的可编译的资源文件,这种文件系统会在R.java里面自动生成资源文件的ID,
    所以访问res目录下的资源文件比较简单,通过R.XXX.ID即可
        3.4  因为系统在编译的时候不会编译assets下的资源文件，所以我们不能通过R.XXX.ID的方式
    访问它们。那我么能不能通过该资源的绝对路径去访问它们呢？
    因为apk安装之后会放在/data/app/**.apk目录下，以apk形式存在，asset/res和被绑定在apk里，
    并不会解压到/data/data/YourApp目录下去，所以我们无法直接获取到assets的绝对路径，
    因为它们根本就没有。还好Android系统为我们提供了一个AssetManager工具类,
    通过getAssets()方法获取AssetManager对象,然后就能调用它提供的方法:
            final String[] list(String path)
            返回指定路径下的所有文件及目录名。

            final InputStream open(String fileName)
            使用 ACCESS_STREAMING模式打开assets下的指定文件。.

            final InputStream open(String fileName, int accessMode)
            使用显示的访问模式打开assets下的指定文件.
            例如打开图片装载:
            InputStream is=getAssets().open(“wpics/0ZR424L-0.jpg”);
            Bitmap bitmap=BitmapFactory.decodeStream(is);
            imgShow.setImageBitmap(bitmap);
    3.本次实例就要把字体文件放在在assets目录下面:font/material-icon-font.ttf
    4.然后如果设置图标就定义IconText,颜色和大小也可以通过iconColor和iconSize啦定义了
    4.
 */
    private Button button;
    private PrintView printView;
    private Resources resources;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, IconFontActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iconfont);
        printView = (PrintView)findViewById(R.id.arrow_icon);
        resources = getResources();
        button = (Button)findViewById(R.id.btn_change_icon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printView.setIconText(resources.getString(R.string.ic_arrow_drop_down_circle));
//                这里需要注意的是最好用第一个参数定义这个尺寸是属于什么类型的,dp,sp,还是其他
//                而resources针对尺寸获取的有3个函数:getDimension,getDimensionPixelSize,getDimensionPixelOffset,注意他们的区别
                printView.setIconSize(TypedValue.COMPLEX_UNIT_DIP,resources.getDimensionPixelSize(R.dimen.icon_size));
            }
        });
    }
}
