package com.monopoly.commands;

import com.monopoly.GameEngine;
import com.monopoly.board.PropertyTile;
import com.monopoly.board.RailroadTile;
import com.monopoly.board.Tile;
import com.monopoly.model.Player;

public class StatusCommand implements ICommand {
    @Override
    public void execute(GameEngine engine) {
        Player currentPlayer = engine.getCurrentPlayer();
        System.out.println("O status de " + currentPlayer.getName() + " – " +
            currentPlayer.getPawnColor().toString().toLowerCase() + " é o seguinte:");
        int pos = currentPlayer.getPosition();
        Tile currentTile = engine.getBoard().getTile(pos);
        System.out.println("Situado na posição " + (pos + 1) + " – " + currentTile.getName());
        System.out.println("Possui $" + currentPlayer.getMoney());
        System.out.println("Títulos:");
        boolean hasTitles = false;
        // Percorre o tabuleiro para identificar os títulos que o jogador possui. ao invés do for, colocar dentro de jogador as propriedades dos títulos e chamar aqui dentro do status
        for (Tile tile : engine.getBoard().getTiles()) {
            if (tile instanceof PropertyTile) {
                PropertyTile pt = (PropertyTile) tile;
                if (pt.getOwner() != null && pt.getOwner().equals(currentPlayer)) {
                    hasTitles = true;
                    System.out.println("[" + pt.getName() + "] – propriedade " + pt.getGroup() + ", aluguel " + pt.getRent());
                }
            } else if (tile instanceof RailroadTile) {
                RailroadTile rt = (RailroadTile) tile;
                if (rt.getOwner() != null && rt.getOwner().equals(currentPlayer)) {
                    hasTitles = true;
                    int currentRent = rt.getCurrentRent(engine);
                    System.out.println("[" + rt.getName() + "] – ferrovia, corrida " + currentRent);
                }
            }
        } // talvez fazer polimorfismo na parte de imprimir os dados. e caso a lista estiver vazia, exibir o "Nenhum título"
        if (!hasTitles) {
            System.out.println("Nenhum título.");
        }
    }
}
