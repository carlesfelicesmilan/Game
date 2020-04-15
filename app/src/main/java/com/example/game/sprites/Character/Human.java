package com.example.game.sprites.Character;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;
import com.example.game.sprites.Sprite;

import java.util.Random;

public class Human implements Sprite {
    int width, height, posX, posY, screenHeight, screenWidth;
    int hp;
    public int atq, def;
    boolean hit = false;
    Bitmap human;
    Bitmap human1;
    Bitmap human2;
    Bitmap human3;
    MediaPlayer sound;
    MediaPlayer damaged;

    public boolean isHit(int evX, int evY) {
        if (evX >= posX && evX < (posX + width)
                && evY >= posY && evY < (posY + height)) {
            //tada, if this is true, you've started your click inside your bitmap
            damaged.start();
            hit = !hit;
            return true;
        }
        else {
            return false;
        }
    }

    // Calculate the damage adding a random value
    public int calculateDamage(int damage) {
        if(hit) {
            if (def < damage) {
                Random randomDamage = new Random(System.currentTimeMillis());
                damage = randomDamage.nextInt(damage);
                hp = hp - (damage - def);
            }
        }
        return damage;
    }

    public boolean isDead() {
        if (hp<0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
       /* if ((hp <= 100) && (hp >= 80)) {
            canvas.drawBitmap(human, posX, posY, null);
        }
        else if ((hp <= 80) && (hp >= 60)) {
            canvas.drawBitmap(human1, posX, posY, null);
        }
        else if ((hp <= 60) && (hp >= 40)) {
            canvas.drawBitmap(human2, posX, posY, null);
        }
        else if ((hp <= 40) && (hp >= 0)) {
            canvas.drawBitmap(human3, posX, posY, null);
        }*/
       if(hit) {
           canvas.drawBitmap(human3, posX, posY, null);
       }
       else {
           canvas.drawBitmap(human, posX, posY, null);
       }
    }

    @Override
    public void update() {

    }
}
