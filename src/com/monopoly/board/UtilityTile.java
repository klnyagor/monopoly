package com.monopoly.board;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;
import com.monopoly.util.Dice;
import java.util.Scanner;

public class UtilityTile extends Tile {
    private int price;
    private Player owner;
    
    public UtilityTile(int position, String name, int price) {
        super(position, name);
        this.price = price;
        this.owner = null;
    }
    
    @Override
    public void landOn(Player player, GameEngine engine) {
        System.out.println("Você caiu em " + (position + 1) + " - " + name + " (Serviço Público).");
        if (owner == null) {
            if (player.getMoney() >= price) {
                System.out.println("O " + name + " está disponível por $" + price + ".");
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
        } else if (!owner.equals(player)) {
            // Se já possui dono, o aluguel é calculado com base na rolagem dos dados
            int[] diceResult = Dice.rollDice();
            int diceSum = diceResult[0] + diceResult[1];
            int factor = (engine.countUtilitiesOwnedBy(owner) >= 2) ? 10 : 4;
            int rent = diceSum * factor;
            System.out.println(name + " já pertence a " + owner.getName() + ".");
            System.out.println("Você rolou " + diceResult[0] + " e " + diceResult[1] + " (Total: " + diceSum + ").");
            System.out.println("Aluguel devido: " + diceSum + " x " + factor + " = $" + rent + ".");
            int payment = Math.min(rent, player.getMoney());
            player.deductMoney(rent);
            owner.addMoney(payment);
            System.out.println(player.getName() + " pagou $" + payment + " de aluguel para " + owner.getName() + ".");
            if (player.getMoney() < 0) {
                System.out.println("O jogador " + player.getName() + " entrou em falência!");
                engine.setBankrupt(player);
            }
        } else {
            System.out.println("Você é o dono deste " + name + ".");
        }
    }
    
    @Override
    public String getTitleInfo(GameEngine engine) {
        return "[" + name + "] - serviço público";
    }
    
    @Override
    public TitleType getTitleType() {
        return TitleType.UTILITY;
    }
    
    @Override
    public Player getOwner() {
        return owner;
    }
    
    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
