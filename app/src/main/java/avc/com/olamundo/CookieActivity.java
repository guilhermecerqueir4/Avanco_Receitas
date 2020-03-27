package avc.com.olamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CookieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
