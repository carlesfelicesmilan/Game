package com.example.game.sprites.Battle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;
import com.example.game.sprites.Background.Background;
import com.example.game.sprites.Button.Button;
import com.example.game.sprites.Dictionary.Letters;
import com.example.game.sprites.Dictionary.Numbers;

public class BattleButtons {

    private Button attack, spell, item, run, defend, back;
    private boolean attackClicked, spellClicked, itemClicked, defendClicked, runClicked;
    private Background panel;
    private Letters name;
    private int  panelX, panelY, panelWidth, panelHeight;
    private int buttonWidth, buttonHeight;
    private int attackButtonX, attackButtonY, spellButtonX, spellButtonY, itemButtonX, itemButtonY, runButtonX, runButtonY, defendButtonX, defendButtonY;
    private int nameX, nameY;
    private int buttonClickedY;
    private int buttonSpeed;


    public BattleButtons(Resources resources, Context context, int screenHeight, int screenWidth) {
        buttonWidth = screenWidth/8;
        buttonHeight = screenHeight/6;

        panelWidth = screenWidth - buttonWidth*5;
        panelHeight = screenHeight/6;
        panelY = screenHeight - screenHeight/6;
        panelX = buttonWidth*5;

        attackButtonX = 0;
        attackButtonY = screenHeight - panelHeight;

        spellButtonX = buttonWidth;
        spellButtonY = screenHeight - panelHeight;

        itemButtonX = buttonWidth*2;
        itemButtonY = screenHeight - panelHeight;

        defendButtonX = buttonWidth*3;
        defendButtonY = screenHeight - panelHeight;

        runButtonX = buttonWidth*4;
        runButtonY = screenHeight - panelHeight;

        buttonClickedY = screenHeight - panelHeight - 30;

        nameX = panelX + panelWidth/4;
        nameY = panelY + panelHeight/4;

        attackClicked = false;
        spellClicked = false;
        itemClicked = false;
        defendClicked = false;
        runClicked = false;

        buttonSpeed = 5;

        attack = new Button(buttonWidth, buttonHeight, attackButtonX, attackButtonY, BitmapFactory.decodeResource(resources, R.drawable.attack), MediaPlayer.create(context, R.raw.clinc));
        spell = new Button(buttonWidth, buttonHeight, spellButtonX, spellButtonY, BitmapFactory.decodeResource(resources, R.drawable.spell), MediaPlayer.create(context, R.raw.clinc));
        item = new Button(buttonWidth, buttonHeight, itemButtonX, itemButtonY, BitmapFactory.decodeResource(resources, R.drawable.item), MediaPlayer.create(context, R.raw.clinc));
        defend = new Button(buttonWidth, buttonHeight, defendButtonX, defendButtonY, BitmapFactory.decodeResource(resources, R.drawable.defend), MediaPlayer.create(context, R.raw.clinc));
        run = new Button(buttonWidth, buttonHeight, runButtonX, runButtonY, BitmapFactory.decodeResource(resources, R.drawable.run), MediaPlayer.create(context, R.raw.clinc));

        panel = new Background(panelWidth, panelHeight, panelX, panelY, BitmapFactory.decodeResource(resources, R.drawable.panel2));

        name = new Letters(" ",  nameX, nameY, resources);

        back = new Button(buttonWidth, buttonHeight, attackButtonX, attackButtonY, BitmapFactory.decodeResource(resources, R.drawable.back), MediaPlayer.create(context, R.raw.clinc));

    }

    public void draw(Canvas canvas) {
        panel.draw(canvas);
        attack.draw(canvas);
        spell.draw(canvas);
        item.draw(canvas);
        defend.draw(canvas);
        run.draw(canvas);
        name.draw(canvas);
    }

    public void buttonClicked(int x, int y) {
        if(attack.isClicked(x,y)) {
                name.updateText("Attack");
                name.centerText("Attack", panelX, panelWidth, panelY, panelHeight);
                attackClicked = true;
                spellClicked = false;
                itemClicked = false;
                defendClicked = false;
                runClicked = false;
                attack.startSound();
        }
        else if(spell.isClicked(x,y)) {
            name.updateText("Spell");
            name.centerText("Spell", panelX, panelWidth, panelY, panelHeight);
            attackClicked = false;
            spellClicked = true;
            itemClicked = false;
            defendClicked = false;
            runClicked = false;
            spell.startSound();
        }
        else if(item.isClicked(x,y)) {
            name.updateText("Item");
            name.centerText("Item", panelX, panelWidth, panelY, panelHeight);
            attackClicked = false;
            spellClicked = false;
            itemClicked = true;
            defendClicked = false;
            runClicked = false;
            item.startSound();
        }
        else if(defend.isClicked(x,y)) {
            name.updateText("Defend");
            name.centerText("Defend", panelX, panelWidth, panelY, panelHeight);
            attackClicked = false;
            spellClicked = false;
            itemClicked = false;
            defendClicked = true;
            runClicked = false;
            defend.startSound();
        }
        else if(run.isClicked(x,y)) {
            name.updateText("Run");
            name.centerText("Run", panelX, panelWidth, panelY, panelHeight);
            attackClicked = false;
            spellClicked = false;
            itemClicked = false;
            defendClicked = false;
            runClicked = true;
            run.startSound();
        }
    }

    public void update() {
        if (attackClicked) {
            if(attack.getY() > buttonClickedY) {
                attack.setPosY(attack.getY() - buttonSpeed);
                spell.setPosY(spellButtonY);
                item.setPosY(itemButtonY);
                defend.setPosY(defendButtonY);
                run.setPosY(runButtonY);
            }
        }
        else if (spellClicked) {
            if(spell.getY() > buttonClickedY) {
                attack.setPosY(attackButtonY);
                spell.setPosY(spell.getY() - buttonSpeed);
                item.setPosY(itemButtonY);
                defend.setPosY(defendButtonY);
                run.setPosY(runButtonY);
            }
        }
        else if (itemClicked) {
            if(item.getY() > buttonClickedY) {
                attack.setPosY(attackButtonY);
                spell.setPosY(spellButtonY);
                item.setPosY(item.getY() - buttonSpeed);
                defend.setPosY(defendButtonY);
                run.setPosY(runButtonY);
            }
        }
        else if (defendClicked) {
            if(defend.getY() > buttonClickedY) {
                attack.setPosY(attackButtonY);
                spell.setPosY(spellButtonY);
                item.setPosY(itemButtonY);
                defend.setPosY(defend.getY() - buttonSpeed);
                run.setPosY(runButtonY);
            }
        }
        else if (runClicked) {
            if(run.getY() > buttonClickedY) {
                attack.setPosY(attackButtonY);
                spell.setPosY(spellButtonY);
                item.setPosY(itemButtonY);
                defend.setPosY(defendButtonY);
                run.setPosY(run.getY() - buttonSpeed);
            }
        }
    }

    public boolean isAttackClicked() {
        return attackClicked;
    }

    public boolean isSpellClicked() {
        return spellClicked;
    }

    public void setInitialState() {

        name.updateText(" ");

        attack.setPosY(attackButtonY);
        spell.setPosY(spellButtonY);
        item.setPosY(itemButtonY);
        defend.setPosY(defendButtonY);
        run.setPosY(run.getY());

        attackClicked = false;
        spellClicked = false;
        itemClicked = false;
        defendClicked = false;
        runClicked = false;
    }
}
