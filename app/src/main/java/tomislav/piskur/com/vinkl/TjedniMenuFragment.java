package tomislav.piskur.com.vinkl;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class TjedniMenuFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Menu> listItems;
    private Button btnHome;
    private static final String URL_DATA = "http://vinkl.somee.com/tjednimenu";


    public TjedniMenuFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tjedni_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initWidgetsSetupListeners(view);

        listItems = new ArrayList<>();

        loadRecycleViewData();
    }

    private void initWidgetsSetupListeners(View view) {

        recyclerView = view.findViewById(R.id.rcRecyclerView);
        btnHome = view.findViewById(R.id.btnHome);
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


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("tjedniMenu");
                    Menu item;
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject o = array.getJSONObject(i);
                        if (i < 4) {
                            item = new Menu(o.getString("Jelo"),
                                    o.getString("Cijena"), "Ponedjeljak");
                            listItems.add(item);

                        } else if (i < 8) {

                            item = new Menu(o.getString("Jelo"),
                                    o.getString("Cijena"), "Utorak");
                            listItems.add(item);

                        } else if (i < 12) {
                            item = new Menu(o.getString("Jelo"),
                                    o.getString("Cijena"), "Srijeda");
                            listItems.add(item);

                        } else if (i < 16) {

                            item = new Menu(o.getString("Jelo"),
                                    o.getString("Cijena"), "Četvrtak");
                            listItems.add(item);
                        } else {
                            item = new Menu(o.getString("Jelo"),
                                    o.getString("Cijena"), "Petak");
                            listItems.add(item);
                        }
                    }
                    adapter = new Adapter(listItems, getContext());

                    recyclerView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Došlo je do pogreške. Molimo pokušajte kasnije.", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }
}

