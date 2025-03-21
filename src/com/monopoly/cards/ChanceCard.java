package com.monopoly.cards;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;

public abstract class ChanceCard extends Card {

    public ChanceCard(int number, String name, String description) {
        super(number, name, description);
    }
    
    @Override
    public void applyEffect(GameEngine engine, Player player) {
            System.out.println("Nenhuma ação definida para esta carta.");
    }
}
