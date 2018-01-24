package squareandcube.com.candidateregister;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

public class UserDetailsActivity extends AppCompatActivity {

    private static final String TAG = "UserDetailsActivity" ;
    private AppCompatActivity activity = UserDetailsActivity.this;

    private DatabaseHelper mDatabaseHelper;
    private String emailFromIntent;

    private EditText mFirstNameTextView;
    private EditText mLastNameTextView;
    private EditText mDobTextView;
    private EditText mMobileNumberTextView;
    private EditText mEmailTextView;
    private EditText mPasswordTextView;
    private EditText mGenderTextView;

    private EditText mSSCMarksTextView;
    private EditText mSSCInstituteTextView;
    private EditText mSSCUniversityTextView;
    private EditText mSSCYearofPassingTextView;
    private EditText mInterMarksTextView;
    private EditText mInterInstituteTextView;
    private EditText mInterUniversityTextView;
    private EditText mInterYearofPassingTextView;
    private EditText mGraduationMarksTextView;
    private EditText mGraduationInstituteTextView;
    private EditText mGraduationUniversityTextView;
    private EditText mGraduationYearofPassingTextView;
    private EditText mPostGraduationMarksTextView;
    private EditText mPostGraduationInstituteTextView;
    private EditText mPostGraduationUniversityTextView;
    private EditText mPostGraduationYearofPassingTextView;
    private EditText mEducationalYearGapsTextView;
    private EditText mAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        initiateViews();
        initiateObjects();
        displayData();
    }
    private void initiateViews() {
        mFirstNameTextView = (EditText) findViewById(R.id.display_first_name_textview);
        mLastNameTextView = (EditText) findViewById(R.id.display_last_name_textview);
        mDobTextView = (EditText) findViewById(R.id.display_dob_textview);
        mMobileNumberTextView = (EditText) findViewById(R.id.display_mobile_textview);
        mEmailTextView = (EditText) findViewById(R.id.display_email_textview);
        mPasswordTextView = (EditText) findViewById(R.id.display_password_textview);
        mGenderTextView = (EditText) findViewById(R.id.display_gender_textview);

        mSSCMarksTextView = (EditText) findViewById(R.id.display_ssc_marks);
        mSSCInstituteTextView = (EditText) findViewById(R.id.display_ssc_institute);
        mSSCUniversityTextView = (EditText) findViewById(R.id.display_ssc_university);
        mSSCYearofPassingTextView = (EditText) findViewById(R.id.display_ssc_yearPassing);
        mInterMarksTextView = (EditText) findViewById(R.id.display_inter_marks);
        mInterInstituteTextView = (EditText) findViewById(R.id.display_inter_institute);
        mInterUniversityTextView = (EditText) findViewById(R.id.display_inter_university);
        mInterYearofPassingTextView = (EditText) findViewById(R.id.display_inter_yearPassing);
        mGraduationMarksTextView = (EditText) findViewById(R.id.display_graduation_marks);
        mGraduationInstituteTextView = (EditText) findViewById(R.id.display_graduation_institute);
        mGraduationUniversityTextView = (EditText) findViewById(R.id.display_graduation_university);
        mGraduationYearofPassingTextView = (EditText) findViewById(R.id.display_graduation_yearPassing);
        mPostGraduationMarksTextView = (EditText) findViewById(R.id.display_postgraduation_marks);
        mPostGraduationInstituteTextView = (EditText) findViewById(R.id.display_postgraduation_institute);
        mPostGraduationUniversityTextView = (EditText) findViewById(R.id.display_postgraduation_university);
        mPostGraduationYearofPassingTextView = (EditText) findViewById(R.id.display_postgraduation_yearPassing);
        mEducationalYearGapsTextView = (EditText) findViewById(R.id.display_educationalGaps);
        mAverage = (EditText) findViewById(R.id.display_avg);

    }

    private void initiateObjects() {
        mDatabaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        emailFromIntent = sharedPreferences.getString("Email","");
        Log.d(TAG, "Email: "+emailFromIntent);
    }
    public void displayData(){
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM candidate_personal_details,candidate_educational_details WHERE candidate_personal_details.candidate_email_id= '"+emailFromIntent+"' AND candidate_personal_details.candidate_email_id = candidate_educational_details.candidate_email_id",null);
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

        mEducationalYearGapsTextView.setText(c.getString(24));

        mAverage.setText(c.getString(25));
    }

}
