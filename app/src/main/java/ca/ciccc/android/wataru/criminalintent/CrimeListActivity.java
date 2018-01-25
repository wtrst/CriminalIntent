package ca.ciccc.android.wataru.criminalintent;


import android.support.v4.app.Fragment;

/**
 * Created by satouwataru on 2018/01/17.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
