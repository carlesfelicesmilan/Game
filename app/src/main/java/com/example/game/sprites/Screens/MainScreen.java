package com.example.game.sprites.Screens;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;
import com.example.game.sprites.Button.Button;

public class MainScreen {
    private Button options;
    private Button start;
    private Button exit;
    private int optionsX, optionsY, startX, startY, exitX, exitY;
    private int width, height;

    public MainScreen(Resources resources, Context context, int screenHeight, int screenWidth) {

        width = (int) resources.getDimension(R.dimen.button_width);
        height = (int) resources.getDimension(R.dimen.button_height);

        optionsX = screenWidth / 2 - width / 2;
        optionsY = screenHeight / 6 - height / 2;

        startX = screenWidth / 2 - width;
        startY = screenHeight / 2 - height / 2;

        exitX = screenWidth / 2 - width / 2;
        exitY = screenHeight  - height * 2;

        options = new Button(width, height, optionsX, optionsY, BitmapFactory.decodeResource(resources, R.drawable.option), MediaPlayer.create(context, R.raw.optionson));
        start = new Button(width*2, height, startX, startY, BitmapFactory.decodeResource(resources, R.drawable.start), MediaPlayer.create(context, R.raw.start));
        exit = new Button(width, height, exitX, exitY, BitmapFactory.decodeResource(resources, R.drawable.exit), MediaPlayer.create(context, R.raw.start));

    }

    public void draw(Canvas canvas) {
        options.draw(canvas);
        start.draw(canvas);
        exit.draw(canvas);
    }

    public boolean isStartClicked(int x, int y) {
        return start.isClicked(x,y);
    }

    public boolean isOptionsClicked(int x, int y) {
        return options.isClicked(x,y);
    }

    public boolean isExitClicked(int x, int y) {
        return exit.isClicked(x,y);
    }
}
