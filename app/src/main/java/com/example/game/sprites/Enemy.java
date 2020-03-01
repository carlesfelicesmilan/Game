package com.example.game.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import java.util.Random;

import com.example.game.GameManager;
import com.example.game.GameManagerCallback;
import com.example.game.R;

import java.util.ArrayList;

public class Enemy implements Sprite {

    private int screenWidth, screenHeight;
    private Resources resources;
    private Bitmap coin;
    private int coinHeight, coinWidth;
    private int coinX, coinY;
    private boolean coinClicked = false;
    private GameManagerCallback callback;

    public Enemy(Resources resources, int screenHeight, int screenWidth, GameManager callback) {
        this.resources = resources;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.callback = callback;

        coinHeight = (int) resources.getDimension(R.dimen.coin_height);
        coinWidth = (int) resources.getDimension(R.dimen.coin_width);

        Bitmap coinBmp = BitmapFactory.decodeResource(resources, R.drawable.coin);
        coin = Bitmap.createScaledBitmap(coinBmp, coinHeight, coinWidth, false);

        Random randomWidth = new Random(System.currentTimeMillis());
        coinX = randomWidth.nextInt(screenWidth - coinWidth);

        Random randomHeight = new Random(System.currentTimeMillis());
        coinY = randomHeight.nextInt(screenHeight - coinHeight);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(coin, coinX, coinY, null);
    }

    @Override
    public void update() {
        if (coinClicked) {
            Random randomWidth = new Random(System.currentTimeMillis());
            coinX = randomWidth.nextInt(screenWidth - coinWidth);

            Random randomHeight = new Random(System.currentTimeMillis());
            coinY = randomHeight.nextInt(screenHeight - coinHeight);

            Rect coinPosition = new Rect(coinX, coinY, coinX + coinWidth, coinY + coinHeight);
            callback.updatePosition(coinPosition);
            coinClicked = false;
        }
    }

    public boolean isCoinClicked(int x, int y) {
        if (x >= coinX && x < (coinX + coinWidth) && y >= coinY && y < (coinY + coinHeight)) {
            coinClicked = true;
            return true;
        }
        else {
            return false;
        }
    }
}
