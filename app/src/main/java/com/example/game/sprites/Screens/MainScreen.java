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

public class MainScreen {
    private Background background;
    private Button newGame, load, options, exit;
    private Letters newGameText, loadText, optionsText, exitText;
    private int backgroundX, backgroundY;
    private int backgroundWidth, backgroundHeight;
    private int newGameX, newGameY, loadX, loadY, optionsX, optionsY, exitX, exitY;
    private int buttonWidth, buttonHeight;

    public MainScreen(Resources resources, Context context, int screenHeight, int screenWidth) {

        backgroundWidth = screenWidth;
        backgroundHeight = screenHeight;

        backgroundX = 0;
        backgroundY = 0;

        buttonWidth = (int) resources.getDimension(R.dimen.button_width)*3;
        buttonHeight = (int) resources.getDimension(R.dimen.button_height);

        newGameX = screenWidth - buttonWidth;
        newGameY = (int) resources.getDimension(R.dimen.button_height);

        loadX = screenWidth - buttonWidth;
        loadY = newGameY + buttonHeight + (int) resources.getDimension(R.dimen.margin);

        optionsX = screenWidth - buttonWidth;
        optionsY = loadY + buttonHeight + (int) resources.getDimension(R.dimen.margin);

        exitX = screenWidth - buttonWidth;
        exitY = optionsY + buttonHeight + (int) resources.getDimension(R.dimen.margin);

        background = new Background(backgroundWidth, backgroundHeight, backgroundX, backgroundY, BitmapFactory.decodeResource(resources, R.drawable.forest));

        newGame = new Button(buttonWidth, buttonHeight, newGameX, newGameY, BitmapFactory.decodeResource(resources, R.drawable.woodenbutton), MediaPlayer.create(context, R.raw.start));
        load = new Button(buttonWidth, buttonHeight, loadX, loadY, BitmapFactory.decodeResource(resources, R.drawable.woodenbutton), MediaPlayer.create(context, R.raw.start));
        options = new Button(buttonWidth, buttonHeight, optionsX, optionsY, BitmapFactory.decodeResource(resources, R.drawable.woodenbutton), MediaPlayer.create(context, R.raw.optionson));
        exit = new Button(buttonWidth, buttonHeight, exitX, exitY, BitmapFactory.decodeResource(resources, R.drawable.woodenbutton), MediaPlayer.create(context, R.raw.start));

        newGameText = new Letters("New game", newGameX, newGameY, resources);
        loadText = new Letters("Continue",loadX, loadY, resources);
        optionsText = new Letters("Options",optionsX, optionsY, resources);
        exitText = new Letters("Exit",exitX, exitY, resources);

        newGameText.centerText("New game", newGameX, buttonWidth, newGameY, buttonHeight);
        loadText.centerText("Continue", loadX, buttonWidth, loadY, buttonHeight);
        optionsText.centerText("Options", optionsX, buttonWidth, optionsY, buttonHeight);
        exitText.centerText("Exit", exitX, buttonWidth, exitY, buttonHeight);
    }

    public void draw(Canvas canvas) {
        background.draw(canvas);
        newGame.draw(canvas);
        newGameText.draw(canvas);
        load.draw(canvas);
        loadText.draw(canvas);
        options.draw(canvas);
        optionsText.draw(canvas);
        exit.draw(canvas);
        exitText.draw(canvas);
    }

    public boolean isnewGameClicked(int x, int y) {
        newGame.startSound();
        return newGame.isClicked(x,y);
    }

    public boolean isLoadClicked(int x, int y) {
        load.startSound();
        return load.isClicked(x,y);
    }

    public boolean isOptionsClicked(int x, int y) {
        options.startSound();
        return options.isClicked(x,y);
    }

    public boolean isExitClicked(int x, int y) {
        exit.startSound();
        return exit.isClicked(x,y);
    }
}
