package com.monopoly;

import com.monopoly.board.Board;
import com.monopoly.board.PropertyTile;
import com.monopoly.board.Tile;
import com.monopoly.board.TitleType;
import com.monopoly.cards.ChanceCard;
import com.monopoly.cards.ChanceCards.GoToJailChanceCard;
import com.monopoly.cards.ChanceCards.PovertyTaxChanceCard;
import com.monopoly.cards.CommunityChestCard;
import com.monopoly.cards.CommunityChestCards.GetOuOfJailCommunityCard;
import com.monopoly.cards.CommunityChestCards.GotoJailCommunityCard;
import com.monopoly.initialization.GameInitializer;
import com.monopoly.model.Player;
import com.monopoly.turn.TurnState;
import com.monopoly.turn.TurnStateFactory;
import java.util.ArrayList;
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

    private int availableHouses = 32;
    private int availableHotels = 12;
    
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
            TurnState turnState = TurnStateFactory.getTurnState(currentPlayer); //verifica o estado do turno de acordo com o jogador
            turnState.handleTurn(this, currentPlayer, scanner);
            if (!gameOver) {
                nextTurn();
            }
        }
        scanner.close();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void initializeDecks() {
        // Deck do Community Chest
        LinkedList<CommunityChestCard> ccCards = new LinkedList<>();

        ccCards.add(new GetOuOfJailCommunityCard());
        ccCards.add(new GotoJailCommunityCard());
        Collections.shuffle(ccCards);
        communityChestDeck = new LinkedList<>(ccCards);
        
        // Deck de Chance
        LinkedList<ChanceCard> chanceCards = new LinkedList<>();
        chanceCards.add(new GoToJailChanceCard());
        chanceCards.add(new PovertyTaxChanceCard());
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

    public int getAvailableHouses() {
    return availableHouses;
    }

    public int getAvailableHotels() {
        return availableHotels;
    }

    public void decrementAvailableHouses(int n) {
        availableHouses -= n;
    }

    public void decrementAvailableHotels(int n) {
        availableHotels -= n;
    }

    public void incrementAvailableHouses(int n) {
        availableHouses += n;
    }

    public boolean hasMonopoly(Player owner, String group) {
        for (Tile tile : board.getTiles()) {
            if (tile.getTitleType() == TitleType.PROPERTY) {
                if (tile.getGroup().equalsIgnoreCase(group)) {
                    if (!owner.equals(tile.getOwner())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public List<PropertyTile> getBuildableProperties(Player player) {
        List<PropertyTile> buildable = new ArrayList<>();
        for (Tile tile : board.getTiles()) {
            if (tile.getTitleType() == TitleType.PROPERTY && player.equals(tile.getOwner())) {
                PropertyTile pt = (PropertyTile) tile;
                if (pt.isEligibleToBuild(this)) { // método implementado em PropertyTile
                    buildable.add(pt);
                }
            }
        }
        return buildable;
    }
    
    public List<PropertyTile> getPropertiesOfGroup(String group, Player owner) {
    List<PropertyTile> list = new ArrayList<>();
    for (Tile tile : board.getTiles()) {
        if (tile.getTitleType() == TitleType.PROPERTY) {
            PropertyTile pt = (PropertyTile) tile;
            if (pt.getGroup().equalsIgnoreCase(group) && owner.equals(pt.getOwner())) {
                list.add(pt);
            }
        }
    }
    return list;
    }
}
