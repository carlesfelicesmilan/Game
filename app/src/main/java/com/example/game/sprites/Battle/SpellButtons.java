package com.example.game.sprites.Battle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;
import com.example.game.sprites.Background.Background;
import com.example.game.sprites.Button.Button;
import com.example.game.sprites.Spells.Spell;

public class SpellButtons {

    private Background spellBackground;
    private Button spells;
    private int posX, posY, width, height;
    private int spellX, spellY, spellWidth, spellHeight;
    boolean spellSelected;

    public SpellButtons(int width, int height, int posX, int posY, Resources resources, Context context) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;

        spellX = posX + 100;
        spellY = posY + 100;
        spellWidth = (int) resources.getDimension(R.dimen.button_height)*2;
        spellHeight = (int) resources.getDimension(R.dimen.button_height)*2;

        spellBackground = new Background(width, height, posX, posY, BitmapFactory.decodeResource(resources, R.drawable.panel2));
        spells = new Button(spellWidth, spellHeight, spellX,spellY, BitmapFactory.decodeResource(resources, R.drawable.enric),MediaPlayer.create(context, R.raw.clinc));

        spellSelected = false;
    }

    public void draw(Canvas canvas) {
        spellBackground.draw(canvas);
        spells.draw(canvas);
    }

    public void spellSelection(int x, int y) {
        if(spells.isClicked(x,y)) {
            spellSelected = true;
            spells.startSound();
        }
    }

    public boolean isSpellSelected() {
        return spellSelected;
    }

    public void setInitialState() {
        spellSelected = false;
    }
}
