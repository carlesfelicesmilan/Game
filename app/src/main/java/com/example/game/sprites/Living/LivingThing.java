package com.example.game.sprites.Living;

public class LivingThing{
    public int health, actualHealth, attack, defense, money, experience;
    public String name;
    public boolean dead;

    public LivingThing(String name, int health, int attack, int defense, int money, int experience) {
        this.name = name;
        this.health = health;
        this.actualHealth = health;
        this.attack = attack;
        this.defense = defense;
        this.money = money;
        this.experience = experience;
        this.dead = false;
    }

    // Calculate the damage adding a random value, we also check if the living thing is dead
    public int takeDamage(int damage) {
        if (this.defense < damage) {
            damage = damage - this.defense;
            this.actualHealth = this.actualHealth - damage;
            if (this.actualHealth <= 0) {
                this.dead = true;
            }
        }
        return damage;
    }

    // Apply the actual points of heal, we can't heal more than the maximum health
    public void heal(int healthPoints) {
        this.actualHealth += healthPoints;
        if (this.actualHealth>=health) {
            this.actualHealth = this.health;
        }
    }

    public boolean isDead() {
        return this.dead;
    }

    public void takeFire() {
        takeDamage(30);
    }

    public int getAtq() {return attack;}

    public int getDef() {return defense;}

    public int getHp() {
        return actualHealth;
    }

    public int getExp() {
        return experience;
    }

    public int getMoney() {
        return money;
    }
}

