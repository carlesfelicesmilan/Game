package com.example.game.sprites.Character;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.R;
import com.example.game.sprites.Numbers.Damage;
import com.example.game.sprites.Numbers.Hp;
import com.example.game.sprites.Numbers.Numbers;
import com.example.game.sprites.Sprite;

import java.util.Random;

public class Orc implements Sprite {

    private int width, height, posX, posY, speedX, finalX;
    private int hp, def, receivedDamage;
    private Bitmap gengar;
    private Bitmap terrexHit;
    private boolean hit, loaded;
    private Hp orcHp;
    private Damage orcDamage;

    public Orc(Resources resources, int screenHeight, int screenWidth) {

        width = (int) resources.getDimension(R.dimen.button_width);
        height = (int) resources.getDimension(R.dimen.button_height);
        finalX = screenWidth - width;

        posX = 0;
        posY = height * 2;
        speedX = 5;

        hp = 100;
        def = 2;

        hit = false;
        loaded = false;

        gengar = BitmapFactory.decodeResource(resources, R.drawable.gengar);
        gengar = Bitmap.createScaledBitmap(gengar, width, height, false);

        terrexHit = BitmapFactory.decodeResource(resources, R.drawable.gengar);
        terrexHit = Bitmap.createScaledBitmap(terrexHit, width, height, false);

        orcHp = new Hp(resources);
        orcHp.updateHp(hp);

        orcDamage = new Damage(resources);
        //orcDamage.setDamagePos(0, posY - height);

    }

    // We calculate the damage by atq - def + extra random damage
    // We also update the hp and the received Damage
    public void calculateDamage(int damage) {
        if (def < damage) {
            receivedDamage = damage - def;
            Random randomDamage = new Random(System.currentTimeMillis());
            int extraDamage = randomDamage.nextInt(receivedDamage);
            hp = hp - (receivedDamage + extraDamage);
            receivedDamage = receivedDamage + extraDamage;
            orcHp.updateHp(hp);
            orcDamage.updateDamage(receivedDamage);
        }
    }

    public boolean isHit(int evX, int evY, int damage) {
        if (evX >= posX && evX < (posX + width) && evY >= posY && evY < (posY + height)) {
            //tada, if this is true, you've started your click inside your bitmap
            calculateDamage(damage);
            hit = true;
            orcDamage.isHit(hit);
            return true;
        }
        else {
            hit = false;
            orcDamage.isHit(hit);
            return false;
        }
    }

    public void hit() {
        hit = true;
    }

    public int orcHp() {return hp;}

    public int damageReceived() {return receivedDamage;}

    public boolean isDead() {
        if (hp<0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(gengar, posX, posY, null);
        if (loaded) {
            orcHp.setHpPos(posX + width/4, posY - height / 2);
            orcHp.draw(canvas);
        }
        if(hit) {
            orcDamage.setDamagePos(posX + width/4, posY - height /2);
            orcDamage.draw(canvas);
            canvas.drawBitmap(gengar, posX, posY, null);
        }
        else {
            canvas.drawBitmap(gengar, posX, posY, null);
        }
    }

    @Override
    public void update() {
        orcDamage.update();
        if(posX <= finalX){
            posX+=speedX;
        }
        else {
            loaded = true;
        }
    }

    public boolean isloaded() {
        return loaded;
    }

}
