package com.monopoly.cards.CommunityChestCards;

import com.monopoly.GameEngine;
import com.monopoly.cards.CommunityChestCard;
import com.monopoly.model.Player;

public class GetOuOfJailCommunityCard extends CommunityChestCard{

    public GetOuOfJailCommunityCard() {
        super(6, "Saia livre da prisão, sem pagar", "Esta carta pode ser mantida até o uso ou venda.");
    }
    
    @Override
    public void applyEffect(GameEngine engine, Player player){
        player.addGetOutOfJailCard(this);
        System.out.println("Você recebeu uma carta 'Saia livre da prisão'. Ela foi adicionada ao seu inventário.");
    }
}
