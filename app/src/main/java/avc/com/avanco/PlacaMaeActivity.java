package avc.com.avanco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import avc.com.avanco.R;

public class PlacaMaeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_placamae);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
