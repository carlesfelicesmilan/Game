package com.example.game.sprites.Screens;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;
import com.example.game.sprites.Background.Background;
import com.example.game.sprites.Button.Button;
import com.example.game.sprites.Numbers.Numbers;

public class StatusScreen {
    private Numbers atqNumber, defNumber, expNumber, moneyNumber;
    private Button back;
    private Background background;
    private int numberWidth, numberHeight, numberX, atqY, defY, expY, moneyY;
    private int backWidth, backHeight, backX, backY;
    private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;

    public StatusScreen(Resources resources, Context context, int screenHeight, int screenWidth) {

        backgroundX = 0;
        backgroundY = 0;
        backgroundWidth = screenWidth;
        backgroundHeight = screenHeight;

        numberWidth = screenWidth / 12;
        numberHeight = screenWidth / 12;

        numberX = screenWidth / 6;
        atqY = numberWidth*2;
        defY = numberWidth*4;
        expY = numberWidth*6;
        moneyY = numberWidth*8;

        backWidth = (int) resources.getDimension(R.dimen.button_width);
        backHeight = (int) resources.getDimension(R.dimen.button_height);;

        backX = screenWidth - backWidth;
        backY = screenHeight - backHeight;

        background = new Background(backgroundWidth,backgroundHeight,backgroundX,backgroundY, BitmapFactory.decodeResource(resources, R.drawable.background2));

        atqNumber = new Numbers(numberWidth, numberHeight, numberX, atqY, resources);
        defNumber = new Numbers(numberWidth, numberHeight, numberX, defY, resources);
        expNumber = new Numbers(numberWidth, numberHeight, numberX, expY, resources);
        moneyNumber = new Numbers(numberWidth, numberHeight, numberX, moneyY, resources);

        back = new Button(backWidth, backHeight, backX, backY, BitmapFactory.decodeResource(resources, R.drawable.back), MediaPlayer.create(context, R.raw.optionsoff));
    }

    public void draw(Canvas canvas) {
        background.draw(canvas);
        atqNumber.draw(canvas);
        defNumber.draw(canvas);
        expNumber.draw(canvas);
        moneyNumber.draw(canvas);
        back.draw(canvas);
    }

    public void updateStatus(int atq, int def, int exp, int money) {
        atqNumber.updateNumber(atq);
        defNumber.updateNumber(def);
        expNumber.updateNumber(exp);
        moneyNumber.updateNumber(money);
    }
    public boolean isBackClicked(int x, int y) {
        return back.isClicked(x, y);
    }
}
