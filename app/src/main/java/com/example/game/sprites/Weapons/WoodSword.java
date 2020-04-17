package com.example.game.sprites.Weapons;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import com.example.game.R;

public class WoodSword extends Weapon {

    public WoodSword(Resources resources, Context context) {
        atq = 5;
        name = "Wooden Sword";
        weaponAsset = BitmapFactory.decodeResource(resources,R.drawable.woodsword);
        weaponSound = MediaPlayer.create(context,R.raw.sword);
    }
}
