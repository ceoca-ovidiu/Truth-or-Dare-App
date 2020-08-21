package com.example.truthordare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;
import java.util.Random;

public class DialogFrame extends AppCompatDialogFragment {

    private ArrayList<String> messageArrayList = new ArrayList<>();
    private String customMessage = "";
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("WARNING !!!");
        if(customMessage.isEmpty()){
            builder.setMessage(getRandomMessage());
        }else{
            builder.setMessage(customMessage);
            customMessage = "";
        }
        builder.setPositiveButton("LET'S DO THIS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

    public void customDialogMessage(String message){
        this.customMessage = message;
    }

    private String getRandomMessage() {

        messageArrayList.clear();
        messageArrayList.add("BEFORE GOING ANY FURTHER, ONE SHOT FOR EACH PLAYER TO WARM THINGS UP");
        messageArrayList.add("BEFORE GOING ANY FURTHER, ONE SIP FOR THE PERSON WHO IS HOSTING THIS GAME");
        messageArrayList.add("BEFORE GOING ANY FURTHER, ONE BIG WARM HUG BETWEEN PLAYERS");
        messageArrayList.add("BEFORE GOING ANY FURTHER, EACH PLAYER TAKES A SIP FROM A RANDOM PLAYER");
        messageArrayList.add("BEFORE GOING ANY FURTHER, ONE SELFIE OF THE GROUP JUST TO REMEMBER THIS MOMENT");

        Random random = new Random();
        return messageArrayList.get(random.nextInt(messageArrayList.size()));

    }
}
