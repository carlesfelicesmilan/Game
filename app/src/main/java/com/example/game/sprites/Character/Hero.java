package com.example.game.sprites.Character;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.R;
import com.example.game.sprites.Sprite;

public class Hero implements Sprite {

    private int level, totalCoins;
    private Numbers HeroHpValue, numberOfPotions;
    private int width, height, posX, posY, posDamageY, potions;
    private int hp, def, receivedDamage, atq;
    private Bitmap terrexBase;
    private Bitmap terrexHit;
    private Bitmap potionRed;
    private boolean hit;

    public Hero(Resources resources, int screenHeight, int screenWidth) {
        width = (int) resources.getDimension(R.dimen.button_width);
        height = (int) resources.getDimension(R.dimen.button_height);
        posX = screenWidth / 2 - width / 2;
        posY = screenHeight - height * 2;
        hp = 100;
        atq = 7;

        terrexBase = BitmapFactory.decodeResource(resources, R.drawable.terrex);
        terrexBase = Bitmap.createScaledBitmap(terrexBase, width, height, false);

        terrexHit = BitmapFactory.decodeResource(resources, R.drawable.terrexhit3);
        terrexHit = Bitmap.createScaledBitmap(terrexHit, width, height, false);

        potionRed = BitmapFactory.decodeResource(resources, R.drawable.redpotion);
        potionRed = Bitmap.createScaledBitmap(potionRed, width, height, false);

        HeroHpValue = new Numbers(resources, screenHeight, screenWidth);
        HeroHpValue.updateHp(hp);

        HeroHpValue.setHpPos(posX + width / 2, posY - height / 2);
        HeroHpValue.setDamagePos(0, posY - height);

        potions = 3;
        numberOfPotions = new Numbers(resources, screenHeight, screenWidth);
        numberOfPotions.updateHp(potions);
        numberOfPotions.setHpPos(posX + width, posY + height);
    }

    public void draw(Canvas canvas) {
        HeroHpValue.drawHp(canvas);
        if(hit) {
            HeroHpValue.drawDamage(canvas);
            canvas.drawBitmap(terrexHit, posX, posY, null);
        }
        else {
            canvas.drawBitmap(terrexBase, posX, posY, null);
        }

        if(potions > 0) {
            numberOfPotions.drawHp(canvas);
            canvas.drawBitmap(potionRed, posX, posY + height, null);
        }
    }

    @Override
    public void update() {

    }

    public boolean isDead() {
        if (hp<0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int atq() {
        return atq;
    }

    public void usePotion() {
        potions--;
        hp+=50;
        HeroHpValue.updateHp(hp);
        numberOfPotions.updateHp(potions);
    }

    public boolean isPotionUsed(int evX, int evY) {
        if (evX >= posX && evX < (posX + width) && evY >= posY + height && evY < (posY + height*2)) {
            //tada, if this is true, you've started your click inside your bitmap
            return true;
        }
        else {
            return false;
        }
    }
}
