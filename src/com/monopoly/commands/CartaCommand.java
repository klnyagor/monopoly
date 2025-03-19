package com.monopoly.commands;

import com.monopoly.GameEngine;
import com.monopoly.cards.Card;
import com.monopoly.model.Player;

public class CartaCommand implements ICommand {
    @Override
    public void execute(GameEngine engine) {
        Player player = engine.getCurrentPlayer();
        if (player.isInPrison()) {
            if (!player.getGetOutOfJailCards().isEmpty()) {
                // Remove uma carta e a utiliza para sair da prisão
                Card card = player.getGetOutOfJailCards().remove(0);
                player.setInPrison(false);
                player.resetPrisonTurnCount();
                System.out.println("Você usou uma carta 'Get Out of Jail Free' e saiu da prisão.");
            } else {
                System.out.println("Você não possui carta de saída da prisão.");
            }
        } else {
            System.out.println("Você não está na prisão.");
        }
    }
}
