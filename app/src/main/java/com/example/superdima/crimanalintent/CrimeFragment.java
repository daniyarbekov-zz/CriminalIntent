//package com.example.superdima.crimanalintent;
//
//import android.app.Activity;
//import android.app.FragmentManager;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.content.FileProvider;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.text.format.DateFormat;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//
//import java.io.File;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import static android.app.Activity.RESULT_OK;
//
//public class CrimeFragment extends Fragment{
//    private Crime mCrime;
//    private File mPhotoFile;
//
//    private EditText mTitleField;
//    private Button mDateButton;
//    private CheckBox mCheckBox;
//    private Button mReportButton;
//    private Button mSuspectButton;
//    private ImageButton mPhotoButton;
//    private ImageView mPhotoView;
//
//
//
//
//    private static final String ARG_CRIME_ID = "crime_id";
//    private static final String RESULT_CRIME = "result_crime";
//    private static final String DIALOG_DATE  = "DialogDate";
//
//
//    private static final int REQUEST_Date = 0;
//    private static final int REQUEST_CONTACT = 1;
//    private static final int REQUEST_PHOTO= 2;


////    public static CrimeFragment newInstance(int crimeID){
////        Bundle args = new Bundle();
////        args.putInt(ARG_CRIME_ID,crimeID);
////
////        CrimeFragment cf = new CrimeFragment();
////        cf.setArguments(args);
////        return cf;
////    }
//
//        public static CrimeFragment newInstance(UUID crimeID){
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_CRIME_ID,crimeID);
//
//        CrimeFragment cf = new CrimeFragment();
//        cf.setArguments(args);
//        return cf;
//    }
//
//
//    public static int getCrimePositionFromData(Intent data, int defaultValue) {
//        return data.getIntExtra(RESULT_CRIME, 0);
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        int id = (Integer) getArguments().getInt(ARG_CRIME_ID);
////        mCrime = CrimeLab.get(getActivity()).getCrimes().get(id);
//        UUID id = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
//        mCrime = CrimeLab.get(getActivity()).getCrime(id);
//        mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);
//
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//
//        CrimeLab.get(getActivity())
//                .updateCrime(mCrime);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != Activity.RESULT_OK) {
//            return;
//        }
//
//        if (requestCode == REQUEST_Date) {
//            Date date = (Date) data
//                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
//            mCrime.setDate(date);
//            updateDate();
//        }
//        else if (requestCode == REQUEST_CONTACT && data != null) {
//            Uri contactUri = data.getData();
//            // Specify which fields you want your query to return
//            // values for.
//            String[] queryFields = new String[]{
//                    ContactsContract.Contacts.DISPLAY_NAME
//            };
//            // Perform your query - the contactUri is like a "where"
//            // clause here
//            Cursor c = getActivity().getContentResolver()
//                    .query(contactUri, queryFields, null, null, null);
//            try {
//                // Double-check that you actually got results
//                if (c.getCount() == 0) {
//                    return;
//                }
//                // Pull out the first column of the first row of data -
//                // that is your suspect's name.
//                c.moveToFirst();
//                String suspect = c.getString(0);
//                mCrime.setSuspect(suspect);
//                mSuspectButton.setText(suspect);
//            } finally {
//                c.close();
//            }
//        }
//        else if(requestCode == REQUEST_PHOTO){
//            Uri uri = FileProvider.getUriForFile(getActivity(),"com.example.superdima.crimanalintent.fileprovider",mPhotoFile);
//
//            getActivity().revokeUriPermission(uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//
//            updatePhotoView();
//        }
//    }
//
//
//
//    private void updateDate() {
//        mDateButton.setText(mCrime.getDate().toString());
//    }
//
//
//
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_crime,container,false);
//
//        mTitleField = v.findViewById(R.id.crime_title);
//        mTitleField.setText(mCrime.getTitle());
//        mTitleField.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mCrime.setTitle(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        mDateButton =  (Button) v.findViewById(R.id.crime_date);
//        mDateButton.setText(mCrime.getDate().toString());
//        mDateButton.setOnClickListener(new View.OnClickListener() {
//       @Override
//           public void onClick(View v) {
//               android.support.v4.app.FragmentManager manager = getFragmentManager();
//               DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
//               dialog.setTargetFragment(CrimeFragment.this,REQUEST_Date);
//               dialog.show(manager, DIALOG_DATE);
//           }
//       });
//        mCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
//        mCheckBox.setChecked(mCrime.isSolved());
//        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked){
//                mCrime.setSolved(isChecked);
//            }
//        });
//
//        mReportButton = (Button) v.findViewById(R.id.crime_report);
//        mReportButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
//                i.putExtra(Intent.EXTRA_SUBJECT,
//                        getString(R.string.crime_report_subject));
//                i = Intent.createChooser(i, getString(R.string.send_report));
//                startActivity(i);
//            }
//        });
//
//        final Intent pickContact = new Intent(Intent.ACTION_PICK,
//                ContactsContract.Contacts.CONTENT_URI);
//        mSuspectButton = (Button) v.findViewById(R.id.crime_suspect);
//        mSuspectButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                startActivityForResult(pickContact, REQUEST_CONTACT);
//            }
//        });
//        if (mCrime.getSuspect() != null) {
//            mSuspectButton.setText(mCrime.getSuspect());
//        }
//
//        PackageManager packageManager = getActivity().getPackageManager();
//        if (packageManager.resolveActivity(pickContact,
//                PackageManager.MATCH_DEFAULT_ONLY) == null) {
//            mSuspectButton.setEnabled(false);
//        }
//
//        mPhotoButton = (ImageButton) v.findViewById(R.id.crime_camera);
//
//        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        boolean canTakePhoto = mPhotoFile!= null && captureImage.resolveActivity((packageManager))!=null;
//
//        mPhotoButton.setEnabled(canTakePhoto);
//
//        mPhotoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = FileProvider.getUriForFile(getActivity(),"com.example.superdima.crimanalintent.fileprovider",mPhotoFile);
//                captureImage.putExtra(MediaStore.EXTRA_OUTPUT,uri);
//
//                List<ResolveInfo> cameraActivities = getActivity().getPackageManager().queryIntentActivities(captureImage,
//                        PackageManager.MATCH_DEFAULT_ONLY);
//
//                for(ResolveInfo activity: cameraActivities){
//                    getActivity().grantUriPermission(activity.activityInfo.packageName,uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                }
//
//                startActivityForResult(captureImage,REQUEST_PHOTO);
//
//            }
//        });
//
//        mPhotoView = (ImageView) v.findViewById(R.id.crime_photo);
//        updatePhotoView();
//
//        return v;
//    }
//
//    private String getCrimeReport() {
//        String solvedString = null;
//        if (mCrime.isSolved()) {
//            solvedString = getString(R.string.crime_report_solved);
//        } else {
//            solvedString = getString(R.string.crime_report_unsolved);
//        }
//        String dateFormat = "EEE, MMM dd";
//        String dateString = DateFormat.format(dateFormat, mCrime.getDate()).toString();
//        String suspect = mCrime.getSuspect();
//        if (suspect == null) {
//            suspect = getString(R.string.crime_report_no_suspect);
//        } else {
//            suspect = getString(R.string.crime_report_suspect, suspect);
//        }
//        String report = getString(R.string.crime_report,
//                mCrime.getTitle(), dateString, solvedString, suspect);
//        return report;
//    }
//
//    private void updatePhotoView(){
//            if (mPhotoFile == null || !mPhotoFile.exists()){
//                mPhotoView.setImageDrawable(null);
//            }
//            else{
//                Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(),getActivity());
//                mPhotoView.setImageBitmap(bitmap);
//            }
//    }
//
//
//}

