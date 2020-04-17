package com.example.game.sprites.Spells;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;

public class Spell {
    int atq;
    String name;
    int spellWidth, spellHeight, spellX, spellY;
    Bitmap spellAsset;
    MediaPlayer spellSound;

    public int getSpellAtq() {
        return atq;
    }

    public void setSpellAtq(int atq) {
        this.atq = atq;
    }

    public String getWeaponName() {
        return name;
    }

    public void setSpellName(String name) {
        this.name = name;
    }

    public void setPosition(int x, int y) {
        this.spellX=x;
        this.spellY=y;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(spellAsset, spellX, spellY, null);
    }

    public void sound() {
        spellSound.start();
    }

    public void setSpellSize(int x, int y) {
        spellAsset = Bitmap.createScaledBitmap(spellAsset, x, y, false);
    }
}
