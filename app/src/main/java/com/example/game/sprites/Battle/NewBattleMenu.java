package com.example.game.sprites.Battle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;
import com.example.game.sprites.Background.Background;
import com.example.game.sprites.Background.NewBackground;
import com.example.game.sprites.Character.Hero;
import com.example.game.sprites.Character.newHero;
import com.example.game.sprites.Enemies.Dragon;
import com.example.game.sprites.Enemies.Orc;
import com.example.game.sprites.NewButton.NewButton;
import com.example.game.sprites.Numbers.Damage;
import com.example.game.sprites.Numbers.Hp;
import com.example.game.sprites.Numbers.NewNumbers;
import com.example.game.sprites.Spells.NewSpell;

public class NewBattleMenu {
    /*
    private int turns;
    private Orc enemy;
    private Hero hero;
    private Hp heroHp, enemyHp;
    private Damage enemyDamage;
    private Background background;
    public boolean loaded;
    private int damage;

*/

    private NewButton attack, spell, item, run;
    private NewButton attackClicked, spellClicked, itemClicked, runClicked;
    private NewButton heroButton, enemyButton;
    private NewBackground field, panel;

    private BattleState battleState;
    private MediaPlayer battleMusic;

    private int fieldX, fieldY, fieldWidth, fieldHeight;
    private int panelX, panelY, panelWidth, panelHeight;
    private int buttonWidth, buttonHeight;
    private int attackButtonX, attackButtonY, spellButtonX, spellButtonY, itemButtonX, itemButtonY, runButtonX, runButtonY;
    private int heroX, heroY, heroWidth, heroHeight, enemyX, enemyY, enemyWidth, enemyHeight;
    private int heroHpX, heroHpY, heroHpWidth, heroHpHeight;
    private int enemyDamageTakenX, enemyDamageTakenY, enemyDamageTakenXWidth, enemyDamageTakenXHeight;

    private int spellHeroX, spellHeroY, spellHeroWidth, spellHeroHeight;

    private boolean isLoaded;

    private Dragon gengar;

    private NewNumbers heroHp;
    private NewNumbers EnemyHp;
    private NewNumbers enemyDamageTaken;

    private NewSpell fire;

    private int menuSpeed;
    private int spellSpeed;

    private boolean hit;


    public NewBattleMenu(Resources resources, Context context, int screenHeight, int screenWidth) {

        battleState = BattleState.INITIAL;

        fieldX = 0;
        fieldY = 0;
        fieldWidth = screenWidth;
        fieldHeight = screenHeight - screenHeight/4;

        panelWidth = screenWidth;
        panelHeight = screenHeight/4;
        panelX = 0;
        panelY = screenHeight - panelHeight;

        heroWidth = screenWidth/3;
        heroHeight = screenWidth/3;
        heroX = screenWidth - heroWidth;
        heroY = panelY - heroHeight;

        enemyWidth = screenWidth/3;
        enemyHeight = screenWidth/3;
        enemyX = 0;
        enemyY = screenHeight/6;

        buttonWidth = panelWidth/2 - 30;
        buttonHeight = panelHeight/2 - 30;

        attackButtonX = 15;
        attackButtonY = panelY + 30;

        spellButtonX = panelWidth/2+10;
        spellButtonY = panelY+30;

        itemButtonX = 15;
        itemButtonY = panelY+buttonHeight+30;

        runButtonX = panelWidth/2+10;
        runButtonY = panelY+buttonHeight+30;

        heroHpX = 0;
        heroHpY = screenHeight - panelHeight - heroHeight - 70;
        heroHpHeight = heroWidth/4;
        heroHpWidth = heroHeight/4;

        enemyDamageTakenX = screenWidth - enemyWidth/2;
        enemyDamageTakenY = enemyY - 70;

        spellHeroWidth = heroWidth;
        spellHeroHeight = heroWidth;

        field = new NewBackground(fieldWidth,fieldHeight,fieldX,fieldY, BitmapFactory.decodeResource(resources, R.drawable.background2));

        heroButton = new NewButton(heroWidth, heroHeight, heroX, heroY, BitmapFactory.decodeResource(resources, R.drawable.charizardback), MediaPlayer.create(context, R.raw.clinc));
        enemyButton = new NewButton(enemyWidth, enemyHeight, enemyX, enemyY, BitmapFactory.decodeResource(resources, R.drawable.gengar), MediaPlayer.create(context, R.raw.hit));

        panel = new NewBackground(panelWidth,panelHeight,panelX,panelY, BitmapFactory.decodeResource(resources, R.drawable.panel2));

        attack = new NewButton(buttonWidth, buttonHeight, attackButtonX, attackButtonY, BitmapFactory.decodeResource(resources, R.drawable.attack), MediaPlayer.create(context, R.raw.clinc));
        spell = new NewButton(buttonWidth, buttonHeight, spellButtonX, spellButtonY, BitmapFactory.decodeResource(resources, R.drawable.pokemon), MediaPlayer.create(context, R.raw.clinc));
        item = new NewButton(buttonWidth, buttonHeight, itemButtonX, itemButtonY, BitmapFactory.decodeResource(resources, R.drawable.bag), MediaPlayer.create(context, R.raw.clinc));
        run = new NewButton(buttonWidth, buttonHeight, runButtonX, runButtonY, BitmapFactory.decodeResource(resources, R.drawable.run), MediaPlayer.create(context, R.raw.clinc));

        attackClicked = new NewButton(buttonWidth, buttonHeight, attackButtonX, attackButtonY, BitmapFactory.decodeResource(resources, R.drawable.attackpressed), MediaPlayer.create(context, R.raw.clinc));

        heroHp = new NewNumbers(heroHpWidth, heroHpHeight, heroHpX, heroHpY, resources);
        enemyDamageTaken = new NewNumbers(heroHpWidth, heroHpHeight, enemyDamageTakenX, enemyDamageTakenY, resources);

        fire = new NewSpell(spellHeroWidth,spellHeroHeight, spellHeroX, spellHeroY, BitmapFactory.decodeResource(resources, R.drawable.nancy),MediaPlayer.create(context, R.raw.flame));

        menuSpeed = 3;
        spellSpeed = 4;

        isLoaded = false;
        hit = false;

        battleMusic = MediaPlayer.create(context, R.raw.battle2);

    }

