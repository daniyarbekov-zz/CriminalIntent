//package com.example.superdima.crimanalintent;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//import java.util.List;
//
//
//public class CrimeListFragment extends Fragment {
//    private RecyclerView mCrimeRecyclerView;
//    private CrimeAdapter mAdapter;
//    private int AdapterPosition;
//    private static final String CLICKED_CRIME_POSITION = "clicked_crime_position_id";
//    private static final int NO_CRIME_SELECTED = -1;
//    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
//
//    private boolean mSubtitleVisible;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        if (savedInstanceState!=null){
//            mSubtitleVisible=savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
//        }
//        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
//
//        mCrimeRecyclerView = (RecyclerView) view
//                .findViewById(R.id.crime_recycler_view);
//        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        if(savedInstanceState != null){
//            AdapterPosition = savedInstanceState.getInt(CLICKED_CRIME_POSITION);
//        }
//        updateUI();
//
//        return view;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onResume(){
//        super.onResume();
//        updateUI();
//    }
//
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putBoolean(SAVED_SUBTITLE_VISIBLE,mSubtitleVisible);
//        outState.putInt(CLICKED_CRIME_POSITION,AdapterPosition);
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//
//        if (resultCode == Activity.RESULT_OK && requestCode == 1){
//           updateUI(CrimeFragment.getCrimePositionFromData(data,-1));
//       }
//    }
//
//    private void updateUI() {
//        updateUI(NO_CRIME_SELECTED);
//    }
//
//    private void updateUI(int crimePosition) {
//        CrimeLab crimeLab = CrimeLab.get(getActivity());
//        List<Crime> crimes = crimeLab.getCrimes();
//
//        if(mAdapter == null) {
//            mAdapter = new CrimeAdapter(crimes);
//            mCrimeRecyclerView.setAdapter(mAdapter);
//        } else {
//            mAdapter.setCrimes(crimes);
//            mAdapter.notifyDataSetChanged();
//        }
//
//        updateSubtitle();
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.fragment_crime_list,menu);
//
//        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
//        if (mSubtitleVisible){
//            subtitleItem.setTitle(R.string.hide_subtitle);
//        }
//        else{
//            subtitleItem.setTitle(R.string.show_subtitle);
//        }
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.new_crime:
//                Crime crime = new Crime();
//                CrimeLab.get(getActivity()).addCrime(crime);
//                Intent intent = CrimePageActivity.newIntent(getActivity(),crime.getId());
//                startActivity(intent);
//                return true;
//            case R.id.show_subtitle:
//                mSubtitleVisible = !mSubtitleVisible;
//                getActivity().invalidateOptionsMenu();
//                updateSubtitle();
//                return true;
//
//            default: return super.onOptionsItemSelected(item);
//        }
//    }
//
//    private void updateSubtitle(){
//        CrimeLab cl = CrimeLab.get(getActivity());
//        int crimeCount = cl.getCrimes().size();
//        String subtitle = getString(R.string.subtitle_format,crimeCount);
//
//        if(!mSubtitleVisible){
//            subtitle = null;
//        }
//
//        AppCompatActivity act = (AppCompatActivity) getActivity();
//        act.getSupportActionBar().setSubtitle(subtitle);
//    }
//
//
//
//    private class CrimeHolder extends RecyclerView.ViewHolder
//            implements View.OnClickListener {
//
//        private Crime mCrime;
//        private int mPosition;
//
//        private TextView mTitleTextView;
//        private TextView mDateTextView;
//        private ImageView mImageView;
//        private static final int REQUEST_CRIME = 1;
//
//        private CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
//            super(inflater.inflate(R.layout.list_item_crime, parent, false));
//            itemView.setOnClickListener(this);
//
//            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
//            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
//            mImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
//        }
//
//        public void bind(Crime crime, int position) {
//            mCrime = crime;
//            mPosition = position;
//            mTitleTextView.setText(mCrime.getTitle());
//            mDateTextView.setText(mCrime.getDate().toString());
//            mImageView.setVisibility(crime.isSolved() ? View.VISIBLE: View.GONE);
//        }
//
//        @Override
//        public void onClick(View view) {
//            CrimeLab cl = CrimeLab.get(getActivity());
//            Intent intento = CrimePageActivity.newIntent(getActivity(),mCrime.getId());
//            startActivityForResult(intento,1);
//        }
//
//
//    }
//
//    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
//
//        private List<Crime> mCrimes;
//
//        public CrimeAdapter(List<Crime> crimes) {
//            mCrimes = crimes;
//        }
//
//
//
//        @Override
//        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
//            return new CrimeHolder(layoutInflater, parent);
//        }
//
//        @Override
//        public void onBindViewHolder(CrimeHolder holder, int position) {
//            AdapterPosition = position;
//            Crime crime = mCrimes.get(position);
//            holder.bind(crime, position);
//        }
//
//        @Override
//        public int getItemCount() {
//            return mCrimes.size();
//        }
//
//        public void setCrimes(List<Crime> Crimes){
//            mCrimes = Crimes;
//        }
//    }
//
//
//}


package com.example.superdima.crimanalintent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import javax.security.auth.callback.Callback;


public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private int AdapterPosition;



    private static final String CLICKED_CRIME_POSITION = "clicked_crime_position_id";
    private static final int NO_CRIME_SELECTED = -1;
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private boolean mSubtitleVisible;
    private Callbacks mCallbacks;


    public interface Callbacks{
        void onCrimeSelected(Crime crime);
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mCallbacks= (Callbacks) context;
    }


    @Override
    public void onDetach(){
        super.onDetach();
        mCallbacks=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState!=null){
            mSubtitleVisible=savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(savedInstanceState != null){
            AdapterPosition = savedInstanceState.getInt(CLICKED_CRIME_POSITION);
        }
        updateUI();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE,mSubtitleVisible);
        outState.putInt(CLICKED_CRIME_POSITION,AdapterPosition);
    }



    public void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if(mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCrimes(crimes);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        }
        else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                updateUI();
                mCallbacks.onCrimeSelected(crime);
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle(){
        CrimeLab cl = CrimeLab.get(getActivity());
        int crimeCount = cl.getCrimes().size();
        String subtitle = getString(R.string.subtitle_format,crimeCount);

        if(!mSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity act = (AppCompatActivity) getActivity();
        act.getSupportActionBar().setSubtitle(subtitle);
    }



    private class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Crime mCrime;
        private int mPosition;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mImageView;
        private static final int REQUEST_CRIME = 1;

        private CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
        }

        public void bind(Crime crime, int position) {
            mCrime = crime;
            mPosition = position;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mImageView.setVisibility(crime.isSolved() ? View.VISIBLE: View.GONE);
        }

        @Override
        public void onClick(View view) {
//            CrimeLab cl = CrimeLab.get(getActivity());
//            Intent intento = CrimePageActivity.newIntent(getActivity(),mCrime.getId());
//            startActivityForResult(intento,1);
            mCallbacks.onCrimeSelected(mCrime);
        }


    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }



        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
            AdapterPosition = position;
            Crime crime = mCrimes.get(position);
            holder.bind(crime, position);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }

        public void setCrimes(List<Crime> Crimes){
            mCrimes = Crimes;
        }
    }


}