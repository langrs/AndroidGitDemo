package lzm.com.androidgitdemo.activity.greenDao.greenUtil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lzm.com.androidgitdemo.activity.greenDao.DaoMaster;
import lzm.com.androidgitdemo.activity.greenDao.DaoSession;

public class GreenDaoUtil {
    private static DaoSession daoSession;
    private static String DBNAME = "MYDB";

    public static DaoSession getDaoSessionInstant(Context context){
        if(daoSession == null){
            DaoMaster.DevOpenHelper devOpenHelper =
                    new DaoMaster.DevOpenHelper(context,DBNAME,null);
            SQLiteDatabase database = devOpenHelper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(database);
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}