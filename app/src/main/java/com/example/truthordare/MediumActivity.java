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
        if(contentCounter < 45){
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
        contentList.add("Have you ever farted and lied about it? Drink 2 times");
        contentList.add("Drink as many sips as persons you kissed in your entire life. Not a single person ? Give out 4 sips you little motherf**ker");
        contentList.add("What illegal thing have you done recently? Everyone tell their story and the most impressive gives out 5 sips.");
        contentList.add("What’s the dumbest thing you did to impress someone? The most impressive story gives out 5 sips");
        contentList.add("From now on, each player must keep a hand on their glass no matter what");
        contentList.add("Let's take a break, one glass of water for everyone (if you want of course)");
        contentList.add(personName + " lick any kind of spread off the person in front of you cheek.");
        contentList.add(personName + " let the person on your right spank you as hard as they want to, on your butt. 3 sips in case of refusal");
        contentList.add(personName + " give a foot massage to the person to your right .The person who refuses has to drink 3 sips");
        contentList.add(personName + " switch your clothes with a player chosen by the group. 5 sips in case of refusal");
        contentList.add(personName + " switch socks with the person to your right");
        contentList.add(personName + " hold hands with the first person (opposite sex) on your left for the rest of the game. 3 sips for the person who refuses to do the dare");
        contentList.add(personName + " for the next 15 minutes, talk with a deep country accent");
        contentList.add(personName + " pretend to be a chicken who’s having trouble laying an egg.3 sips in case of refusal");
        contentList.add(personName + " put the mark of Simba on the front facing player head using ketchup. The person who refuses has to drink 3 sips");
        contentList.add(personName + " take off your underwear and put it on your head. Wear it for 10 minutes. 4 sips in case of refusal");
        contentList.add(personName + " seduce a member of the same gender in the group. The group must choose the player. 3 sips for the one who refuses");
        contentList.add("All the sips get doubled for 3 rounds. Cheers !!");
        contentList.add(personName + " drag your butt on the carpet like a dog from one end to another. 3 sips in case of refusal");
        contentList.add(personName + " bend at the waist so that you are looking behind you between your legs. Now run backwards until you can tag someone with your butt.3 sips in case of refusal. The person tagged has to drink the same amount of sips");
        contentList.add(personName + " act like whatever animal someone yells out for the next 3 minutes.3 sips in case of refusal");
        contentList.add("The person with the closest birthday can give out 4 sips.");
        contentList.add(personName + " invent a new rule for the rest of the game");
        contentList.add(personName + " eat a lemon");
        contentList.add(personName + " take your chair and pretend it is the love of your life. 3 sips in case of refusal");
        contentList.add(personName + " yell the first sentence or word that comes into your mind right now");
        contentList.add(personName + " brush the teeth of the person to your right. 2 sips for the person who refuses to complete the dare");
        contentList.add("The first who laugh must finish their drink");
        contentList.add(personName + " pretend to be the person to your right for 10 minutes");
        contentList.add(personName + " dance without music for 1 minute. 2 sips if you refuse");
        contentList.add(personName + " open your front door and howl like a wolf for 30 seconds. 3 sips in case of refusal");
        contentList.add(personName + " until the next round, talk super loud, like nobody can hear you. 2 sips in case of refusal");
        contentList.add(personName + " get into a debate with a wall or drink 3 times");
        contentList.add(personName + " take a selfie with the toilet. Give out 4 sips if you post it on your story.");
        contentList.add(personName + " go outside and try to summon the rain. 3 sips in case of refusal");
        contentList.add(personName + " pick the nose of the person next to you.");
        contentList.add(personName + " silently do the macarena");
        contentList.add(personName + " give everyone in the room a hug.");
        contentList.add(personName + " go outside and hug a tree or drink 2 sips in case of refusal");
        contentList.add(personName + " wear your underwear on the outside of your clothes or drink 3 times");
        contentList.add(personName + " run down the street with a wet T-shirt on. 2 sips in case of refusal");
        contentList.add(personName + " put all of your clothes on backward. 2 sips in case of refusal");
        contentList.add(personName + " if you are a man, wear lipstick for the rest of the game. If you are a girl, let a man do your makeup. 3 sips in case of refusal");
        contentList.add(personName + " walk like a crab for the rest of the game or drink 2 times");
        contentList.add(personName + " read two paragraphs from a book of someone’s choice");
        contentList.add(personName + " make fart noises with your armpit. Drink 3 times if you cannot do that");
        contentList.add(personName + " hop on one foot wherever you have to go.");
        contentList.add(personName + " let your friends pose you and stay like that until the next round.");
        contentList.add(personName + " eat a single spaghetti like in Lady and the Tramp with the person to your left or drink 3 times");
        contentList.add(personName + " take a selfie with the person to your right and post it on your story or drink 2 times"); //TODO : mai vad ca poate pun ceva mai interesant
        contentList.add("It is karaoke night." + personName + " has to sing a song chosen by the group");
        contentList.add("People wearing glasses drink 3 times");
        contentList.add("Anyone who has used a dating website drink 3 times");
        contentList.add("The first player to find a triangular object in the room gives out 2 sips, no phones allowed");
        contentList.add(personName + " if you know all the names of the person in front of you (first, middle, last), this player will drink 2 times. Otherwise you will drink 3 times");
        contentList.add("Until " + personName + " sees his/her name on the screen, he/she will drink for every new screen");
        contentList.add(personName + " if you are wearing clothes that don't belong to you, give out 3 sips. Otherwise drink them");
        contentList.add("Everyone older than " + personName + " drink 5 times");
        contentList.add(personName + " put on as many clothes as glasses you finished until now");
        contentList.add(personName + " you say 'In my suitcase there is...' and each player has to add a new object after repeating the ones already said. 2 sips in case of a repeated object or forgetting one");
        contentList.add(personName + " give out as many sips as single persons are around the table");
        contentList.add(personName + " if your first time was after 18, give out 3 sips");
        contentList.add(personName + " you are the clock for 5 rounds. Loudly announce the time every minute. Drink once for each minute you forget");
        contentList.add(personName + " give out 3 sips if you find yourself attractive, otherwise drink them");
        contentList.add("When the clock shows a multiple of ten (8:00 8:10 8:20 etc.) the first player who shout 'shit i left my cat back in space' can give out 10 sips");
        contentList.add("FREEZE !!! the last person to stop moving drinks 4 times");
        contentList.add("If you cannot touch your toes while standing straight legs drink 2 times");
        contentList.add("Foods that give you bad breath. The first who runs out of ideas has to drink 4 times. " + personName + " you start.");
        contentList.add(personName + " if you smoke, throw a cigarette to the trash or drink 2 times. If you do not smoke give out 5 sips");
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
