package com.example.game.sprites.Spells;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;

public class Spell {
    int atq;
    int width, height, posX, posY;
    Bitmap asset;
    MediaPlayer sound;

    public Spell(int width, int height, int posX, int posY, Bitmap asset, MediaPlayer sound) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.asset = Bitmap.createScaledBitmap(asset, width, height, false);
        this.sound = sound;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(asset, posX, posY, null);
    }

    public void startSound() {
        sound.start();
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }


}
