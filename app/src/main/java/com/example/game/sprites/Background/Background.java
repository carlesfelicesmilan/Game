package com.example.game.sprites.Background;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {
    int width, height, posX, posY;
    Bitmap asset;

    public Background(int width, int height, int posX, int posY, Bitmap asset) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.asset= Bitmap.createScaledBitmap(asset, width, height, false);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(asset, posX, posY, null);
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
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

}
