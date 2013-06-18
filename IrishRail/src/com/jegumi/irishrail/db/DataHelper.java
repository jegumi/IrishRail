package com.jegumi.irishrail.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.jegumi.irishrail.model.Station;

public class DataHelper {

    private static final String TAG = DataHelper.class.getName();
    private SQLiteDatabase db;
    private Context context;

    public DataHelper(Context context) {
        this.context = context;
        DbHelper openHelper = new DbHelper(context);
        db = openHelper.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys = ON;");
    }

    public long insertStation(Station station) {
        Log.i(TAG, "insertStation");
        ContentValues values = getStationValues(station);
        return db.insert(DbHelper.STATIONS_TABLE, null, values);
    }

    private ContentValues getStationValues(Station station) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.STATION_DESC, station.getDesc());
        values.put(DbHelper.STATION_ALIAS, station.getAlias());
        values.put(DbHelper.STATION_CODE, station.getCode());
        values.put(DbHelper.STATION_ID, station.getId());

        return values;
    }

    public boolean isTableEmpty(String tableName) {
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        boolean isEmpty = cursor.getCount() == 0;
        cursor.close();
        return isEmpty;
    }

    public Cursor getCursor(String tableName) {
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        return cursor;
    }

    public Cursor getStationsCursor(String selection) {
        Cursor cursor = db.query(DbHelper.STATIONS_TABLE, null, selection, null, null, null, DbHelper.STATION_DESC + " ASC");
        return cursor;
    }

    public String createSelection(String name, String value) {
        value = value.replaceAll("'", "");
        return name + " = '" + value + "'";
    }

    public void fillStationsSpinner(Spinner spinner) {
        Cursor cursor = getStationsCursor(null);
        String[] columns = new String[] { DbHelper.STATION_DESC };
        int[] to = new int[] { android.R.id.text1 };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context, android.R.layout.simple_spinner_item, cursor,
                columns, to, 0);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public String getStationNameById(long id) {
        String name = "";
        String selection = createSelection(DbHelper.ID, String.valueOf(id));
        Cursor cursor = getStationsCursor(selection);
        if (cursor.moveToFirst()) {
            int index = cursor.getColumnIndex(DbHelper.STATION_DESC);
            name = cursor.getString(index);
        }
        cursor.close();
        return name;
    }
}
