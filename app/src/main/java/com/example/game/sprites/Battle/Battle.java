package com.example.game.sprites.Battle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;
import com.example.game.sprites.Background.Background;
import com.example.game.sprites.Button.Button;
import com.example.game.sprites.Living.Hero.Hero;
import com.example.game.sprites.Living.Enemies.Enemy;
import com.example.game.sprites.Dictionary.Numbers;
import com.example.game.sprites.Spells.Spell;

public class Battle {

    private Button heroButton, enemyButton;
    private Background field;
    private BattleState battleState;
    private MediaPlayer battleMusic;
    private Enemy enemy;
    private Hero hero;

    private int fieldX, fieldY, fieldWidth, fieldHeight;
    private int panelX, panelY, panelWidth, panelHeight;
    private int buttonWidth;
    private int heroX, heroY, heroWidth, heroHeight, enemyX, enemyY, enemyWidth, enemyHeight;
    private int heroHpX, heroHpY, heroHpWidth, heroHpHeight;
    private int enemyDamageTakenX, enemyDamageTakenY;

    private int spellHeroX, spellHeroY, spellHeroWidth, spellHeroHeight;

    private boolean isLoaded;

    private Numbers heroHp;
    private Numbers enemyDamageTaken;

    private BattleButtons battleButtons;
    private SpellButtons spellButtons;

    private Spell fire;

    private int menuSpeed;
    private int spellSpeed;

    private boolean hit;


    public Battle(Resources resources, Context context, int screenHeight, int screenWidth, Hero hero) {

        battleState = BattleState.HEROTURN;

        battleButtons = new BattleButtons(resources, context, screenHeight, screenWidth);

        fieldX = 0;
        fieldY = 0;
        fieldWidth = screenWidth;
        fieldHeight = screenHeight - screenHeight/6;

        heroWidth = screenHeight/4;
        heroHeight = screenHeight/4;
        heroX = screenWidth - heroWidth;
        heroY = screenHeight - screenHeight/6 - heroHeight;

        enemyWidth = screenHeight/4;
        enemyHeight = screenHeight/4;
        enemyX = 0;
        enemyY = screenHeight/8;

        buttonWidth = screenWidth/8;

        heroHpX = 0;
        heroHpY = screenHeight - screenHeight/6 - heroHeight - 70;
        heroHpHeight = heroWidth/4;
        heroHpWidth = heroHeight/4;

        enemyDamageTakenX = screenWidth - enemyWidth/2;
        enemyDamageTakenY = enemyY - 70;

        spellHeroWidth = heroWidth;
        spellHeroHeight = heroWidth;

        field = new Background(fieldWidth, fieldHeight, fieldX, fieldY, BitmapFactory.decodeResource(resources, R.drawable.background2));

        heroButton = new Button(heroWidth, heroHeight, heroX, heroY, BitmapFactory.decodeResource(resources, R.drawable.charizardback), MediaPlayer.create(context, R.raw.clinc));
        enemyButton = new Button(enemyWidth, enemyHeight, enemyX, enemyY, BitmapFactory.decodeResource(resources, R.drawable.gengar), MediaPlayer.create(context, R.raw.imbecil));

        heroHp = new Numbers(heroHpWidth, heroHpHeight, heroHpX, heroHpY, resources);
        enemyDamageTaken = new Numbers(heroHpWidth, heroHpHeight, enemyDamageTakenX, enemyDamageTakenY, resources);

        fire = new Spell(spellHeroWidth,spellHeroHeight, spellHeroX, spellHeroY, BitmapFactory.decodeResource(resources, R.drawable.enric),MediaPlayer.create(context, R.raw.flame));

        spellButtons = new SpellButtons(fieldWidth, fieldHeight - 30, fieldX, fieldY, resources, context);

        menuSpeed = 7;
        spellSpeed = 4;

        isLoaded = false;
        hit = false;

        battleMusic = MediaPlayer.create(context, R.raw.battle2);

        enemy = new Enemy("Dragon", 150, 20, 5, 30, 20);
        this.hero = hero;
    }

