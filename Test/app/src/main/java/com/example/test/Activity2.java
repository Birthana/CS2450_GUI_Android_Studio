package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {

    public Concentration game;
    public ArrayList<View> cards;
    MediaPlayer mediaPlayer;
    ImageView playIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        int number = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 0);

        TextView test = findViewById(R.id.textview1);
        test.setText("" + number);

        playIcon = findViewById(R.id.playIcon);

        playIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    playIcon.setImageResource(R.drawable.ic_pause_black_24dp);
                }
                    else {
                        mediaPlayer.pause();
                        playIcon.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                }
            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.song);





        game = new Concentration(number);

        TableRow[] rows = new TableRow[4];
        rows[0] = findViewById(R.id.table_row1);
        rows[1] = findViewById(R.id.table_row2);
        rows[2] = findViewById(R.id.table_row3);
        rows[3] = findViewById(R.id.table_row4);

        cards = new ArrayList<>();
        Button btn[] = new Button[number];
        int rowCount = 0;
        for (int i = 1; i < number + 1; i++) {
            btn[i - 1] = new Button(this);
            btn[i - 1].setLayoutParams(new TableRow.LayoutParams(150, 200));
            btn[i - 1].setBackgroundResource(R.drawable.playing_card);
            btn[i - 1].setOnClickListener(new View.OnClickListener() {
                public String word = "";

                @Override
                public void onClick(View v) {
                    Button temp = (Button) v;
                    if(!game.isRevealed() && temp.getText().equals("")) {
                        if(word.matches("")){
                            word = game.RandomWord();
                        }else{
                            game.SetRevealed(word);
                        }
                        temp.setText(word);
                        temp.setBackgroundResource(R.drawable.playing_card_blank);
                        game.CheckMatch();
                        CheckIfGameWon();
                    }
                }
            });
            rows[rowCount].addView(btn[i - 1]);
            cards.add(btn[i - 1]);
            if (i % 5 == 0) {
                rowCount++;
            }
        }

        Button tryAgainButton = findViewById(R.id.TryAgain);
        tryAgainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Reset();
                game.TryAgain();
            }
        });

        Button endGameButton = findViewById(R.id.EndGame);
        endGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndGame();
            }
        });
    }

    public void EndGame(){
        for(int i = 0; i < cards.size(); i++){
            Button temp = (Button) cards.get(i);
            if(game.isRevealed()){
                game.revealed1 = "";
                game.revealed2 = "";
            }
            temp.callOnClick();
        }
        TextView textView = findViewById(R.id.textview1);
        textView.setText("Game End.");
    }

    public void CheckIfGameWon(){
        boolean result = true;
        for(int i = 0; i < cards.size(); i++){
            Button temp = (Button) cards.get(i);
            if(temp.getText().equals("")){
                result = false;
            }
        }
        if(result){
            TextView textView = findViewById(R.id.textview1);
            textView.setText("Game Won. Score: " + game.getScore());
        }
    }

    public void Reset(){
        for(int i = 0; i < cards.size(); i++){
            Button temp = (Button) cards.get(i);
            if(game.revealed1.equals(temp.getText()) ||
                game.revealed2.equals(temp.getText())){
                temp.setText("");
                temp.setBackgroundResource(R.drawable.playing_card);
            }
        }
    }


}
