package com.example.game.sprites.Numbers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Hp {

    private int posX, posY, numberWidth, numberHeight;
    private Numbers number;
    private int hp;

    public Hp(Resources resources) {
        number = new Numbers(resources);
        numberWidth = number.getNumberWidth();
        numberHeight = number.getNumberHeight();
    }

    public void draw(Canvas canvas) {
        ArrayList<Bitmap> currentHp = number.convertToBitmap(hp);
        for (int i = 0; i < currentHp.size(); i++) {
            int x = posX + number.getNumberWidth() * i;
            canvas.drawBitmap(currentHp.get(i), x, posY, null);
        }
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void updateHp(int hp) {
        this.hp = hp;
    }

    public void setSize(int x, int y) {
        number.scaleNumbers(x,y);
    }

}

