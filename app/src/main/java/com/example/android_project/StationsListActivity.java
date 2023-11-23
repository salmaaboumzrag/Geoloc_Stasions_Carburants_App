package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android_project.modle.ApiResponse;
import com.example.android_project.modle.RetrofitClientInstance;
import com.example.android_project.modle.StationCarburant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StationsListActivity extends AppCompatActivity {

    private ListView listView;
    private StationCarburantAdapter adapter;
    private List<StationCarburant> stationList = new ArrayList<>();
    private int currentOffset = 0;
    private final int limit = 50;
    private boolean isLoading = false;
    private String additionalWhereClause = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations_list);

        listView = findViewById(R.id.listView);

        // Récupérez les critères de recherche de l'Intent
        if (getIntent().hasExtra("whereClause")) {
            additionalWhereClause = getIntent().getStringExtra("whereClause");
        }

        // Initialize my custom adapter
        adapter = new StationCarburantAdapter(this, stationList);
        listView.setAdapter(adapter);

        // Set the itemClick listener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StationCarburant selectedStation = (StationCarburant) adapter.getItem(position);

                Intent detailIntent = new Intent(StationsListActivity.this, StationDetailActivity.class);
                detailIntent.putExtra("SELECTED_STATION", selectedStation);

                startActivity(detailIntent);
            }
        });
        // Set the itemLongClick listener for the ListView
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                StationCarburant selectedStation = (StationCarburant) adapter.getItem(position);

                Intent mapIntent = new Intent(StationsListActivity.this, MapActivity.class);
                mapIntent.putExtra("SELECTED_STATION", selectedStation);
                startActivity(mapIntent);
                return true;
            }
        });
        // Set the scroll listener for the ListView
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (!isLoading && (firstVisibleItem + visibleItemCount >= totalItemCount) && totalItemCount != 0) {
                    // Load more data here
                    isLoading = true;
                    currentOffset += limit; // Increase offset
                    fetchStations(currentOffset);
                }
            }
        });

        Button showOnMapButton = findViewById(R.id.b_map);
        showOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<StationCarburant> allStationsList = stationList;

                Intent mapIntent = new Intent(StationsListActivity.this, MapActivity.class);
                mapIntent.putParcelableArrayListExtra("ALL_STATIONS", new ArrayList<>(allStationsList));
                startActivity(mapIntent);
            }
        });

        // Initial data fetch
        fetchStations(currentOffset);
    }


    private void fetchStations(int offset) {
        isLoading = true; // Set isLoading to true when starting to fetch data

        String whereClause = "name is not null and price_gazole is not null and pop!=\"A\"";
        if (!additionalWhereClause.isEmpty()) {
            whereClause += " and " + additionalWhereClause;
        }

        OpenDataSoftApi service = RetrofitClientInstance.getApiService();
        Call<ApiResponse> call = service.getStations(
                whereClause,
                "nom,brand,adresse,com_arm_name,price_gazole,price_sp95,price_sp98,price_gplc,price_e10,price_e85,geo_point",
                "update",
                limit,
                offset
        );

        Log.d("MainActivity", "URL: " + call.request().url().toString());

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getResults() != null) {
                    stationList.clear();
                    stationList.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0); // Scroll to the top of the list
                    Toast.makeText(StationsListActivity.this, "Liste mise à jour", Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("MainActivity", "Response Code: " + response.code());
                    try {
                        String errorBody = response.errorBody() != null ? response.errorBody().string() : "null";
                        Log.e("MainActivity", "Error Body: " + errorBody);
                    } catch (IOException e) {
                        Log.e("MainActivity", "Error reading error body", e);
                    }
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle failure - e.g., show a toast message
                Toast.makeText(StationsListActivity.this, "Failed to fetch stations", Toast.LENGTH_SHORT).show();
                isLoading = false;
            }
        });
    }
}