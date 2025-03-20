package com.monopoly.board;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;
import java.util.Scanner;

public class PropertyTile extends Tile {
    private int price;
    private int rent;
    private String group;
    private Player owner;
    
    public PropertyTile(int position, String name, int price, int rent, String group) {
        super(position, name);
        this.price = price;
        this.rent = rent;
        this.group = group;
        this.owner = null;
    }
    
    @Override
    public void landOn(Player player, GameEngine engine) {
        System.out.println("Você caiu em " + (position + 1) + " - " + name + ".");
        if (owner == null) {
            if (player.getMoney() >= price) {
                System.out.println("A título da propriedade " + name + " está disponível por $" + price + ".");
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
                System.out.println("Você não tem dinheiro suficiente para comprar " + name + ".");
            }
        } else if (!owner.equals(player)) {
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
            System.out.println("Você é o dono desta propriedade.");
        }
    }
    
    @Override
    public String getTitleInfo(GameEngine engine) {
        return "[" + name + "] – propriedade " + group + ", aluguel " + rent;
    }
    
    @Override
    public TitleType getTitleType() {
        return TitleType.PROPERTY;
    }
    
    @Override
    public Player getOwner() {
        return owner;
    }
    
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
