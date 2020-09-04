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
        if(contentCounter < 30){
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
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_brown", "drawable", this.getPackageName()));
        drawableIdList.add(this.getResources().getIdentifier("rounded_textview_grey", "drawable", this.getPackageName()));
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
        contentList.add(personName + " eat a spoon of ketchup. 2 sips in case of refusal");
        contentList.add(personName + " swallow a raw egg");
        contentList.add(personName + " swap shirts with the person on your left.");
        contentList.add(personName + " spin an imaginary hula hoop around your waist for 3 minutes while the game continues. 4 sips in case of refusal");
        contentList.add(personName + " make a funny face and keep it for 2 minutes while the game continues");
        contentList.add(personName + " act like whatever animal someone yells out for the next 3 minutes.3 sips in case of refusal");
        contentList.add(personName + " invent a new rule for the rest of the game");
        contentList.add(personName + " yell the first sentence or word that comes into your mind right now");
        contentList.add("The first who laugh must finish their drink");
        contentList.add(personName + " pretend to be the person to your left for 10 minutes");
        contentList.add(personName + " until the next round, talk super loud, like nobody can hear you. 2 sips in case of refusal");
        contentList.add(personName + " pretend you’re a bird and eat cereal off the floor using only your mouth for 2 round straight");
        contentList.add(personName + " let someone else style your hair and keep it that way for the rest of the game.");
        contentList.add(personName + " use a brush or something that looks like it and pretend you’re talking into a microphone each time you speak.");
        contentList.add(personName + " talk without closing your mouth.");
        contentList.add(personName + " get into a debate with a wall or drink 3 times");
        contentList.add(personName + " take a selfie with the toilet. Give out 2 sips if you post it on your story.");
        contentList.add(personName + " go outside and try to summon the rain. 3 sips in case of refusal");
        contentList.add(personName + " pick the nose of the person next to you.");
        contentList.add(personName + " silently do the macarena");
        contentList.add(personName + " hold your nose while talking. 2 sips in case of refusal");
        contentList.add(personName + " go outside and hug a tree. 3 sips in case of refusal");
        contentList.add(personName + " give everyone in the room a hug.");
        contentList.add(personName + " go outside and hug a tree or drink 2 sips in case of refusal");
        contentList.add(personName + " put all of your clothes on backward. 2 sips in case of refusal");
        contentList.add(personName + " walk like a crab for the rest of the game.");
        contentList.add(personName + " hop on one foot wherever you have to go.");
        contentList.add(personName + " trade socks with the person to your left or drink 2 times");
        contentList.add("Anyone who is shorter than " + personName + " has to drink 3 times");
        contentList.add(personName + " give out as many sips as push-ups you can do with one hand");
        contentList.add("Until " + personName + " sees his/her name on the screen, he/she will drink for every new screen");
        contentList.add(personName + " if you are wearing clothes that don't belong to you, give out 3 sips. Otherwise drink them");
        contentList.add("Everyone older than " + personName + " drink 5 times");
        contentList.add(personName + " put on as many clothes as glasses you finished until now");
        contentList.add(personName + " you say 'In my suitcase there is...' and each player has to add a new object after repeating the ones already said. 2 sips in case of a repeated object or forgetting one");
        contentList.add(personName + " give out as many sips as single persons are around the table");
        contentList.add(personName + " you are the clock for 5 rounds. Loudly announce the time every minute. Drink once for each minute you forget");
        contentList.add(personName + " give out 3 sips if you find yourself attractive, otherwise drink them");
        contentList.add("When the clock shows a multiple of ten (8:00 8:10 8:20 etc.) the first player who shout 'shit i left my cat back in space' can give out 10 sips");
        contentList.add("FREEZE !!! the last person to stop moving drinks 4 times");
        contentList.add("If you cannot touch your toes while standing straight legs drink 2 times");
        contentList.add(personName + " if you smoke, throw a cigarette to the trash or drink 2 times. If you do not smoke give out 5 sips");
        contentList.add("Foods that give you bad breath. The first who runs out of ideas has to drink 4 times. " + personName + " you start.");
        contentList.add("Things you can find in a purse. The first who runs out of ideas has to drink 4 times. " + personName + " you start.");
        contentList.add("The oldest person in the room must drink 3 sips");
        contentList.add("Things you can find in a purse. The first who runs out of ideas has to drink 4 times. " + personName + " you start.");
        contentList.add(personName + " drink as many times as you have balls ");
        contentList.add(personName + " choose who will drink 3 times from your glass");
        contentList.add(personName + " is Caesar !! Anytime: thumb up and the targeted person is free from drinking, thumb down and the player doubles");
        contentList.add(personName + " must do a piggyback to the person to his/her right. 3 sips in case of refusal");
        contentList.add(personName + " you are the dictator. You can dare anyone anytime. 3 sips in case of refusal. (5 rounds)");
        contentList.add("The last person to still have their feet on the ground drinks 2 times");

    }
}
