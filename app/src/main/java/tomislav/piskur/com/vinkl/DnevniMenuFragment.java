package tomislav.piskur.com.vinkl;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tomislav.piskur.com.vinkl.modelAdapter.Adapter;
import tomislav.piskur.com.vinkl.modelAdapter.Menu;


public class DnevniMenuFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Menu> listItems;
    private TextView tvDnevniMenu;
    private Button btnHome;
    private ProgressBar spinner;
    private static final String URL_DATA = "http://vinkl.somee.com/tjednimenu";

    public DnevniMenuFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_dnevni_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWidgetsSetOnClickListener(view);

        listItems = new ArrayList<>();
        spinner.setVisibility(View.VISIBLE);
        loadRecycleViewData();

    }

    private void initWidgetsSetOnClickListener(View view) {


        tvDnevniMenu = view.findViewById(R.id.tvDnevniMenu);
        Typeface typeface = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/font_marelene_pro_italic.otf");
        tvDnevniMenu.setTypeface(typeface);
        btnHome = view.findViewById(R.id.btnHome);
        spinner = view.findViewById(R.id.progressBar1);
        recyclerView = view.findViewById(R.id.rcRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void loadRecycleViewData() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                btnHome.setVisibility(View.VISIBLE);
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE d.MM", java.util.Locale.getDefault());
                Date d = new Date();
                String dayOfTheWeek = sdf.format(d);
                dayOfTheWeek = dayOfTheWeek.substring(0, 1).toUpperCase() + dayOfTheWeek.substring(1);
                tvDnevniMenu.setText(dayOfTheWeek);
                sdf = new SimpleDateFormat("EEEE", java.util.Locale.UK);
                dayOfTheWeek = sdf.format(d);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("tjedniMenu");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject o = array.getJSONObject(i);

                        Menu item = new Menu(
                                o.getString("Jelo"), o.getString("Cijena"), "");
                        if (o.getString("Dan").equals(dayOfTheWeek)){
                            listItems.add(item);
                        }






                    }

                    adapter = new Adapter(listItems, getContext());
                    if (adapter.getItemCount() != 0) {
                        recyclerView.setAdapter(adapter);
                        spinner.setVisibility(View.GONE);
                    } else {

                        Toast.makeText(getContext(), "Poštovani, danas nema dnevnih jela.", Toast.LENGTH_LONG).show();
                        spinner.setVisibility(View.GONE);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                    spinner.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Došlo je do pogreške. Molimo pokušajte kasnije.", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Došlo je do pogreške. Provjerite mrežne postavke i pokušajte ponovno.", Toast.LENGTH_SHORT).show();
                spinner.setVisibility(View.GONE);

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}
