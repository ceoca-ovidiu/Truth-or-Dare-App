package com.example.truthordare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EnterPlayersActivity extends AppCompatActivity {

    private LinearLayout secondLinearLayout;
    private ArrayList<EditText> editTextList = new ArrayList<>();
    private ArrayList<String> namesList = new ArrayList<>();
    private static final String TAG = "EnterPlayersActivity";
    private static final String TEXT_CONTENTS = "";
    private static final String PLAYERS_NUMBER = "";
    private int playersNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button doneButton = findViewById(R.id.enterPlayersDoneButton);
        secondLinearLayout = findViewById(R.id.secondLinearLayout);
        playersNumber = getIntent().getIntExtra("PLAYERS_NUMBER", 0);
        loadEditTexts(playersNumber, secondLinearLayout, editTextList);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openLevelsActivity();
            }
        });
        Log.d(TAG, "onCreate: out");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadEditTexts(playersNumber, secondLinearLayout, editTextList);
    }

    private void loadEditTexts (int numberOfPlayers, LinearLayout layout, ArrayList<EditText> editTextArrayList){
        for(int i = 1; i <= numberOfPlayers; i++){
            EditText editText = new EditText(EnterPlayersActivity.this);
            editText.setHint("Player " + i);
            editText.setBackgroundColor(Color.parseColor("#22566b"));
            editText.setTextColor(Color.parseColor("#ffffff"));
            layout.addView(editText);
            editTextArrayList.add(editText);
        }
    }

    private void openLevelsActivity(){
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        namesList.clear();
        if(verifyEmptiness()){
            closeKeyboard();
            Toast.makeText(EnterPlayersActivity.this, "EMPTY FIELDS", Toast.LENGTH_LONG).show();
            long[] pattern = {0, 50, 100, 50};
            vibrator.vibrate(pattern, -1);
        }else{
            for(int i = 0 ; i < secondLinearLayout.getChildCount(); i++){
                View tempView = secondLinearLayout.getChildAt(i);
                if(tempView instanceof EditText){
                    if(verifyDuplicates(((EditText) tempView).getText().toString(), namesList)){
                        closeKeyboard();
                        Toast.makeText(EnterPlayersActivity.this, "DUPLICATED FIELDS", Toast.LENGTH_LONG).show();
                        long[] pattern = {0, 50, 100, 50};
                        vibrator.vibrate(pattern, -1);
                        return;
                    }else{
                        namesList.add(((EditText) tempView).getText().toString());
                    }
                }
            }
            vibrator.vibrate(50);
            Intent intent = new Intent(this, LevelsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);                               ///TODO: aici am schimbat
            intent.putStringArrayListExtra("NAMES_LIST", namesList);
            startActivity(intent);
        }
    }

    private void closeKeyboard (){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean verifyEmptiness(){
        for(int i = 0 ; i < secondLinearLayout.getChildCount(); i++){
            View tempView = secondLinearLayout.getChildAt(i);
            if(tempView instanceof EditText){
                if(TextUtils.isEmpty(((EditText) tempView).getText().toString())){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verifyDuplicates(String inputString, @NonNull ArrayList<String> stringArrayList){

        if(stringArrayList.isEmpty()){
            return false;
        }else{
            for(String stringIterator : stringArrayList){
                if(inputString.equalsIgnoreCase(stringIterator)){
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: in");
        for(int i = 0 ; i < secondLinearLayout.getChildCount(); i++){
            View tempView = secondLinearLayout.getChildAt(i);
            if(tempView instanceof EditText){
                namesList.add(((EditText) tempView).getText().toString());                           //TODO: de modificat aici ca am schimbat si nu merge bine
            }
        }
        outState.putInt(PLAYERS_NUMBER, secondLinearLayout.getChildCount());
        outState.putStringArrayList(TEXT_CONTENTS, namesList);
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: out");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState: in");
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<String> savedStringList = savedInstanceState.getStringArrayList(TEXT_CONTENTS);
        playersNumber = savedInstanceState.getInt(PLAYERS_NUMBER);
        for(int i = 0 ; i < playersNumber; i++){
            EditText editText = new EditText(EnterPlayersActivity.this);
            editText.setBackgroundColor(Color.parseColor("#22566b"));                        //TODO: de modificat aici ca am schimbat si nu merge bine
            editText.setTextColor(Color.parseColor("#ffffff"));
            secondLinearLayout.addView(editText);
            editTextList.add(editText);
        }
        if(savedStringList != null){
            for(int i = 0 ; i < secondLinearLayout.getChildCount() ; i++ ){
                View tempView = secondLinearLayout.getChildAt(i);
                if(tempView instanceof EditText){
                    ((EditText) tempView).setText(savedStringList.get(i));
                }
            }
        }
        Log.d(TAG, "onRestoreInstanceState: out");
    }
}
