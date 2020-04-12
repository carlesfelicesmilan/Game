package com.example.game.sprites.Character;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.R;

public class Hero extends Human {

    private int level, totalCoins;
    public Hero(Resources resources, int screenHeight, int screenWidth) {
        width = (int) resources.getDimension(R.dimen.button_width);
        height = (int) resources.getDimension(R.dimen.button_height);
        posX = screenWidth / 2 - width / 2;
        posY = screenHeight - height * 2;
        hp = 100;
        atq = 7;

        human = BitmapFactory.decodeResource(resources, R.drawable.terrex);
        human = Bitmap.createScaledBitmap(human, width, height, false);

        human1 = BitmapFactory.decodeResource(resources, R.drawable.terrexhit1);
        human1 = Bitmap.createScaledBitmap(human1, width, height, false);

        human2 = BitmapFactory.decodeResource(resources, R.drawable.terrexhit2);
        human2 = Bitmap.createScaledBitmap(human2, width, height, false);

        human3 = BitmapFactory.decodeResource(resources, R.drawable.terrexhit3);
        human3 = Bitmap.createScaledBitmap(human3, width, height, false);
    }

}
