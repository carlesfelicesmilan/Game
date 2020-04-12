package com.example.game.sprites.Character;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import com.example.game.R;

public class Orc extends Human {

    public Orc(Resources resources, Context context, int screenHeight, int screenWidth) {

        width = (int) resources.getDimension(R.dimen.button_width);
        height = (int) resources.getDimension(R.dimen.button_height);
        posX = screenWidth / 2 - width / 2;
        posY = screenHeight / 2 - height / 2;
        hp = 100;
        def = 2;

        human = BitmapFactory.decodeResource(resources, R.drawable.terrex);
        human = Bitmap.createScaledBitmap(human, width, height, false);

        human1 = BitmapFactory.decodeResource(resources, R.drawable.terrexhit1);
        human1 = Bitmap.createScaledBitmap(human1, width, height, false);

        human2 = BitmapFactory.decodeResource(resources, R.drawable.terrexhit2);
        human2 = Bitmap.createScaledBitmap(human2, width, height, false);

        human3 = BitmapFactory.decodeResource(resources, R.drawable.terrexhit3);
        human3 = Bitmap.createScaledBitmap(human3, width, height, false);

        sound = MediaPlayer.create(context, R.raw.monster);
        damaged = MediaPlayer.create(context, R.raw.sword);
    }
}
