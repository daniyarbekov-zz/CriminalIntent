//package com.example.superdima.crimanalintent;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v4.app.Fragment;
//
//import java.util.UUID;
//
//public class CriminalActivity extends SingleFragmentActivity {
//    private final static String CRIME_DEFINITION = "superdima.crime.id";
//
//    protected Fragment createFragment(){
//        int crimeID  = (Integer) getIntent().getIntExtra(CRIME_DEFINITION,-1);
//        return CrimeFragment.newInstance(crimeID);
//    }
//
//    public static Intent newIntent(Context packageContext, int crimeID){
//        Intent intent =  new Intent(packageContext,CriminalActivity.class);
//        intent.putExtra(CRIME_DEFINITION,crimeID);
//        return intent;
//    }
//
//
//
//}