    public void draw(Canvas canvas) {
        battleMusic.start();
        panel.draw(canvas);
        heroButton.draw(canvas);
        enemyButton.draw(canvas);
        if(isLoaded) {
            switch(battleState) {
                case INITIAL:
                    field.draw(canvas);
                    attack.draw(canvas);
                    spell.draw(canvas);
                    item.draw(canvas);
                    run.draw(canvas);
                    heroHp.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    break;
                case ATTACK:
                    field.draw(canvas);
                    attackClicked.draw(canvas);
                    spell.draw(canvas);
                    item.draw(canvas);
                    run.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    break;
                case SPELL:
                    field.draw(canvas);
                    attack.draw(canvas);
                    spell.draw(canvas);
                    item.draw(canvas);
                    run.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    break;
                case BAG:
                    field.draw(canvas);
                    attack.draw(canvas);
                    spell.draw(canvas);
                    item.draw(canvas);
                    run.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    break;
                case RUN:
                    field.draw(canvas);
                    attack.draw(canvas);
                    spell.draw(canvas);
                    item.draw(canvas);
                    run.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    break;
                case ENEMYHIT:
                    field.draw(canvas);
                    attack.draw(canvas);
                    spell.draw(canvas);
                    item.draw(canvas);
                    run.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    break;
                case ENEMYFIRE:
                    field.draw(canvas);
                    attack.draw(canvas);
                    spell.draw(canvas);
                    item.draw(canvas);
                    run.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    fire.draw(canvas);
                    break;
                case ENEMYDAMAGED:
                    field.draw(canvas);
                    attack.draw(canvas);
                    spell.draw(canvas);
                    item.draw(canvas);
                    run.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    enemyDamageTaken.draw(canvas);
                    break;
            }
        }
    }

    public void isButtonPressed(int x, int y) {
        if(battleState == BattleState.INITIAL) {
            if(attack.isClicked(x,y)) {
                battleState = BattleState.ATTACK;
                attack.startSound();
            }
            else if (spell.isClicked(x,y)) {
                spell.startSound();
                battleState = BattleState.SPELL;
            }
            else if (item.isClicked(x,y)) {
                item.startSound();
                battleState = BattleState.BAG;
            }
            else if(run.isClicked(x,y)) {
                run.startSound();
                battleState = BattleState.RUN;
            }
        }
        else if(battleState == BattleState.ATTACK) {
            if(enemyButton.isClicked(x,y)) {
                enemyButton.startSound();
                hit = true;
                battleState = battleState.ENEMYHIT;
            }
            else {
                battleState = battleState.INITIAL;
            }
           // battleState = BattleState.INITIAL;
        }
        else if(battleState == BattleState.SPELL) {
            if(enemyButton.isClicked(x,y)) {
                spellHeroX = heroX + heroWidth/2;
                spellHeroY = heroY - heroHeight/2;
                hit = true;
                battleState = battleState.ENEMYFIRE;
            }
            else {
                battleState = battleState.INITIAL;
            }
           // battleState = BattleState.INITIAL;
        }
        else if(battleState == BattleState.BAG) {
            battleState = BattleState.INITIAL;
        }
        else if(battleState == BattleState.RUN) {
            battleState = BattleState.INITIAL;
        }
    }

    public void update(newHero hero, Dragon dragon) {
        if((heroX > 0) && (enemyX < panelWidth - enemyWidth)){
            heroX-=menuSpeed;
            enemyX+=menuSpeed;
            enemyButton.setPosition(enemyX,enemyY);
            heroButton.setPosition(heroX,heroY);
        }
        else {
            isLoaded = true;
            heroHp.updateNumber(hero.actualHealth);
        }
        if(battleState == battleState.ENEMYHIT) {
            dragon.takeDamage(hero.attack);
            enemyDamageTaken.updateNumber(dragon.takeDamage(hero.attack));
            hero.takeDamage(dragon.attack);
            heroHp.updateNumber(hero.actualHealth);
            battleState = BattleState.ENEMYDAMAGED;
        }
        if(battleState == battleState.ENEMYFIRE) {
            if((spellHeroX < enemyX) && (spellHeroY > 0)) {
                spellHeroX+=spellSpeed;
                spellHeroY-=spellSpeed;
                fire.setPosition(spellHeroX, spellHeroY);
                fire.startSound();
            }
            else {
                enemyButton.startSound();
                dragon.takeFire();
                enemyDamageTaken.updateNumber(30);
                battleState = BattleState.ENEMYDAMAGED;
            }
        }
        if(battleState == battleState.ENEMYDAMAGED) {
            if (hit) {
                enemyDamageTakenX = enemyX + enemyWidth/3;
                enemyDamageTakenY = enemyY - 40;
                hit = false;
            }
            if((enemyDamageTakenX < panelWidth) && (enemyDamageTakenY > 0)) {
                enemyDamageTakenX += spellSpeed;
                enemyDamageTakenY -= spellSpeed;
                enemyDamageTaken.setPosition(enemyDamageTakenX, enemyDamageTakenY);
            }
            else {
                battleState = BattleState.INITIAL;
                }
        }
    }
}