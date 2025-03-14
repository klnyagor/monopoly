package com.monopoly;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import com.monopoly.model.Player;
import com.monopoly.initialization.GameInitializer;
import com.monopoly.board.Board;
import com.monopoly.commands.CommandFactory;
import com.monopoly.commands.ICommand;

public class GameEngine {
    private List<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private boolean gameOver;

    public GameEngine() {
        GameInitializer initializer = new GameInitializer();
        this.players = initializer.initializePlayers();
        this.board = new Board();
        this.currentPlayerIndex = 0;
        this.gameOver = false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        if (!players.isEmpty()) {
            return players.get(currentPlayerIndex);
        }
        return null;
    }

    public void setBankrupt(Player player) {
        System.out.println("O jogador " + player.getName() + " foi removido do jogo.");
        players.remove(player);
        if (players.size() == 1) {
            System.out.println("O jogo terminou. O vencedor é " + players.get(0).getName() + ".");
            gameOver = true;
        } else if (players.isEmpty()) {
            System.out.println("Todos os jogadores foram à falência. Jogo encerrado.");
            gameOver = true;
        } else {
            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            }
        }
    }

    public int countRailroadsOwnedBy(Player owner) {
        int count = 0;
        for (var tile : board.getTiles()) {
            if (tile instanceof com.monopoly.board.RailroadTile) {
                com.monopoly.board.RailroadTile rr = (com.monopoly.board.RailroadTile) tile;
                if (owner.equals(rr.getOwner())) {
                    count++;
                }
            }
        }
        return count;
    }

    public void nextTurn() {
        if (!gameOver) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    public void startGame() {
    Scanner scanner = new Scanner(System.in);
    while (!gameOver) {
        Player currentPlayer = getCurrentPlayer();
        boolean turnComplete = false;
        while (!turnComplete && !gameOver) {
            System.out.println("\nA jogada de " + currentPlayer.getName() + " começou.");
            System.out.println("Comandos disponíveis: [jogar][status][sair]");
            System.out.print("Entre com um comando: ");
            String input = scanner.nextLine().trim();
            try {
                ICommand command = CommandFactory.getCommand(input);
                command.execute(this);
                // Se o comando for jogar ou sair ele vai passar a vezz
                if (input.equalsIgnoreCase("jogar") || input.equalsIgnoreCase("sair")) {
                    turnComplete = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        if (!gameOver) {
            nextTurn();
        }
    }
    scanner.close();
}
}
