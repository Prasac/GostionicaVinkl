package tomislav.piskur.com.vinkl;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Kontakt extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static final LatLng VINKL = new LatLng(45.810897, 15.93811);
    private static final Uri URI = Uri.parse("geo:0,0?q=45.810897,15.93811(Gostionica+Vinkl)");
    private Marker mVinkl;
    private TextView tvKontakt;
    private Button btnFiksni, btnMobitel, btnEmail, btnHome;
    private static final String TEL_BROJ = "tel:+38518895094";
    private static final String MOBITEL = "tel:+385992323243";
    private static final String E_MAIL = "gostionica.vinkl@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontakt);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        initWidgets();
        setupListeners();


    }

    private void setupListeners() {

        btnFiksni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(TEL_BROJ));
                startActivity(intent);
            }
        });
        btnMobitel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(MOBITEL));
                startActivity(intent);
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{E_MAIL});
                startActivity(Intent.createChooser(intent, getString(R.string.odabirEmailAplikacije)));
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initWidgets() {

        btnFiksni = findViewById(R.id.btnFiksni);
        btnMobitel = findViewById(R.id.btnMobitel);
        btnEmail = findViewById(R.id.btnEmail);
        btnHome = findViewById(R.id.btnHome);
        tvKontakt = findViewById(R.id.tvRazmak);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/font_marelene_pro_italic.otf");
        tvKontakt.setTypeface(typeface);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        if (metrics.densityDpi == DisplayMetrics.DENSITY_LOW) {
            tvKontakt.setPadding(25, 0, 25, 0);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mVinkl = mMap.addMarker(new MarkerOptions()
                .position(VINKL)

                .title(getString(R.string.Adresa)));
        mVinkl.setTag(0);
        mVinkl.showInfoWindow();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(VINKL)
                .zoom(16).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.setOnMarkerClickListener(this);
        onInfoClickListener(mVinkl);

    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        if (marker.equals(mVinkl)) {

            Uri gmmIntentUri = URI;
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }

        return false;
    }

    public void onInfoClickListener(Marker marker) {
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.equals(mVinkl)) {
                    Uri gmmIntentUri = URI;
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }
                }

            }
        });

    }
}
