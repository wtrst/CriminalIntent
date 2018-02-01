package ca.ciccc.android.wataru.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import ca.ciccc.android.wataru.criminalintent.Crime;

/**
 * Created by satouwataru on 2018/01/26.
 */

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Crime getCrime(){
        String uuidString = getString(getColumnIndex(CrimeDbScheme.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbScheme.CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeDbScheme.CrimeTable.Cols.DATE));
        int isSolved = getColumnIndex(CrimeDbScheme.CrimeTable.Cols.SOLVED);
        String suspect = getString(getColumnIndex(CrimeDbScheme.CrimeTable.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setSuspect(suspect);

        return crime;
    }


}