    public void draw(Canvas canvas) {
        battleMusic.start();
        heroButton.draw(canvas);
        enemyButton.draw(canvas);
        if(isLoaded) {
            switch(battleState) {
                case HEROTURN:
                    field.draw(canvas);
                    heroHp.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    battleButtons.draw(canvas);
                    if(battleButtons.isSpellClicked() && !spellButtons.isSpellSelected()){
                        spellButtons.draw(canvas);
                    }
                    break;
                case ATTACK:
                    field.draw(canvas);
                    battleButtons.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    break;
                case SPELL:
                    field.draw(canvas);
                    battleButtons.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    break;
                case BAG:
                    field.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    break;
                case RUN:
                    field.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    break;
                case ENEMYHIT:
                    field.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    enemyDamageTaken.draw(canvas);
                    break;
                case ENEMYFIRE:
                    field.draw(canvas);
                    heroButton.draw(canvas);
                    enemyButton.draw(canvas);
                    fire.draw(canvas);
                    break;
            }
        }
    }

    public void isButtonPressed(int x, int y) {
        if(battleState == BattleState.HEROTURN) {
            battleButtons.buttonClicked(x,y);
            if(battleButtons.isAttackClicked() && enemyButton.isClicked(x,y)) {
                enemy.takeDamage(hero.attack);
                enemyDamageTaken.updateNumber(enemy.takeDamage(hero.attack));
                hero.takeDamage(enemy.attack);
                heroHp.updateNumber(hero.actualHealth);
                enemyDamageTakenX = enemyX + enemyWidth/3;
                enemyDamageTakenY = enemyY - 40;
                enemyButton.startSound();
                battleState = BattleState.ENEMYHIT;
            }
            if(battleButtons.isSpellClicked()) {
                spellButtons.spellSelection(x,y);
                if(spellButtons.isSpellSelected() && enemyButton.isClicked(x,y)) {
                    spellHeroX = heroX + heroWidth;
                    spellHeroY = heroY;
                    hit = true;
                    battleState = battleState.ENEMYFIRE;
                }
            }

        }

                   /* if(attack.isClicked(x,y)) {
                battleState = BattleState.ATTACK;

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
                battleState = battleState.HEROTURN;
            }
        }
        else if(battleState == BattleState.SPELL) {
            if(enemyButton.isClicked(x,y)) {
                spellHeroX = heroX + heroWidth;
                spellHeroY = heroY;
                hit = true;
                battleState = battleState.ENEMYFIRE;
            }
            else {
                battleState = battleState.HEROTURN;
            }
        }
        else if(battleState == BattleState.BAG) {
            battleState = BattleState.HEROTURN;
        }
        else if(battleState == BattleState.RUN) {
            battleState = BattleState.HEROTURN;
        }*/
    }

    public void update() {
        if((heroX > 0) && (enemyX < fieldWidth - enemyWidth)){
            heroX-=menuSpeed;
            enemyX+=menuSpeed;
            enemyButton.setPosition(enemyX,enemyY);
            heroButton.setPosition(heroX,heroY);
        }
        else {
            isLoaded = true;
            heroHp.updateNumber(hero.actualHealth);
        }
        if(battleState == battleState.HEROTURN) {
            battleButtons.update();
        }
        if(battleState == battleState.ENEMYHIT) {
            if((enemyDamageTakenX < fieldWidth) && (enemyDamageTakenY > 0)) {
                enemyDamageTakenX += spellSpeed;
                enemyDamageTakenY -= spellSpeed;
                enemyDamageTaken.setPosition(enemyDamageTakenX, enemyDamageTakenY);
            }
            else {
                battleButtons.setInitialState();
                spellButtons.setInitialState();
                battleState = BattleState.HEROTURN;
            }
        }
        if(battleState == battleState.ENEMYFIRE) {
            if((spellHeroX < enemyX) && (spellHeroY > enemyY)) {
                spellHeroX+=spellSpeed*3;
                spellHeroY-=spellSpeed;
                fire.setPosition(spellHeroX, spellHeroY);
                fire.startSound();
            }
            else {
                enemyButton.startSound();
                enemy.takeFire();
                enemyDamageTaken.updateNumber(30);
                battleState = BattleState.ENEMYHIT;
            }
        }
      /*  if(battleState == battleState.ENEMYDAMAGED) {
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
                battleState = BattleState.HEROTURN;
                }
        }*/
        if(enemy.isDead()) {
            battleMusic.stop();
            battleState = BattleState.WIN;
        }
        if(hero.isDead()) {
            battleMusic.stop();
            battleState = BattleState.GAME_OVER;
        }
    }

    public String getBattleState() {
        return battleState.toString();
    }

    public int getMoney() {
        return enemy.getMoney();
    }

    public int getExp() {
        return enemy.getExp();
    }
}