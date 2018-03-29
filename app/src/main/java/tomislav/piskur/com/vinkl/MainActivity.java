package tomislav.piskur.com.vinkl;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnTjednaPonuda, btnAlaCarte, btnKontakt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initWidgets();
        setupListeners();

    }

    private void initWidgets() {
        btnTjednaPonuda = findViewById(R.id.btnTjednaPonuda);
        btnAlaCarte = findViewById(R.id.btnAlaCarte);
        btnKontakt = findViewById(R.id.btnKontakt);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/font_marelene_pro_italic.otf");
        btnTjednaPonuda.setTypeface(typeface);
        btnAlaCarte.setTypeface(typeface);
        btnKontakt.setTypeface(typeface);
    }

    private void setupListeners() {

        btnTjednaPonuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, TjednaPonuda.class);
                startActivity(i);

            }
        });
        btnAlaCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, AlaCarte.class);
                startActivity(i);

            }
        });
        btnKontakt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, Kontakt.class);
                startActivity(i);

            }
        });

    }


}
