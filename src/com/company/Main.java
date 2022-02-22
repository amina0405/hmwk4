package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        public static int bossHealth = 3000;
        public static int bossDamage = 50;
        public static String bossDefenceType;

        public static int[] heroesHealth = {260, 250, 270, 450, 600, 300, 280, 240};
        public static int[] heroesDamage = {20, 25, 10, 0, 15, 34, 40, 5};
        public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
        public static int roundNumber;

        public static void main(String[] args) {
            printStatiscics();
            while (!iaGameFinished()) {
                round();
            }
        }

        public static void chooseBossDefence() {
            Random random = new Random();
            int rundomIndex = random.nextInt(heroesAttackType.length);
            bossDefenceType = heroesAttackType[rundomIndex];
            System.out.println("Boss choose " + bossDefenceType);
        }


        public static void round() {
            roundNumber++;
            chooseBossDefence();
            bossHits();
            heroesHits();
            medic();
            golem();
            lucky();
            berserk();
            thor();
            printStatiscics();

        }

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

        public static void heroesHits() {
            for (int i = 0; i < heroesDamage.length; i++) {
                if (heroesHealth[i] > 0 && bossHealth > 0) {
                    if (bossDefenceType == heroesAttackType[i]) {
                        Random random = new Random();
                        int coeff = random.nextInt(12);
                        if (bossHealth - heroesDamage[i] * coeff < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i] * coeff;
                        }
                        System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                    } else {
                        if (bossHealth - heroesDamage[i] < 0) {
                            bossHealth = 0;
                        }
                    }
                }
            }
        }

        public static boolean iaGameFinished() {
            if (bossHealth <= 0) {
                System.out.println("Heroes won!!!");
                return true;
            }
       /* if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0){
            System.out.println("Boss won!!!");
            return true;
        }
         return false;*/
            boolean allHeroesDead = true;
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[i] > 0) {
                    allHeroesDead = false;
                    break;
                }
            }
            if (allHeroesDead) {
                System.out.println("Boss won !!!");
            }
            return allHeroesDead;
        }

        public static void printStatiscics() {
            System.out.println(roundNumber + " ROUND--------------");
            System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
            for (int i = 0; i < heroesHealth.length; i++) {
                System.out.println(heroesAttackType[i] + " health " + heroesHealth[i] + " (" + heroesDamage[i] + ")");
            }
        }

        public static void medic() {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 3) {
                    continue;
                }
                if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                    Random random = new Random();
                    int med = random.nextInt(100);
                    heroesHealth[i] = med + heroesHealth[i];
                    System.out.println("Medic cured " + heroesHealth[i] + med);
                    break;
                }
            }
        }

        public static void golem() {
            int getDamage = bossDamage / 5;
            for (int i = 0; i < heroesHealth.length; i++) {
                if (heroesHealth[4] > 0 && heroesHealth[i] > 0 && heroesHealth[4] != heroesHealth[i]) {
                    heroesHealth[i] += getDamage;
                    heroesHealth[4] -= getDamage;
                }
                System.out.println("Golem get 1/5 damage for "  + heroesAttackType[i]);
            }

        }

        public static void lucky() {
            Random random = new Random();
            boolean wer = random.nextBoolean();
            if (heroesHealth[5] > 0 && wer == true) {
                heroesHealth[5] += 34;
                System.out.println("Lucky Dodged damage " + wer);
            }
        }

        public static void berserk() {
            int berserksDamage = bossDamage / 2;
            if (heroesHealth[6] > 0) {
                heroesDamage[6] = heroesDamage[6] + berserksDamage;
                System.out.println("Berserk attacks " + berserksDamage);
            }
        }
        public static void thor (){
            Random random = new Random();
            boolean thorAttack =random.nextBoolean();
            if (heroesHealth[7] > 0 && thorAttack == true) {
                bossDamage = 0;
            }else {
                bossDamage = 50;
            }
            System.out.println("Thor stuned the boss" + thorAttack);
        }
    }
