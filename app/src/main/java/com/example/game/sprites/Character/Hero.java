package com.example.game.sprites.Character;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.R;
import com.example.game.sprites.Numbers.Hp;
import com.example.game.sprites.Spells.Fire;
import com.example.game.sprites.Sprite;
import com.example.game.sprites.Weapons.Weapon;
import com.example.game.sprites.Weapons.WoodSword;

public class Hero implements Sprite {

    private int level, totalCoins;
    private Hp heroHp;
    private Hp numberOfPotions;
    private int width, height, posX, posY, speedX;
    private int potions;
    private int hp, def, receivedDamage, atq, str;
    private Bitmap charizard;
    private Bitmap terrexHit;
    private Bitmap potionRed;
    private boolean hit;
    private boolean loaded;
    private WoodSword woodenSword;
    private Fire fire;

    public Hero(Resources resources, Context context, int screenHeight, int screenWidth) {
        width = (int) resources.getDimension(R.dimen.hero_size);
        height = (int) resources.getDimension(R.dimen.hero_size);
        posX = screenWidth - width;
        posY = screenHeight - height * 2;
        speedX = -5;

        hp = 100;
        str = 7;


        charizard = BitmapFactory.decodeResource(resources, R.drawable.charizardback);
        charizard = Bitmap.createScaledBitmap(charizard, width, height, false);

        terrexHit = BitmapFactory.decodeResource(resources, R.drawable.terrexhit3);
        terrexHit = Bitmap.createScaledBitmap(terrexHit, width, height, false);

        potionRed = BitmapFactory.decodeResource(resources, R.drawable.redpotion);
        potionRed = Bitmap.createScaledBitmap(potionRed, width, height, false);

        //HeroHp = new Hp(resources);
        heroHp = new Hp(resources);
        heroHp.updateHp(hp);

        potions = 3;
        numberOfPotions = new Hp(resources);
        //numberOfPotions.scaleNumbers(width/2, height/2);
        numberOfPotions.updateHp(potions);

        woodenSword = new WoodSword(resources, context);
        atq = 7 + woodenSword.getWeaponAtq();

        fire = new Fire(resources, context);

        loaded = false;
    }

    public void draw(Canvas canvas) {
    canvas.drawBitmap(charizard, posX, posY, null);
        if(potions > 0) {
            numberOfPotions.draw(canvas);
            canvas.drawBitmap(potionRed, posX, posY + height, null);
        }
    }

    public void drawSpell(Canvas canvas) {
        fire.draw(canvas);
    }

    public void spellSound() {
        fire.sound();
    }

    public void setSpellPosition(int x, int y) {
        fire.setPosition(x,y);
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
        heroHp.updateHp(hp);
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

    public void isHit() {
        hp-=3;
        heroHp.updateHp(hp);
    }

    public void weaponAttackSound() {
        woodenSword.sound();
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
        return charizard;
    }

    public int getHp() {
        return hp;
    }

    public int getAtq() {
        return atq;
    }

}
