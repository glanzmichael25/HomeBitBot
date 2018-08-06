package net.michelison.homebitbot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseActivity {

    // database constants
    public static final String DB_NAME = "bitbot.db"; //also wondering if we need another DB but i assume we don't due to a DB holding multiple tables
    public static final int DB_VERSION = 1;

    // term table contents
    public static final String TERM_TABLE = "term";

    public static final String TERM_ID = "term_id";
    public static final int TERM_ID_COL = 0;

    public static final String TERM_NAME = "term_name";
    public static final int TERM_NAME_COL = 1;

    public static final String TERM_DESC = "term_def";
    public static final int TERM_DESC_COL = 2;

    // description table constants
    public static final String DESC_TABLE = "description";

    public static final String DESC_ID = "desc_id";
    public static final int DESC_ID_COL = 0;

    public static final String DESCRIPTION = "description";
    public static final int DESCRIPTION_COL = 1;

    //foriegn key column
    public static final int TERM_FK_COL = 2;

    // create and drop term tables statements
    public static final String CREATE_TERM_TABLE =
            "CREATE TABLE " + TERM_TABLE + " (" +
                    TERM_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TERM_NAME   + " STRING NOT NULL, " +
                    TERM_DESC   + " STRING NOT NULL);";

    public static final String DROP_TERM_TABLE =
            "DROP TABLE IF EXISTS " + TERM_TABLE;


    // create and drop desc tables statements
    public static final String CREATE_DESC_TABLE =
            "CREATE TABLE " + DESC_TABLE + " (" +
                    DESC_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DESCRIPTION + " STRING NOT NULL, " +
                    TERM_ID     + " INTEGER REFERENCES "+ TERM_TABLE +");";

    public static final String DROP_DESC_TABLE =
            "DROP TABLE IF EXISTS " + DESC_TABLE;


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version){
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            // create tables
            db.execSQL(CREATE_TERM_TABLE);
            db.execSQL(CREATE_DESC_TABLE);

            // inserting test data
            db.execSQL("INSERT INTO term VALUES (1, 'Address', 'LOREM IPSUM')" );
            db.execSQL("INSERT INTO term VALUES (2, 'BIT', 'IPSUM CON DEFINTIONISM')");

            db.execSQL("INSERT INTO description VALUES (1, 'LOREM IPSUM', 1)");
            db.execSQL("INSERT INTO description VALUES (2, 'IPSUM CON DEFINTIONISM', 2)");

        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            // this method may be complicated due to the dual table circumstances
            // we may need both drop table circumstances we may not

            Log.d("TermDB", "Upgrading TermDB from version "
                + oldVersion + " to " + newVersion);

            db.execSQL(DatabaseActivity.DROP_TERM_TABLE);
            onCreate(db);

            Log.d("DescriptionDB", "Upgrading DescriptionDB from version "
            + oldVersion + " to " + newVersion);

            db.execSQL(DatabaseActivity.DROP_DESC_TABLE);
            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;


    // constructor
    public DatabaseActivity(Context context){
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB(){
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if(db != null)
            db.close();
    }

    // need to add the public methods for BitBotDB
    // we will be using an ArrayList<Strings>, except we need a hashMap??

    // TODO we need a fileIO.java
    // TODO we need a JSON object of the Database
    // TODO We need a SQLDBParser to a hashmap from the RSSItemsActivity File
    // TODO update and Display FROM ItemsActivity.java RSSNewsFeed
    // TODO JSONHandler getters and setters



}
