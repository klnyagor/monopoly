package com.monopoly.cards;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;

public class CommunityChestCard extends Card {

    public CommunityChestCard(int number, String name, String description) {
        super(number, name, description);
    }
    
    @Override
    public void applyEffect(GameEngine engine, Player player) {
        if (this.number == 6) {
            player.addGetOutOfJailCard(this);
            System.out.println("Você recebeu uma carta 'Saia livre da prisão'. Ela foi adicionada ao seu inventário.");
        } else if (this.number == 7) {
            System.out.println("Você deve ir para a prisão.");
            player.setPosition(engine.getJailPosition());
            player.setInPrison(true);
            player.resetPrisonTurnCount();
        } else {
            System.out.println("Nenhuma ação definida para esta carta.");
        }
    }
}
