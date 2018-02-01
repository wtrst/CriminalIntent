package ca.ciccc.android.wataru.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.ciccc.android.wataru.criminalintent.database.CrimeBaseHelper;
import ca.ciccc.android.wataru.criminalintent.database.CrimeCursorWrapper;
import ca.ciccc.android.wataru.criminalintent.database.CrimeDbScheme;

/**
 * Created by satouwataru on 2018/01/17.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    //    private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeDbScheme.CrimeTable.NAME, null, values);
    }

//    //*****************
//    public void deleteCrime(int index){
//        mCrimes.remove(index);
//    }
//    //*****************

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeDbScheme.CrimeTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Crime crime){
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, crime.getPhotoFilename());
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbScheme.CrimeTable.NAME, values, CrimeDbScheme.CrimeTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeDbScheme.CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeDbScheme.CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeDbScheme.CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeDbScheme.CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeDbScheme.CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeDbScheme.CrimeTable.Cols.SUSPECT, crime.getSuspect());

        return values;
    }
}
