package avc.com.olamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SorveteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorvete);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
