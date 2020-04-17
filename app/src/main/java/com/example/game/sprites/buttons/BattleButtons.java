package com.example.game.sprites.buttons;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import com.example.game.R;

public class BattleButtons {

    private Bitmap panel;
    private Button attack, spell, item, run;
    private Button attackPressed;
    private int panelWidth, panelHeight, panelX, panelY;
    private int buttonWidth, buttonHeight;
    private boolean clicked;
    private MediaPlayer sound;

    public BattleButtons(Resources resources, Context context, int screenHeight, int screenWidth) {

        panelWidth = screenWidth;
        panelHeight = (int) resources.getDimension(R.dimen.button_width)*2;
        panelX = 0;
        panelY = screenHeight - panelHeight;

        panel = BitmapFactory.decodeResource(resources, R.drawable.panel2);
        panel = Bitmap.createScaledBitmap(panel, panelWidth, panelHeight, false);

        buttonWidth = panelWidth/2 - 30;
        buttonHeight = panelHeight/2 - 30;

        attack = new Button();
        attack.button = BitmapFactory.decodeResource(resources, R.drawable.attack);
        attack.setSize(buttonWidth, buttonHeight);
        attack.setPosition(panelX + 15,panelY +30);

        attackPressed = new Button();
        attackPressed.button = BitmapFactory.decodeResource(resources, R.drawable.attackpressed);
        attackPressed.setSize(buttonWidth, buttonHeight);
        attackPressed.setPosition(panelX + 15,panelY +30);

        spell = new Button();
        spell.button = BitmapFactory.decodeResource(resources, R.drawable.pokemon);
        spell.setSize(buttonWidth, buttonHeight);
        spell.setPosition( panelX + buttonWidth + 30,panelY +25);

        item = new Button();
        item.button = BitmapFactory.decodeResource(resources, R.drawable.bag);
        item.setSize(buttonWidth, buttonHeight);
        item.setPosition(panelX + 15,panelY + buttonHeight +30);

        run = new Button();
        run.button = BitmapFactory.decodeResource(resources, R.drawable.run);
        run.setSize(buttonWidth, buttonHeight);
        run.setPosition(panelX + buttonWidth + 30,panelY + buttonHeight +30);

        clicked = false;

        sound = MediaPlayer.create(context, R.raw.clinc);


    }

    public void drawPanel(Canvas canvas) {
        canvas.drawBitmap(panel, panelX, panelY, null);
    }

    public void drawAttackButton(Canvas canvas){
        if(!clicked) {
            attack.draw(canvas);
        }
        else {
            attackPressed.draw(canvas);
        }
    }

    public void drawSpellButton(Canvas canvas){
        spell.draw(canvas);
    }

    public void drawItemButton(Canvas canvas){
        item.draw(canvas);
    }

    public void drawRunButton(Canvas canvas){
        run.draw(canvas);
    }


    public int getPanelY(){
        return panelY;
    }

    public int getSize() {
        return panelWidth;
    }

    public boolean isAtttackClicked(int evX, int evY) {
        if (evX >= panelX + 15 && evX < (panelX + 15 + buttonWidth)
                && evY >= panelY +30 && evY < (panelY +30 + buttonHeight)) {
            //tada, if this is true, you've started your click inside your bitmap
            clicked = true;
            sound.start();
            return true;
        }
        else {
            clicked = false;
            return false;
        }
    }

    public boolean isSpellClicked(int evX, int evY) {
        if (evX >= panelX + buttonWidth + 30 && evX < (panelX + buttonWidth + 30 + buttonWidth)
                && evY >= panelY +25 && evY < (panelY +25 + buttonHeight)) {
            //tada, if this is true, you've started your click inside your bitmap
            //clicked = true;
            sound.start();
            return true;
        }
        else {
            //clicked = false;
            return false;
        }
    }
}
