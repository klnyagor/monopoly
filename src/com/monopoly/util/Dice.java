package com.monopoly.util;

import java.util.Random;

public class Dice {
    private static Random random = new Random();

    public static int[] rollDice() {
        int die1 = random.nextInt(6) + 1;
        int die2 = random.nextInt(6) + 1;
        return new int[]{die1, die2};
    }
}
