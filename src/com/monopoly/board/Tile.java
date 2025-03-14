package com.monopoly.board;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;

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

    // Método para processar a ação ao se cair na casa
    public abstract void landOn(Player player, GameEngine engine);
    
    // Método para retornar informações do título (para status)
    public abstract String getTitleInfo(GameEngine engine); //ver com o professor se essa solução atende a melhora da verificação dos títulos ou se seria mais interessante criar uma interface ou classe abstrata só para os títulos
}
