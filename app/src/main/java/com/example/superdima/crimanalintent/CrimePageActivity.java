//package com.example.superdima.crimanalintent;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//
//import java.util.List;
//import java.util.UUID;
//
//public class CrimePageActivity extends AppCompatActivity{
//
//    private final static String CRIME_DEFINITION = "superdima.crime.id";
//
//    private ViewPager mViewPager;
//    private List<Crime> mCrimes;
//    private Button mFirstButton;
//    private Button mLastButton;
//    private UUID mID;
//    private int mIndex;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_crime_pager);
//
//        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                // Do nothing
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mFirstButton.setEnabled(position != 0);
//                mLastButton.setEnabled(position != (mCrimes.size() - 1));
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                // Do nothing
//            }
//        });
//
//
//        mLastButton = (Button) findViewById(R.id.last_crime_button);
//        mLastButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mViewPager.setCurrentItem(mCrimes.size() - 1);
//
//            }
//        });
//
//
//        mFirstButton = (Button) findViewById(R.id.first_crime_button);
//        mFirstButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                mViewPager.setCurrentItem(0);
//
//            }
//        });
//
//        mCrimes = CrimeLab.get(this).getCrimes();
//        FragmentManager fM = getSupportFragmentManager();
//
//        mViewPager.setAdapter(new FragmentStatePagerAdapter(fM) {
//            @Override
//            public Fragment getItem(int position) {
//                Crime cr = mCrimes.get(position);
//                return CrimeFragment.newInstance(cr.getId());
//            }
//
//            @Override
//            public int getCount() {
//
//                return mCrimes.size();
//            }
//        });
//
//        mID = (UUID) getIntent().getSerializableExtra(CRIME_DEFINITION);
//
//        for (int i=0;i<mCrimes.size();i++){
//            if(mCrimes.get(i).getId().equals(mID)){
//                mIndex = i;
//                break;
//            }
//        }
//
//        mViewPager.setCurrentItem(mIndex);
//        if(mIndex == 0 || mIndex == mCrimes.size()-1){
//            mFirstButton.setEnabled(mIndex != 0);
//            mLastButton.setEnabled(mIndex != (mCrimes.size() - 1));
//        }
//        else{
//            mFirstButton.setEnabled(true);
//            mLastButton.setEnabled(true);
//        }
//
//    }
//
//
//
////    public static Intent newIntent(Context packageContext, int crimeID){
////        Intent intent =  new Intent(packageContext,CrimePageActivity.class);
////        intent.putExtra(CRIME_DEFINITION,crimeID);
////        return intent;
////    }
//
//        public static Intent newIntent(Context packageContext, UUID crimeID){
//        Intent intent =  new Intent(packageContext,CrimePageActivity.class);
//        intent.putExtra(CRIME_DEFINITION,crimeID);
//        return intent;
//    }
//
//
//
//}


package com.example.superdima.crimanalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

public class CrimePageActivity extends AppCompatActivity implements CrimeFragment.Callbacks{

    private final static String CRIME_DEFINITION = "superdima.crime.id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button mFirstButton;
    private Button mLastButton;
    private UUID mID;
    private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Do nothing
            }

            @Override
            public void onPageSelected(int position) {
                mFirstButton.setEnabled(position != 0);
                mLastButton.setEnabled(position != (mCrimes.size() - 1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Do nothing
            }
        });


        mLastButton = (Button) findViewById(R.id.last_crime_button);
        mLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCrimes.size() - 1);

            }
        });


        mFirstButton = (Button) findViewById(R.id.first_crime_button);
        mFirstButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);

            }
        });

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fM = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fM) {
            @Override
            public Fragment getItem(int position) {
                Crime cr = mCrimes.get(position);
                return CrimeFragment.newInstance(cr.getId());
            }

            @Override
            public int getCount() {

                return mCrimes.size();
            }
        });

        mID = (UUID) getIntent().getSerializableExtra(CRIME_DEFINITION);

        for (int i=0;i<mCrimes.size();i++){
            if(mCrimes.get(i).getId().equals(mID)){
                mIndex = i;
                break;
            }
        }

        mViewPager.setCurrentItem(mIndex);
        if(mIndex == 0 || mIndex == mCrimes.size()-1){
            mFirstButton.setEnabled(mIndex != 0);
            mLastButton.setEnabled(mIndex != (mCrimes.size() - 1));
        }
        else{
            mFirstButton.setEnabled(true);
            mLastButton.setEnabled(true);
        }

    }

    public void onCrimeUpdated(Crime crime){

    }



//    public static Intent newIntent(Context packageContext, int crimeID){
//        Intent intent =  new Intent(packageContext,CrimePageActivity.class);
//        intent.putExtra(CRIME_DEFINITION,crimeID);
//        return intent;
//    }

        public static Intent newIntent(Context packageContext, UUID crimeID){
        Intent intent =  new Intent(packageContext,CrimePageActivity.class);
        intent.putExtra(CRIME_DEFINITION,crimeID);
        return intent;
    }



}
