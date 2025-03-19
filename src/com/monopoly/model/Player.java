package com.monopoly.model;

import com.monopoly.board.Tile;
import java.util.ArrayList;
import java.util.List;

import com.monopoly.cards.Card;

public class Player {
    private String name;
    private PawnColor pawnColor;
    private int money;
    private int position;
    private List<Tile> titles; // Lista dos títulos (propriedades e ferrovias) do jogador
    private List<Card> getOutOfJailCards;

    // Novos atributos para a prisão
    private boolean inPrison;
    private int prisonTurnCount; // Quantos turnos foram usados tentando sair

    public Player(String name, PawnColor pawnColor) {
        this.name = name;
        this.pawnColor = pawnColor;
        this.money = 1500;
        // Inicia no "Go"
        this.position = 39;
        this.titles = new ArrayList<>();
        this.getOutOfJailCards = new ArrayList<>();
        this.inPrison = false;
        this.prisonTurnCount = 0;
    }

    public String getName() {
        return name;
    }

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void deductMoney(int amount) {
        this.money -= amount;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    // Métodos que gerencia os títulos do jogador
    public void addTitle(Tile tile) {
        titles.add(tile);
    }
    
    public List<Tile> getTitles() {
        return titles;
    }

    public void addGetOutOfJailCard(Card card) {
        getOutOfJailCards.add(card);
    }
    
    public List<Card> getGetOutOfJailCards() {
        return getOutOfJailCards;
    }
    
    // Métodos para gerenciar a prisão
    public boolean isInPrison() {
        return inPrison;
    }
    
    public void setInPrison(boolean inPrison) {
        this.inPrison = inPrison;
        if (!inPrison) {
            prisonTurnCount = 0;
        }
    }
    
    public int getPrisonTurnCount() {
        return prisonTurnCount;
    }
    
    public void incrementPrisonTurnCount() {
        prisonTurnCount++;
    }

    public void resetPrisonTurnCount() {
    this.prisonTurnCount = 0;
}
}
