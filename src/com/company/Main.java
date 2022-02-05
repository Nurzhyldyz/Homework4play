package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";

    public static int[] heroesHealth = {270, 260, 250, 220, 300, 200, 150, 202};
    public static int[] heroesDamage = {15, 20, 25, 0, 5, 18, 20, 30};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic ", "Golem", "Lucky", "Berserk", "Thor"};
    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void Medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 3) {
                continue;
            }
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                heroesHealth[i] += 20;
            }
            System.out.println(" Medic healed " + heroesAttackType[i]);
            break;
        }
    }

    public static void Lucky() {
        Random random = new Random();
        boolean lol = random.nextBoolean();
        if (heroesHealth[5] > 0 && lol) {
            heroesHealth[5] += bossDamage - bossDamage / 5;
            System.out.println("Lucky dodged damage: " + lol);
        }
    }

    public static void BersekSkill() {
        for (int Dop = 0; Dop < heroesHealth.length; Dop++) {
            if (heroesHealth[6] > 0) {
                heroesHealth[6] += bossDamage / 5 * 2;
                bossHealth -= bossDamage / 5 * 2;
                break;
            }
        }
    }

    public static void Thor() {
        Random random = new Random();
        boolean th = random.nextBoolean();
        for (int F = 0; F < heroesHealth.length; F++) {
            if (heroesHealth[7] > 0) {
                if (th) {
                    bossDamage = 0;
                    System.out.println("Thor suppressed the Boss" + th);
                    break;
                }
            } else {
                bossDamage = 50;
                break;
            }
        }
    }

    public static void GolemSkill() {
        int D = bossDamage / 5;
        int howAliveHerro = 0;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 4) {
                continue;
            } else if (heroesHealth[i] > 0) {
                howAliveHerro++;
                heroesHealth[i] += D;
            }
            heroesHealth[4] -= D * howAliveHerro;
            System.out.println(" Golem took damage: " + D * howAliveHerro);
            break;
        }
    }



    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }


    public static void round() {
        round_number++;
        Medic();
        changeBossDefence();
        bossHits();
        heroesHit();
        Lucky();
        Thor();
        BersekSkill();
        GolemSkill();
        printStatistics();
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2;
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static int toHeal;

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(round_number + " ROUND _______________");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
        System.out.println("_______________");
    }
}