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
    
    // Método que retorna o tipo do título (titleType)
    public TitleType getTitleType() {
        return TitleType.NONE; // por padrao vai ser nenhum
    }
    
    // Método para obter o dono da tile
    public Player getOwner() {
        return null; // o padrao sera null pq tem tiler que não sao titulos
    }
}
