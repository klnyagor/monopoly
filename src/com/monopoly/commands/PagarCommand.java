package com.monopoly.commands;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;

public class PagarCommand implements ICommand {
    @Override
    public void execute(GameEngine engine) {
        Player player = engine.getCurrentPlayer();
        if (player.isInPrison()) {
            if (player.getMoney() >= 50) {
                player.deductMoney(50);
                player.setInPrison(false);
                player.resetPrisonTurnCount();
                System.out.println("Você pagou $50 e saiu da prisão.");
            } else {
                System.out.println("Você não possui dinheiro suficiente para pagar $50.");
            }
        } else {
            System.out.println("Você não está na prisão.");
        }
    }
}
