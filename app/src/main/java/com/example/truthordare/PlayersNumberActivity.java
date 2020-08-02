package com.example.truthordare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PlayersNumberActivity extends AppCompatActivity {

    private EditText playersNumberEditText;
    private static final String TAG = "PlayersNumberActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_number);
        Button doneButton = findViewById(R.id.enterPlayersDoneButton);
        playersNumberEditText = findViewById(R.id.playersNumberEditText);
        playersNumberEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passPlayersNumber();
            }
        });
        Log.d(TAG, "onCreate: out");
    }



    private void savePlayersNumber(int numberOfPlayers){
        SharedPreferences.Editor editor = getSharedPreferences("PREF_NUMBER_OF_PLAYERS", MODE_PRIVATE).edit();
        editor.putInt("NUMBER_OF_PLAYERS", numberOfPlayers);
        editor.apply();
    }

    public void passPlayersNumber(){
        Log.d(TAG, "passPlayersNumber: in");
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        closeKeyboard();
        try {
            int playersNumber = Integer.parseInt(playersNumberEditText.getText().toString());
            if(playersNumber > 15 || playersNumber <= 1){
                Toast.makeText(PlayersNumberActivity.this, "ENTER A VALID NUMBER", Toast.LENGTH_LONG).show();
                long[] pattern = {0, 50, 100, 50};
                vibrator.vibrate(pattern, -1);
            }else{
                vibrator.vibrate(50);
                Intent intent = new Intent(PlayersNumberActivity.this, EnterPlayersActivity.class);
                intent.putExtra("PLAYERS_NUMBER", playersNumber);
                savePlayersNumber(playersNumber);
                startActivity(intent);
            }
        }catch (Exception e){
            Toast.makeText(PlayersNumberActivity.this, "ENTER A VALID NUMBER", Toast.LENGTH_LONG).show();
            long[] pattern = {0, 50, 100, 50};
            vibrator.vibrate(pattern, -1);
        }
        Log.d(TAG, "passPlayersNumber: out");
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}