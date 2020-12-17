package arsen.baktiyarov.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY, %s TEXT  NOT NULL)",db_table,db_column);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
