package com.example.game.sprites.buttons;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import com.example.game.R;

public class StartButton extends Button {

    public StartButton(Resources resources, Context context, int screenHeight, int screenWidth) {

        width = (int) resources.getDimension(R.dimen.button_width)*2;
        height = (int) resources.getDimension(R.dimen.button_height);
        posX = screenWidth / 2 - width / 2;
        posY = screenHeight / 2 - height / 2;

        button = BitmapFactory.decodeResource(resources, R.drawable.start);
        button = Bitmap.createScaledBitmap(button, width, height, false);

        sound = MediaPlayer.create(context, R.raw.start);
    }

}
