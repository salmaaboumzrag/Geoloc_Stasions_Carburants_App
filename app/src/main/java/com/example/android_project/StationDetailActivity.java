package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android_project.modle.StationCarburant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class StationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);
        StationCarburant station = getIntent().getParcelableExtra("SELECTED_STATION");

        if (station != null) {
            // Retrieve the TextViews
            TextView tvStationName = findViewById(R.id.textView_station);
            TextView tvGazolePrice = findViewById(R.id.textView_gazole);
            TextView tvAddress = findViewById(R.id.adresse);
            TextView tvMarque = findViewById(R.id.marque);
            TextView tvPriceE10 = findViewById(R.id.PriceE10);
            TextView tvPriceE85 = findViewById(R.id.PriceE85);
            TextView tvPriceGplc = findViewById(R.id.PriceGplc);
            TextView tvPriceSp98 = findViewById(R.id.PriceSp98);
            TextView tvCodePostal = findViewById(R.id.cp);
            TextView tvTimeLab = findViewById(R.id.timeLab);

            // Set the text for each TextView
            setTextViewTextWithDefault(tvStationName, station.getNom());
            setTextViewTextWithDefault(tvGazolePrice, station.getPriceGazole() != null ? station.getPriceGazole().toString() + " €" : null);
            setTextViewTextWithDefault(tvAddress, station.getAdresse());
            setTextViewTextWithDefault(tvMarque, station.getMarque());
            setTextViewTextWithDefault(tvPriceE10, station.getPriceE10() != null ? station.getPriceE10().toString() + " €" : null);
            setTextViewTextWithDefault(tvPriceE85, station.getPriceE85() != null ? station.getPriceE85().toString() + " €" : null);
            setTextViewTextWithDefault(tvPriceGplc, station.getPriceGplc() != null ? station.getPriceGplc().toString() + " €" : null);
            setTextViewTextWithDefault(tvPriceSp98, station.getPriceSp98() != null ? station.getPriceSp98().toString() + " €" : null);
            setTextViewTextWithDefault(tvCodePostal, station.getCodePostal());


            String timetable = station.getTimetable();
            if (timetable != null) {
                try {
                    JSONObject timetableJson = new JSONObject(timetable);
                    StringBuilder timetableBuilder = new StringBuilder();

                    // Iterate over each day of the week
                    Iterator<String> days = timetableJson.keys();
                    while (days.hasNext()) {
                        String day = days.next();
                        JSONObject daySchedule = timetableJson.getJSONObject(day);
                        String openTime = daySchedule.optString("ouverture", "Fermé");
                        String closeTime = daySchedule.optString("fermeture", "Fermé");
                        boolean isOpen = daySchedule.optInt("ouvert", 0) == 1;

                        timetableBuilder.append(day)
                                .append(" \t : \t")
                                .append(isOpen ? openTime + " - " + closeTime : "Fermé")
                                .append("\n");
                    }
                    tvTimeLab.setText(timetableBuilder.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                    // Handle the error properly
                }
            } else {
                // Gérez le cas où `timetable` est null
                tvTimeLab.setTextColor(Color.RED);
                tvTimeLab.setText("Horaires non disponibles");
            }
        }
    }
    private void setTextViewTextWithDefault(TextView textView, String text) {
        if (text != null && !text.isEmpty()) {
            textView.setText(text);
            textView.setTextColor(Color.BLACK); // Ou une autre couleur par défaut
        } else {
            textView.setText("Non renseigné");
            textView.setTextColor(Color.RED); // Définir la couleur en rouge
        }
    }
}