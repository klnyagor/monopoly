package com.monopoly.cards.ChanceCards;

import com.monopoly.GameEngine;
import com.monopoly.cards.ChanceCard;
import com.monopoly.model.Player;

public class PovertyTaxChanceCard extends ChanceCard{

    public PovertyTaxChanceCard(){
        super(10, "Imposto de Pobreza", "Pague imposto de pobreza de $15");
    }

    @Override
    public void applyEffect(GameEngine engine, Player player) {
        player.deductMoney(15);
        System.out.println(player.getName() + " pagou $15 de imposto de pobreza!");
    }
    
}
