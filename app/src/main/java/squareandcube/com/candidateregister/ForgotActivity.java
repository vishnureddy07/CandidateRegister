package squareandcube.com.candidateregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBatch1Button;
    private Button mBatch2Button;
    private Button mBatch3Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        mBatch1Button = (Button) findViewById(R.id.batch1_button);
        mBatch2Button = (Button) findViewById(R.id.batch2_button);
        mBatch3Button = (Button) findViewById(R.id.batch3_button);

        mBatch1Button.setOnClickListener(this);
        mBatch2Button.setOnClickListener(this);
        mBatch3Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.batch1_button:

                break;
            case R.id.batch2_button:

                break;
            case R.id.batch3_button:
                Intent mB3Intent = new Intent(getApplicationContext(),B3Activity.class);
                startActivity(mB3Intent);
                break;
        }
    }
}
