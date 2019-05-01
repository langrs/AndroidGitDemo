package lzm.com.androidgitdemo.activity.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import lzm.com.androidgitdemo.R;
import lzm.com.androidgitdemo.adapter.MyExpandableListView;

public class DataBaseActivity extends AppCompatActivity {

    private Button btn_create_datebase;
    private Button btn_update_database;
    private Button btn_insert_book;
    private EditText et_name;
    private EditText et_price;
    private EditText et_author;
    private TextView tv_show;
    private MyDbHelper myDbHelper;
    private static final String DBNAME = "MYDB";
    private static final int DB_VER = 1;
    private static final int DB_UPDATE_VER = 2;
    private static final String TABLE_BOOK = "Book";
    private static final String TABLE_CATEGORY = "Category";

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, DataBaseActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_base);
        init_view();
    }

    private void init_view() {
        et_author = (EditText)findViewById(R.id.et_author);
        et_price = (EditText)findViewById(R.id.et_price);
        et_name = (EditText)findViewById(R.id.et_name);
        tv_show = (TextView)findViewById(R.id.tv_show);
        btn_create_datebase = (Button)findViewById(R.id.btn_create_datebase);
        btn_insert_book = (Button)findViewById(R.id.btn_insert_book);
        btn_update_database = (Button)findViewById(R.id.btn_update_database);
//        创建数据库
        btn_create_datebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        建立实例的时候,默认就会调用到onCreate函数,在构造函数中传入新建的数据库名称,
// 在onCreate函数中定义好新建的表名称
                myDbHelper = new MyDbHelper(getApplicationContext(),DBNAME,null,DB_VER);
//                实例化之后,就能通过getWritableDatabase或getReadableDatabase来获取对象SQLiteDatabase,
// 通过这个对象就能操作数据库了

                SQLiteDatabase sqLiteDatabase = myDbHelper.getReadableDatabase();
                //查询book表的数据
                Cursor query = sqLiteDatabase.query(TABLE_BOOK, null, null, null, null, null, null);
                if(query.moveToFirst()){
                    do{
                        String author = query.getString(query.getColumnIndex("author"));
                        double price = query.getDouble(query.getColumnIndex("price"));
                        int pages = query.getInt(query.getColumnIndex("pages"));
                        String name = query.getString(query.getColumnIndex("name"));
                        String show_txt = name + "/" + author ;
                        tv_show.setText(tv_show.getText() + show_txt );
                    }while (query.moveToNext());
                }


            }
        });
//        更新数据库SQLiteOpenHelper自定义对象的构造函数中传入的数据库版本和上次版本不一致,
// 就会触发到自定义对象中的onUpdate方法了
        btn_update_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                只要实例化
                myDbHelper = new MyDbHelper(getApplicationContext(),DBNAME,null,DB_UPDATE_VER);
                SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
            }
        });
//        插入书表
        btn_insert_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbHelper myDbHelper = new MyDbHelper(getApplicationContext(),DBNAME,null,DB_VER);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name",et_name.getText().toString());
                contentValues.put("author",et_author.getText().toString());
                contentValues.put("price",Double.parseDouble(et_price.getText().toString()));
                SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
                sqLiteDatabase.beginTransaction();
                sqLiteDatabase.insert(TABLE_BOOK,null,contentValues);
                sqLiteDatabase.setTransactionSuccessful();
                sqLiteDatabase.endTransaction();
            }
        });
    }

}
