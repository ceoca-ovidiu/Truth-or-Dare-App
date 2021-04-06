package com.example.truthordare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button doneButton = findViewById(R.id.enterPlayersDoneButton);
        secondLinearLayout = findViewById(R.id.secondLinearLayout);
        retrivePlayersNumber();
        if(getIntent().getBooleanExtra("VALIDATION",false)){
            namesList = retriveNamesList();
            loadCompletedEditTexts(playersNumber, secondLinearLayout, editTextList, namesList);
        }else{
            loadEditTexts(playersNumber, secondLinearLayout, editTextList);
        }
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLevelsActivity();
            }
        });
        Log.i(TAG, "onCreate: out");
    }

    Drawable textViewGreenBackground = getDrawable(R.drawable.green_blue_gradient_rounded_text_view);

    private void loadCompletedEditTexts(int numberOfPlayers, LinearLayout layout, ArrayList<EditText> editTextArrayList, ArrayList<String> stringArrayList) { // number of players = 2  // editTextArrayList = 0

        for (int i = 1; i <= numberOfPlayers; i++) {
            EditText editText = new EditText(EnterPlayersActivity.this);
            editText.setBackground(textViewGreenBackground);
            editText.setTextColor(Color.parseColor(String.valueOf(R.color.blueSaphire)));
            editText.setText(stringArrayList.get(i));
            layout.addView(editText);
            editTextArrayList.add(editText);
        }
    }

    private void retrivePlayersNumber()  {
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_NUMBER_OF_PLAYERS", MODE_PRIVATE);
        playersNumber = sharedPreferences.getInt("NUMBER_OF_PLAYERS", 0);
    }

    private void saveNamesList(ArrayList<String> stringArrayList){
        Set<String> namesSet = new HashSet<>();
        for(String string : stringArrayList){
            namesSet.add(string);
        }
        SharedPreferences.Editor editor = getSharedPreferences("PREF_NAMES_SET", MODE_PRIVATE).edit();
        editor.putStringSet("NAMES_SET", namesSet);
        editor.apply();
    }

    private ArrayList<String> retriveNamesList(){
        SharedPreferences sharedPreferences = getSharedPreferences("PREF_NAMES_SET",MODE_PRIVATE);
        Set<String> stringSet = sharedPreferences.getStringSet("NAMES_SET",null);
        if(stringSet == null){
            Toast.makeText(this, "STRING SET IS NULL", Toast.LENGTH_LONG);
            return null;
        }else{
            Toast.makeText(this, "STRING SET NOT NULL", Toast.LENGTH_LONG);
            for(String string : stringSet){
                namesList.add(string);
            }
            return namesList;
        }
    }

    private void loadEditTexts(int numberOfPlayers, LinearLayout layout, ArrayList<EditText> editTextArrayList) {
        for (int i = 1; i <= numberOfPlayers; i++) {
            EditText editText = new EditText(EnterPlayersActivity.this);
            editText.setHint("Player " + i);
            editText.setBackgroundColor(Color.parseColor("#22566b"));
            editText.setTextColor(Color.parseColor("#ffffff"));
            layout.addView(editText);
            editTextArrayList.add(editText);
        }
    }

    private void openLevelsActivity() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        namesList.clear();
        if (verifyEmptiness()) {
            closeKeyboard();
            Toast.makeText(EnterPlayersActivity.this, "EMPTY FIELDS", Toast.LENGTH_LONG).show();
            long[] pattern = {0, 50, 100, 50};
            vibrator.vibrate(pattern, -1);
        } else {
            for (int i = 0; i < secondLinearLayout.getChildCount(); i++) {
                View tempView = secondLinearLayout.getChildAt(i);
                if (tempView instanceof EditText) {
                    if (verifyDuplicates(((EditText) tempView).getText().toString(), namesList)) {
                        closeKeyboard();
                        Toast.makeText(EnterPlayersActivity.this, "DUPLICATED FIELDS", Toast.LENGTH_LONG).show();
                        long[] pattern = {0, 50, 100, 50};
                        vibrator.vibrate(pattern, -1);
                        return;
                    } else {
                        namesList.add(((EditText) tempView).getText().toString());
                    }
                }
            }
            closeKeyboard();
            vibrator.vibrate(50);
            Intent intent = new Intent(this, LevelsActivity.class);
            intent.putStringArrayListExtra("NAMES_LIST", namesList);
            saveNamesList(namesList);
            startActivity(intent);
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean verifyEmptiness() {
        for (int i = 0; i < secondLinearLayout.getChildCount(); i++) {
            View tempView = secondLinearLayout.getChildAt(i);
            if (tempView instanceof EditText) {
                if (TextUtils.isEmpty(((EditText) tempView).getText().toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verifyDuplicates(String inputString, @NonNull ArrayList<String> stringArrayList) {

        if (!stringArrayList.isEmpty()) {
            for (String stringIterator : stringArrayList) {
                if (inputString.equalsIgnoreCase(stringIterator)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(PLAYERS_NUMBER, playersNumber);
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(TEXT_CONTENTS, namesList);
        Log.i(TAG, "onSaveInstanceState: out");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<String> savedStringList = savedInstanceState.getStringArrayList(TEXT_CONTENTS);
        if (savedStringList != null) {
            for (int i = 0; i < secondLinearLayout.getChildCount(); i++) {
                View tempView = secondLinearLayout.getChildAt(i);
                if (tempView instanceof EditText) {
                    ((EditText) tempView).setText(savedStringList.get(i));
                }
            }
        }
        Log.i(TAG, "onRestoreInstanceState: out");
    }
}
