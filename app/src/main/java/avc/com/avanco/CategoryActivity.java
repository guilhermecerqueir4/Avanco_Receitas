package avc.com.avanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {
    GridView gridView ;
    String[] numberWord = {"Placas-Mãe", "Placas de Vídeo", "Processadores","Discos (HD)"};
    int[] numberImage = {R.drawable.categorymotherboard, R.drawable.categoryvideocard, R.drawable.categoryprocessor, R.drawable.categoryhd};

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        gridView = findViewById(R.id.grid_view);
         CategoryAdapter adapter = new CategoryAdapter(CategoryActivity.this, numberWord, numberImage);
         gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (numberWord[+position].equals("Placas-Mãe")) {
                    Intent intent = new Intent(CategoryActivity.this, PlacaMaeActivity.class);
                    startActivity(intent);
                }
                if (numberWord[+position].equals("Placas de Vídeo")) {
                    Intent intent = new Intent(CategoryActivity.this, PlacaVideoActivity.class);
                    startActivity(intent);
                }
                if (numberWord[+position].equals("Processadores")) {
                    Intent intent = new Intent(CategoryActivity.this, ProcessadorActivity.class);
                    startActivity(intent);
                }
                if (numberWord[+position].equals("Discos (HD)")) {
                    Intent intent = new Intent(CategoryActivity.this, DiscoRigidoActivity.class);
                    startActivity(intent);
                }

            }


        });

    }
}
