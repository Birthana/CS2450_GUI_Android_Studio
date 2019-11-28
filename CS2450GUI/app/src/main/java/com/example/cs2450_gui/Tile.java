package com.example.concentration;

import android.widget.Button;

//To be able to create a Tile object accessible by other classes
public class Tile {

    public int x;
    public int y;
    public Button button;

    public Tile(Button button, int x, int y) {
        this.button = button;
        this.x = x;
        this.y = y;

    }

}
