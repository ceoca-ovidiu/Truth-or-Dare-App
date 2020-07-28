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

public class HardActivity extends AppCompatActivity {

    private TextView textView;
    private ArrayList<String> contentList = new ArrayList<>();
    private static ArrayList<String> namesList = new ArrayList<>();
    private int contentCounter = 0;
    private static final String TAG = "HardActivity";
    private GestureDetector gestureDetector;
    private ArrayList<Integer> drawableIdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);
        textView = findViewById(R.id.hardDisplayTextView);
        Button hardNextButton = findViewById(R.id.hardNextButton);
        namesList = getIntent().getStringArrayListExtra("HARD_NAMES_LIST");
        gestureDetector = new GestureDetector(this, new GestureListener());
        loadDrawableIdList();
        loadContentList(getRandomPerson(namesList));
        textView.setText(getRandomContent(contentList));
        hardNextButton.setOnClickListener(new View.OnClickListener() {
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
            Intent intent = new Intent(HardActivity.this, EndActivity.class);
            startActivity(intent);
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(MotionEvent e) {
            Log.d(TAG, "onLongPress: in");
            super.onLongPress(e);
            startActivity(new Intent(HardActivity.this, PopUpActivity.class));
            Log.d(TAG, "onLongPress: out");
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
        contentList.add("Drink 3 times if you have ever given a massage that turned into something more interesting");
        contentList.add(personName + " for each piece of clothing you take off, give out 1 sip");
        contentList.add("Everyone unbutton your shirts and drink once for each button");
        contentList.add(personName + " choose a person to caress you while you are finishing your drink (the zone is chosen by the others)"); //TODO: daca am scris bine
        contentList.add("Drink 3 times if you have ever had more fuck buddies at the same time");
        contentList.add(personName + " choose your drinking bitch. You will be able to transfer your sips to him/her 2 times in this game");
        contentList.add("Drink 3 times if you kissed 2 people in the same night");
        contentList.add("Go around the room and touch the crotch of the person to your right or drink 2 times. " + personName + " starts");
        contentList.add("If you ever cried out the wrong person's name while having sex, drink 4 times");
        contentList.add(personName + " blindfold yourself. 2 players will kiss you anywhere. If you guess one of the two players you can give 4 sips. You have one chance");
        contentList.add(personName + " tell a funny story about a player or drink 2 times");
        contentList.add("If you look people in the eyes while speaking to them, drink one sip (5 rounds)"); //TODO: VIRUS
        contentList.add("Drink 3 times if your first time was not that good");
        contentList.add("If you ever had sex while you or your partner was on her period, drink 5 times");
        contentList.add(personName + " has to speak with Chinese accent (5 rounds)");  //TODO : VIRUS
        contentList.add(personName + " drink as many sips as you poured. None ? Drink 3 times for the bartender");
        contentList.add("Girls, the first one to have had her period can give 4 sips");
        contentList.add("Spin the bottle baby! Each player has to spin the bottle and kiss the targeted player (5 sips for refusal). " + personName + " starts");
        contentList.add(personName + " is Cupid! You choose who kisses or hugs who. (5 sips for the person who refused) (3 rounds)"); //TODO : VIRUS
        contentList.add(personName + " if you have friends to whom you usually speak in a foreign language, give out 3 sips. Drink them otherwise");
        contentList.add(personName + " show your boobs or drink 3 times. If you are a man, you can give out 3 sips");
        contentList.add("If you ever painted your nails, drink 3 times");
        contentList.add("Those who ever faked an orgasm drink 2 times");
        contentList.add("The group choose a player." + personName + " mime a sexual position with that player.(5 sips for the person who refused)");
        contentList.add("Drink 2 times if you ever fucked in a friend's bed without them finding out");
        contentList.add(personName + " let the player chosen by the group to lick your nipples or drink 4 times");
        contentList.add("What is arousing your partner ? (first to repeat or cannot find anything has to drink 3 times)" + personName + " starts");
        contentList.add("If you ever had sex despite friends sleeping in the same room, give out 3 sips");
        contentList.add("'Typical places for fantasies'. " + personName + " , you start. (first to repeat or cannot find anything has to drink 2 times)");
        contentList.add(personName + ", ask a question to the room in large. The first person who tell you the answer can give out 3 sips, the rest drink one sip");
        contentList.add(personName + " go around the room and tap the back of the player who must take 2 sips");
        contentList.add("The lovely person hosting this game gives out 2 sips");
        contentList.add(personName + " if you manage to kiss a player anywhere without forcing them, this player will finish their drink. You have 5 minutes ");
        contentList.add(personName + " has to pause 2 seconds between each spoken word. Drink once every time you forget to pause. (5 rounds)");
        contentList.add("Girls, if you ever had vaginal orgasm drink 2 times");
        contentList.add("Things to do when nobody is home. " + personName + " you start. (first to repeat or cannot find anything has to drink 2 times)");
        contentList.add("Things to do before going out when you are single. " + personName + " you start. (first to repeat or cannot find anything has to drink 2 times)");
        contentList.add("Players have ever dreamt about fighting someone drink 2 times");
        contentList.add("The group has to choose a player. " + personName + ", kiss the chosen player on the neck or drink 4 times (be sensual)");
        contentList.add("Smokers who have ever tried to stop smoking but failed, drink 3 times");
        contentList.add("The last person to have performed passionate cunnilingus, gives out 4 sips");
        contentList.add("If you ever thought of someone else during sex, drink 3 times");
        contentList.add("Drink 5 times if you have ever been pulled over by the police");
        contentList.add(personName + ", choose someone to cup your ass with their hands");
        contentList.add("'Sexual practices other than penetration'. (first to repeat or cannot find anything has to drink 2 times). " + personName + " you start");
        contentList.add("Give out 5 sips if you have ever masturbated at work");
        contentList.add("New rule, each time someone talks, he/she has to begin the sentence with 'but sir,...'");
        contentList.add("Male panties contest. Girls, you are the jury. Loser get 3 sips");
        contentList.add("The group has to choose two players (male and female) who will finish their drinks and french kiss each other");
        contentList.add("Kiss party. Go around the room and kiss the person to the right or drink 5 times." + personName + " you start");
        contentList.add(personName + " you can give out as many sips as you have uncles");
        contentList.add(personName + " and the player in front of him/her has to tell us a bedroom fantasy (it does not matter if it happened or not). The player with the most interesting one can give out 5 sips to any player");
        contentList.add(personName + " you are a special player. You can rest your feet on the right player lap for 2 rounds and refuse to drink");
        contentList.add("The clock tells you to drink. The first player has to drink the first number of the hour, the second, the second number and so on." + personName + " you start and then everything goes to the right.");
    }

}
