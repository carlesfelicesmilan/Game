package com.example.game.sprites.Screens;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;
import com.example.game.sprites.Background.Background;
import com.example.game.sprites.Button.Button;
import com.example.game.sprites.Dictionary.Letters;
import com.example.game.sprites.Dictionary.Numbers;

public class StatusScreen {
    private Background background;
    private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;

    private String attack, defense, currentExp, nextLevel, money;

    private Letters name, atqText, defText, expText, nextLvlText, moneyText;
    private int nameX, nameY, atqTextX, atqTextY, defTextX, defTextY, expTextX, expTextY, nextLvlTextX, nextLvlTextY, moneyTextX, moneyTextY;

    private Numbers atqNumber, defNumber, expNumber, nextLvlNumber, moneyNumber;
    private int numberWidth, numberHeight, atqX, atqY, defX, defY, expX, expY, nextLvlX, nextLvlY, moneyX, moneyY;

    private Button back;
    private int backWidth, backHeight, backX, backY;


    public StatusScreen(Resources resources, Context context, int screenHeight, int screenWidth) {

        backgroundX = 0;
        backgroundY = 0;
        backgroundWidth = screenWidth;
        backgroundHeight = screenHeight;

        nameX = (int) resources.getDimension(R.dimen.capital_letter_size);
        nameY = (int) resources.getDimension(R.dimen.capital_letter_size);

        attack = "Atk";
        atqTextX = (int) resources.getDimension(R.dimen.capital_letter_size);
        atqTextY = screenHeight / 2;

        defense = "Def";
        defTextX = (int) resources.getDimension(R.dimen.capital_letter_size);
        defTextY = atqTextY + (int) resources.getDimension(R.dimen.margin)*4;

        currentExp = "Exp";
        expTextX = screenWidth/2;
        expTextY = (int) resources.getDimension(R.dimen.capital_letter_size)*2;

        nextLevel = "Next";
        nextLvlTextX = screenWidth/2;
        nextLvlTextY = (int) resources.getDimension(R.dimen.capital_letter_size)*4;

        money = "Gp";
        moneyTextX = screenWidth/2;
        moneyTextY = (int) resources.getDimension(R.dimen.capital_letter_size)*8;

        numberWidth = (int) resources.getDimension(R.dimen.number_size);
        numberHeight = (int) resources.getDimension(R.dimen.number_size);

        atqX = atqTextX + attack.length()*(int) resources.getDimension(R.dimen.capital_letter_size);
        atqY = atqTextY;

        defX = defTextX + defense.length()*(int) resources.getDimension(R.dimen.capital_letter_size);
        defY = defTextY;

        expX = expTextX + currentExp.length()*(int) resources.getDimension(R.dimen.capital_letter_size);
        expY = expTextY;

        nextLvlX = nextLvlTextX + currentExp.length()*(int) resources.getDimension(R.dimen.capital_letter_size);
        nextLvlY = nextLvlTextY;

        moneyX = moneyTextX + money.length()*(int) resources.getDimension(R.dimen.capital_letter_size);
        moneyY = moneyTextY;

        backWidth = (int) resources.getDimension(R.dimen.button_width);
        backHeight = (int) resources.getDimension(R.dimen.button_height);;

        backX = screenWidth - backWidth;
        backY = screenHeight - backHeight;

        background = new Background(backgroundWidth, backgroundHeight, backgroundX, backgroundY, BitmapFactory.decodeResource(resources, R.drawable.background2));

        name = new Letters("Steyn",  nameX, nameY, resources);
        atqText = new Letters(attack,  atqTextX, atqTextY, resources);
        defText = new Letters(defense, defTextX, defTextY, resources);
        expText = new Letters(currentExp, expTextX, expTextY, resources);
        nextLvlText = new Letters(nextLevel, nextLvlTextX, nextLvlTextY, resources);
        moneyText = new Letters(money, moneyTextX, moneyTextY, resources);

        atqNumber = new Numbers(numberWidth, numberHeight, atqX, atqY, resources);
        defNumber = new Numbers(numberWidth, numberHeight, defX, defY, resources);
        expNumber = new Numbers(numberWidth, numberHeight, expX, expY, resources);
        nextLvlNumber = new Numbers(numberWidth, numberHeight, nextLvlX, nextLvlY, resources);
        moneyNumber = new Numbers(numberWidth, numberHeight, moneyX, moneyY, resources);

        back = new Button(backWidth, backHeight, backX, backY, BitmapFactory.decodeResource(resources, R.drawable.back), MediaPlayer.create(context, R.raw.optionsoff));
    }

    public void draw(Canvas canvas) {
        background.draw(canvas);
        name.draw(canvas);
        atqText.draw(canvas);
        defText.draw(canvas);
        expText.draw(canvas);
        nextLvlText.draw(canvas);
        moneyText.draw(canvas);
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
