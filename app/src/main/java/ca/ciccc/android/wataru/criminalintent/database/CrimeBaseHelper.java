package ca.ciccc.android.wataru.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by satouwataru on 2018/01/26.
 */

public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + CrimeDbScheme.CrimeTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                CrimeDbScheme.CrimeTable.Cols.UUID + ", " +
                CrimeDbScheme.CrimeTable.Cols.TITLE + ", " +
                CrimeDbScheme.CrimeTable.Cols.DATE + ", " +
                CrimeDbScheme.CrimeTable.Cols.SOLVED + ", " +
                CrimeDbScheme.CrimeTable.Cols.SUSPECT +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
