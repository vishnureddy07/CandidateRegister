package squareandcube.com.candidateregister;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class CandidateLoginActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = CandidateLoginActivity.this;

    private ScrollView mScrollView;

    private TextInputEditText mEmailTextInputEditText;
    private TextInputEditText mPasswordTextInputEditText;

    private Button mLoginButton;
    private Button mRegisterLinkTextView;

    private TextView mForgotLinkTextView;

    String email;


    private InputValidation mInputValidation;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_login);
        initiateViews();
        initiateListeners();
        initiateObjects();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                verifyFromSQLite();
                break;
            case R.id.button_registration:
                Intent intentRegister = new Intent(getApplicationContext(), CandidateRegistrationActivity.class);
                startActivity(intentRegister);
                break;

            case R.id.forgot_link_textvie:
                Intent forgotIntent = new Intent(getApplicationContext(),ForgotActivity.class);
                startActivity(forgotIntent);
                break;

        }
    }

    private void initiateViews() {


        mScrollView = (ScrollView)findViewById(R.id.scrollView);

        mEmailTextInputEditText=(TextInputEditText)findViewById(R.id.textinputedittext_email);
        mPasswordTextInputEditText=(TextInputEditText)findViewById(R.id.textinputedittext_password);

        mLoginButton = (Button)findViewById(R.id.button_login) ;
        mRegisterLinkTextView = (Button) findViewById(R.id.button_registration);

        mForgotLinkTextView = (TextView) findViewById(R.id.forgot_link_textvie);


    }

    public void initiateListeners(){

        mLoginButton.setOnClickListener(this);
        mRegisterLinkTextView.setOnClickListener(this);
        mForgotLinkTextView.setOnClickListener(this);

    }

    public void initiateObjects(){

        mDatabaseHelper = new DatabaseHelper(activity);
        mInputValidation = new InputValidation(activity);

    }

    private void verifyFromSQLite() {
        if (!mInputValidation.isInputEditTextFilled(mEmailTextInputEditText, getString(R.string.error_message_email))) {
            return;
        }
        if (!mInputValidation.isInputEditTextEmail(mEmailTextInputEditText, getString(R.string.error_message_email))) {
            return;
        }
        if (!mInputValidation.isInputEditTextFilled(mPasswordTextInputEditText, getString(R.string.error_message_password))) {
            return;
        }

        if (mDatabaseHelper.checkUser(mEmailTextInputEditText.getText().toString().trim()
                , mPasswordTextInputEditText.getText().toString().trim())) {
            email = mEmailTextInputEditText.getText().toString().trim();
            String avg,yeargap;
            SQLiteDatabase db =mDatabaseHelper.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM candidate_personal_details,candidate_educational_details WHERE candidate_personal_details.candidate_email_id= '"+email+"' AND candidate_personal_details.candidate_email_id=candidate_educational_details.candidate_email_id",null);
            c.moveToFirst();
            avg = c.getString(25);
            yeargap = c.getString(24);
            double avgs = Double.parseDouble(avg);
            int gapYear = Integer.parseInt(yeargap);
            if(avgs>60 && gapYear<=2) {
                Intent accountsIntent = new Intent(activity, B1AndB3Activity.class);
                Bundle b = new Bundle();
                b.putString("Email",mEmailTextInputEditText.getText().toString().trim());
                accountsIntent.putExtras(b);
                emptyInputEditText();
                startActivity(accountsIntent);
            }
            else{
                Intent accountsIntent = new Intent(activity, B2AndB3Activity.class);
                Bundle b = new Bundle();
                b.putString("Email",mEmailTextInputEditText.getText().toString().trim());
                accountsIntent.putExtras(b);
                emptyInputEditText();
                startActivity(accountsIntent);
            }

        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.error_valid_email_password),Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyInputEditText() {
        mEmailTextInputEditText.setText(null);
        mPasswordTextInputEditText.setText(null);
    }
}
