package com.monopoly;

import com.monopoly.board.Board;
import com.monopoly.board.Tile;
import com.monopoly.board.TitleType;
import com.monopoly.cards.ChanceCard;
import com.monopoly.cards.CommunityChestCard;
import com.monopoly.commands.CommandFactory;
import com.monopoly.commands.ICommand;
import com.monopoly.initialization.GameInitializer;
import com.monopoly.model.Player;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class GameEngine {
    private List<Player> players;
    private Board board;
    private int currentPlayerIndex;
    private boolean gameOver;
    
    // Decks para Community Chest e Chance
    private Queue<CommunityChestCard> communityChestDeck;
    private Queue<ChanceCard> chanceDeck;
    
    public GameEngine() {
        GameInitializer initializer = new GameInitializer();
        this.players = initializer.initializePlayers();
        this.board = new Board();
        this.currentPlayerIndex = 0;
        this.gameOver = false;
        initializeDecks();
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
        for (Tile tile : board.getTiles()) {
            if (tile.getTitleType() == TitleType.RAILROAD && owner.equals(tile.getOwner())) {
                count++;
            }
        }
        return count;
    }
    
    public int countUtilitiesOwnedBy(Player owner) {
        int count = 0;
        for (Tile tile : board.getTiles()) {
            if (tile.getTitleType() == TitleType.UTILITY && owner.equals(tile.getOwner())) {
                count++;
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
            if (currentPlayer.isInPrison()) {
                System.out.println(currentPlayer.getName() + " está na prisão.");
                System.out.println("Comandos disponíveis: [pagar][carta][jogar][status][sair]");
            } else {
                System.out.println("Comandos disponíveis: [jogar][status][sair]");
            }
            System.out.print("Entre com um comando: ");
            String input = scanner.nextLine().trim();
            try {
                ICommand command = CommandFactory.getCommand(input);
                command.execute(this);
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

    private void initializeDecks() {
        // Deck do Community Chest
        LinkedList<CommunityChestCard> ccCards = new LinkedList<>();
        ccCards.add(new CommunityChestCard(6, "Saia livre da prisão, sem pagar", 
                "Esta carta pode ser mantida até o uso ou venda."));
        ccCards.add(new CommunityChestCard(7, "Vá para a prisão", 
                "Vá diretamente para a prisão - Não passe pelo ponto de partida - Não receba $200."));
        Collections.shuffle(ccCards);
        communityChestDeck = new LinkedList<>(ccCards);
        
        // Deck de Chance
        LinkedList<ChanceCard> chanceCards = new LinkedList<>();
        chanceCards.add(new ChanceCard(8, "Vá diretamente para a Prisão", 
                "Não passe pelo ponto de partida, não receba $200."));
        Collections.shuffle(chanceCards);
        chanceDeck = new LinkedList<>(chanceCards);
    }
    
    public CommunityChestCard drawCommunityChestCard() {
        CommunityChestCard card = communityChestDeck.poll();
        communityChestDeck.offer(card);
        return card;
    }
    
    public ChanceCard drawChanceCard() {
        ChanceCard card = chanceDeck.poll();
        chanceDeck.offer(card);
        return card;
    }
    
    public int getJailPosition() {
        return 9;
    }
}
