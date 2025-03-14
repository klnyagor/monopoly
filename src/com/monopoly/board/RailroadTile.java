package com.monopoly.board;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;
import java.util.Scanner;

public class RailroadTile extends Tile {
    private int price;
    private Player owner;

    public RailroadTile(int position, String name, int price) {
        super(position, name);
        this.price = price;
        this.owner = null;
    }
    
    public Player getOwner() {
        return owner;
    }
    
    private int calculateRent(GameEngine engine) {
        int count = engine.countRailroadsOwnedBy(owner);
        switch (count) {
            case 1: return 25;
            case 2: return 50;
            case 3: return 75;
            case 4: return 100;
            default: return 25;
        }
    }
    
    public int getCurrentRent(GameEngine engine) {
        return calculateRent(engine);
    }

    @Override
    public void landOn(Player player, GameEngine engine) {
        System.out.println("Você caiu em " + (position + 1) + " - " + name + ".");
        if (owner == null) {
            if (player.getMoney() >= price) {
                System.out.println("A título da ferrovia " + name + " está disponível por $" + price + ".");
                System.out.println(player.getName() + ", você possui $" + player.getMoney() + ".");
                System.out.print("Você deseja comprar " + name + " (Sim/Não)? ");
                Scanner scanner = new Scanner(System.in);
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.startsWith("s")) {
                    player.deductMoney(price);
                    owner = player;
                    player.addTitle(this);
                    System.out.println("O jogador " + player.getName() + " comprou " + name + " por $" + price + ".");
                } else {
                    System.out.println("Você optou por não comprar " + name + ".");
                }
            } else {
                System.out.println("O jogador " + player.getName() + " não tem dinheiro suficiente para comprar " + name + ".");
            }
        } else if (owner != player) {
            int rent = calculateRent(engine);
            System.out.println(name + " já pertence a " + owner.getName() + ". Aluguel: $" + rent + ".");
            int payment = Math.min(rent, player.getMoney());
            player.deductMoney(rent);
            owner.addMoney(payment);
            System.out.println(player.getName() + " pagou $" + payment + " de aluguel para " + owner.getName() + ".");
            if (player.getMoney() < 0) {
                System.out.println("O jogador " + player.getName() + " entrou em falência!");
                engine.setBankrupt(player);
            }
        } else {
            System.out.println("Você é o dono desta ferrovia.");
        }
    }
    
    @Override
    public String getTitleInfo(GameEngine engine) {
        int currentRent = getCurrentRent(engine);
        return "[" + name + "] – ferrovia, corrida " + currentRent;
    }
}
