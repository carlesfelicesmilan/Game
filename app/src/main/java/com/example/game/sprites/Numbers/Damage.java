package com.example.game.sprites.Numbers;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Damage {
    private int posX, posY, initPosY, initPosX, numberWidth, numberHeight;
    private Numbers number;
    private int dmg, speedX, speedY;
    public boolean hit;

    public Damage(Resources resources) {
        number = new Numbers(resources);
        numberWidth = number.getNumberWidth();
        numberHeight = number.getNumberHeight();

        // Speed of the damage when enemy is hit
        speedY = -5;
        speedX = 5;
    }

    public void draw(Canvas canvas) {
        ArrayList<Bitmap> damage = number.convertToBitmap(dmg);
        for (int i = 0; i < damage.size(); i++) {
            int x = posX - damage.size() * numberWidth / 2 + numberWidth * i;
            canvas.drawBitmap(damage.get(i), x , posY, null);
        }
    }

    public void drawEnemyDmg(Canvas canvas) {
        ArrayList<Bitmap> damage = number.convertToBitmap(dmg);
        for (int i = 0; i < damage.size(); i++) {
            int x = posX - damage.size() * numberWidth / 2 + numberWidth * i;
            canvas.drawBitmap(damage.get(i), x , posY, null);
        }
    }

    public void setDamagePos(int x, int y) {
        this.initPosX = x;
        this.initPosY = y;
    }

    public void updateDamage(int dmg) {
        this.dmg = dmg;
    }

    public void update() {
        if (hit) {
            posX = initPosX;
            posY = initPosY;
            hit = false;
        }
        posX+=speedX;
        posY+=speedY;
    }

    public void isHit(boolean hit) {
        this.hit = hit;

    }

    public void setSize(int x, int y) {
        number.scaleNumbers(x,y);
    }

}
