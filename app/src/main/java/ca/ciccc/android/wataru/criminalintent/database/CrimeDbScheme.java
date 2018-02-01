package ca.ciccc.android.wataru.criminalintent.database;

/**
 * Created by satouwataru on 2018/01/26.
 */

public class CrimeDbScheme {
    public static final class CrimeTable{
        public static final String NAME = "crimes";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }
}
