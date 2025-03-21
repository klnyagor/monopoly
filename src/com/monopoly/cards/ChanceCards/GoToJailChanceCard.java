package com.monopoly.cards.ChanceCards;

import com.monopoly.GameEngine;
import com.monopoly.cards.ChanceCard;
import com.monopoly.model.Player;

public class GoToJailChanceCard extends ChanceCard {

    public GoToJailChanceCard() {
        super(8, "Vá diretamente para a Prisão", "Não passe pelo ponto de partida, não receba $200.");
    }

    @Override
    public void applyEffect(GameEngine engine, Player player){
        System.out.println("Você deve ir diretamente para a prisão.");
        player.setPosition(engine.getJailPosition());
        player.setInPrison(true);
        player.resetPrisonTurnCount();
    }
    
}
