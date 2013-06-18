package com.jegumi.irishrail;

import android.content.Context;
import android.content.SharedPreferences;

public class IrishRailPreferences {

    private static final String PREFS_NAME = "IRISHRAIL_PREFS";
    private static final String DATABASE_INIT = "databaseInit";
    private static final String LAST_FROM = "lastFrom";
    private static final String LAST_TO = "lastTo";

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    public IrishRailPreferences(Context context) {
        settings = context.getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
    }

    public boolean isDatabaseInit() {
        return settings.getBoolean(DATABASE_INIT, false);
    }

    public int getLastFrom() {
        return settings.getInt(LAST_FROM, 0);
    }

    public int getLastTo() {
        return settings.getInt(LAST_TO, 0);
    }

    public void setDatabaseInit(boolean databaseInit) {
        editor.putBoolean(DATABASE_INIT, databaseInit);
        editor.commit();
    }

    public void setLastFrom(int lastFrom) {
        editor.putInt(LAST_FROM, lastFrom);
        editor.commit();
    }

    public void setLastTo(int lastTo) {
        editor.putInt(LAST_TO, lastTo);
        editor.commit();
    }
}
