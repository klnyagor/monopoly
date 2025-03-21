package com.monopoly.cards;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;

public abstract class CommunityChestCard extends Card {

    public CommunityChestCard(int number, String name, String description) {
        super(number, name, description);
    }
    
    @Override
    public void applyEffect(GameEngine engine, Player player) {
        
            System.out.println("Nenhuma ação definida para esta carta.");

    }
}
