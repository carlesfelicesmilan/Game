package com.example.game.sprites.Living.Hero;

import android.content.SharedPreferences;

import com.example.game.sprites.Living.LivingThing;

public class Hero extends LivingThing {

    private static final String MONEY = "Money";
    private static final String EXPERIENCE = "Experience";

    public Hero(String name, int health, int attack, int defense, int money, int experience) {
        super(name, health, attack, defense, money, experience);
    }

    public void updateMoney(SharedPreferences prefs) {
        if (money > prefs.getInt(MONEY, money)) {
            prefs.edit().putInt(MONEY, money).apply();
        }
        else {
            money = prefs.getInt(MONEY, money);
        }
    }

    public void updateExp(SharedPreferences prefs) {
        if (experience > prefs.getInt(EXPERIENCE, experience)) {
            prefs.edit().putInt(EXPERIENCE, experience).apply();
        }
        else {
            experience = prefs.getInt(EXPERIENCE, experience);
        }
    }

    public void receiveMoney(int money) {
        this.money = this.money + money;
    }

    public void receiveExp(int experience) {
        this.experience = this.experience + experience;
    }
}
