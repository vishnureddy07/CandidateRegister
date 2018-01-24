package squareandcube.com.candidateregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class B2AndB3Activity extends AppCompatActivity {

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2_and_b3);
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
                Intent mEditIntent = new Intent(B2AndB3Activity.this,CandidateUsersListUpdateActivity.class);
                mEditIntent.putExtra("EMAIL", email);
                startActivity(mEditIntent);
                startActivity(mEditIntent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
