package com.example.game.sprites.Character;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.R;
import com.example.game.sprites.Numbers.Damage;
import com.example.game.sprites.Numbers.Hp;
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

        width = (int) resources.getDimension(R.dimen.hero_size);
        height = (int) resources.getDimension(R.dimen.hero_size);
        finalX = screenWidth - width;

        //posX = 0;
        //posY = height * 2;
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

        //orcDamage = new Damage(resources);
    }

    // We calculate the damage by atq - def + extra random damage
    // We also update the hp and the received Damage
    public int calculateDamage(int damage) {
        if (def < damage) {
            receivedDamage = damage - def;
            Random randomDamage = new Random(System.currentTimeMillis());
            int extraDamage = randomDamage.nextInt(receivedDamage);
            hp = hp - (receivedDamage + extraDamage);
            receivedDamage = receivedDamage + extraDamage;
        }
        return receivedDamage;
    }

    public boolean isHit(int evX, int evY) {
        if (evX >= posX && evX < (posX + width) && evY >= posY && evY < (posY + height)) {
            //tada, if this is true, you've started your click inside your bitmap
            hit = true;
            return true;
        }
        else {
            hit = false;
            return false;
        }
    }

    public boolean isHit() {
        return hit;
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

    //Size is the same for width and Height
    public int getSize() {
        return width;
    }

    public void setPosition(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Bitmap getBitmap() {
        return gengar;
    }

    public int getHp() {
        return hp;
    }

}
