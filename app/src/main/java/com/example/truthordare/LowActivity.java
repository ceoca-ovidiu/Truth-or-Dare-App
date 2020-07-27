package com.example.truthordare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LowActivity extends AppCompatActivity {

    private TextView textView;
    private ArrayList<String> contentList = new ArrayList<>();
    private ArrayList<String> namesList = new ArrayList<>();
    private int contentCounter = 0;
    private static final String TAG = "LowActivity";
    private GestureDetector gestureDetector;
    private ArrayList<Integer> drawableIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_low);
        textView = findViewById(R.id.lowDisplayTextView);
        Button lowNextButton = findViewById(R.id.lowNextButton);
        gestureDetector = new GestureDetector(this, new GestureListener());
        namesList = getIntent().getStringArrayListExtra("LOW_NAMES_LIST");
        loadDrawableIdList();
        loadContentList(getRandomPerson(namesList));
        textView.setText(getRandomContent(contentList));
        lowNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextContentDisplay();
            }
        });
        Log.d(TAG, "onCreate: out");
    }

    @Override
    public void onBackPressed(){}

    private int getRandomDrawableFile(ArrayList<Integer> drawablesIdList){
        Random random = new Random();
        return drawablesIdList.get(random.nextInt(drawablesIdList.size()));
    }

    private String getRandomPerson(ArrayList<String> stringArrayList){
        Random random = new Random();
        return stringArrayList.get(random.nextInt(stringArrayList.size()));
    }

    private String getRandomContent(ArrayList<String> stringArrayList){
        Random random = new Random();
        Collections.shuffle(stringArrayList, random);
        return stringArrayList.get(random.nextInt(stringArrayList.size()));
    }

    private void nextContentDisplay(){
        if(contentCounter < 10){
        loadContentList(getRandomPerson(namesList));
        textView.setBackgroundResource(getRandomDrawableFile(drawableIdList));
        textView.setText(getRandomContent(contentList));
        contentCounter++;
        }else{
            Intent intent = new Intent(LowActivity.this, EndActivity.class);
            startActivity(intent);
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent e) {
            startActivity(new Intent(LowActivity.this, PopUpActivity.class));
            super.onLongPress(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    private void loadDrawableIdList() {
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_green", "drawable", this.getPackageName()));
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_orange", "drawable", this.getPackageName()));
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_pink", "drawable", this.getPackageName()));
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_purple", "drawable", this.getPackageName()));
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_turquoise", "drawable", this.getPackageName()));
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_red", "drawable", this.getPackageName()));
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_blue", "drawable", this.getPackageName()));
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_yellow", "drawable", this.getPackageName()));
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_brick", "drawable", this.getPackageName()));
    }

    private void loadContentList(String personName){

        contentList.clear();
        contentList.add(personName + " drink 5 sips if you planned to finish the night before 3 AM, otherwise give them out");
        contentList.add("Those who are sick today drink 3 times");
        contentList.add(personName + " sit on one butt cheek for maximum 5 minutes. For each minute you can give out one sip to another player ");
        contentList.add("Who likes to pet cats ? Drink 3 times");
        contentList.add(personName + ", the person in front of you will ask you a question. If you know the correct answer, you can give out 5 sips, otherwise you drink them");
        contentList.add("People with M in your first name, bottoms up");
        contentList.add("Movies about the end of the world (first to run out of ideas drink 5 times). " + personName + " you start" );
        contentList.add(personName + " challenge someone to thumb war, the loser drinks 2 times");
        contentList.add(personName + ", you have the ability to swap the drinks of two players (max. 3 swaps)");
        contentList.add("The hairiest person has to drink 5 times");
        contentList.add("Those who have brown eyes, drink 4 times");
        contentList.add("Everyone with brown hair drink 2 times");
        contentList.add(personName + ", choose a player who has to down his/her drink or give out 3 sips to whoever you want");
        contentList.add("Everyone has to do a gesture after repeating the gestures done so far. The loser has to drink 5 sips." + personName + " you start");
        contentList.add(personName + " get on your knees for 5 minutes");
        contentList.add(personName + " invent a new rule");
        contentList.add("Everyone take as many sips as the amount of years you studied after high school");
        contentList.add("Clothes to wear in summer (first to run out of ideas drink 5 times).");
        contentList.add("The next person who has to drink will double his/her amount of sips");

    }
}
