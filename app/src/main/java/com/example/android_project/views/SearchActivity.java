package com.example.android_project.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android_project.R;

public class SearchActivity extends AppCompatActivity {

    private Spinner spinnerMarque;
    private EditText etRegion, etVille;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        spinnerMarque = findViewById(R.id.spinner_marque);
        etRegion = findViewById(R.id.search_criteria_region);
        etVille = findViewById(R.id.search_criteria_ville);
        searchButton = findViewById(R.id.search_button);

        // Configurez votre spinner ici comme dans le message précédent

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marque = spinnerMarque.getSelectedItem().toString();
                String region = etRegion.getText().toString();
                String ville = etVille.getText().toString();

                // Construction de la chaîne de requête
                StringBuilder whereClause = new StringBuilder();
                if (!marque.isEmpty() && !marque.equals("Tous")) {
                    whereClause.append("brand='").append(marque).append("'");
                }
                if (!region.isEmpty()) {
                    if (whereClause.length() > 0) whereClause.append(" and ");
                    whereClause.append("reg_code='").append(region).append("'");
                }
                if (!ville.isEmpty()) {
                    if (whereClause.length() > 0) whereClause.append(" and ");
                    whereClause.append("com_arm_name='").append(ville).append("'");
                }

                // Lancement de StationsListe avec les critères de recherche
                Intent intent = new Intent(SearchActivity.this, StationsListActivity.class);
                intent.putExtra("whereClause", whereClause.toString());
                startActivity(intent);
            }
        });
    }
}