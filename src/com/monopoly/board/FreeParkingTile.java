package com.monopoly.board;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;

public class FreeParkingTile extends Tile {
    public FreeParkingTile(int position, String name) {
        super(position, name);
    }

    @Override
    public void landOn(Player player, GameEngine engine) {
        System.out.println("Você caiu em " + (position + 1) + " - " + name + ". Nada aconteceu.");
    }
    
    @Override
    public String getTitleInfo(GameEngine engine) {
        // como FreeParkingTile não é um título ele vai retornar uma string vazia
        return "";
    }
}
