package com.example.truthordare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LevelsActivity extends AppCompatActivity {

    private ArrayList<String> namesList = new ArrayList<>();
    private static final String TAG = "LevelsActivity";
    private int playersNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        Button lowButton = findViewById(R.id.lowButton);
        Button mediumButton = findViewById(R.id.mediumButton);
        Button hardButton = findViewById(R.id.hardButton);
        namesList = getIntent().getStringArrayListExtra("NAMES_LIST");
        playersNumber = getIntent().getIntExtra("PLAYERS_NUMBER_FORWARD", 0);
        lowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLowActivity();
            }
        });
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMediumActivity();
            }
        });
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHardActivity();
            }
        });
        Log.d(TAG, "onCreate: out");
    }


    public void openLowActivity() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(50);
        Intent intent = new Intent(this, LowActivity.class);
        intent.putStringArrayListExtra("LOW_NAMES_LIST", namesList);
        startActivity(intent);
    }

    public void openMediumActivity() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(50);
        Intent intent = new Intent(this, MediumActivity.class);
        intent.putStringArrayListExtra("MEDIUM_NAMES_LIST", namesList);
        startActivity(intent);
    }

    public void openHardActivity() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(50);
        Intent intent = new Intent(this, HardActivity.class);
        intent.putStringArrayListExtra("HARD_NAMES_LIST", namesList);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, EnterPlayersActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("PLAYERS_NUMBER_BACK", playersNumber); //FIXME : players number e 2
        startActivity(intent);
    }
}
