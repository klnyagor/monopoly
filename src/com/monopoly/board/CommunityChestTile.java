package com.monopoly.board;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;
import com.monopoly.cards.CommunityChestCard;
import java.util.Scanner;

public class CommunityChestTile extends Tile {
    
    public CommunityChestTile(int position, String name) {
        super(position, name);
    }
    
    @Override
    public void landOn(Player player, GameEngine engine) {
        System.out.println("Você caiu em " + (position + 1) + " - " + name + ".");
        CommunityChestCard card = engine.drawCommunityChestCard();
        System.out.println("Carta sorteada: " + card.getName() + " - " + card.getDescription());
        System.out.println("Pressione qualquer tecla para continuar...");
        new Scanner(System.in).nextLine();
        card.applyEffect(engine, player);
    }
    
    @Override
    public String getTitleInfo(GameEngine engine) {
        return "";
    }
}
