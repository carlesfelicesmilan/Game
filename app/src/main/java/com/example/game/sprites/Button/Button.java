package com.example.game.sprites.Button;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.sprites.Dictionary.Letters;

public class Button {
    int width, height, posX, posY;
    Bitmap asset;
    MediaPlayer sound;
    Letters text;

    public Button(int width, int height, int posX, int posY, Bitmap asset, MediaPlayer sound) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.asset = Bitmap.createScaledBitmap(asset, width, height, false);
        this.sound = sound;
    }

    public boolean isClicked(int evX, int evY) {
        if (evX >= posX && evX < (posX + width) && evY >= posY && evY < (posY + height)) {
            //tada, if this is true, you've started your click inside your bitmap
            return true;
        }
        else {
            return false;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(asset, posX, posY, null);
    }

    public void update() {
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setSize(int width, int height) {
        asset = Bitmap.createScaledBitmap(asset, width, height, false);
    }

    public int getSize() {
        return width;
    }

    public Bitmap getBitmap() {
        return asset;
    }

    public void startSound() {
        sound.start();
    }

    public void changeAsset(Bitmap asset) {
        this.asset = Bitmap.createScaledBitmap(asset, width, height, false);
    }

    public void addText(String text) {

    }

}
