package pl.mbien.krokomierz.sql;

import android.provider.BaseColumns;

public class DatabaseContract {
    private DatabaseContract() {
    }

    public static class Records implements BaseColumns {
        public static final String TABLE_NAME = "records";
        public static final String COLUMN_NAME = "username";
        public static final String COLUMN_SCORE = "score";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SCORE + " INTEGER" + ")";
    }
}
