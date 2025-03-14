package com.monopoly.board;

import com.monopoly.model.Player;
import com.monopoly.GameEngine;

public class TaxTile extends Tile {
    private int taxAmount;

    public TaxTile(int position, String name, int taxAmount) {
        super(position, name);
        this.taxAmount = taxAmount;
    }

    @Override
    public void landOn(Player player, GameEngine engine) {
        System.out.println("Você caiu em " + (position + 1) + " - " + name + ". Taxa: $" + taxAmount + ".");
        player.deductMoney(taxAmount);
        System.out.println(player.getName() + " pagou $" + taxAmount + " de taxa.");
        if (player.getMoney() < 0) {
            System.out.println("O jogador " + player.getName() + " entrou em falência!");
            engine.setBankrupt(player);
        }
    }
}
