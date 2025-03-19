package com.monopoly.turn;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;
import com.monopoly.util.Dice;
import com.monopoly.board.Tile;

public class NormalTurnStrategy implements TurnStrategy {
    @Override
    public void executeTurn(GameEngine engine, Player player) {
        int oldPosition = player.getPosition();
        int[] diceResult = Dice.rollDice();
        int diceSum = diceResult[0] + diceResult[1];
        System.out.println(player.getName() + " tirou " 
                + diceResult[0] + ", " + diceResult[1] + " (Total: " + diceSum + ").");

        int newPosition = (oldPosition + diceSum) % 40;
        if (oldPosition + diceSum >= 40) {
            System.out.println(player.getName() + " passou pelo Go e recebeu $200.");
            player.addMoney(200);
        }
        player.setPosition(newPosition);
        Tile tile = engine.getBoard().getTile(newPosition);
        System.out.println(player.getName() + " avançou para " + (newPosition + 1) 
                + " - " + tile.getName() + ".");
        tile.landOn(player, engine);
    }
}
