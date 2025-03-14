package com.monopoly.commands;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;
import com.monopoly.util.Dice;
import com.monopoly.board.Tile;

public class JogarCommand implements ICommand {
    @Override
    public void execute(GameEngine engine) {
        Player currentPlayer = engine.getCurrentPlayer();
        int oldPosition = currentPlayer.getPosition();
        int[] diceResult = Dice.rollDice();
        int diceSum = diceResult[0] + diceResult[1];
        System.out.println(currentPlayer.getName() + " tirou " + diceResult[0] + ", " + diceResult[1] + " (Total: " + diceSum + ").");

        int newPosition = (oldPosition + diceSum) % 40;
        // Se passou pelo Go de novo, recebe $200.
        if (oldPosition + diceSum >= 40) {
            System.out.println(currentPlayer.getName() + " passou pelo Go e recebeu $200.");
            currentPlayer.addMoney(200);
        }
        currentPlayer.setPosition(newPosition);
        Tile tile = engine.getBoard().getTile(newPosition);
        System.out.println(currentPlayer.getName() + " avançou para " + (newPosition + 1) + " - " + tile.getName() + ".");
        tile.landOn(currentPlayer, engine);
    }
}
