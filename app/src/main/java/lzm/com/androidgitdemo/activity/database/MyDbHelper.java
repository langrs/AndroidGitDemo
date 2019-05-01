package lzm.com.androidgitdemo.activity.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String CREATE_BOOK = "create table Book(" +
            "id integer primary key autoincrement," +
            "author text, " +
            "price real, " +
            "pages integer, " +
            "name text)";
    private static final String BOOK_INSERT = "insert into Book(author,price,pages,name) values(\'lzm\',111,30,\'100 mary sotry\')";
    private static final String CATE_INSERT = "insert into Category(category_name,category_code) values(\'big\',1) ";
    private static final String CREATE_CATE = "create table Category("+
            "id integer primary key autoincrement, "+
            "category_name text, " +
            "category_code integer)";

    private Context context;

    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }
//必须要重载的函数,功能是在这个函数中新建数据库表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(BOOK_INSERT);
        Toast.makeText(context,"Create database and table book success!",Toast.LENGTH_SHORT).show();
    }
//必须重载的函数,功能是更新数据库表,这个方法怎么能触发呢,就是new MyDbHelper对象传入的版本号和上次版本号不一样就会被触发
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_CATE);
        db.execSQL(CATE_INSERT);
        Toast.makeText(context,"Create table category",Toast.LENGTH_SHORT).show();
    }
}