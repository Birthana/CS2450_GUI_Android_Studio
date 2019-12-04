/*******************************************
 *  file: MainActivity.java
 *  author: Thana S. & Joe C.
 *  class: CS 2450 - GUI
 *
 *  assignment: project 2
 *  date last modified: 12/4/2019
 *
 *  purpose: creat the game concentration in
 *  Android studio
 *
 *******************************************/

package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NUMBER = "com.example.test.EXTRA_NUMBER";
    public String selected_size;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button testButton = findViewById(R.id.TestButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected_size.matches("")) {
                    Toast.makeText(getApplication().getBaseContext(), "Select a number.", Toast.LENGTH_SHORT).show();
                } else {
                    Play();
                }
            }
        });

        Button highscoreButton = findViewById(R.id.HighScoreButton);
        highscoreButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (selected_size.matches("")) {
                    Toast.makeText(getApplication().getBaseContext(), "Select a number.", Toast.LENGTH_SHORT).show();
                } else {
                    HighScores();
                }
            }
        });

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sizes));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_size = "" + parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //nothing
            }
        });
    }


    private void HighScores(){
        FileInputStream fis = null;
        try{
            fis = openFileInput(Activity2.FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            String text;
            sb.append("HIGH SCORES:\n");

            while((text = br.readLine()) != null){
                String[] temp = text.split(" ");
                if(Integer.parseInt(temp[0]) == Integer.parseInt(selected_size)){
                    sb.append(temp[1]).append(" " + temp[2]).append("\n");
                }
            }

            TextView test = findViewById(R.id.highscoreText);
            test.setText(sb.toString());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if(fis != null){
                try{
                    fis.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void Play() {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(EXTRA_NUMBER, Integer.parseInt(selected_size));
        startActivity(intent);
    }

}