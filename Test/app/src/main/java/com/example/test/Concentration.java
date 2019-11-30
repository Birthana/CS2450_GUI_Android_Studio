package com.example.test;

import android.util.Log;

import java.util.Random;

public class Concentration {
    public String[][] words;
    public int numberOfWords;
    public int numberOfMatches;
    public int numberOfIncorrectMatches;
    public String revealed1;
    public String revealed2;


    public Concentration(int number){
        words = new String[10][2];
        words[0][0] = "Test";
        words[1][0] = "Amp";
        words[2][0] = "Null";
        words[3][0] = "Many";
        words[4][0] = "Few";
        words[5][0] = "What";
        words[6][0] = "Cash";
        words[7][0] = "Berry";
        words[8][0] = "Ever";
        words[9][0] = "How";
        for(int i = 0; i < words.length; i++){
            words[i][1] = "2";
        }
        numberOfWords = number / 2;
        numberOfMatches = 0;
        numberOfIncorrectMatches = 0;
        revealed1 = "";
        revealed2 = "";
    }

    public String RandomWord() {
        boolean still_looking = true;
        String result = "";
        while(still_looking) {
            Random rng = new Random();
            int rngNumber = rng.nextInt(numberOfWords);
            if (Integer.parseInt(words[rngNumber][1]) > 0) {
                words[rngNumber][1] = "" + (Integer.parseInt(words[rngNumber][1]) - 1);
                still_looking = false;
                result = words[rngNumber][0];
                SetRevealed(result);
            }
        }
        return result;
    }

    public void SetRevealed(String word){
        if(revealed1.matches("")){
            revealed1 = word;
        }else{
            revealed2 = word;
        }
    }

    public void CheckMatch(){
        if(!(revealed1.matches("") || revealed2.matches(""))) {
            if (revealed1.equals(revealed2)) {
                numberOfMatches++;
                revealed1 = "";
                revealed2 = "";
            }
        }
    }

    public boolean isRevealed(){
        boolean result = true;
        if(revealed1.matches("") || revealed2.matches("")){
            result = false;
        }
        return result;
    }

    public void TryAgain(){
        numberOfIncorrectMatches++;
        revealed1 = "";
        revealed2 = "";
    }

    public int getScore(){
        int result = numberOfMatches * 2 - numberOfIncorrectMatches;
        if(result < 0){
            result = 0;
        }
        return result;
    }
}
