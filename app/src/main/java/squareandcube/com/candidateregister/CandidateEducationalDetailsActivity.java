package squareandcube.com.candidateregister;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

public class CandidateEducationalDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG ="EductionlActivity" ;
    private final AppCompatActivity activity = CandidateEducationalDetailsActivity.this;

    private ScrollView mscrollView;

    private TextInputEditText mSSCMarksEditText;
    private TextInputEditText mSSCInstituteEditText;
    private TextInputEditText mSSCUniversityEditText;
    private TextInputEditText mSSCYearPassEditText;
    private TextInputEditText mInterMarksEditText;
    private TextInputEditText mInterInstituteEditText;
    private TextInputEditText mInterUniversityEditText;
    private TextInputEditText mInterYearPassEditText;
    private TextInputEditText mGraduationMarksEditText;
    private TextInputEditText mGraduationInstituteEditText;
    private TextInputEditText mGraduationUniversityEditText;
    private TextInputEditText mGraducationYearPassEditText;
    private TextInputEditText mPostGraduationMarksEditText;
    private TextInputEditText mPostGraduationInstituteEditText;
    private TextInputEditText mPostGraduationUniversityEditText;
    private TextInputEditText mPostGraducationYearPassEditText;

    private RadioGroup mEducationGapsRadioGroup;
    private RadioButton radioButtonEdu;

    private double averageMarks;

    private Button mSaveButton;

    private InputValidation mEduInputValidation;
    private DatabaseHelper mEduDatabaseHelper;
    private CandidateDataModel mEduCandidateDataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_educational_details);
        initiateViews();
        initiateListeners();
        initiateObjects();

        mEducationGapsRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButtonEdu = (RadioButton)findViewById(checkedId);
            }
        });
    }

    private void initiateViews() {

        mscrollView = (ScrollView) findViewById(R.id.educationscroll);

        mSSCMarksEditText = (TextInputEditText) findViewById(R.id.textinputedittext_ssc_marks);
        mSSCInstituteEditText = (TextInputEditText) findViewById(R.id.textinputedittext_ssc_institute);
        mSSCUniversityEditText = (TextInputEditText) findViewById(R.id.textinputedittext_ssc_university);
        mSSCYearPassEditText = (TextInputEditText) findViewById(R.id.textinputedittext_ssc_yearPassing);

        mInterMarksEditText = (TextInputEditText) findViewById(R.id.textinputedittext_inter_marks);
        mInterInstituteEditText = (TextInputEditText) findViewById(R.id.textinputedittext_inter_institute);
        mInterUniversityEditText = (TextInputEditText) findViewById(R.id.textinputedittext_inter_university);
        mInterYearPassEditText = (TextInputEditText) findViewById(R.id.textinputedittext_inter_yearPassing);

        mGraduationMarksEditText = (TextInputEditText) findViewById(R.id.textinputedittext_graduation_marks);
        mGraduationInstituteEditText = (TextInputEditText) findViewById(R.id.textinputedittext_graduation_institute);
        mGraduationUniversityEditText = (TextInputEditText) findViewById(R.id.textinputedittext_graduation_university);
        mGraducationYearPassEditText = (TextInputEditText) findViewById(R.id.textinputedittext_graduation_yearPassing);

        mPostGraduationMarksEditText = (TextInputEditText) findViewById(R.id.textinputedittext_postgraduation_marks);
        mPostGraduationInstituteEditText = (TextInputEditText) findViewById(R.id.textinputedittext_postgraduation_institute);
        mPostGraduationUniversityEditText = (TextInputEditText) findViewById(R.id.textinputedittext_postgraduation_university);
        mPostGraducationYearPassEditText = (TextInputEditText) findViewById(R.id.textinputedittext_postgraduation_yearPassing);

        mEducationGapsRadioGroup = (RadioGroup) findViewById(R.id.yearGap_radioGroup);

        mSaveButton = (Button) findViewById(R.id.button_save);
    }

    private void initiateListeners() {
        mSaveButton.setOnClickListener(this);
    }

    private void initiateObjects() {
        mEduDatabaseHelper = new DatabaseHelper(activity);
        mEduInputValidation = new InputValidation(activity);
        mEduCandidateDataModel = new CandidateDataModel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                saveDataToSQLite();
                    break;
        }
    }

    private void saveDataToSQLite() {
        if (mPostGraduationMarksEditText.getText().toString().matches("")) {
            if (!mEduInputValidation.isInputEditTextFilled(mSSCMarksEditText, getString(R.string.error_marks))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mSSCInstituteEditText, getString(R.string.error_institute))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mSSCUniversityEditText, getString(R.string.error_university))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mSSCYearPassEditText, getString(R.string.error_year))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mInterMarksEditText, getString(R.string.error_marks))) {

                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mInterInstituteEditText, getString(R.string.error_institute))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mInterUniversityEditText, getString(R.string.error_university))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mInterYearPassEditText, getString(R.string.error_year))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mGraduationMarksEditText, getString(R.string.error_marks))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mGraduationInstituteEditText, getString(R.string.error_institute))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mGraduationUniversityEditText, getString(R.string.error_university))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mGraducationYearPassEditText, getString(R.string.error_year))) {
                return;
            }
            if (!mEduInputValidation.isRadioButtonGapChecked(mEducationGapsRadioGroup,getApplicationContext())){
                return;
            }
            if ((Integer.parseInt(mSSCMarksEditText.getText().toString().trim())) > 100
                    || (Integer.parseInt(mInterMarksEditText.getText().toString().trim())) > 100
                    || (Integer.parseInt(mGraduationMarksEditText.getText().toString().trim())) > 100) {
                Snackbar.make(mscrollView, "Enter Proper Percentage (0-100) only", Snackbar.LENGTH_LONG).show();
            }else{
                if (mPostGraduationMarksEditText.getText().toString().matches("")) {
                    double ssc = Double.parseDouble(mSSCMarksEditText.getText().toString().trim());
                    double inter = Double.parseDouble(mInterMarksEditText.getText().toString().trim());
                    double grad = Double.parseDouble(mGraduationMarksEditText.getText().toString().trim());
                    averageMarks = (ssc + inter + grad) / 3;
                } else {
                    double ssc = Double.parseDouble(mSSCMarksEditText.getText().toString().trim());
                    double inter = Double.parseDouble(mInterMarksEditText.getText().toString().trim());
                    double grad = Double.parseDouble(mGraduationMarksEditText.getText().toString().trim());
                    double post = Double.parseDouble(mPostGraduationMarksEditText.getText().toString().trim());
                    averageMarks = (ssc + inter + grad + post) / 4;
                }


                Bundle b = getIntent().getExtras();
                String fname = b.getString("FName");
                String lname = b.getString("LName");
                String dob = b.getString("DOB");
                String mobilenumber = b.getString("MobileNo");
                String emailId = b.getString("Email");
                String password = b.getString("Password");
                String gender = b.getString("Gender");

                mEduCandidateDataModel.setFirstName(fname);
                mEduCandidateDataModel.setLastName(lname);
                mEduCandidateDataModel.setDateOfBirth(dob);
                mEduCandidateDataModel.setMobileNumber(mobilenumber);
                mEduCandidateDataModel.setEmailId(emailId);
                mEduCandidateDataModel.setPassword(password);
                mEduCandidateDataModel.setGender(gender);

                mEduCandidateDataModel.setSecondaryTotalNumber(mSSCMarksEditText.getText().toString().trim());
                mEduCandidateDataModel.setSecondaryInstitutionName(mSSCInstituteEditText.getText().toString().trim());
                mEduCandidateDataModel.setSecondaryUniversityName(mSSCUniversityEditText.getText().toString().trim());
                mEduCandidateDataModel.setSecondaryYOP(mSSCYearPassEditText.getText().toString().trim());
                mEduCandidateDataModel.setHigherSecondaryTotalNumber(mInterMarksEditText.getText().toString().trim());
                mEduCandidateDataModel.setHigherSecondaryInstitutionName(mInterInstituteEditText.getText().toString().trim());
                mEduCandidateDataModel.setHigherSecondaryUniversityName(mInterUniversityEditText.getText().toString().trim());
                mEduCandidateDataModel.setHigherSecondaryYOP(mInterYearPassEditText.getText().toString().trim());
                mEduCandidateDataModel.setGraduationTotalNumber(mGraduationMarksEditText.getText().toString().trim());
                mEduCandidateDataModel.setGraduationInstitutionName(mGraduationInstituteEditText.getText().toString().trim());
                mEduCandidateDataModel.setGraduationUniversityName(mGraduationUniversityEditText.getText().toString().trim());
                mEduCandidateDataModel.setGraduationYOP(mGraducationYearPassEditText.getText().toString().trim());
                mEduCandidateDataModel.setPostGraduationTotalNumber(mPostGraduationMarksEditText.getText().toString().trim());
                mEduCandidateDataModel.setPostGraduationInstitutionName(mPostGraduationInstituteEditText.getText().toString().trim());
                mEduCandidateDataModel.setPostGraduationUniversityName(mPostGraduationUniversityEditText.getText().toString().trim());
                mEduCandidateDataModel.setPostGraduationYOP(mPostGraducationYearPassEditText.getText().toString().trim());
                mEduCandidateDataModel.setYearGap(radioButtonEdu.getText().toString().trim());
                mEduCandidateDataModel.setAvg(String.valueOf(averageMarks));

                mEduDatabaseHelper.addUser(mEduCandidateDataModel);

                mEduDatabaseHelper.addUserEducationDetails(mEduCandidateDataModel);

                Snackbar.make(mscrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                Intent mLoginIntent = new Intent(CandidateEducationalDetailsActivity.this, CandidateLoginActivity.class);
                startActivity(mLoginIntent);
                finish();
                emptyInputEditText();
            }
        }
        else {
            if (!mEduInputValidation.isInputEditTextFilled(mSSCMarksEditText, getString(R.string.error_marks))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mSSCInstituteEditText, getString(R.string.error_institute))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mSSCUniversityEditText, getString(R.string.error_university))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mSSCYearPassEditText, getString(R.string.error_year))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mInterMarksEditText, getString(R.string.error_marks))) {

                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mInterInstituteEditText, getString(R.string.error_institute))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mInterUniversityEditText, getString(R.string.error_university))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mInterYearPassEditText, getString(R.string.error_year))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mGraduationMarksEditText, getString(R.string.error_marks))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mGraduationInstituteEditText, getString(R.string.error_institute))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mGraduationUniversityEditText, getString(R.string.error_university))) {
                return;
            }
            if (!mEduInputValidation.isInputEditTextFilled(mGraducationYearPassEditText, getString(R.string.error_year))) {
                return;
            }
            if (!mEduInputValidation.isRadioButtonGapChecked(mEducationGapsRadioGroup,getApplicationContext())){
                return;
            }
            if ((Integer.parseInt(mSSCMarksEditText.getText().toString().trim())) > 100
                    || (Integer.parseInt(mInterMarksEditText.getText().toString().trim())) > 100
                    || (Integer.parseInt(mGraduationMarksEditText.getText().toString().trim())) > 100
                    || (Integer.parseInt(mPostGraduationMarksEditText.getText().toString().trim())) > 100) {
                Snackbar.make(mscrollView, "Enter Proper Percentage (0-100) only", Snackbar.LENGTH_LONG).show();
            } else {
                if (mPostGraduationMarksEditText.getText().toString().matches("")) {
                    double ssc = Double.parseDouble(mSSCMarksEditText.getText().toString().trim());
                    double inter = Double.parseDouble(mInterMarksEditText.getText().toString().trim());
                    double grad = Double.parseDouble(mGraduationMarksEditText.getText().toString().trim());
                    averageMarks = (ssc + inter + grad) / 3;
                } else {
                    double ssc = Double.parseDouble(mSSCMarksEditText.getText().toString().trim());
                    double inter = Double.parseDouble(mInterMarksEditText.getText().toString().trim());
                    double grad = Double.parseDouble(mGraduationMarksEditText.getText().toString().trim());
                    double post = Double.parseDouble(mPostGraduationMarksEditText.getText().toString().trim());
                    averageMarks = (ssc + inter + grad + post) / 4;
                }


                Bundle b = getIntent().getExtras();
                String fname = b.getString("FName");
                String lname = b.getString("LName");
                String dob = b.getString("DOB");
                String mobilenumber = b.getString("MobileNo");
                String emailId = b.getString("Email");
                String password = b.getString("Password");
                String gender = b.getString("Gender");

                mEduCandidateDataModel.setFirstName(fname);
                mEduCandidateDataModel.setLastName(lname);
                mEduCandidateDataModel.setDateOfBirth(dob);
                mEduCandidateDataModel.setMobileNumber(mobilenumber);
                mEduCandidateDataModel.setEmailId(emailId);
                mEduCandidateDataModel.setPassword(password);
                mEduCandidateDataModel.setGender(gender);

                mEduCandidateDataModel.setSecondaryTotalNumber(mSSCMarksEditText.getText().toString().trim());
                mEduCandidateDataModel.setSecondaryInstitutionName(mSSCInstituteEditText.getText().toString().trim());
                mEduCandidateDataModel.setSecondaryUniversityName(mSSCUniversityEditText.getText().toString().trim());
                mEduCandidateDataModel.setSecondaryYOP(mSSCYearPassEditText.getText().toString().trim());
                mEduCandidateDataModel.setHigherSecondaryTotalNumber(mInterMarksEditText.getText().toString().trim());
                mEduCandidateDataModel.setHigherSecondaryInstitutionName(mInterInstituteEditText.getText().toString().trim());
                mEduCandidateDataModel.setHigherSecondaryUniversityName(mInterUniversityEditText.getText().toString().trim());
                mEduCandidateDataModel.setHigherSecondaryYOP(mInterYearPassEditText.getText().toString().trim());
                mEduCandidateDataModel.setGraduationTotalNumber(mGraduationMarksEditText.getText().toString().trim());
                mEduCandidateDataModel.setGraduationInstitutionName(mGraduationInstituteEditText.getText().toString().trim());
                mEduCandidateDataModel.setGraduationUniversityName(mGraduationUniversityEditText.getText().toString().trim());
                mEduCandidateDataModel.setGraduationYOP(mGraducationYearPassEditText.getText().toString().trim());
                mEduCandidateDataModel.setPostGraduationTotalNumber(mPostGraduationMarksEditText.getText().toString().trim());
                mEduCandidateDataModel.setPostGraduationInstitutionName(mPostGraduationInstituteEditText.getText().toString().trim());
                mEduCandidateDataModel.setPostGraduationUniversityName(mPostGraduationUniversityEditText.getText().toString().trim());
                mEduCandidateDataModel.setPostGraduationYOP(mPostGraducationYearPassEditText.getText().toString().trim());
                mEduCandidateDataModel.setYearGap(radioButtonEdu.getText().toString().trim());
                mEduCandidateDataModel.setAvg(String.valueOf(averageMarks));

                mEduDatabaseHelper.addUser(mEduCandidateDataModel);

                mEduDatabaseHelper.addUserEducationDetails(mEduCandidateDataModel);

                Snackbar.make(mscrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                Intent mLoginIntent = new Intent(CandidateEducationalDetailsActivity.this, CandidateLoginActivity.class);
                startActivity(mLoginIntent);
                finish();
                emptyInputEditText();
            }
        }
    }
    private void emptyInputEditText() {
        mSSCMarksEditText.setText(null);
        mSSCInstituteEditText.setText(null);
        mSSCUniversityEditText.setText(null);
        mSSCYearPassEditText.setText(null);
        mInterMarksEditText.setText(null);
        mInterInstituteEditText.setText(null);
        mInterUniversityEditText.setText(null);
        mInterYearPassEditText.setText(null);
        mGraduationMarksEditText.setText(null);
        mGraduationInstituteEditText.setText(null);
        mGraduationUniversityEditText.setText(null);
        mGraducationYearPassEditText.setText(null);
        mPostGraduationMarksEditText.setText(null);
        mPostGraduationInstituteEditText.setText(null);
        mPostGraduationUniversityEditText.setText(null);
        mPostGraducationYearPassEditText.setText(null);

    }

}
