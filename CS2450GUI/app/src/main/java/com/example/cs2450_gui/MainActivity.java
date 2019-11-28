package com.example.concentration;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;


public class MainActivity extends AppCompatActivity {

    private Tile tileOne;
    private Tile tileTwo;
    private int tiles [][];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Method: compareTiles
    //The purpose of this method is to compare the two selected tiles

    public void compareTiles(){
        if(tiles[tileOne.x][tileTwo.y] == tiles[tileOne.x][tileTwo.y]){
            tileOne.button.setVisibility(View.INVISIBLE);
            tileTwo.button.setVisibility(View.INVISIBLE);
        }
       // else {

        //}

        tileOne = null;
        tileTwo = null;
    }
}
