package com.monopoly.turn;

import com.monopoly.GameEngine;
import com.monopoly.board.Tile;
import com.monopoly.model.Player;
import com.monopoly.util.Dice;

public class JailTurnStrategy implements TurnStrategy {
    @Override
    public void executeTurn(GameEngine engine, Player player) {
        int[] diceResult = Dice.rollDice();
        int diceSum = diceResult[0] + diceResult[1];
        System.out.println(player.getName() + " (na prisão) tirou " 
                + diceResult[0] + " e " + diceResult[1] + ".");

        if (diceResult[0] == diceResult[1]) {
            System.out.println("Você tirou números iguais e saiu da prisão!");
            player.setInPrison(false);
            player.resetPrisonTurnCount();
            // quando sair movimenta o peão normalmente
            int newPosition = (player.getPosition() + diceSum) % 40;
            if (player.getPosition() + diceSum >= 40) {
                System.out.println(player.getName() + " passou pelo Go e recebeu $200.");
                player.addMoney(200);
            }
            player.setPosition(newPosition);
            Tile tile = engine.getBoard().getTile(newPosition);
            System.out.println(player.getName() + " avançou para " 
                    + (newPosition + 1) + " - " + tile.getName() + ".");
            tile.landOn(player, engine);
        } else {
            player.incrementPrisonTurnCount();
            if (player.getPrisonTurnCount() >= 3) {
                System.out.println("Tentativa 3 sem sair da prisão. Você é obrigado a pagar $50.");
                player.deductMoney(50);
                player.setInPrison(false);
                player.resetPrisonTurnCount();
                int newPosition = (player.getPosition() + diceSum) % 40;
                if (player.getPosition() + diceSum >= 40) {
                    System.out.println(player.getName() + " passou pelo Go e recebeu $200.");
                    player.addMoney(200);
                }
                player.setPosition(newPosition);
                Tile tile = engine.getBoard().getTile(newPosition);
                System.out.println(player.getName() + " avançou para " 
                        + (newPosition + 1) + " - " + tile.getName() + ".");
                tile.landOn(player, engine);
            } else {
                System.out.println("Você não conseguiu sair da prisão nesta tentativa.");
                //a vez termina sem movimentação
            }
        }
    }
}
