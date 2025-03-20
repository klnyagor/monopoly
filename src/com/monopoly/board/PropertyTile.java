package com.monopoly.board;

import com.monopoly.GameEngine;
import com.monopoly.model.Player;
import java.util.List;
import java.util.Scanner;

public class PropertyTile extends Tile {
    private int price;
    private String group;
    private Player owner;
    
    private int[] rentLevels;
    private int hotelRent;
    private int housePrice;
    private int numHouses;
    
    public PropertyTile(int position, String name, int price, int[] rentLevels, int hotelRent, String group, int housePrice) {
        super(position, name);
        this.price = price;
        this.rentLevels = rentLevels;
        this.hotelRent = hotelRent;
        this.group = group;
        this.housePrice = housePrice;
        this.owner = null;
        this.numHouses = 0;
    }
    
    public PropertyTile(int position, String name, int price, int baseRent, String group) {
        this(position, name, price, new int[]{baseRent, baseRent*5, baseRent*15, baseRent*45, baseRent*80}, baseRent*125, group, 200);
    }
    
    public int getNumHouses() {
        return numHouses;
    }
    
    public void setNumHouses(int numHouses) {
        this.numHouses = numHouses;
    }
    
    public int getHousePrice() {
        return housePrice;
    }
    
    public String getGroup() {
        return group;
    }
    
    @Override
public void landOn(Player player, GameEngine engine) {
    System.out.println("Você caiu em " + (position + 1) + " - " + name + ".");
    if (owner == null) {
        if (player.getMoney() >= price) {
            System.out.println("A propriedade " + name + " está disponível por $" + price + ".");
            System.out.println(player.getName() + ", você possui $" + player.getMoney() + ".");
            System.out.print("Você deseja comprar " + name + " (Sim/Não)? ");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.startsWith("s")) {
                player.deductMoney(price);
                owner = player;
                player.addTitle(this);
                System.out.println("Você comprou " + name + " por $" + price + ".");
            } else {
                System.out.println("Você optou por não comprar " + name + ".");
            }
        } else {
            System.out.println("Você não tem dinheiro suficiente para comprar " + name + ".");
        }
    } else if (!owner.equals(player)) {
        int finalRent = calculateRent(engine);
        System.out.println(name + " pertence a " + owner.getName() + ". Aluguel: $" + finalRent + ".");
        int payment = Math.min(finalRent, player.getMoney());
        player.deductMoney(finalRent);
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

private int calculateRent(GameEngine engine) {
    int baseRent = 0;
    if (numHouses == 0) {
        baseRent = rentLevels[0];
        if (engine.hasMonopoly(owner, group)) {
            baseRent *= 2;
            System.out.println("Como o dono possui o monopólio, o aluguel é dobrado.");
        }
    } else if (numHouses > 0 && numHouses < 5) {
        baseRent = rentLevels[numHouses];
    } else if (numHouses == 5) {
        baseRent = hotelRent;
    }
    return baseRent;
}

    
    @Override
    public String getTitleInfo(GameEngine engine) {
        String info = "[" + name + "] - propriedade " + group + ", aluguel base " + rentLevels[0];
        if (numHouses < 5) {
            info += ", " + numHouses + " casa(s)";
        } else {
            info += ", hotel";
        }
        return info;
    }
    
    @Override
    public TitleType getTitleType() {
        return TitleType.PROPERTY;
    }
    
    @Override
    public Player getOwner() {
        return owner;
    }

    public boolean isEligibleToBuild(GameEngine engine) {
    // Se já houver hotel, não pode construir
    if (this.numHouses >= 5) {
        return false;
    }
    // Só pode construir se o jogador tem o monopólio
    if (!engine.hasMonopoly(this.getOwner(), this.group)) {
        return false;
    }
    List<PropertyTile> groupProps = engine.getPropertiesOfGroup(this.group, this.getOwner());
    int min = Integer.MAX_VALUE;
    for (PropertyTile p : groupProps) {
        if (p.getNumHouses() < min) {
            min = p.getNumHouses();
        }
    }
    return (this.numHouses == min);
}

    private int getMinHousesInGroup(GameEngine engine) {
        int min = Integer.MAX_VALUE;
        for (Tile tile : engine.getBoard().getTiles()) {
            if (tile.getTitleType() == TitleType.PROPERTY && tile.getGroup().equalsIgnoreCase(this.group)) {
                int houses = ((PropertyTile) tile).getNumHouses();
                if (houses < min) {
                    min = houses;
                }
            }
        }
        return min;
    }

}
