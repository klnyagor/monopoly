package com.monopoly.board;

import com.monopoly.model.Player;
import com.monopoly.GameEngine;

public class FreeParkingTile extends Tile {
    public FreeParkingTile(int position, String name) {
        super(position, name);
    }

    @Override
    public void landOn(Player player, GameEngine engine) {
        System.out.println("Você caiu em " + (position + 1) + " - " + name + ". Nada aconteceu.");
    }
}
