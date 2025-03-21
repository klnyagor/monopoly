package com.monopoly.cards.CommunityChestCards;

import com.monopoly.GameEngine;
import com.monopoly.cards.CommunityChestCard;
import com.monopoly.model.Player;

public class GotoJailCommunityCard extends CommunityChestCard{
    

    public GotoJailCommunityCard() {
            super(7, "Vá para a prisão", "Vá diretamente para a prisão - Não passe pelo ponto de partida - Não receba $200.");
        }
    
    @Override
    public void applyEffect(GameEngine engine, Player player){
        System.out.println("Você deve ir para a prisão.");
        player.setPosition(engine.getJailPosition());
        player.setInPrison(true);
        player.resetPrisonTurnCount();
    }
}
