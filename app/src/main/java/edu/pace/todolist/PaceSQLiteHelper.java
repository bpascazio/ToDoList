package edu.pace.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sscsis on 1/14/15.
 */
public class PaceSQLiteHelper extends SQLiteOpenHelper {

    private Context context = null;
    private String name = null;

    public PaceSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
        this.context = context;
        this.name = name;
    }

    private void copyDataBase()
    {
        Log.i("Database",
                "New database is being copied to device!");
        byte[] buffer = new byte[1024];
        OutputStream myOutput = null;
        int length;
        // Open your local db as the input stream
        InputStream myInput = null;
        try
        {
            myInput =context.getAssets().open(name);
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput =new FileOutputStream("/data/data/edu.pace.todolist/"+ name);
            while((length = myInput.read(buffer)) > 0)
            {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.i("database",
                    "New database has been copied to device!");

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }


    public ArrayList<String> getAllTodos() {

        String path = "/data/data/edu.pace.todolist/"+name;
        SQLiteDatabase db = null;
        ArrayList list = null;
        try {

            Log.d("database", "attempt to open database");

            String queryString = "select * from todo_items";

            db = SQLiteDatabase.openDatabase(path, null,
                    SQLiteDatabase.OPEN_READWRITE);

            Cursor cursor = db.rawQuery(queryString, null);

            list = new ArrayList();

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                list.add(cursor.getColumnIndex("name"));
                Log.d("database", "found db item "+cursor.getString(1));
            }

        } catch (SQLException e) {
            Log.d("database", "failed to open so copying database");

            copyDataBase();
        }
        return list;
    }

    public void openDatabase() throws SQLException {
        String path = "/data/data/edu.pace.todolist/"+name;
        SQLiteDatabase db = null;

        try {

            Log.d("database", "attempt to open database");
            db = SQLiteDatabase.openDatabase(path, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLException e) {
            Log.d("database", "failed to open so copying database");

            copyDataBase();
        }

        if (db != null) {
            Log.d("database", "database was opened");
            db.close();
        } else {
            Log.d("database", "database was not opened - failed");
        }

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
