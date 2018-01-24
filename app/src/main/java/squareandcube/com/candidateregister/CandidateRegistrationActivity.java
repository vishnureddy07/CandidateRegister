package squareandcube.com.candidateregister;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Calendar;

public class CandidateRegistrationActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

    private final AppCompatActivity activity = CandidateRegistrationActivity.this;
    private static final String TAG = "CandidateRegisterActivi";

    private ScrollView mScrollView;

    private TextInputEditText mFirstNameTextInputEditText;
    private TextInputEditText mLastNameTextInputEditText;
    private TextInputEditText mDateOfBirthTextInputEditText;
    private TextInputEditText mMobileNumberTextInputEditText;
    private TextInputEditText mEmailTextInputEditText;
    private TextInputEditText mPasswordTextInputEditText;
    private TextInputEditText mConfirmPasswordTextInputEditText;

    private RadioGroup mGenderRadioGroup;
    private RadioButton radioButton;

    private Button mRegisterButton;
    private TextView mGoToLoginTextView;

    private DatePickerDialog mDatePickerDialog;

    private InputValidation mInputValidation;
    private DatabaseHelper mDatabaseHelper;
    private CandidateDataModel mCandidateDataModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_registration);
        initiateViews();
        initiateListeners();
        initiateObjects();


        mGenderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton) group.findViewById(checkedId);
//                mGender = radioButton.getText().toString().trim();
            }
        });
    }
    private void initiateViews() {


        mScrollView = (ScrollView) findViewById(R.id.registerscroll);
        mFirstNameTextInputEditText = (TextInputEditText) findViewById(R.id.textinputedittext_first_name);
        mLastNameTextInputEditText = (TextInputEditText) findViewById(R.id.textinputedittext_last_name);
        mDateOfBirthTextInputEditText = (TextInputEditText) findViewById(R.id.textinputedittext_dob);
        mMobileNumberTextInputEditText = (TextInputEditText) findViewById(R.id.textinputedittext_mobile_number);
        mEmailTextInputEditText = (TextInputEditText) findViewById(R.id.textinputedittext_email_id);
        mPasswordTextInputEditText = (TextInputEditText) findViewById(R.id.textinputedittext_password);
        mConfirmPasswordTextInputEditText = (TextInputEditText) findViewById(R.id.textinputedittext_confirm_password);

        mRegisterButton = (Button) findViewById(R.id.button_registration);
        mGoToLoginTextView = (TextView) findViewById(R.id.textview_login_link);

        mGenderRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_gender);


    }

    public void initiateListeners() {

        mDateOfBirthTextInputEditText.setOnTouchListener(this);
        mRegisterButton.setOnClickListener(this);
        mGoToLoginTextView.setOnClickListener(this);
    }

    public void initiateObjects() {

        mDatabaseHelper = new DatabaseHelper(activity);
        mInputValidation = new InputValidation(activity);
        mCandidateDataModel = new CandidateDataModel();

    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int DRAWABLE_RIGHT = 2;
        if(motionEvent.getAction()==MotionEvent.ACTION_UP){
            if(motionEvent.getRawX() >= (mDateOfBirthTextInputEditText.getRight() - mDateOfBirthTextInputEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())){
                Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mDatePickerDialog = new DatePickerDialog(CandidateRegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mDateOfBirthTextInputEditText.setText(day+"/"+(month+1)+"/"+year);
                    }
                },mYear,mMonth,mDay);
                mDatePickerDialog.show();
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_registration:
                postDataToSQLite();
                break;

            case R.id.textview_login_link:
                finish();
                break;
        }

    }

    private void postDataToSQLite() {
        if (!mInputValidation.isInputEditTextFilled(mFirstNameTextInputEditText, getString(R.string.error_message_first_name))) {
            return;
        }
        if (!mInputValidation.isInputEditTextFilled(mLastNameTextInputEditText, getString(R.string.error_message_last_name))) {
            return;
        }

        if (!mInputValidation.isInputEditTextFilled(mDateOfBirthTextInputEditText, getString(R.string.error_dob))) {
            return;
        }

        if (!mInputValidation.isInputEditTextFilled(mMobileNumberTextInputEditText, getString(R.string.error_mobile_number))) {
            return;
        }

        if (!mInputValidation.isInputEditTextFilled(mEmailTextInputEditText, getString(R.string.error_message_email))) {
            return;
        }
        if (!mInputValidation.isInputEditTextEmail(mEmailTextInputEditText, getString(R.string.error_message_email))) {
            return;
        }
        if (!mInputValidation.isInputEditTextFilled(mPasswordTextInputEditText, getString(R.string.error_message_password))) {
            return;
        }
        if (!mInputValidation.isInputEditTextMatches(mPasswordTextInputEditText, mConfirmPasswordTextInputEditText, getString(R.string.error_password_match))) {
            return;
        }
        if (!mInputValidation.isRadioButtonChecked(mGenderRadioGroup,getApplicationContext())){
            return;
        }

        if (!mDatabaseHelper.checkUser(mEmailTextInputEditText.getText().toString().trim())) {

            Intent mNextIntent = new Intent(activity,CandidateEducationalDetailsActivity.class);
            Bundle b = new Bundle();
            b.putString("FName",mFirstNameTextInputEditText.getText().toString().trim());
            b.putString("LName",mLastNameTextInputEditText.getText().toString().trim());
            b.putString("DOB",mDateOfBirthTextInputEditText.getText().toString().trim());
            b.putString("MobileNo",mMobileNumberTextInputEditText.getText().toString().trim());
            b.putString("Email",mEmailTextInputEditText.getText().toString().trim());
            b.putString("Password",mPasswordTextInputEditText.getText().toString().trim());
            b.putString("Gender",radioButton.getText().toString().trim());
            mNextIntent.putExtras(b);
            startActivity(mNextIntent);
            emptyInputEditText();

        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(mScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }


    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        mFirstNameTextInputEditText.setText(null);
        mLastNameTextInputEditText.setText(null);
        mDateOfBirthTextInputEditText.setText(null);
        mMobileNumberTextInputEditText.setText(null);
        mPasswordTextInputEditText.setText(null);
        mConfirmPasswordTextInputEditText.setText(null);
    }

}
