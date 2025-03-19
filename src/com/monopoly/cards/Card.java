package com.monopoly.cards;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;

public abstract class Card {
    protected int number;
    protected String name;
    protected String description;
    
    public Card(int number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    // Cada carta aplica seu efeito específico
    public abstract void applyEffect(GameEngine engine, Player player);
}
