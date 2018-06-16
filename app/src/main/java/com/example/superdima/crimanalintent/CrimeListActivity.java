package com.example.superdima.crimanalintent;

import android.content.Intent;
import android.support.v4.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity implements CrimeListFragment.Callbacks,CrimeFragment.Callbacks{

    protected Fragment createFragment(){
        return new CrimeListFragment();
    }

    public void onCrimeSelected(Crime crime){
        if(findViewById(R.id.detail_fragment_container) == null){
            Intent intent = CrimePageActivity.newIntent(this,crime.getId());
            startActivity(intent);
        }
        else{
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    public void onCrimeUpdated(Crime crime){
        CrimeListFragment listFragment = (CrimeListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }


}
