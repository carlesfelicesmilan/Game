package com.example.game.sprites.Weapons;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;

public class Weapon {
    int atq;
    String name;
    Bitmap weaponAsset;
    MediaPlayer weaponSound;

    public int getWeaponAtq() {
        return atq;
    }

    public void setWeaponAtq(int atq) {
        this.atq = atq;
    }

    public String getWeaponName() {
        return name;
    }

    public void setWeaponName(String name) {
        this.name = name;
    }

    public void sound() {
        weaponSound.start();
    }

}
