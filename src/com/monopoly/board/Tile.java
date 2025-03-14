package com.monopoly.board;

import com.monopoly.model.Player;
import com.monopoly.GameEngine;

public abstract class Tile {
    protected int position;
    protected String name;

    public Tile(int position, String name) {
        this.position = position;
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    // Método abstrato que processa a ação ao se cair na casa.
    public abstract void landOn(Player player, GameEngine engine);
}
