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

public class MediumActivity extends AppCompatActivity {

    private TextView textView;
    private ArrayList<String> contentList = new ArrayList<>();
    private static ArrayList<String> namesList = new ArrayList<>();
    private int contentCounter = 0;
    private static final String TAG = "MediumActivity";
    private GestureDetector gestureDetector;
    private ArrayList<Integer> drawableIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium);
        textView = findViewById(R.id.mediumDisplayTextView);
        Button mediumNextButton = findViewById(R.id.mediumNextButton);
        gestureDetector = new GestureDetector(this, new GestureListener());
        namesList = getIntent().getStringArrayListExtra("MEDIUM_NAMES_LIST");
        loadDrawableIdList();
        loadContentList(getRandomPerson(namesList));
        textView.setText(getRandomContent(contentList));
        mediumNextButton.setOnClickListener(new View.OnClickListener() {
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
            Intent intent = new Intent(MediumActivity.this, EndActivity.class);
            startActivity(intent);
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent e) {
            startActivity(new Intent(MediumActivity.this, PopUpActivity.class));
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
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_brown", "drawable", this.getPackageName()));
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_grey", "drawable", this.getPackageName()));
    }


    private void loadContentList(String personName){
        contentList.clear();
        contentList.add("Give out 5 sips if you have nude pictures of your boyfriend or girlfriend");
        contentList.add("One by one, refill the glasses. " + personName + " you start");
        contentList.add(personName + " draw yourself a unibrow and you will be able to tell someone to down his/her drink");
        contentList.add("Let's take a break, each one has to drink 3 sips of water");
        contentList.add("Things that stinks (first to run out of ideas drink 5 times)." + personName + " you start");
        contentList.add(personName + " drinks as many sips as there are filled glasses on the table");
        contentList.add("Each player has to personName someone who must drink 2 sips." + personName + " you start");
        contentList.add("If you can't fit the entire top of your glass in your mouth, drink 2 times. If you can, give out 3 sips.");
        contentList.add(personName + " you must end your sentences with 'in my ass'. Each time you forget, you drink");
        contentList.add("Each player drinks as many times as there are vowels in their personName." + personName + " you start");
        contentList.add("Drink 4 times if your shoes have shoelaces");
        contentList.add(personName + ", if you broke up with someone by text message, give out 3 sips. If not, drink 2 times");
        contentList.add("Drink 2 times if you have ever seen your parents fuck");
        contentList.add("McDonalds or Burger King ? Vote at the same time. Losers drink 3 times");
        contentList.add("The girl with the biggest boobs can give out 2 sips. Boys can help to measure them");
        contentList.add(personName + " spit in your drink or drink twice");
        contentList.add("Drink 3 times if you have ever tried on your partener's underwear");
        contentList.add(personName + " let out a big burp or drink 3 sips.");
        contentList.add("Go around the room and tell us the last movie you watched. Those who watched the same movie drink 2 times together");
        contentList.add("The couples has to drink 3 times. No couples, everybody drinks 3 times");
        contentList.add(personName + " find a tampon and tuck it over your ear, mrrr so classy ...");
        contentList.add("Everyone with the letter 'a' in their first personName drink 2 times");
        contentList.add(personName + " you can choose someone to buy you a drink, the person and the type of drink is up to you");
        contentList.add(personName + " you have to say 'obviously' at the end of each sentence for 5 rounds. If you forget, take a sip");
        contentList.add(personName + " the next person you touch has to drink");
        contentList.add(personName + " smile for 1 minute straight. If you can't, drink 2 sips");
        contentList.add(personName + " go get the number of a stranger or drink 2 times");
        contentList.add("The last person to go to the bathroom, drink 4 times");
        contentList.add("Things that turns on a person." + personName + " you start.(first to repeat or cannot find anything has to drink 2 times)");
        contentList.add(personName + " compose a poem for the person to your right. If that person is not impressed you have to drink 3 times, otherwise give them out");
        contentList.add(personName + " do the worm. In case of refusal you have to drink 2 times");
        contentList.add(personName + " let the person in front of you to give you a new hairstyle. 2 sips for refusal");
        contentList.add(personName + " take a shower with your clothes on. 3 sips in case of refusal");
        contentList.add(personName + " blindfold yourself. You have to eat or at least chew the thing we give you to complete the dare. If you guess that thing you can give out 4 sips otherwise drink them. (no hands allowed, just your mouth)");
        contentList.add(personName + " juggle with  at least 2 eggs for 30 seconds. You can give one sip per egg, but if one breaks you have to drink them");
        contentList.add("Hot potato. Each player says a word and then pass the egg to a random player.The person who breaks the egg has to drink 4 times and the sentence must have a logic.I know, I know it is not a potato but let's make things funnier");
    }

}
