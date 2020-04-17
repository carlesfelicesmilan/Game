package com.example.game.sprites.buttons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;
import com.example.game.sprites.Sprite;

public class Button implements Sprite{
    int width, height, posX, posY, screenHeight, screenWidth;
    Bitmap button;
    MediaPlayer sound;

    public boolean isClicked(int evX, int evY) {
        if (evX >= posX && evX < (posX + width)
                && evY >= posY && evY < (posY + height)) {
            //tada, if this is true, you've started your click inside your bitmap
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

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void setSize(int width, int height) {
        button = Bitmap.createScaledBitmap(button, width, height, false);
    }

    public int getSize() {
        return width;
    }

    public Bitmap getBitmap() {
        return button;
    }

}
