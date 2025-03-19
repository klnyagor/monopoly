package com.monopoly.cards;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;

public class ChanceCard extends Card {

    public ChanceCard(int number, String name, String description) {
        super(number, name, description);
    }
    
    @Override
    public void applyEffect(GameEngine engine, Player player) {
        if (this.number == 8) {
            System.out.println("Você deve ir diretamente para a prisão.");
            player.setPosition(engine.getJailPosition());
            player.setInPrison(true);
            player.resetPrisonTurnCount();
        } else {
            System.out.println("Nenhuma ação definida para esta carta.");
        }
    }
}
