package com.example.weatherforecast;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//创建SQLite数据库的步骤
//1.创建一个类继承SQLiteOpenHelper类
//2.在该类中重写onCreate()方法和onUpgrade()方法
public class NoteDb extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "notes";
    public static final String CONTENT = "content";
    public static final String ID = "_id";
    public static final String TIME = "time";

    public NoteDb(Context context) {
        //通过super()调用父类SQLiteOpenHelper的构造方法，并传入4个参数
        //上下文、数据库名称、游标工厂（通常是null）、数据库版本
        super(context, "notes", null,1);
    }

    @Override
    //数据库第一次被创建时调用该方法
    public void onCreate(SQLiteDatabase db) {
        //初始化数据库的表结构，执行一条建表的SQL语句
        String sql ="create table "+TABLE_NAME+" ( "+ID+" integer primary key AUTOINCREMENT, "+CONTENT
                +" TEXT NOT NULL, "+TIME+" TEXT NOT NULL )";
        db.execSQL(sql);
    }

    @Override
    //onUpgrade()方法在数据库版本号增加时调用，如果版本号不增加，则该方法不调用。
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
