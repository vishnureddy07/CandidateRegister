package squareandcube.com.candidateregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class B1AndB3Activity extends AppCompatActivity {

    //Edit the data from git
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1_and_b3);
        email=getIntent().getStringExtra("Email");
        Log.d("data", "onCreate: "+email);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_candidateData:
                Intent mEditIntent = new Intent(B1AndB3Activity.this,CandidateUsersListUpdateActivity.class);
                mEditIntent.putExtra("EMAIL", email);
                startActivity(mEditIntent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