package com.example.superdima.crimanalintent;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class CrimeFragment extends Fragment{
    private Crime mCrime;
    private File mPhotoFile;
    private Callbacks mCallbacks;



    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mCheckBox;
    private Button mReportButton;
    private Button mSuspectButton;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;


    private static final String ARG_CRIME_ID = "crime_id";
    private static final String RESULT_CRIME = "result_crime";
    private static final String DIALOG_DATE  = "DialogDate";


    private static final int REQUEST_Date = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_PHOTO= 2;
//    public static CrimeFragment newInstance(int crimeID){
//        Bundle args = new Bundle();
//        args.putInt(ARG_CRIME_ID,crimeID);
//
//        CrimeFragment cf = new CrimeFragment();
//        cf.setArguments(args);
//        return cf;
//    }

    public interface Callbacks{
        void onCrimeUpdated(Crime crime);
    }

    public void onAttach(Context context){
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }
//
    private void updateCrime(){
        CrimeLab.get(getActivity()).updateCrime(mCrime);
        mCallbacks.onCrimeUpdated(mCrime);
    }
    public void onDetach(){
        super.onDetach();
        mCallbacks=null;
    }



    public static CrimeFragment newInstance(UUID crimeID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeID);

        CrimeFragment cf = new CrimeFragment();
        cf.setArguments(args);
        return cf;
    }


    public static int getCrimePositionFromData(Intent data, int defaultValue) {
        return data.getIntExtra(RESULT_CRIME, 0);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int id = (Integer) getArguments().getInt(ARG_CRIME_ID);
//        mCrime = CrimeLab.get(getActivity()).getCrimes().get(id);
        UUID id = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(id);
        mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);

    }

    @Override
    public void onPause() {
        super.onPause();

        CrimeLab.get(getActivity())
                .updateCrime(mCrime);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_Date) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateCrime();
            updateDate();
        }


        else if (requestCode == REQUEST_CONTACT && data != null) {
            Uri contactUri = data.getData();
            // Specify which fields you want your query to return
            // values for.
            String[] queryFields = new String[]{
                    ContactsContract.Contacts.DISPLAY_NAME
            };
            // Perform your query - the contactUri is like a "where"
            // clause here
            Cursor c = getActivity().getContentResolver()
                    .query(contactUri, queryFields, null, null, null);
            try {
                // Double-check that you actually got results
                if (c.getCount() == 0) {
                    return;
                }
                // Pull out the first column of the first row of data -
                // that is your suspect's name.
                c.moveToFirst();
                String suspect = c.getString(0);
                mCrime.setSuspect(suspect);
                updateCrime();
                mSuspectButton.setText(suspect);
            } finally {
                c.close();
            }
        }
        else if(requestCode == REQUEST_PHOTO){
            Uri uri = FileProvider.getUriForFile(getActivity(),"com.example.superdima.crimanalintent.fileprovider",mPhotoFile);

            getActivity().revokeUriPermission(uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            updateCrime();
            updatePhotoView();
        }
    }



    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime,container,false);

        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
                updateCrime();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton =  (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
       @Override
           public void onClick(View v) {
               android.support.v4.app.FragmentManager manager = getFragmentManager();
               DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
               dialog.setTargetFragment(CrimeFragment.this,REQUEST_Date);
               dialog.show(manager, DIALOG_DATE);
           }
       });
        mCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mCheckBox.setChecked(mCrime.isSolved());
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked){
                mCrime.setSolved(isChecked);
                updateCrime();
            }
        });

        mReportButton = (Button) v.findViewById(R.id.crime_report);
        mReportButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
                i.putExtra(Intent.EXTRA_SUBJECT,
                        getString(R.string.crime_report_subject));
                i = Intent.createChooser(i, getString(R.string.send_report));
                startActivity(i);
            }
        });

        final Intent pickContact = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        mSuspectButton = (Button) v.findViewById(R.id.crime_suspect);
        mSuspectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });
        if (mCrime.getSuspect() != null) {
            mSuspectButton.setText(mCrime.getSuspect());
        }

        PackageManager packageManager = getActivity().getPackageManager();
        if (packageManager.resolveActivity(pickContact,
                PackageManager.MATCH_DEFAULT_ONLY) == null) {
            mSuspectButton.setEnabled(false);
        }

        mPhotoButton = (ImageButton) v.findViewById(R.id.crime_camera);

        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        boolean canTakePhoto = mPhotoFile!= null && captureImage.resolveActivity((packageManager))!=null;

        mPhotoButton.setEnabled(canTakePhoto);

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = FileProvider.getUriForFile(getActivity(),"com.example.superdima.crimanalintent.fileprovider",mPhotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT,uri);

                List<ResolveInfo> cameraActivities = getActivity().getPackageManager().queryIntentActivities(captureImage,
                        PackageManager.MATCH_DEFAULT_ONLY);

                for(ResolveInfo activity: cameraActivities){
                    getActivity().grantUriPermission(activity.activityInfo.packageName,uri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(captureImage,REQUEST_PHOTO);

            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.crime_photo);
        updatePhotoView();

        return v;
    }

    private String getCrimeReport() {
        String solvedString = null;
        if (mCrime.isSolved()) {
            solvedString = getString(R.string.crime_report_solved);
        } else {
            solvedString = getString(R.string.crime_report_unsolved);
        }
        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat, mCrime.getDate()).toString();
        String suspect = mCrime.getSuspect();
        if (suspect == null) {
            suspect = getString(R.string.crime_report_no_suspect);
        } else {
            suspect = getString(R.string.crime_report_suspect, suspect);
        }
        String report = getString(R.string.crime_report,
                mCrime.getTitle(), dateString, solvedString, suspect);
        return report;
    }

    private void updatePhotoView(){
            if (mPhotoFile == null || !mPhotoFile.exists()){
                mPhotoView.setImageDrawable(null);
            }
            else{
                Bitmap bitmap = PictureUtils.getScaledBitmap(mPhotoFile.getPath(),getActivity());
                mPhotoView.setImageBitmap(bitmap);
            }
    }


}