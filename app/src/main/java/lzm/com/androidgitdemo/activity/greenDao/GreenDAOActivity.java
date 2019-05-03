package lzm.com.androidgitdemo.activity.greenDao;

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
import android.widget.Toast;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import lzm.com.androidgitdemo.R;
import lzm.com.androidgitdemo.activity.greenDao.greenUtil.GreenDaoUtil;

public class GreenDAOActivity extends AppCompatActivity {
    private Button btn_insert;
    private Button btn_query;
    private Button btn_query_rawsql;
    private Button btn_del;
    private EditText et_query_name;
    private EditText et_query_author;
    private EditText et_name;
    private EditText et_author;
    private EditText et_price;
    private EditText et_pages;

    private static final String DBNAME = "MYDB";

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context,GreenDAOActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        init_view();
    }

    private void init_view() {
        btn_query_rawsql = (Button) findViewById(R.id.btn_query_rawsql);
        et_query_name =(EditText)findViewById(R.id.et_query_name);
        et_query_author = (EditText)findViewById(R.id.et_query_author);
        btn_del = (Button)findViewById(R.id.btn_del);
        btn_query = (Button)findViewById(R.id.btn_query);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        et_author = (EditText) findViewById(R.id.et_author_show);
        et_name= (EditText) findViewById(R.id.et_name_show);
        et_price = (EditText) findViewById(R.id.et_price_show);
        et_pages = (EditText) findViewById(R.id.et_pages_show);

//        原生sql查询
        btn_query_rawsql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                通过静态方法获取session
                DaoSession daoSession = GreenDaoUtil.getDaoSessionInstant(getApplicationContext());
                String querySql = "select * from book where id > 9";
//                Query<Book> query = daoSession.getBookDao().queryBuilder().where(
//                  new WhereCondition.StringCondition("name in(select name from book where id=9)")
//                ).build();
//                Book book = query.unique();
//                et_name.setText(book.getName());
//                et_author.setText(book.getAuthor());
//                这个用法就是没有生成具体的表对应的DAO,而是直接执行sql语句,通过游标来逐条获取返回值,
// 不过这种没有办法直接组装到entity里面,需要和sqlite的原始操作一样的,,没有体现出来greenDao的高明之处
                Cursor c = daoSession.getDatabase().rawQuery(querySql,null);
                c.moveToFirst();
                String str = "";
                do{
                    str = str + c.getString(c.getColumnIndex(BookDao.Properties.Name.columnName));
                    str = str + c.getString(c.getColumnIndex(BookDao.Properties.Author.columnName));
                }while (c.moveToNext());
                                et_author.setText(str);
            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                查找字段name为输入的查询条件
                String name = et_query_name.getText().toString();
                if(name != null && name.length() > 0){
                    String author = et_query_author.getText().toString();
                    DaoMaster.DevOpenHelper devOpenHelper =
                            new DaoMaster.DevOpenHelper(getApplicationContext(),DBNAME,null);
                    SQLiteDatabase database = devOpenHelper.getWritableDatabase();
                    DaoMaster daoMaster = new DaoMaster(database);
                    DaoSession daoSession = daoMaster.newSession();
                    BookDao bookDao = daoSession.getBookDao();
                    QueryBuilder<Book> qb = bookDao.queryBuilder();
//                    Book book = (Book)qb.where(BookDao.Properties.Name.eq(name)).unique();
//                    排序
//                    qb.where(BookDao.Properties.Name.eq("Joe"),
//                            qb.or(BookDao.Properties.Author.eq(""),
//                                    qb.and(BookDao.Properties.Author.eq(""), BookDao.Properties.Author.ge(""))));
//                    以下是并且的关系and
//                    List<Book> books = qb.where(BookDao.Properties.Name.eq(name),BookDao.Properties.Author.eq(author)
//                            ).list();
//以下是并且关系
//                    List<Book> books = qb.where(
//                            qb.or(BookDao.Properties.Name.eq(name),
//                            BookDao.Properties.Author.eq(author))
//                            ).list();
//                    以下是混合用法,就是and后再or,or短语里面又是有2个and
                    List<Book> books = qb.where(BookDao.Properties.Name.eq(name),BookDao.Properties.Id.gt(1),
                            qb.or(BookDao.Properties.Name.eq(name),
                            BookDao.Properties.Author.eq(author))
                            ).list();

                    //以下通过组合sql成为子查询来适合其他的子查询,因为这样条件中就是其他的select子查询句子了,而这个短句直接被挂在where后面
//                    Query<Book> query = daoSession.getBookDao().queryBuilder().where(
//                            new WhereCondition.StringCondition("name in(select name from book where id=9)")
//                    ).build();
//                    Book book = query.unique();

//                    List<Book> books =
//                            qb.where(BookDao.Properties.Name.eq(name)).
//                                    orderAsc(BookDao.Properties.Price).
//                                    list();
                    if(books != null){
                        for(Book book :books){
                            et_name.setText(et_name.getText() + "$" + book.getName());
                            et_author.setText(et_author.getText() + "$" + book.getAuthor());
                        }
//                        et_name.setText(book.getName());
//                        et_author.setText(book.getAuthor());
//                        et_price.setText(String.valueOf(book.getPrice()));
//                        et_pages.setText(String.valueOf(book.getPages()));
                    }

                }
            }
        });
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaoMaster.DevOpenHelper devOpenHelper =
                        new DaoMaster.DevOpenHelper(getApplicationContext(),DBNAME,null);
                SQLiteDatabase database = devOpenHelper.getWritableDatabase();
                DaoMaster daoMaster = new DaoMaster(database);
                DaoSession daoSession = daoMaster.newSession();
                BookDao bookDao = daoSession.getBookDao();
                Book book = new Book();
                book.setName(et_name.getText().toString());
                book.setAuthor(et_author.getText().toString());
                book.setPages(Integer.valueOf(et_pages.getText().toString()));
                book.setPrice(Double.valueOf(et_price.getText().toString()));
                bookDao.insert(book);
            }
        });
    }
}
