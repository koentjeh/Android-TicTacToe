package com.koen.tictactoe.controller.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.koen.tictactoe.model.Score;


public class ScoreDBHandler extends SQLiteOpenHelper {

    private static final String TAG = "ScoreDBHandler";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "score.db";
    private static final String DB_TABLE_NAME = "score";

    // Tabel en kolom namen ...
    private static final String COLOMN_ID = "_id";  // primary key, auto increment
    private static final String COLOMN_WINS = "win";
    private static final String COLOMN_DRAWS = "draw";
    private static final String COLOMN_LOSSES = "lose";

    // Default constructor
    public ScoreDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORE_TABLE = "CREATE TABLE " + DB_TABLE_NAME +
                "(" +
                COLOMN_ID + " INTERGER PRIMARY KEY," +
                COLOMN_WINS + " INTERGER," +
                COLOMN_DRAWS + " INTERGER," +
                COLOMN_LOSSES + " INTERGER" +
                ")";

        db.execSQL(CREATE_SCORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(db);
    }

    public Score getScore() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT win, draw, lose FROM " + DB_TABLE_NAME, null);
        Score score = (Score) c;
        c.close();

        return score;
    }
}
