package com.monopoly.board;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;

public class GoTile extends Tile {
    public GoTile(int position, String name) {
        super(position, name);
    }

    @Override
    public void landOn(Player player, GameEngine engine) {
        System.out.println("Você caiu em " + (position + 1) + " - " + name + ". Recebe $200.");
        player.addMoney(200);
    }
    
    @Override
    public String getTitleInfo(GameEngine engine) {
        // como GoTile não é um título ele vai retornar uma string vazia
        return "";
    }
}
