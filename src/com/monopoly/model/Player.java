package com.monopoly.model;

import com.monopoly.board.Tile;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private PawnColor pawnColor;
    private int money;
    private int position;
    private List<Tile> titles; // Lista dos títulos (propriedades e ferrovias) do jogador

    public Player(String name, PawnColor pawnColor) {
        this.name = name;
        this.pawnColor = pawnColor;
        this.money = 1500;
        // Inicia no "Go"
        this.position = 39;
        this.titles = new ArrayList<>();
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
}
