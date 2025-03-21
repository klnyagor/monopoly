package com.monopoly.board;

import com.monopoly.GameEngine;
import com.monopoly.cards.ChanceCard;
import com.monopoly.model.Player;
import java.util.Scanner;

public class ChanceTile extends Tile {
    
    public ChanceTile(int position, String name) {
        super(position, name);
    }
    
    @Override
    public void landOn(Player player, GameEngine engine) {
        System.out.println("Você caiu em " + (position + 1) + " - " + name + ".");
        ChanceCard card = engine.drawChanceCard();
        System.out.println("Carta sorteada: " + card.getName() + " - " + card.getDescription());
        card.applyEffect(engine, player);
        System.out.println("Pressione qualquer tecla para continuar...");
        new Scanner(System.in).nextLine();
        // card.applyEffect(engine, player);
    }
    
    @Override
    public String getTitleInfo(GameEngine engine) {
        return "";
    }
}
