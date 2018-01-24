package squareandcube.com.candidateregister;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CandidateUsersListUpdateActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{

    private static final String TAG = "CandidateUsersListUpdateActivity";
    private AppCompatActivity activity = CandidateUsersListUpdateActivity.this;

    private CandidateDataModel mCandidateDalaList;
    private DatabaseHelper mDatabaseHelper;
    private InputValidation mInputValidation;
    private String emailFromIntent;

    private DatePickerDialog mDatePickerDialog;

    private TextInputEditText mFirstNameTextView;
    private TextInputEditText mLastNameTextView;
    private TextInputEditText mDobTextView;
    private TextInputEditText mMobileNumberTextView;
    private TextInputEditText mEmailTextView;
    private TextInputEditText mPasswordTextView;
    private TextInputEditText mGenderTextView;

    private TextInputEditText mSSCMarksTextView;
    private TextInputEditText mSSCInstituteTextView;
    private TextInputEditText mSSCUniversityTextView;
    private TextInputEditText mSSCYearofPassingTextView;
    private TextInputEditText mInterMarksTextView;
    private TextInputEditText mInterInstituteTextView;
    private TextInputEditText mInterUniversityTextView;
    private TextInputEditText mInterYearofPassingTextView;
    private TextInputEditText mGraduationMarksTextView;
    private TextInputEditText mGraduationInstituteTextView;
    private TextInputEditText mGraduationUniversityTextView;
    private TextInputEditText mGraduationYearofPassingTextView;
    private TextInputEditText mPostGraduationMarksTextView;
    private TextInputEditText mPostGraduationInstituteTextView;
    private TextInputEditText mPostGraduationUniversityTextView;
    private TextInputEditText mPostGraduationYearofPassingTextView;
    private TextInputEditText mEducationalYearGapsTextView;
    private TextInputEditText mAverage;

    private double average;
    private Button medit;
    private Button msave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_users_list_update);
        initiateViews();
        initiateObjects();
        initListeners();
        displayData();
    }

    private void initListeners() {
        mDobTextView.setOnTouchListener(this);

    }

    private void initiateViews() {
        mFirstNameTextView = (TextInputEditText) findViewById(R.id.textinputedittext_first_name);
        mLastNameTextView = (TextInputEditText) findViewById(R.id.textinputedittext_last_name);
        mDobTextView = (TextInputEditText) findViewById(R.id.textinputedittext_dob);
        mMobileNumberTextView = (TextInputEditText) findViewById(R.id.textinputedittext_mobile_number);
        mEmailTextView = (TextInputEditText) findViewById(R.id.textinputedittext_email);
        mPasswordTextView = (TextInputEditText) findViewById(R.id.textinputedittext_password);
        mGenderTextView = (TextInputEditText) findViewById(R.id.textinputedittext_gender);

        mSSCMarksTextView = (TextInputEditText) findViewById(R.id.textinputedittext_ssc_marks);
        mSSCInstituteTextView = (TextInputEditText) findViewById(R.id.textinputedittext_ssc_institute);
        mSSCUniversityTextView = (TextInputEditText) findViewById(R.id.textinputedittext_ssc_university);
        mSSCYearofPassingTextView = (TextInputEditText) findViewById(R.id.textinputedittext_ssc_yearPassing);
        mInterMarksTextView = (TextInputEditText) findViewById(R.id.textinputedittext_inter_marks);
        mInterInstituteTextView = (TextInputEditText) findViewById(R.id.textinputedittext_inter_institute);
        mInterUniversityTextView = (TextInputEditText) findViewById(R.id.textinputedittext_inter_university);
        mInterYearofPassingTextView = (TextInputEditText) findViewById(R.id.textinputedittext_inter_yearPassing);
        mGraduationMarksTextView = (TextInputEditText) findViewById(R.id.textinputedittext_graduation_marks);
        mGraduationInstituteTextView = (TextInputEditText) findViewById(R.id.textinputedittext_graduation_institute);
        mGraduationUniversityTextView = (TextInputEditText) findViewById(R.id.textinputedittext_graduation_university);
        mGraduationYearofPassingTextView = (TextInputEditText) findViewById(R.id.textinputedittext_graduation_yearPassing);
        mPostGraduationMarksTextView = (TextInputEditText) findViewById(R.id.textinputedittext_postgraduation_marks);
        mPostGraduationInstituteTextView = (TextInputEditText) findViewById(R.id.textinputedittext_postgraduation_institute);
        mPostGraduationUniversityTextView = (TextInputEditText) findViewById(R.id.textinputedittext_postgraduation_university);
        mPostGraduationYearofPassingTextView = (TextInputEditText) findViewById(R.id.textinputedittext_postgraduation_yearPassing);
        mEducationalYearGapsTextView = (TextInputEditText) findViewById(R.id.textinputedittext_educationalGaps);
        mAverage = (TextInputEditText) findViewById(R.id.textinputedittext_avg);

        medit = (Button) findViewById(R.id.button_edit);
        medit.setOnClickListener(this);

        msave = (Button) findViewById(R.id.button_save);
        msave.setOnClickListener(this);

        editableFalse();
    }

    private void editableFalse() {
        mFirstNameTextView.setEnabled(false);
        mLastNameTextView.setEnabled(false);
        mDobTextView.setEnabled(false);
        mMobileNumberTextView.setEnabled(false);
        mEmailTextView.setEnabled(false);
        mPasswordTextView.setEnabled(false);
        mGenderTextView.setEnabled(false);

        mSSCMarksTextView.setEnabled(false);
        mSSCInstituteTextView.setEnabled(false);
        mSSCUniversityTextView.setEnabled(false);
        mSSCYearofPassingTextView.setEnabled(false);

        mInterMarksTextView.setEnabled(false);
        mInterInstituteTextView.setEnabled(false);
        mInterUniversityTextView.setEnabled(false);
        mInterYearofPassingTextView.setEnabled(false);

        mGraduationMarksTextView.setEnabled(false);
        mGraduationInstituteTextView.setEnabled(false);
        mGraduationUniversityTextView.setEnabled(false);
        mGraduationYearofPassingTextView.setEnabled(false);

        mPostGraduationMarksTextView.setEnabled(false);
        mPostGraduationInstituteTextView.setEnabled(false);
        mPostGraduationUniversityTextView.setEnabled(false);
        mPostGraduationYearofPassingTextView.setEnabled(false);

        mEducationalYearGapsTextView.setEnabled(false);
        mAverage.setEnabled(false);
    }

    public void editableTrue() {
        mFirstNameTextView.setEnabled(true);
        mLastNameTextView.setEnabled(true);
        mDobTextView.setEnabled(true);
        mMobileNumberTextView.setEnabled(true);
        mPasswordTextView.setEnabled(true);

        mSSCMarksTextView.setEnabled(true);
        mSSCInstituteTextView.setEnabled(true);
        mSSCUniversityTextView.setEnabled(true);
        mSSCYearofPassingTextView.setEnabled(true);

        mInterMarksTextView.setEnabled(true);
        mInterInstituteTextView.setEnabled(true);
        mInterUniversityTextView.setEnabled(true);
        mInterYearofPassingTextView.setEnabled(true);

        mGraduationMarksTextView.setEnabled(true);
        mGraduationInstituteTextView.setEnabled(true);
        mGraduationUniversityTextView.setEnabled(true);
        mGraduationYearofPassingTextView.setEnabled(true);

        mPostGraduationMarksTextView.setEnabled(true);
        mPostGraduationInstituteTextView.setEnabled(true);
        mPostGraduationUniversityTextView.setEnabled(true);
        mPostGraduationYearofPassingTextView.setEnabled(true);

        mEducationalYearGapsTextView.setEnabled(true);

    }


    private void initiateObjects() {
        mDatabaseHelper = new DatabaseHelper(this);
        mCandidateDalaList = new CandidateDataModel();
        mInputValidation = new InputValidation(activity);
        emailFromIntent = getIntent().getStringExtra("EMAIL");
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        final int DRAWABLE_RIGHT = 2;
        if(motionEvent.getAction()==MotionEvent.ACTION_UP){
            if(motionEvent.getRawX() >= (mDobTextView.getRight() - mDobTextView.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mDatePickerDialog = new DatePickerDialog(CandidateUsersListUpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mDobTextView.setText(day+"/"+(month+1)+"/"+year);
                    }
                },mYear,mMonth,mDay);
                mDatePickerDialog.show();
            }
        }
        return false;
    }

    public void displayData(){
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM candidate_personal_details,candidate_educational_details WHERE candidate_personal_details.candidate_email_id= '"+emailFromIntent+"' AND candidate_personal_details.candidate_email_id=candidate_educational_details.candidate_email_id",null);
        c.moveToFirst();
        mFirstNameTextView.setText(c.getString(0));
        mLastNameTextView.setText(c.getString(1));
        mDobTextView.setText(c.getString(4));
        mMobileNumberTextView.setText(c.getString(3));
        mEmailTextView.setText(c.getString(2));
        mPasswordTextView.setText(c.getString(6));
        mGenderTextView.setText(c.getString(5));

        mSSCMarksTextView.setText(c.getString(8));
        mSSCInstituteTextView.setText(c.getString(9));
        mSSCUniversityTextView.setText(c.getString(10));
        mSSCYearofPassingTextView.setText(c.getString(11));

        mInterMarksTextView.setText(c.getString(12));
        mInterInstituteTextView.setText(c.getString(13));
        mInterUniversityTextView.setText(c.getString(14));
        mInterYearofPassingTextView.setText(c.getString(15));

        mGraduationMarksTextView.setText(c.getString(16));
        mGraduationInstituteTextView.setText(c.getString(17));
        mGraduationUniversityTextView.setText(c.getString(18));
        mGraduationYearofPassingTextView.setText(c.getString(19));

        mPostGraduationMarksTextView.setText(c.getString(20));
        mPostGraduationInstituteTextView.setText(c.getString(21));
        mPostGraduationUniversityTextView.setText(c.getString(22));
        mPostGraduationYearofPassingTextView.setText(c.getString(23));

        if(mPostGraduationMarksTextView.getText().toString().matches("")
                || mPostGraduationInstituteTextView.getText().toString().matches("")
                || mPostGraduationUniversityTextView.getText().toString().matches("")
                || mPostGraduationYearofPassingTextView.getText().toString().matches("")){
            mPostGraduationMarksTextView.setVisibility(View.INVISIBLE);
            mPostGraduationInstituteTextView.setVisibility(View.INVISIBLE);
            mPostGraduationUniversityTextView.setVisibility(View.INVISIBLE);
            mPostGraduationYearofPassingTextView.setVisibility(View.INVISIBLE);
        }
        mEducationalYearGapsTextView.setText(c.getString(24));

        mAverage.setText(c.getString(25));


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_edit:
                medit.setVisibility(View.INVISIBLE);
                msave.setVisibility(View.VISIBLE);
                editableTrue();
                break;
            case R.id.button_save:
                mCandidateDalaList.setFirstName(mFirstNameTextView.getText().toString().trim());
                mCandidateDalaList.setLastName(mLastNameTextView.getText().toString().trim());
                mCandidateDalaList.setDateOfBirth(mDobTextView.getText().toString().trim());
                mCandidateDalaList.setMobileNumber(mMobileNumberTextView.getText().toString().trim());
                mCandidateDalaList.setEmailId(mEmailTextView.getText().toString().trim());
                mCandidateDalaList.setPassword(mPasswordTextView.getText().toString().trim());
                mCandidateDalaList.setGender(mGenderTextView.getText().toString().trim());

                mCandidateDalaList.setSecondaryTotalNumber(mSSCMarksTextView.getText().toString().trim());
                mCandidateDalaList.setSecondaryInstitutionName(mSSCInstituteTextView.getText().toString().trim());
                mCandidateDalaList.setSecondaryUniversityName(mSSCUniversityTextView.getText().toString().trim());
                mCandidateDalaList.setSecondaryYOP(mSSCYearofPassingTextView.getText().toString().trim());

                mCandidateDalaList.setHigherSecondaryTotalNumber(mInterMarksTextView.getText().toString().trim());
                mCandidateDalaList.setHigherSecondaryInstitutionName(mInterInstituteTextView.getText().toString().trim());
                mCandidateDalaList.setHigherSecondaryUniversityName(mInterUniversityTextView.getText().toString().trim());
                mCandidateDalaList.setHigherSecondaryYOP(mInterYearofPassingTextView.getText().toString().trim());

                mCandidateDalaList.setGraduationTotalNumber(mGraduationMarksTextView.getText().toString().trim());
                mCandidateDalaList.setGraduationInstitutionName(mGraduationInstituteTextView.getText().toString().trim());
                mCandidateDalaList.setGraduationUniversityName(mGraduationUniversityTextView.getText().toString().trim());
                mCandidateDalaList.setGraduationYOP(mGraduationYearofPassingTextView.getText().toString().trim());

                mCandidateDalaList.setPostGraduationTotalNumber(mPostGraduationMarksTextView.getText().toString().trim());
                mCandidateDalaList.setPostGraduationInstitutionName(mPostGraduationInstituteTextView.getText().toString().trim());
                mCandidateDalaList.setPostGraduationUniversityName(mPostGraduationUniversityTextView.getText().toString().trim());
                mCandidateDalaList.setPostGraduationYOP(mPostGraduationYearofPassingTextView.getText().toString().trim());

                mCandidateDalaList.setYearGap(mEducationalYearGapsTextView.getText().toString().trim());

                Log.e("fname",""+mFirstNameTextView.getText().toString());
                if(mSSCInstituteTextView.getText().toString().matches("")||
                        mSSCUniversityTextView.getText().toString().matches("")||
                        mSSCYearofPassingTextView.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"SSC details Cannot Be Empty!",Toast.LENGTH_SHORT).show();
                }

                if(mInterInstituteTextView.getText().toString().matches("")||
                        mInterUniversityTextView.getText().toString().matches("")||
                        mInterYearofPassingTextView.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"Intermediate details Cannot Be Empty!",Toast.LENGTH_SHORT).show();
                }

                if(mGraduationInstituteTextView.getText().toString().matches("")||
                        mGraduationUniversityTextView.getText().toString().matches("")||
                        mGraduationYearofPassingTextView.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"Graduation details Cannot Be Empty!",Toast.LENGTH_SHORT).show();
                }

                if(mEducationalYearGapsTextView.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"Year Gap can't be Empty",Toast.LENGTH_SHORT).show();
                }

                if(mPostGraduationMarksTextView.getText().toString().matches("")) {

                    if (mSSCMarksTextView.getText().toString().matches("") ||
                            mInterMarksTextView.getText().toString().matches("") ||
                            mGraduationMarksTextView.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(),"Marks Cannot Be Empty!",Toast.LENGTH_SHORT).show();

                    } else {
                        double ssc = Double.parseDouble(mSSCMarksTextView.getText().toString().trim());
                        double inter = Double.parseDouble(mInterMarksTextView.getText().toString().trim());
                        double grad = Double.parseDouble(mGraduationMarksTextView.getText().toString().trim());
                        average = (ssc + inter + grad) / 3;
                    }
                }
                else {
                    if (mSSCMarksTextView.getText().toString().matches("") ||
                            mInterMarksTextView.getText().toString().matches("") ||
                            mGraduationMarksTextView.getText().toString().matches("")
                            || mPostGraduationMarksTextView.getText().toString().matches("")) {
                        Toast.makeText(getApplicationContext(), "Marks Cannot Be Empty!", Toast.LENGTH_SHORT).show();

                    } else {
                        double ssc = Double.parseDouble(mSSCMarksTextView.getText().toString().trim());
                        double inter = Double.parseDouble(mInterMarksTextView.getText().toString().trim());
                        double grad = Double.parseDouble(mGraduationMarksTextView.getText().toString().trim());
                        double post = Double.parseDouble(mPostGraduationMarksTextView.getText().toString().trim());
                        average = (ssc + inter + grad + post) / 4;
                    }
                }

                mCandidateDalaList.setAvg(String.valueOf(average));

                //Validation part

                postDataToSQLite();
                if(!mFirstNameTextView.getText().toString().matches("")
                        && !mLastNameTextView.getText().toString().matches("")
                        && !mDobTextView.getText().toString().matches("")
                        && !mMobileNumberTextView.getText().toString().matches("")
                        && !mPasswordTextView .getText().toString().matches("")

                        && !mSSCMarksTextView.getText().toString().matches("")
                        && !mSSCInstituteTextView.getText().toString().matches("")
                        && !mSSCUniversityTextView.getText().toString().matches("")
                        && !mSSCYearofPassingTextView.getText().toString().matches("")

                        && !mInterMarksTextView.getText().toString().matches("")
                        && !mInterInstituteTextView.getText().toString().matches("")
                        && !mInterUniversityTextView.getText().toString().matches("")
                        && !mInterYearofPassingTextView.getText().toString().matches("")

                        && !mGraduationMarksTextView.getText().toString().matches("")
                        && !mGraduationInstituteTextView.getText().toString().matches("")
                        && !mGraduationUniversityTextView.getText().toString().matches("")
                        && !mGraduationYearofPassingTextView.getText().toString().matches("")

                        && !mEducationalYearGapsTextView.getText().toString().matches("")) {

                    mDatabaseHelper.updateUser(mCandidateDalaList);
                    mDatabaseHelper.updateCandidateEducationalDetails(mCandidateDalaList);

                    SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();

                    Cursor c = db.rawQuery("SELECT * FROM candidate_personal_details,candidate_educational_details WHERE candidate_personal_details.candidate_email_id= '"+emailFromIntent+"' AND candidate_personal_details.candidate_email_id=candidate_educational_details.candidate_email_id",null);
                    c.moveToFirst();
                    mAverage.setText(c.getString(25));

                    medit.setVisibility(View.VISIBLE);
                    msave.setVisibility(View.INVISIBLE);
                    editableFalse();
                }
                break;
        }
    }
    private void postDataToSQLite() {
        if (!mInputValidation.isInputEditTextFilled(mFirstNameTextView, getString(R.string.error_message_first_name))) {
            return;
        }
        if (!mInputValidation.isInputEditTextFilled(mLastNameTextView, getString(R.string.error_message_last_name))) {
            return;
        }
        if (mInputValidation.isInputEditTextFilled(mDobTextView, getString(R.string.error_message_date_of_birth))) {
            return;
        }
        if (mInputValidation.isInputEditTextFilled(mMobileNumberTextView, getString(R.string.error_mobile_number))) {
            return;
        }
        if (mInputValidation.isInputEditTextFilled(mPasswordTextView, getString(R.string.error_message_password))) {
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mSSCMarksTextView,getString(R.string.error_marks))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mSSCInstituteTextView,getString(R.string.error_institute))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mSSCUniversityTextView,getString(R.string.error_university))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mInterMarksTextView,getString(R.string.error_marks))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mInterInstituteTextView,getString(R.string.error_institute))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mInterUniversityTextView,getString(R.string.error_university))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mInterYearofPassingTextView,getString(R.string.error_year))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mSSCYearofPassingTextView,getString(R.string.error_year))){
            return;
        }

        if(mInputValidation.isInputEditTextFilled(mGraduationMarksTextView,getString(R.string.error_marks))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mGraduationInstituteTextView,getString(R.string.error_institute))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mGraduationUniversityTextView,getString(R.string.error_university))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mGraduationYearofPassingTextView,getString(R.string.error_year))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mPostGraduationMarksTextView,getString(R.string.error_marks))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mPostGraduationInstituteTextView,getString(R.string.error_institute))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mPostGraduationUniversityTextView,getString(R.string.error_university))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mPostGraduationYearofPassingTextView,getString(R.string.error_year))){
            return;
        }
        if(mInputValidation.isInputEditTextFilled(mEducationalYearGapsTextView,getString(R.string.error_year_gap))){
            return;
        }


        emptyInputEditText();
    }
    private void emptyInputEditText() {
        mFirstNameTextView.setText(null);
        mLastNameTextView.setText(null);
        mDobTextView.setText(null);
        mMobileNumberTextView.setText(null);
        mEmailTextView.setText(null);
        mPasswordTextView.setText(null);
        mSSCMarksTextView.setText(null);
        mSSCInstituteTextView.setText(null);
        mSSCUniversityTextView.setText(null);
        mSSCYearofPassingTextView.setText(null);

        mInterMarksTextView.setText(null);
        mInterInstituteTextView.setText(null);
        mInterUniversityTextView.setText(null);
        mInterYearofPassingTextView.setText(null);

        mGraduationMarksTextView.setText(null);
        mGraduationInstituteTextView.setText(null);
        mGraduationUniversityTextView.setText(null);
        mGraduationYearofPassingTextView.setText(null);

        mPostGraduationMarksTextView.setText(null);
        mPostGraduationInstituteTextView.setText(null);
        mPostGraduationUniversityTextView.setText(null);
        mPostGraduationYearofPassingTextView.setText(null);
    }


}
