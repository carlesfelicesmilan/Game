package com.example.game.sprites.buttons;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.game.R;
import com.example.game.sprites.buttons.Button;

public class ExitButton extends Button {

    public ExitButton(Resources resources, int screenHeight, int screenWidth) {

        width = (int) resources.getDimension(R.dimen.button_width);
        height = (int) resources.getDimension(R.dimen.button_height);
        posX = screenWidth / 2 - width / 2;
        posY = screenHeight  - height * 2;

        button = BitmapFactory.decodeResource(resources, R.drawable.exit);
        button = Bitmap.createScaledBitmap(button, width, height, false);
    }

}
