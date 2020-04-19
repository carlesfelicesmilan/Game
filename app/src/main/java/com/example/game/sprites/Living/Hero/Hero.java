package com.example.game.sprites.Living.Hero;

import android.content.SharedPreferences;

import com.example.game.sprites.Living.LivingThing;

public class Hero extends LivingThing {

    private int actualExp;
    private int actualMoney;
    public Hero(String name, int health, int attack, int defense, int money, int exp) {
        super(name, health, attack, defense, money, exp);
        this.actualExp = exp;
        this.actualMoney = money;
    }

    public void updateExp(int experience) {
        this.actualExp = this.actualExp + experience;
    }

    public void updateMoney(int money) {
        this.actualMoney = this.actualMoney + money;
    }

}
