package com.example.game.sprites.buttons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.sprites.Sprite;

public class Button implements Sprite{
    int width, height, posX, posY, screenHeight, screenWidth;
    boolean buttonClicked = false;
    Bitmap button;
    MediaPlayer sound;

    public boolean isClicked(int evX, int evY) {
        if (evX >= posX && evX < (posX + width)
                && evY >= posY && evY < (posY + height)) {
            //tada, if this is true, you've started your click inside your bitmap
            buttonClicked = !buttonClicked;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(button, posX, posY, null);
    }

    @Override
    public void update() {
    }
}
