package com.example.game.sprites.Spells;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import com.example.game.R;

public class Fire extends Spell {
    public Fire(Resources resources, Context context) {
        atq = 5;
        name = "Wooden Sword";
        spellWidth = (int) resources.getDimension(R.dimen.button_width);
        spellHeight = (int) resources.getDimension(R.dimen.button_height);
        spellAsset = BitmapFactory.decodeResource(resources, R.drawable.woodsword2);
        spellAsset = Bitmap.createScaledBitmap(spellAsset, spellWidth, spellHeight, false);
        spellSound = MediaPlayer.create(context,R.raw.sword);
    }
}
