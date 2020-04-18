package com.example.game.sprites.Battle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;
import com.example.game.sprites.Background.Background;
import com.example.game.sprites.Character.Hero;
import com.example.game.sprites.Enemies.Orc;
import com.example.game.sprites.Numbers.Damage;
import com.example.game.sprites.Numbers.Hp;
import com.example.game.sprites.buttons.BattleButtons;

public class BattleMenu {
    private int turns;
    private Orc enemy;
    private Hero hero;
    private Hp heroHp, enemyHp;
    private Damage enemyDamage;
    private BattleButtons battlebuttons;
    private Background background;
    public boolean loaded;
    private int heroX, heroY, enemyX, enemyFinalX, enemyY, attackX, attackY;
    private int damage;
    private double menuSpeed;
    private BattleState battleState = BattleState.INITIAL;
    private MediaPlayer battleMusic;

    public BattleMenu(Resources resources, Context context, int screenHeight, int screenWidth) {
        enemy = new Orc(resources, screenHeight, screenWidth);
        hero = new Hero(resources, context, screenHeight, screenWidth);
        background = new Background(resources, screenHeight, screenWidth);
        battlebuttons = new BattleButtons(resources, context, screenHeight, screenWidth);

        heroX = screenWidth - hero.getSize();
        heroY = battlebuttons.getPanelY() - hero.getSize() ;

        heroHp = new Hp(resources);
        heroHp.updateHp(hero.getHp());
        heroHp.setSize(hero.getSize()/4, hero.getSize()/4);
        heroHp.setPosition(0, heroY - hero.getSize()/4);

        enemyX = 0;
        enemyY = screenHeight/6;
        enemyFinalX = screenWidth - enemy.getSize();

        enemyHp = new Hp(resources);
        enemyHp.updateHp(enemy.getHp());
        enemyHp.setSize(enemy.getSize()/4, enemy.getSize()/4);
        enemyHp.setPosition(enemyFinalX + enemy.getSize()/2, enemyY - enemy.getSize()/5);

        enemyDamage = new Damage(resources);
        enemyDamage.setSize(enemy.getSize()/4, enemy.getSize()/4);
        enemyDamage.setDamagePos(enemyFinalX + enemy.getSize()/2, enemyY - enemy.getSize()/5);

        background.setSize(screenWidth, battlebuttons.getPanelY());

        menuSpeed = 3;

        loaded = false;

        battleMusic = MediaPlayer.create(context, R.raw.battle2);
    }

    //If the screen is loaded we initilize the positions of all elements
    public void loadingScreen() {
        if((heroX > 0) && (enemyX < enemyFinalX)){
            heroX-=menuSpeed;
            enemyX+=menuSpeed;
            enemy.setPosition(enemyX,enemyY);
            hero.setPosition(heroX,heroY);
        }
        else {
            loaded = true;
        }
    }

    public void draw(Canvas canvas) {
        battleMusic.start();
        battlebuttons.drawPanel(canvas);
        if (loaded){
            background.draw(canvas);
            battlebuttons.drawAttackButton(canvas);
            battlebuttons.drawItemButton(canvas);
            battlebuttons.drawSpellButton(canvas);
            battlebuttons.drawRunButton(canvas);
            switch(battleState) {
                case INITIAL:
                    heroHp.draw(canvas);
                    break;
                case ATTACK:
                    break;
                case ENEMYHIT:
                    heroHp.draw(canvas);
                    enemyDamage.draw(canvas);
                    //enemyHp.drawEnemyHp(canvas);
                    break;
                case SPELL:
                    hero.drawSpell(canvas);
                    spellAnimation();
                    break;
            }
        }
        canvas.drawBitmap(hero.getBitmap(), heroX, heroY, null);
        enemy.draw(canvas);
    }

    public void update() {
        loadingScreen();
        enemyDamage.update();
    }

    public void buttonClicked(int x, int y) {
        if(battlebuttons.isAtttackClicked(x,y)) {
            battleState = BattleState.ATTACK;
        }
        else if(enemy.isHit(x,y) && (battleState == BattleState.ATTACK)) {
            battleState = BattleState.ENEMYHIT;
            damage = enemy.calculateDamage(hero.getAtq());
            enemyDamage.updateDamage(damage);
            enemyDamage.isHit(true);
            enemyHp.updateHp(enemy.getHp()-damage);
            hero.weaponAttackSound();
        }
        else if(battlebuttons.isSpellClicked(x,y)) {
            hero.spellSound();
            battleState = BattleState.SPELL;
        }
        else {
            battleState = BattleState.INITIAL;
        }
    }

    public void spellAnimation(){
        int spellX = heroX;
        int spellY = heroY;
        if(heroX < enemyX) {
            spellX+=menuSpeed;
            spellY+=menuSpeed;
            hero.setSpellPosition(spellX, spellY);
        }
    }

}
