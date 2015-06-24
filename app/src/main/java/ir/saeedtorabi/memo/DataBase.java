package ir.saeedtorabi.memo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by saeed_trb on 6/20/2015.
 */
public class DataBase extends SQLiteOpenHelper{
    public static String DBName = "MyMemo";
    public static int DBVersion = 1;

    public DataBase(Context context) {
        super(context, DBName , null , DBVersion );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE memo ( ID int AUTO_INCREMENT,title varchar(255),content varchar(255), PRIMARY KEY (ID) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {}

    public void Insert(DataBase DB , String title , String content ){
        SQLiteDatabase db = DB.getWritableDatabase();
        db.execSQL("Insert Into Memo ( title , content  ) values ('" + title + "','" + content +"')");
        db.close();
    }
    public void delete( DataBase DB , int id ){
        SQLiteDatabase db = DB.getWritableDatabase();
        db.execSQL( "DELETE FROM Memo WHERE ID =" + id );
        db.close();
    }
    public void update( DataBase DB , int id , String title , String content ){
        SQLiteDatabase db = DB.getWritableDatabase();
        db.execSQL( "UPDATE Memo SET title ='" + title +"' , content='"+content+"' WHERE ID =" + id );
        db.close();
    }
    public MemoModel GetMemo( DataBase DB , String id ){
        SQLiteDatabase db = DB.getReadableDatabase();
        String Query = "SELECT * FROM Memo WHERE ID = " + id;
        Cursor cur = db.rawQuery(Query, null);
        MemoModel row = null;
        if(cur.moveToFirst()){
            do {
                //cur.getString(0);
                String title = cur.getString(cur.getColumnIndex("title"));
                String content = cur.getString(cur.getColumnIndex("content"));
                row = new MemoModel( title , content );
            }while(cur.moveToNext());
        }
        cur.close();
        db.close();
        return row;
    }
    public ArrayList<MemoModel> ListMemo(DataBase DB){
        SQLiteDatabase db = DB.getReadableDatabase();
        String Query = "SELECT * FROM Memo ORDER BY ID DESC";
        Cursor cur = db.rawQuery(Query, null);
        ArrayList<MemoModel> rows = new ArrayList<MemoModel>();
        if(cur.moveToFirst()){
            do {
                //cur.getString(0);
                String title = cur.getString(cur.getColumnIndex("title"));
                String content = cur.getString(cur.getColumnIndex("content"));
                rows.add( new MemoModel( title , content ) );
            }while(cur.moveToNext());
        }
        cur.close();
        db.close();
        return rows;
    }
}
