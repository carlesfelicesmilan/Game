package com.example.game.sprites.Numbers;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.game.R;
import com.example.game.sprites.Sprite;

import java.util.ArrayList;
import java.util.HashMap;

public class Numbers implements Sprite {

    private static final String SCORE_PREF = "Score pref";
    private Bitmap zero;
    private Bitmap one;
    private Bitmap two;
    private Bitmap three;
    private Bitmap four;
    private Bitmap five;
    private Bitmap six;
    private Bitmap seven;
    private Bitmap eight;
    private Bitmap nine;
    private HashMap<Integer, Bitmap> map = new HashMap<>();
    private int screenHeight, screenWidth, hpPosX, hpPosY, damagePosY, damagePosX, damageInitialPosY, damageInitialPosX;
    private int hp;
    private int receivedDamage;
    private int speedY, speedX;
    private boolean hit = false;

    public Numbers(Resources resources) {

        zero = BitmapFactory.decodeResource(resources, R.drawable.zero);
        one = BitmapFactory.decodeResource(resources, R.drawable.one);
        two = BitmapFactory.decodeResource(resources, R.drawable.two);
        three = BitmapFactory.decodeResource(resources, R.drawable.three);
        four = BitmapFactory.decodeResource(resources, R.drawable.four);
        five = BitmapFactory.decodeResource(resources, R.drawable.five);
        six = BitmapFactory.decodeResource(resources, R.drawable.six);
        seven = BitmapFactory.decodeResource(resources, R.drawable.seven);
        eight = BitmapFactory.decodeResource(resources, R.drawable.eight);
        nine = BitmapFactory.decodeResource(resources, R.drawable.nine);

        map.put(0, zero);
        map.put(1, one);
        map.put(2, two);
        map.put(3, three);
        map.put(4, four);
        map.put(5, five);
        map.put(6, six);
        map.put(7, seven);
        map.put(8, eight);
        map.put(9, nine);

        // Speed of the damage when enemy is hit
        speedY = -5;
        speedX = 5;
    }

    // In this Array we show the actual amount of the score shown when the user is playing
    // In the first state the amount will always be 0 because the user started the game
    // Once the user got some score we always take the last digit before dies
    // We need to reverse the array to get the proper number (for)
    public ArrayList<Bitmap> convertToBitmap (int amount) {
        ArrayList<Bitmap> digits = new ArrayList();
        if (amount == 0) {
            digits.add(zero);
        }
        while (amount > 0) {
            int lastDigit = amount % 10;
            amount /= 10;
            digits.add(map.get(lastDigit));
        }
        ArrayList<Bitmap> finalDigits = new ArrayList<>();
        for (int i = digits.size()-1; i>=0; i--) {
            finalDigits.add(digits.get(i));
        }
        return finalDigits;
    }

    // We alwyas draw the hp, we only draw the damage when the enemy is hit
    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }

    public void isHit(boolean hit) {
        this.hit = hit;
    }

    public void updateDamage(int damage) {
        this.receivedDamage = damage;
    }

    public void scaleNumbers(int x, int y) {
        zero = Bitmap.createScaledBitmap(zero, x, y, false);
        one = Bitmap.createScaledBitmap(one, x, y, false);
        two = Bitmap.createScaledBitmap(two, x, y, false);
        three = Bitmap.createScaledBitmap(three, x, y, false);
        four = Bitmap.createScaledBitmap(four, x, y, false);
        five = Bitmap.createScaledBitmap(five, x, y, false);
        six = Bitmap.createScaledBitmap(six, x, y, false);
        seven = Bitmap.createScaledBitmap(seven, x, y, false);
        eight = Bitmap.createScaledBitmap(eight, x, y, false);
        nine = Bitmap.createScaledBitmap(nine, x, y, false);

        map.put(0, zero);
        map.put(1, one);
        map.put(2, two);
        map.put(3, three);
        map.put(4, four);
        map.put(5, five);
        map.put(6, six);
        map.put(7, seven);
        map.put(8, eight);
        map.put(9, nine);
    }

    public int getNumberWidth() {
        return zero.getWidth();
    }

    public int getNumberHeight() {
        return zero.getHeight();
    }

}
