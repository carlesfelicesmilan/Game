package com.example.game.sprites.Character;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.R;
import com.example.game.sprites.Numbers.Hp;
import com.example.game.sprites.Numbers.Numbers;
import com.example.game.sprites.Sprite;

public class Hero implements Sprite {

    private int level, totalCoins;
    private Hp HeroHp;
    private Hp numberOfPotions;
    private int width, height, posX, posY, speedX;
    private int potions;
    private int hp, def, receivedDamage, atq;
    private Bitmap charizard;
    private Bitmap terrexHit;
    private Bitmap potionRed;
    private boolean hit;
    private boolean loaded;

    public Hero(Resources resources, int screenHeight, int screenWidth) {
        width = (int) resources.getDimension(R.dimen.button_width);
        height = (int) resources.getDimension(R.dimen.button_height);
        posX = screenWidth - width;
        posY = screenHeight - height * 2;
        speedX = -5;

        hp = 100;
        atq = 7;

        charizard = BitmapFactory.decodeResource(resources, R.drawable.charizardback);
        charizard = Bitmap.createScaledBitmap(charizard, width, height, false);

        terrexHit = BitmapFactory.decodeResource(resources, R.drawable.terrexhit3);
        terrexHit = Bitmap.createScaledBitmap(terrexHit, width, height, false);

        potionRed = BitmapFactory.decodeResource(resources, R.drawable.redpotion);
        potionRed = Bitmap.createScaledBitmap(potionRed, width, height, false);

        //HeroHp = new Hp(resources);
        HeroHp = new Hp(resources);
        HeroHp.updateHp(hp);

        potions = 3;
        numberOfPotions = new Hp(resources);
        //numberOfPotions.scaleNumbers(width/2, height/2);
        numberOfPotions.updateHp(potions);

        loaded = false;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(charizard, posX, posY, null);
        if(loaded) {
            HeroHp.setHpPos(posX + width/2, posY - height / 2);
            HeroHp.draw(canvas);
            numberOfPotions.setHpPos(posX + width, posY + height);
            if(hit) {
                canvas.drawBitmap(terrexHit, posX, posY, null);
            }
            else {
                canvas.drawBitmap(charizard, posX, posY, null);
            }

            if(potions > 0) {
                numberOfPotions.draw(canvas);
                canvas.drawBitmap(potionRed, posX, posY + height, null);
            }
        }
    }

    @Override
    public void update() {
        if(posX > 0){
            posX+=speedX;
        }
        else {
            loaded = true;
        }
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
        HeroHp.updateHp(hp);
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

    public boolean isloaded() {
        return loaded;
    }
}
