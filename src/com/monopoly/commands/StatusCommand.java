package com.monopoly.commands;

import com.monopoly.GameEngine;
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
        if (currentPlayer.getTitles().isEmpty()) { // ver com o professor se essa solução atende à melhora da verificação dos títulos ou se seria mais interessante criar uma interface ou classe abstrata só para os títulos
            System.out.println("Nenhum título.");
        } else {
            for (Tile title : currentPlayer.getTitles()) {
                System.out.println(title.getTitleInfo(engine));
            }
        }
    }
}
