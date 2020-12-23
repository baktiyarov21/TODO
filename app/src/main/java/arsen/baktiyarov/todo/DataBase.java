package arsen.baktiyarov.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    public static final String db_name = "AppDB";
    public static final int version = 1;
    public static final String db_column = "TaskName";
    public static final String db_table = "Task";


    public DataBase(Context context) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT  NOT NULL)", db_table, db_column);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = String.format("DELETE TABLE IF EXISTS %s", db_table);
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public void insert(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(db_column, task);
        db.insertWithOnConflict(db_table, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void delete(String task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(db_table, db_column + " = ?", new String[]{task});
        db.close();
    }

    public ArrayList<String> allTasks() {
        ArrayList<String> allTasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(db_table, new String[]{db_column}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(db_column);
            allTasks.add(cursor.getString(index));

        }
        cursor.close();
        db.close();
        return allTasks;


    }

}
