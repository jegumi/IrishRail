package com.jegumi.irishrail.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = DbHelper.class.getName();
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "irishrail";
    public static final String STATIONS_TABLE = "stations";

    public static final String STATION_DESC = "StationDesc";
    public static final String STATION_CODE = "StationCode";
    public static final String STATION_ID = "StationId";
    public static final String STATION_ALIAS = "StationAlias";
    public static final String STATION_LATITUDE = "StationLatitude";
    public static final String ID = "_id";
    public static final String STATION_LONGITUDE = "StationLongitude";

    public static final String CREATE_STATION_TABLE = "CREATE TABLE " + DbHelper.STATIONS_TABLE
            + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, " + STATION_ID + " TEXT," + STATION_DESC + " TEXT, "
            + STATION_CODE + " TEXT, " + STATION_ALIAS + " TEXT, " + STATION_LATITUDE + " TEXT, "
            + STATION_LONGITUDE + " TEXT)";;

    public DbHelper(Context context) {
        super(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbHelper.CREATE_STATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database, adding new changes from " + oldVersion + " to " + newVersion);
    }
}