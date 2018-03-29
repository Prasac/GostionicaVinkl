package tomislav.piskur.com.vinkl;


import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


import tomislav.piskur.com.vinkl.modelAdapter.Adapter;
import tomislav.piskur.com.vinkl.modelAdapter.Menu;

public class AlaCarte extends AppCompatActivity {


    private Button btnHome;
    private TextView tvaLaCarte;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Menu> listItems;
    private ProgressBar spinner;
    private static final String URL_DATA = "http://vinkl.somee.com/menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ala_carte);
        getSupportActionBar().hide();

        initWidgets();
        setupListener();
        spinner.setVisibility(View.VISIBLE);

        listItems = new ArrayList<>();

        loadRecycleViewData();

    }

    private void setupListener() {

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initWidgets() {
        recyclerView = findViewById(R.id.rcRecyclerView);
        spinner = findViewById(R.id.progressBar1);
        tvaLaCarte = findViewById(R.id.tvAlacarte);
        btnHome = findViewById(R.id.btnHome);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/font_marelene_pro_italic.otf");
        tvaLaCarte.setTypeface(typeface);

    }

    private void loadRecycleViewData() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("menu");
                    Menu item;
                    Menu item2;
                    String oGrupa = "";
                    spinner.setVisibility(View.GONE);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);


                        if (o.getString("grupa").equals(oGrupa)) {
                            item = new Menu(o.getString("artikl"),
                                    o.getString("cijena"));

                            listItems.add(item);


                        } else {

                            item = new Menu(o.getString("grupa"), "-------", "-------");
                            item2 = new Menu(o.getString("artikl"), o.getString("cijena"));
                            listItems.add(item);
                            listItems.add(item2);

                        }
                        oGrupa = o.getString("grupa");


                    }

                    adapter = new Adapter(listItems, getApplicationContext());


                    recyclerView.setAdapter(adapter);
                    spinner.setVisibility(View.GONE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Došlo je do pogreške. Molimo pokušajte kasnije.", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AlaCarte.this, "Došlo je do pogreške. Provjerite mrežne postavke i pokušajte ponovno.", Toast.LENGTH_SHORT).show();
                spinner.setVisibility(View.GONE);
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public static String odaslanUpit() {

        return "1";
    }
}

