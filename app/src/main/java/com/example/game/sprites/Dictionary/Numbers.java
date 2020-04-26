package com.example.game.sprites.Dictionary;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.R;
import com.example.game.sprites.Sprite;

import java.util.ArrayList;
import java.util.HashMap;

public class Numbers {

    private static final String SCORE_PREF = "Score pref";
    Bitmap zero;
    Bitmap one;
    Bitmap two;
    Bitmap three;
    Bitmap four;
    Bitmap five;
    Bitmap six;
    Bitmap seven;
    Bitmap eight;
    Bitmap nine;
    int width, height, posX, posY;
    HashMap<Integer, Bitmap> map = new HashMap<>();
    int points;


    public Numbers(int width, int height, int posX, int posY, Resources resources) {

        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;

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

        zero = Bitmap.createScaledBitmap(zero, width, height, false);
        one = Bitmap.createScaledBitmap(one, width, height, false);
        two = Bitmap.createScaledBitmap(two, width, height, false);
        three = Bitmap.createScaledBitmap(three, width, height, false);
        four = Bitmap.createScaledBitmap(four, width, height, false);
        five = Bitmap.createScaledBitmap(five, width, height, false);
        six = Bitmap.createScaledBitmap(six, width, height, false);
        seven = Bitmap.createScaledBitmap(seven, width, height, false);
        eight = Bitmap.createScaledBitmap(eight, width, height, false);
        nine = Bitmap.createScaledBitmap(nine, width, height, false);

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

    public void draw(Canvas canvas) {
        ArrayList<Bitmap> number = convertToBitmap(points);
        for (int i = 0; i < number.size(); i++) {
            int x = posX + getNumberWidth() * i;
            canvas.drawBitmap(number.get(i), x, posY, null);
        }
    }


    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public int getNumberWidth() {
        return zero.getWidth();
    }

    public void updateNumber(int amount) {
        this.points = amount;
    }

}
